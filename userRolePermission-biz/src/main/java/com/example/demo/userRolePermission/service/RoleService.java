package com.example.demo.userRolePermission.service;

import com.example.demo.userRolePermission.dao.TRole;
import com.example.demo.userRolePermission.domain.req.QueryRoleReq;
import com.example.demo.userRolePermission.domain.req.RoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;

import java.util.List;
import java.util.Map;

public interface RoleService {
    PageModel<TRole> query(QueryRoleReq req);

    List<TRole> getAllByIds(List<Long> ids);

    Map<Long, String> getRoleIdRoleCodeMapByRoleIds(List<Long> ids);

    TRole get(Long id);

    TRole getByRoleCode(String roleCode);

    int create(RoleReq req);

    int modify(RoleReq req);

    int delete(Long id);
}
