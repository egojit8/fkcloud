create table fk_app
(
  id           varchar(32)  null
  comment 'id',
  name         varchar(40)  not null
    primary key,
  version      varchar(20)  null
  comment '版本',
  tags         varchar(20)  null,
  remark       varchar(200) null
  comment '备注和描述',
  create_time  timestamp    null,
  icon         varchar(40)  null
  comment '图标',
  size         int          null
  comment '包大小kb',
  cmd          varchar(100) null,
  default_port int          null
  comment '默认端口'
)
  comment '应用管理';

create table fk_menu
(
  id          varchar(32) not null
    primary key,
  title       varchar(40) not null,
  url         varchar(64) null,
  parent_id   varchar(32) null,
  sort        int         null
  comment '排序',
  create_time timestamp   null,
  icon        varchar(40) null
)
  comment '菜单';

create table fk_runing_app
(
  id           varchar(64)             not null
    primary key,
  app_id       varchar(64)             null
  comment '应用id',
  title        varchar(40)             null
  comment '运行时标题',
  create_time  timestamp               null,
  inside_port  int                     null
  comment '容器内部端口',
  outside_port int                     null
  comment '容器外部端口',
  remark       varchar(200)            null,
  status       int default '0'         null
  comment '状态，0 已安装，1运行中',
  container_id varchar(64)             null,
  group_id     varchar(36) default '1' null
  comment '分组id',
  image_name   varchar(40)             null
  comment '名称',
  version      varchar(20)             null
)
  comment '运行中的应用';

create table fk_runing_group
(
  id          varchar(36)  not null
    primary key,
  title       varchar(40)  null
  comment '分组标题',
  remark      varchar(200) null
  comment '分组描述',
  create_time timestamp    null
)
  comment '应用分组';

create table fk_user
(
  id          varchar(32) not null
  comment 'id'
    primary key,
  login_name  varchar(40) not null,
  pwd         varchar(40) not null,
  create_time timestamp   null
)
  comment '用户表';


