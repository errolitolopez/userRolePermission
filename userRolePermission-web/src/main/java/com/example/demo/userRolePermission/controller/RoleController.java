package com.example.demo.userRolePermission.controller;

import com.example.demo.userRolePermission.domain.req.QueryRoleReq;
import com.example.demo.userRolePermission.domain.req.RoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.RoleRsp;

public interface RoleController {
    Response<PageModel<RoleRsp>> query(QueryRoleReq req);

    Response<Integer> create(RoleReq req);

    Response<Integer> modify(RoleReq req);

    Response<Integer> delete(Long id);
}
