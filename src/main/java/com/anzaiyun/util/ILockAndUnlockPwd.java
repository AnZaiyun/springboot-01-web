package com.anzaiyun.util;

public interface ILockAndUnlockPwd {
	
	public String lockPassword(String pwd) throws Exception;
	
	public String unlockPassword(String pwd) throws Exception;

}
