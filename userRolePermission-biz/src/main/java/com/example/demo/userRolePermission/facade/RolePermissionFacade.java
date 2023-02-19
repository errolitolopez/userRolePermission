package com.example.demo.userRolePermission.facade;

import com.example.demo.userRolePermission.domain.req.RolePermissionReq;
import com.example.demo.userRolePermission.domain.req.query.QueryRolePermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.RolePermissionRsp;

import java.util.List;

public interface RolePermissionFacade {
    Response<PageModel<RolePermissionRsp>> query(QueryRolePermissionReq req);

    Response<RolePermissionRsp> getByRoleIdAndPermissionId(Long roleId, Long permissionId);

    Response<List<RolePermissionRsp>> getAllByRoleId(Long roleId);

    Response<List<RolePermissionRsp>> getAllByPermissionId(Long permissionId);

    Response<Integer> create(RolePermissionReq req);

    Response<Integer> deleteByRoleId(Long roleId);

    Response<Integer> deleteByPermissionId(Long permissionId);

    Response<Integer> deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
