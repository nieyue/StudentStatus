# 数据库 
#创建数据库
DROP DATABASE IF EXISTS student_status_db;
CREATE DATABASE student_status_db;

#使用数据库 
use student_status_db;
#创建角色表
CREATE TABLE role_tb(
role_id int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
name varchar(255) COMMENT '角色名',
duty varchar(255) COMMENT '角色职责',
update_date datetime COMMENT '更新时间',
PRIMARY KEY (role_id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='角色表';

#创建账户表
CREATE TABLE account_tb(
account_id int(11) NOT NULL AUTO_INCREMENT COMMENT '账户id',
realname varchar(255) COMMENT '姓名',
sid varchar(255) COMMENT '学号',
sex tinyint(4) DEFAULT 0 COMMENT '性别,默认为0未知，为1男性，为2女性',
nation varchar(255) COMMENT '民族',
identity_cards varchar(255) COMMENT '身份证',
phone varchar(255) COMMENT '联系电话',
password varchar(255) COMMENT '密码',
icon varchar(255) COMMENT '头像',
birth_date datetime COMMENT '出生年月日',
reg_cer_name varchar(255) COMMENT '报考证书名称',
reg_major varchar(255) COMMENT '报考专业',
gradation varchar(255) COMMENT '层次',
educational_system varchar(255) COMMENT '学制',
total_cost decimal(13,2) DEFAULT 0 COMMENT '总费用',
already_pay decimal(13,2) DEFAULT 0 COMMENT '已缴费',
arrears decimal(13,2) DEFAULT 0 COMMENT '欠费',
expect_get_cer_date datetime COMMENT '预计取证时间',
create_date datetime COMMENT '创建时间',
login_date datetime COMMENT '登陆时间',
status tinyint DEFAULT 0 COMMENT '状态，默认0正常，1封禁，2异常',
role_id int(11) COMMENT '角色id外键',
PRIMARY KEY (account_id),
INDEX INDEX_REALNAME (realname) USING BTREE,
INDEX INDEX_ROLEID (role_id) USING BTREE,
INDEX INDEX_STATUS (status) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='账户表';

#学生信息
CREATE TABLE student_info_tb(
student_info_id int(11) NOT NULL AUTO_INCREMENT COMMENT '学生信息id',
examination_account varchar(255) COMMENT '报考账号',
examination_pasword varchar(255) COMMENT '报考密码',
recommender varchar(255) COMMENT '推荐人',
director_unit varchar(255) COMMENT '报送主管单位',
unit_charge decimal(13,2) DEFAULT 0 COMMENT '报送单位收费金额',
cer_query_website varchar(255) COMMENT '证书查询认证网址',
link varchar(255) COMMENT '链接',
input_teacher varchar(255) COMMENT '录入老师姓名',
enrollment_year varchar(10) COMMENT '入学年份',
examination_school varchar(255) COMMENT '报考院校',
examination_point varchar(255) COMMENT '考点',
section_type varchar(255) COMMENT '科类',
learn_method varchar(255) COMMENT '学习形式',
recruiter varchar(255) COMMENT '代理（招生员）',
reg_place varchar(255) COMMENT '注册地',
reg_money1 decimal(13,2) DEFAULT 0 COMMENT '第1年注册(金额)',
reg_money2 decimal(13,2) DEFAULT 0 COMMENT '第2年注册(金额)',
reg_money3 decimal(13,2) DEFAULT 0 COMMENT '第3年注册(金额)',
graduation_date datetime COMMENT '毕业时间',
mark longtext COMMENT '备注',
create_date datetime COMMENT '创建时间',
update_date datetime COMMENT '更新时间',
account_id int(11) COMMENT '账户id外键',
PRIMARY KEY (student_info_id),
INDEX INDEX_ACCOUNTID (account_id) USING BTREE
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='学生信息表';
#设置初始角色
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date)
VALUES (1000,"超级管理员","超级管理员",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date)
VALUES (1001,"教师","教师",now());
INSERT IGNORE INTO role_tb (role_id,name,duty,update_date)
VALUES (1002,"学生","学生",now());

#设置初始管理员密码MD5加密123456
INSERT IGNORE INTO account_tb (account_id,realname,phone,password,create_date,login_date,role_id)
VALUES (1000,"超级管理员","1000","11874bb6149dd45428da628c9766b252",now(),now(),1000);