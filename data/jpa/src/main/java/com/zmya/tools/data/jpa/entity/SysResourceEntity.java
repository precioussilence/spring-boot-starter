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
@Table(name = "sys_resource")
public class SysResourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "resource_name", nullable = false, length = 100)
    private String resourceName;

    @Size(max = 50)
    @NotNull
    @Column(name = "resource_code", nullable = false, length = 50)
    private String resourceCode;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "resource_type", nullable = false)
    private Integer resourceType;

    @ColumnDefault("0")
    @Column(name = "parent_id")
    private Long parentId;

    @Size(max = 500)
    @Column(name = "path", length = 500)
    private String path;

    @Size(max = 100)
    @Column(name = "icon", length = 100)
    private String icon;

    @ColumnDefault("0")
    @Column(name = "sort")
    private Integer sort;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "visible", nullable = false)
    private Integer visible;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Integer status;

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
    @JoinColumn(name = "resource_id")
    private Set<SysResourceApiEntity> sysResourceApis = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "resource_id")
    private Set<SysRoleResourceEntity> sysRoleResources = new LinkedHashSet<>();

}