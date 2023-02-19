package com.example.demo.userRolePermission.facade;

import com.example.demo.userRolePermission.domain.req.PermissionReq;
import com.example.demo.userRolePermission.domain.req.query.QueryPermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.PermissionRsp;
import com.example.demo.userRolePermission.domain.rsp.Response;

public interface PermissionFacade {
    Response<PageModel<PermissionRsp>> query(QueryPermissionReq req);

    Response<Integer> create(PermissionReq req);

    Response<Integer> modify(PermissionReq req);

    Response<Integer> deletePermissionAndRolePermissionByPermissionId(Long id);
}
