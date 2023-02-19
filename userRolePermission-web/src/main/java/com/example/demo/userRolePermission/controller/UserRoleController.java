package com.example.demo.userRolePermission.controller;

import com.example.demo.userRolePermission.domain.req.UserRoleReq;
import com.example.demo.userRolePermission.domain.req.query.QueryUserRoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.UserRoleRsp;

import java.util.List;

public interface UserRoleController {
    Response<PageModel<UserRoleRsp>> query(QueryUserRoleReq req);

    Response<UserRoleRsp> getByUserIdAndRoleId(Long userId, Long roleId);

    Response<List<UserRoleRsp>> getAllByUserId(Long userId);

    Response<List<UserRoleRsp>> getAllByRoleId(Long roleId);

    Response<Integer> create(UserRoleReq req);

    Response<Integer> deleteByUserId(Long userId);

    Response<Integer> deleteByRoleId(Long roleId);

    Response<Integer> deleteByUserIdAndRoleId(Long userId, Long roleId);
}
