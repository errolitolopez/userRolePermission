package com.example.demo.userRolePermission.service;

import com.example.demo.userRolePermission.dao.TPermission;
import com.example.demo.userRolePermission.domain.req.PermissionReq;
import com.example.demo.userRolePermission.domain.req.query.QueryPermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    PageModel<TPermission> query(QueryPermissionReq req);

    TPermission get(Long id);

    List<TPermission> getAllByIds(List<Long> ids);

    TPermission getByPermissionCode(String roleCode);

    Map<Long, String> getPermissionIdPermissionCodeMapByPermissionIds(List<Long> permissionIds);

    int create(PermissionReq req);

    int modify(PermissionReq req);

    int delete(Long id);
}
