--岗位初始化
insert into gy_role(id,role_name,role_desc) values(null,'程序员','写代码');
insert into gy_role(id,role_name,role_desc) values(null,'经理','管理项目');
insert into gy_role(id,role_name,role_desc) values(null,'测试员','做测试');
--创建岗位表
create table gy_role (
	id bigint (20),
	role_name varchar (765),
	role_desc varchar (765),
	crtDate datetime,
	updDate datetime 
); 
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('7','程序员','写代码',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('8','经理','管理项目',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('9','测试员','做测试',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('10','程序员2','写代码，不想做测试啊',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('11','程序员2','写代码，不想做测试啊',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('12','时光守护则','记录时间..',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('13','时间大哥','时间大哥',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('14','redis','C语言==》反照java','2018-01-30 23:42:01',NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('15','modeldriven','修改成模型对应简化了',NULL,NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('17','添加uI','必须有那个跳转Action','2018-01-30 23:48:14',NULL);
insert into gy_role (id, role_name, role_desc, crtDate, updDate) values('20','新增1222','保存 一下啊  页面和新增一样啊',NULL,NULL);


select * from gy_role;

--部门初始化
insert into gy_department(id,dept_name,dept_desc,crtDate)
values(null,'OA产品部','研发OA产品',now());
insert into gy_department(id,dept_name,dept_desc,crtDate,parentId)
values(null,'研发部','研发代码',now(),0);
INSERT INTO gy_department(id,dept_name,dept_desc,crtDate,parentId)
VALUES(NULL,'售服部','产品售后服务维修等',NOW(),0);
INSERT INTO gy_department(id,dept_name,dept_desc,crtDate,parentId)
VALUES(NULL,'市场部','市场开发',NOW(),0);

update  gy_department set parentId=null where id=3;

--创建部门表
CREATE TABLE gy_department (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dept_name varchar(255) DEFAULT NULL,
  dept_desc varchar(255) DEFAULT NULL,
  crtDate datetime DEFAULT NULL,
  updDate datetime DEFAULT NULL,
  parentId bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKD1B60BF28519BFEsss (parentId),
  CONSTRAINT FKD1B60BF28519BFE3333 FOREIGN KEY (parentId) REFERENCES gy_department (id)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


--用户初始化
insert into gy_user(id,login_name,password,real_name,gender,email,crt_date) 
values(null,'三哥','123456','张三','男','1234qq@.com',now());
insert into gy_user(id,login_name,password,real_name,gender,email,crt_date) 
values(null,'四哥','123456','李四','男','1234qq@.com',now());
insert into gy_user(id,login_name,password,real_name,gender,email,crt_date) 
values(null,'五哥','123456','王五','男','1234qq@.com',now());
insert into gy_user(id,login_name,password,real_name,gender,email,crt_date) 
values(null,'满香','123456','谭满香','女','1234qq@.com',now());

--用户岗位表
CREATE TABLE gy_user_role (
  userId bigint(20) NOT NULL,
  roleId bigint(20) NOT NULL,
  PRIMARY KEY (roleId,userId),
  KEY FK340EF55DF9C2672E (roleId),
  KEY FK340EF55DFF17BC98 (userId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--部门
select * from gy_department d where d.parentId=14

--权限表创建
create table gy_privilege(
	id bigint(20) auto_increment comment '主键id',
	privUrl varchar(100) comment '权限对应功能的url路径',
	privName varchar(100) comment '权限对应显示名称',
	privIcon varchar(100) comment '菜单栏对应显示图标',
	crtDate datetime DEFAULT NULL comment '创建时间',
  	updDate datetime DEFAULT NULL comment '最近更新时间',
  	parentId bigint(20) DEFAULT NULL comment '父亲权限',
  	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--岗位权限表
create table gy_role_privilege(
	roleId bigint(20) NOT NULL comment '岗位id',
	privId bigint(20) not null comment '权限id',
	PRIMARY KEY (roleId,privId),
	constraint fk_roles_id foreign key(roleId) references gy_role(id),
	constraint fk_privs_id foreign key(privId) references gy_privilege(id)
)ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


--版块表 : 一对多 主题表 
--一对一关系： 对应 主题表
create table gy_forum(
	id bigint(20) not null comment '版块id',
	name varchar(100) comment '版块名称',
	description varchar(500) comment '版块描述',
	position int(10) comment '版块位置',
	crtDate datetime DEFAULT NULL comment '创建时间',
  	updDate datetime DEFAULT NULL comment '最近更新时间',
  	topicCount bigint(20) comment '主题数',
  	articleCount bigint(20) comment '文章数',
  	lastTopic bigint(20) comment '最近一次更新主题',
	PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--查询前一条记录
select * from gy_forum where position < 5 order by position;
select count(*) from gy_forum where position < 5 order by position;
--查出上面5条排序数据的最后一条
select * from gy_forum where position = (
	select position from gy_forum where position < 5 order by position desc limit 1
);
--高级啊: 查询上一个
select * from gy_forum where position < 5 order by position desc limit 1;
--高级啊： 查询下一个
select * from gy_forum where position > 4 order by position asc limit 1;


select * from gy_forum where position = (
	select max(position) from gy_forum where position < 5 
);

--多对一： 主题表多对一版块表
---主题表: 一对多 ： 回复 ： 增删改查： 发帖 + 删除 + 修改主贴（限制5分钟内） + 浏览主题
/*
	版块列表： 版块查
	显示单个模块： 主题查
	显示单个主题： 查单条主题 + 回复表查

	发新帖： 主题新增
	回帖： 回复表新增

	主题：	
		设置类型 ： 
		移动到其他版块 ： 归属关系 
		删除： 主题删除
		修改： 主题修改
	  回复：
		删除： 回复删除
		修改： 回复修改
	版块管理：
		增删改查
		上下移动
	
**/
create table gy_topic(
	id bigint(20) not null comment '主题id',
	title varchar(5000) comment '标题',
	content text comment '文本内容',
	faceIcon varchar(500) comment '表情名称',
	postTime datetime,
	author bigint(20) not null,
	ipAddr varchar(20),
	forumId bigint(20),
	topicType int(10) comment '主题类型',
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--回复表 : 多对一： 主题 : 增删改查 ： 回帖 + 删除 + 修改 + 查询显示 
create table gy_reply(
	id bigint(20) not null comment '回复id',
	content text comment '文本内容',
	faceIcon varchar(500) comment '表情名称',
	postTime datetime,
	author bigint(20) not null,
	ipAddr varchar(20),
	topicId bigint(20),
	primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


--主题回复关联表
/*
一对多的关系不需要中间表啊
create table gy_topic_reply(
	topicId bigint(20),
	replyId bigint(20)
)ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
**/
---权限初始化数据
--\. 脚本文件












