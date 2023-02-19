package com.example.demo.userRolePermission.facade;

import com.example.demo.userRolePermission.domain.req.UserReq;
import com.example.demo.userRolePermission.domain.req.query.QueryUserReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.UserRsp;

public interface UserFacade {
    Response<PageModel<UserRsp>> query(QueryUserReq req);

    Response<Integer> create(UserReq req);

    Response<Integer> modify(UserReq req);

    Response<Integer> deleteUserAndUserRole(Long id);
}
