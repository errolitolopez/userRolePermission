package com.example.demo.userRolePermission.service;

import com.example.demo.userRolePermission.dao.TUser;
import com.example.demo.userRolePermission.domain.req.QueryUserReq;
import com.example.demo.userRolePermission.domain.req.UserReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;

public interface UserService {
    PageModel<TUser> query(QueryUserReq req);

    TUser get(Long id);

    TUser getByUsername(String username);

    TUser getByEmail(String email);

    int create(UserReq req);

    int modify(UserReq req);

    int delete(Long id);
}
