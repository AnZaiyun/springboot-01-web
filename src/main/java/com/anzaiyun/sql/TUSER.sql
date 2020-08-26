create table TUSER
(
  l_id       NUMBER(10)   not null,
  vc_name    VARCHAR2(30) not null,
  l_sex      NUMBER(1)    not null,
  l_tel      NUMBER(11),
  vc_mail    VARCHAR2(30) ,
  vc_address VARCHAR2(300),
  d_jrsj     date,
  l_state    NUMBER(1)  default 0 
);
