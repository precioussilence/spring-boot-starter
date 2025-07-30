CREATE TABLE `sys_user`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`        varchar(50)  NOT NULL COMMENT '登录账号（唯一）',
    `password`        varchar(100) NOT NULL COMMENT '加密后的密码（BCrypt/SHA256）',
    `nickname`        varchar(50)           DEFAULT NULL COMMENT '用户昵称',
    `email`           varchar(100)          DEFAULT NULL COMMENT '邮箱',
    `phone`           varchar(20)           DEFAULT NULL COMMENT '手机号',
    `status`          tinyint      NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-正常）',
    `avatar`          varchar(255)          DEFAULT NULL COMMENT '头像URL',
    `last_login_time` datetime              DEFAULT NULL COMMENT '最后登录时间',
    `created_by`      bigint                DEFAULT NULL COMMENT '创建人ID',
    `created_time`    datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`      bigint                DEFAULT NULL COMMENT '更新人ID',
    `updated_time`    datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`) COMMENT '登录账号唯一',
    KEY `idx_status` (`status`) COMMENT '按状态查询用户'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '系统用户表';

CREATE TABLE `sys_role`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_code`    varchar(50)  NOT NULL COMMENT '角色编码（唯一，如：ADMIN、USER）',
    `role_name`    varchar(100) NOT NULL COMMENT '角色名称',
    `description`  varchar(500)          DEFAULT NULL COMMENT '角色描述',
    `status`       tinyint      NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-正常）',
    `created_by`   bigint                DEFAULT NULL COMMENT '创建人ID',
    `created_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`   bigint                DEFAULT NULL COMMENT '更新人ID',
    `updated_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_code` (`role_code`) COMMENT '角色编码唯一',
    KEY `idx_status` (`status`) COMMENT '按状态查询角色'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '角色表';

CREATE TABLE `sys_api`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT COMMENT '接口ID',
    `api_name`     varchar(200) NOT NULL COMMENT '接口名称（如：用户列表查询）',
    `url`          varchar(500) NOT NULL COMMENT '接口路径（支持Ant风格，如：/api/user/**）',
    `method`       varchar(10)  NOT NULL COMMENT '请求方法（GET/POST/PUT/DELETE/PATCH）',
    `description`  varchar(500)          DEFAULT NULL COMMENT '接口描述',
    `status`       tinyint      NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-启用，禁用后不参与权限校验）',
    `created_time` datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（首次扫描时间）',
    `updated_time` datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_url_method` (`url`, `method`) COMMENT 'URL+方法唯一（同一URL不同方法为不同接口）',
    KEY `idx_status` (`status`) COMMENT '按状态筛选接口'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '接口表（动态扫描维护）';

CREATE TABLE `sys_resource`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT COMMENT '资源ID',
    `resource_name` varchar(100) NOT NULL COMMENT '资源名称（如：用户管理菜单、新增用户按钮）',
    `resource_code` varchar(50)  NOT NULL COMMENT '资源编码（唯一，如：MENU_USER、BTN_USER_ADD）',
    `resource_type` tinyint      NOT NULL DEFAULT 1 COMMENT '资源类型：0-菜单、1-页面、2-按钮',
    `parent_id`     bigint                DEFAULT 0 COMMENT '父级资源ID（0表示顶级资源，菜单可包含页面，页面可包含按钮）',
    `path`          varchar(500)          DEFAULT NULL COMMENT '路由路径（仅菜单/页面有效，如：/system/user）',
    `icon`          varchar(100)          DEFAULT NULL COMMENT '图标（仅菜单/按钮有效，如：el-icon-user）',
    `sort`          int                   DEFAULT 0 COMMENT '排序号（越小越靠前）',
    `visible`       tinyint      NOT NULL DEFAULT 1 COMMENT '是否可见（0-隐藏，1-显示，前端渲染用）',
    `status`        tinyint      NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-启用）',
    `created_by`    bigint                DEFAULT NULL COMMENT '创建人ID',
    `created_time`  datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`    bigint                DEFAULT NULL COMMENT '更新人ID',
    `updated_time`  datetime              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_resource_code` (`resource_code`) COMMENT '资源编码唯一',
    KEY `idx_parent_id` (`parent_id`) COMMENT '按父级查询子资源（层级查询用）',
    KEY `idx_resource_type` (`resource_type`) COMMENT '按资源类型筛选',
    KEY `idx_status` (`status`) COMMENT '按状态筛选资源'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '资源表（手动维护：菜单、页面、按钮）';

CREATE TABLE `sys_user_role`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `user_id`      bigint NOT NULL COMMENT '用户ID（关联sys_user.id）',
    `role_id`      bigint NOT NULL COMMENT '角色ID（关联sys_role.id）',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`) COMMENT '用户-角色组合唯一（避免重复关联）',
    KEY `idx_role_id` (`role_id`) COMMENT '按角色查询关联的用户',
    CONSTRAINT `fk_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_user_role_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '用户角色关联表（用户删除时级联删除关联，角色删除时级联删除关联）';

CREATE TABLE `sys_role_resource`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `role_id`      bigint NOT NULL COMMENT '角色ID，关联sys_role表的id',
    `resource_id`  bigint NOT NULL COMMENT '资源ID，关联sys_resource表的id',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_resource` (`role_id`, `resource_id`) COMMENT '确保同一角色不会重复关联同一资源',
    KEY `idx_resource_id` (`resource_id`) COMMENT '通过资源ID查询关联的角色',
    CONSTRAINT `fk_role_resource_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_role_resource_resource` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '角色与资源的关联表（删除角色时级联删除关联，删除资源时级联删除关联）';

CREATE TABLE `sys_resource_api`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `resource_id`  bigint NOT NULL COMMENT '资源ID（关联sys_resource.id，仅PAGE/BUTTON类型）',
    `api_id`       bigint NOT NULL COMMENT '接口ID（关联sys_api.id）',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '关联创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_resource_api` (`resource_id`, `api_id`) COMMENT '资源-接口组合唯一（避免重复关联）',
    KEY `idx_api_id` (`api_id`) COMMENT '按接口查询关联的资源',
    CONSTRAINT `fk_resource_api_resource` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_resource_api_api` FOREIGN KEY (`api_id`) REFERENCES `sys_api` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT '资源接口关联表（页面、按钮关联接口，资源/接口删除时级联删除关联）';
