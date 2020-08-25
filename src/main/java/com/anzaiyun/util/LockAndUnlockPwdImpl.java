package com.anzaiyun.util;

import java.security.Key;

import javax.crypto.Cipher;

import com.anzaiyun.util.ILockAndUnlockPwd;

public class LockAndUnlockPwdImpl implements ILockAndUnlockPwd {
	
	private static String defaultSecretKeyPrivate = "AnZaiyunLoveYoona"; //默认私有密钥
	public static String defaultSecretKeyPublic = "Kill Me Heal Me";//默认开放秘钥
	
    private Cipher encryptCipherPublic = null; //加密器
    private Cipher decryptCipherPublic = null; //解密器
    private Cipher encryptCipherPrivate = null; //加密器
    private Cipher decryptCipherPrivate = null; //解密器
    
    public LockAndUnlockPwdImpl() throws Exception {
        this(defaultSecretKeyPublic);
    }
     
    /**
     * @param secretKey 加密解密使用的密钥
     */
    public LockAndUnlockPwdImpl(String secretKey) {
        Key keyPublic;
        Key keyPrivate ;
        try {
        	keyPublic = getKey(secretKey.getBytes());
            encryptCipherPublic = Cipher.getInstance("DES");
            encryptCipherPublic.init(Cipher.ENCRYPT_MODE, keyPublic);
            decryptCipherPublic = Cipher.getInstance("DES");
            decryptCipherPublic.init(Cipher.DECRYPT_MODE, keyPublic);
            
            keyPrivate = getKey(this.defaultSecretKeyPrivate.getBytes());
            encryptCipherPrivate = Cipher.getInstance("DES");
            encryptCipherPrivate.init(Cipher.ENCRYPT_MODE, keyPrivate);
            decryptCipherPrivate = Cipher.getInstance("DES");
            decryptCipherPrivate.init(Cipher.DECRYPT_MODE, keyPrivate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
	 * @return the defaultSecretKeyPublic
	 */
	public static String getDefaultSecretKeyPublic() {
		return defaultSecretKeyPublic;
	}

	/**
	 * @param defaultSecretKeyPublic the defaultSecretKeyPublic to set
	 */
	public static void setDefaultSecretKeyPublic(String defaultSecretKeyPublic) {
		LockAndUnlockPwdImpl.defaultSecretKeyPublic = defaultSecretKeyPublic;
	}

	/**
     * 加密 (逻辑: 1. 将要加密的字符串转换为字节数组(byte array)<br/>
     *            2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行加密, 返回字节数组<br/>
     *            3. 把加密后的字节数组转换成十六进制的字符串)<br/>
     * @param strIn 要加密的字符串
     * @return 返回加密后的十六进制字符串
     * @throws Exception
     */
    private String encrypt(String strIn, Cipher cipher) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes(), cipher));
    }

    private byte[] encrypt(byte[] arrB, Cipher cipher) throws Exception {
        return cipher.doFinal(arrB);
    }
    
    /**
     * 解密 (逻辑: 1. 把加密后的十六进制字符串转换成字节数组(byte array)<br/>
     *            2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行解密, 返回字节数组(byte array)<br/>
     *            3. 把解密后的字节数组转换成字符串)<br/>
     * @param strIn
     * @return
     * @throws Exception
     */
    private String decrypt(String strIn, Cipher cipher) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn), cipher));
    }

    private byte[] decrypt(byte[] arrB, Cipher cipher) throws Exception {
        return cipher.doFinal(arrB);
    }
    
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    
    private Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }



	@Override
	public String lockPassword(String pwd) throws Exception {
		// TODO Auto-generated method stub
		String publicDes = encrypt(pwd,encryptCipherPublic);  //一次加密，开放秘钥
        
        String privateDes = encrypt(publicDes,encryptCipherPrivate);   //二次加密，私有秘钥
		
		return privateDes;
	}

	@Override
	public String unlockPassword(String pwd) throws Exception{

		String privateDes = decrypt(pwd, decryptCipherPrivate);
		
		String publicDes = decrypt(privateDes, decryptCipherPublic);

		return publicDes;
	}
	
	 /**
     * 用法实例
     */
//    public static void main(String[] args) {
//        try {
//            String test = "https://blog.csdn.net/cainiao0589/article/details/101015485";
//            //注意这里，自定义的加密的KEY要和解密的KEY一致，这就是钥匙，如果你上锁了，却忘了钥匙，那么是解密不了的
//            LockAndUnlockPwdImpl des = new LockAndUnlockPwdImpl("leemenz"); //自定义密钥
//            String lockpwd = des.lockPassword(test);
//            String ulockpwd = des.unlockPassword(lockpwd);
//            System.out.println("加密前的字符：" + test);
//            System.out.println("加密后的字符：" + lockpwd);
//            System.out.println("解密后的字符：" + ulockpwd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
