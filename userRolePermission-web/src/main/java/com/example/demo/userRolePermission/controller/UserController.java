package com.example.demo.userRolePermission.controller;

import com.example.demo.userRolePermission.domain.req.QueryUserReq;
import com.example.demo.userRolePermission.domain.req.UserReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.UserRsp;

public interface UserController {
    Response<PageModel<UserRsp>> query(QueryUserReq req);

    Response<Integer> create(UserReq userReq);

    Response<Integer> modify(UserReq userReq);

    Response<Integer> delete(Long id);
}
