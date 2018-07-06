--用户表
/*
用户id	登陆名（登陆名添加唯一性约束）	登陆密码
*/
create table gy_user(
	id number(8) not null,
	login_name varchar2(20) not null,
	password varchar2(20) default('123456'),
	constraint pk_gy_user_id primary key(id)
);
alter table gy_user add constraint unique_login_name unique(login_name);--给用户名添加唯一性约束
comment on column gy_user.id is '主键';
comment on column gy_user.login_name is '登陆名';
comment on column gy_user.password is '登陆密码';

--用户详情表
/*
用户id	真实姓名	性别	联系电话	Email	备注	用户头像id	用户状态-->用户删除用户（用户冻结就不可以用）
所属部门
创建时间
创建人
修改时间
修改人
*/
create table gy_user_detl(
	id number(8) not null,
	real_name varchar2(20) not null,
	birthday Date,
	gender varchar2(2) not null,
	phone_number varchar2(20),
	email varchar2(30),
	note varchar2(500),
	pic_id number(8),
	status nubmer(1) default(0),
	dept_id number(6),
	
	crt_date Timestamp,
	crtor number(8),
	upd_date Timestamp,
	updor number(8),
	constraint pk_gy_user_detl_id primary key(id)
);
alter table gy_user_detl add constraint fk_id foreign key(id) references gy_user(id);
comment on column gy_user_detl.id is '既是主键又是外键';
comment on column gy_user_detl.real_name is '真实姓名';
comment on column gy_user_detl.birthday is '出生年月';
comment on column gy_user_detl.gender is '性别';
comment on column gy_user_detl.phone_number is '联系电话';
comment on column gy_user_detl.email is '邮箱';
comment on column gy_user_detl.note is '说明，自我介绍';
comment on column gy_user_detl.pic_id is '图片id';
comment on column gy_user_detl.status is '用户状态';
comment on column gy_user_detl.dept_id is '所属部门';

comment on column gy_user_detl.crt_date is '创建时间';
comment on column gy_user_detl.crtor is '创建人';
comment on column gy_user_detl.upd_date is '修改时间';
comment on column gy_user_detl.updor is '修改人';

--岗位表

/*
岗位id	岗位名	岗位说明	岗位状态-->(用户修改、删除岗位)同冻结
创建时间
创建人
修改时间
修改人
*/

create table gy_role(
	id number(8) not null,
	role_name varchar2(20),
	role_desc varchar2(200),
	status number(1) default(0),
	
	crt_date Timestamp,
	crtor number(8),
	upd_date Timestamp,
	updor number(8),
	
	constraint pk_gy_role_id primary key(id)
);
comment on column gy_role.id is '岗位id';
comment on column gy_role.role_name is '岗位名字';
comment on column gy_role.role_desc is '岗位描述';
comment on column gy_role.status is '岗位状态';
comment on column gy_user_detl.crt_date is '创建时间';
comment on column gy_user_detl.crtor is '创建人';
comment on column gy_user_detl.upd_date is '修改时间';
comment on column gy_user_detl.updor is '修改人';
--部门表
/*
部门id	部门名称（添加唯一性约束）	部门描述	上级部门	（子部门可以去掉）	部门状态（公司扩展 缩减 新增 冻结部门）
创建时间
创建人
修改时间
修改人
*/

create table gy_department(
	id number(6) not null,
	dept_name varchar2(20),
	dept_desc varchar2(200),
	parent_dept number(6),
	status number(1) default(0),
	
	crt_date Timestamp,
	crtor number(8),
	upd_date Timestamp,
	updor number(8),
	
	constraint pk_gy_department_id primary key(id)
);
comment on column gy_department.id is '部门id';
comment on column gy_department.dept_name is '部门名字';
comment on column gy_department.dept_desc is '部门描述';
comment on column gy_department.parent_dept is '父部门';
comment on column gy_department.status is '部门状态(如果是新建部门,看是否有人进入该部门工作而判断是否激活)';
comment on column gy_department.crt_date is '创建时间';
comment on column gy_department.crtor is '创建人';
comment on column gy_department.upd_date is '修改时间';
comment on column gy_department.updor is '修改人';
--用户岗位表
/*
用户id
岗位id
设置用户外键引用
设置岗位外键引用
设置联合主键
*/
create table gy_user_role(
	user_id number(8) not null,
	role_id number(8) not null,
	constraint fk_user_id foreign key(user_id)  references gy_user(id),
	constraint fk_role_id foreign key(role_id) references gy_role(id),
	constraint union_pk_user_role primary key(user_id,role_id)
);

--菜单表
/*
菜单id	菜单名authName	菜单路径authPath	菜单图标icon	父菜单parentAuthId	菜单描述authDesc	菜单状态status
创建时间
创建人
修改时间
修改人
*/

--岗位菜单表
/*
岗位id
菜单id
*/

--按钮表
/*
按钮btnId	按钮名字btnName（增加、修改、删除、查询）	按钮描述btnDesc	所属菜单authId（这个自动把用户对某菜单的操作进行了关联）
创建时间
创建人
修改时间
修改人
*/

--图片图标表
/*
图片picId	图片名字picName	图片路径picPath	图片描述picDesc
创建时间
创建人
修改时间
修改人
*/

--唯一性约束
alter table gy_user 
	add constraint unique_username_uq unique(login_name);
--禁用+使用+删除 唯一性约束
alter table gy_user enable constraint unique_username_uq;

alter table gy_user disable constraint unique_username_uq;

alter table gy_user drop constraint unique_username_uq;






