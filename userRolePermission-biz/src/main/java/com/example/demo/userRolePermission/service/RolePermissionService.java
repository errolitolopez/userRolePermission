package com.example.demo.userRolePermission.service;

import com.example.demo.userRolePermission.dao.TRolePermission;
import com.example.demo.userRolePermission.domain.req.RolePermissionReq;
import com.example.demo.userRolePermission.domain.req.query.QueryRolePermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;

import java.util.List;

public interface RolePermissionService {
    PageModel<TRolePermission> query(QueryRolePermissionReq req);

    List<TRolePermission> getAllByRoleId(Long roleId);

    List<TRolePermission> getAllByPermissionId(Long permissionId);

    TRolePermission getByRoleIdAndPermissionId(Long roleId, Long permissionId);

    int create(RolePermissionReq req);

    int deleteByRoleId(Long roleId);

    int deleteByPermissionId(Long permissionId);

    int deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
