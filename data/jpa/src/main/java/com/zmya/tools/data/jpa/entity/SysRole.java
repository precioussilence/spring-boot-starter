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
@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "role_code", nullable = false, length = 50)
    private String roleCode;

    @Size(max = 100)
    @NotNull
    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_by")
    private Long createdBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_time", insertable = false, updatable = false)
    private Instant createdTime;

    @Column(name = "updated_by")
    private Long updatedBy;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_time", insertable = false, updatable = false)
    private Instant updatedTime;

    @OneToMany(mappedBy = "role")
    private Set<SysRoleResource> sysRoleResources = new LinkedHashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<SysUserRole> sysUserRoles = new LinkedHashSet<>();

}