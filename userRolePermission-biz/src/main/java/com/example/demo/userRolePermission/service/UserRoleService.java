package com.example.demo.userRolePermission.service;

import com.example.demo.userRolePermission.dao.TUserRole;
import com.example.demo.userRolePermission.domain.req.UserRoleReq;
import com.example.demo.userRolePermission.domain.req.query.QueryUserRoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;

import java.util.List;

public interface UserRoleService {
    PageModel<TUserRole> query(QueryUserRoleReq req);

    List<TUserRole> getAllByUserId(Long userId);

    List<TUserRole> getAllByRoleId(Long roleId);

    TUserRole getByUserIdAndRoleId(Long userId, Long roleId);

    int create(UserRoleReq req);

    int deleteByUserId(Long userId);

    int deleteByRoleId(Long roleId);

    int deleteByUserIdAndRoleId(Long userId, Long roleId);
}
