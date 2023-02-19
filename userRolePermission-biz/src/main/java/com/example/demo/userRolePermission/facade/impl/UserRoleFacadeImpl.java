package com.example.demo.userRolePermission.facade.impl;

import com.example.demo.userRolePermission.dao.TRole;
import com.example.demo.userRolePermission.dao.TUser;
import com.example.demo.userRolePermission.dao.TUserRole;
import com.example.demo.userRolePermission.domain.req.UserRoleReq;
import com.example.demo.userRolePermission.domain.req.query.QueryUserRoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.UserRoleRsp;
import com.example.demo.userRolePermission.facade.UserRoleFacade;
import com.example.demo.userRolePermission.service.RoleService;
import com.example.demo.userRolePermission.service.UserRoleService;
import com.example.demo.userRolePermission.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRoleFacadeImpl implements UserRoleFacade {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public Response<PageModel<UserRoleRsp>> query(QueryUserRoleReq req) {
        Response<PageModel<UserRoleRsp>> response = new Response<>();
        PageModel<TUserRole> pModel = userRoleService.query(req);
        PageModel<UserRoleRsp> rModel = new PageModel<>(pModel.getTotal(), pModel.getPageNumber(), pModel.getPageSize());
        List<TUserRole> userRoles = pModel.getPageData();
        List<Long> roleIds = getRoleIds(userRoles);
        Map<Long, String> roleIdRoleCodeMap = new HashMap<>();
        if (!roleIds.isEmpty()) {
            roleIdRoleCodeMap =  roleService.getRoleIdRoleCodeMapByRoleIds(roleIds);
        }
        List<UserRoleRsp> userRolesRsp = new ArrayList<>();
        for (TUserRole t : userRoles) {
            UserRoleRsp userRoleRsp = map(t);
            userRoleRsp.setRoleCode(roleIdRoleCodeMap.get(userRoleRsp.getRoleId()));
            userRolesRsp.add(userRoleRsp);
        }
        rModel.setPageData(userRolesRsp);
        response.setData(rModel);
        return response;
    }

    @Override
    public Response<UserRoleRsp> getByUserIdAndRoleId(Long userId, Long roleId) {
        Response<UserRoleRsp> response = new Response<>();
        TUserRole userRole = userRoleService.getByUserIdAndRoleId(userId, roleId);
        UserRoleRsp userRoleRsp = map(userRole);
        TRole role = roleService.get(roleId);
        if (role != null) {
            userRoleRsp.setRoleCode(role.getRoleCode());
        }
        response.setData(userRoleRsp);
        return response;
    }

    @Override
    public Response<List<UserRoleRsp>> getAllByUserId(Long userId) {
        Response<List<UserRoleRsp>> response = new Response<>();
        List<TUserRole> userRoles = userRoleService.getAllByUserId(userId);
        List<Long> roleIds = getRoleIds(userRoles);
        Map<Long, String> roleIdRoleCodeMap = new HashMap<>();
        if (!roleIds.isEmpty()) {
            roleIdRoleCodeMap =  roleService.getRoleIdRoleCodeMapByRoleIds(roleIds);
        }
        List<UserRoleRsp> userRolesRsp = new ArrayList<>();
        for (TUserRole t : userRoles) {
            UserRoleRsp userRoleRsp = map(t);
            userRoleRsp.setRoleCode(roleIdRoleCodeMap.get(userRoleRsp.getRoleId()));
            userRolesRsp.add(userRoleRsp);
        }
        response.setData(userRolesRsp);
        return response;
    }

    @Override
    public Response<List<UserRoleRsp>> getAllByRoleId(Long roleId) {
        Response<List<UserRoleRsp>> response = new Response<>();
        List<TUserRole> userRoles = userRoleService.getAllByRoleId(roleId);
        List<Long> roleIds = getRoleIds(userRoles);
        Map<Long, String> roleIdRoleCodeMap = new HashMap<>();
        if (!roleIds.isEmpty()) {
            roleIdRoleCodeMap =  roleService.getRoleIdRoleCodeMapByRoleIds(roleIds);
        }
        List<UserRoleRsp> userRolesRsp = new ArrayList<>();
        for (TUserRole t : userRoles) {
            UserRoleRsp userRoleRsp = map(t);
            userRoleRsp.setRoleCode(roleIdRoleCodeMap.get(userRoleRsp.getRoleId()));
            userRolesRsp.add(userRoleRsp);
        }
        response.setData(userRolesRsp);
        return response;
    }

    @Override
    public Response<Integer> create(UserRoleReq req) {
        Response<Integer> response = new Response<>();
        TUser user = userService.get(req.getUserId());
        if (user == null) {
            throw new RuntimeException("user not found");
        }
        TRole role = roleService.get(req.getRoleId());
        if (role == null) {
            throw new RuntimeException("role not found");
        }
        int count = userRoleService.create(req);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> deleteByUserId(Long userId) {
        Response<Integer> response = new Response<>();
        int count = userRoleService.deleteByUserId(userId);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> deleteByRoleId(Long roleId) {
        Response<Integer> response = new Response<>();
        int count = userRoleService.deleteByRoleId(roleId);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> deleteByUserIdAndRoleId(Long userId, Long roleId) {
        Response<Integer> response = new Response<>();
        int count = userRoleService.deleteByUserIdAndRoleId(userId, roleId);
        response.setData(count);
        return response;
    }

    private static UserRoleRsp map(TUserRole t) {
        UserRoleRsp r = new UserRoleRsp();
        BeanUtils.copyProperties(t, r);
        return r;
    }

    private static List<Long> getRoleIds(List<TUserRole> userRoles) {
        List<Long> roleIds = new ArrayList<>();
        for (TUserRole t : userRoles) {
            roleIds.add(t.getRoleId());
        }
        return roleIds;
    }
}
