package com.zmya.tools.data.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sys_user")
public class SysUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Size(max = 100)
    @NotNull
    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Size(max = 50)
    @Column(name = "nickname", length = 50)
    private String nickname;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Integer status;

    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "last_login_time")
    private Instant lastLoginTime;

    @Column(name = "created_by")
    private Long createdBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "updated_by")
    private Long updatedBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_time")
    private Instant updatedTime;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<SysUserRoleEntity> sysUserRoles = new LinkedHashSet<>();

}