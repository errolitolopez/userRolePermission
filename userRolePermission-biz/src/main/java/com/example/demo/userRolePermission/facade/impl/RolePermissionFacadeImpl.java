package com.example.demo.userRolePermission.facade.impl;

import com.example.demo.userRolePermission.dao.TPermission;
import com.example.demo.userRolePermission.dao.TRole;
import com.example.demo.userRolePermission.dao.TRolePermission;
import com.example.demo.userRolePermission.domain.req.QueryRolePermissionReq;
import com.example.demo.userRolePermission.domain.req.RolePermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.RolePermissionRsp;
import com.example.demo.userRolePermission.facade.RolePermissionFacade;
import com.example.demo.userRolePermission.service.PermissionService;
import com.example.demo.userRolePermission.service.RolePermissionService;
import com.example.demo.userRolePermission.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RolePermissionFacadeImpl implements RolePermissionFacade {

    @Resource
    private RolePermissionService rolePermissionService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Override
    public Response<PageModel<RolePermissionRsp>> query(QueryRolePermissionReq req) {
        Response<PageModel<RolePermissionRsp>> response = new Response<>();
        PageModel<TRolePermission> pModel = rolePermissionService.query(req);
        PageModel<RolePermissionRsp> rModel = new PageModel<>(pModel.getTotal(), pModel.getPageNumber(), pModel.getPageSize());
        List<TRolePermission> rolePermissions = pModel.getPageData();
        List<Long> roleIds = getRoleIds(rolePermissions);
        List<Long> permissionIds = getPermissionIds(rolePermissions);
        Map<Long, String> roleIdRoleCodeMap = new HashMap<>();
        Map<Long, String> permissionIdPermissionCodeMap = new HashMap<>();
        if (!roleIds.isEmpty()) {
            roleIdRoleCodeMap = roleService.getRoleIdRoleCodeMapByRoleIds(roleIds);
        }
        if (!permissionIds.isEmpty()) {
            permissionIdPermissionCodeMap = permissionService.getPermissionIdPermissionCodeMapByPermissionIds(permissionIds);
        }
        List<RolePermissionRsp> rolePermissionsRsp = new ArrayList<>();
        for (TRolePermission t : rolePermissions) {
            RolePermissionRsp r = map(t);
            r.setRoleCode(roleIdRoleCodeMap.get(r.getRoleId()));
            r.setPermissionCode(permissionIdPermissionCodeMap.get(r.getPermissionId()));
            rolePermissionsRsp.add(r);
        }
        rModel.setPageData(rolePermissionsRsp);
        response.setData(rModel);
        return response;
    }

    @Override
    public Response<RolePermissionRsp> getByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        Response<RolePermissionRsp> response = new Response<>();
        TRolePermission rolePermission = rolePermissionService.getByRoleIdAndPermissionId(roleId, permissionId);
        RolePermissionRsp rolePermissionRsp = map(rolePermission);
        TRole role = roleService.get(roleId);
        if (role != null) {
            rolePermissionRsp.setRoleCode(role.getRoleCode());
        }
        TPermission permission = permissionService.get(permissionId);
        if (permission != null) {
            rolePermissionRsp.setPermissionCode(permission.getPermissionCode());
        }
        response.setData(rolePermissionRsp);
        return response;
    }

    @Override
    public Response<List<RolePermissionRsp>> getAllByRoleId(Long roleId) {
        Response<List<RolePermissionRsp>> response = new Response<>();
        List<TRolePermission> rolePermissions = rolePermissionService.getAllByRoleId(roleId);
        List<RolePermissionRsp> rolePermissionsRsp = new ArrayList<>();
        List<Long> roleIds = getRoleIds(rolePermissions);
        List<Long> permissionIds = getPermissionIds(rolePermissions);
        Map<Long, String> roleIdRoleCodeMap = new HashMap<>();
        Map<Long, String> permissionIdPermissionCodeMap = new HashMap<>();
        if (!roleIds.isEmpty()) {
            roleIdRoleCodeMap = roleService.getRoleIdRoleCodeMapByRoleIds(roleIds);
        }
        if (!permissionIds.isEmpty()) {
            permissionIdPermissionCodeMap = permissionService.getPermissionIdPermissionCodeMapByPermissionIds(permissionIds);
        }
        for (TRolePermission t : rolePermissions) {
            RolePermissionRsp r = map(t);
            r.setRoleCode(roleIdRoleCodeMap.get(r.getRoleId()));
            r.setPermissionCode(permissionIdPermissionCodeMap.get(r.getPermissionId()));
            rolePermissionsRsp.add(r);
        }
        response.setData(rolePermissionsRsp);
        return response;
    }

    @Override
    public Response<List<RolePermissionRsp>> getAllByPermissionId(Long permissionId) {
        Response<List<RolePermissionRsp>> response = new Response<>();
        List<TRolePermission> rolePermissions = rolePermissionService.getAllByPermissionId(permissionId);
        List<RolePermissionRsp> rolePermissionsRsp = new ArrayList<>();
        List<Long> roleIds = getRoleIds(rolePermissions);
        List<Long> permissionIds = getPermissionIds(rolePermissions);
        Map<Long, String> roleIdRoleCodeMap = new HashMap<>();
        Map<Long, String> permissionIdPermissionCodeMap = new HashMap<>();
        if (!roleIds.isEmpty()) {
            roleIdRoleCodeMap = roleService.getRoleIdRoleCodeMapByRoleIds(roleIds);
        }
        if (!permissionIds.isEmpty()) {
            permissionIdPermissionCodeMap = permissionService.getPermissionIdPermissionCodeMapByPermissionIds(permissionIds);
        }
        for (TRolePermission t : rolePermissions) {
            RolePermissionRsp r = map(t);
            r.setRoleCode(roleIdRoleCodeMap.get(r.getRoleId()));
            r.setPermissionCode(permissionIdPermissionCodeMap.get(r.getPermissionId()));
            rolePermissionsRsp.add(r);
        }
        response.setData(rolePermissionsRsp);
        return response;
    }

    @Override
    public Response<Integer> create(RolePermissionReq req) {
        Response<Integer> response = new Response<>();
        TRole role = roleService.get(req.getRoleId());
        if (role == null) {
            throw new RuntimeException("role not found");
        }
        TPermission permission = permissionService.get(req.getPermissionId());
        if (permission == null) {
            throw new RuntimeException("permission not found");
        }
        int count = rolePermissionService.create(req);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> deleteByRoleId(Long roleId) {
        Response<Integer> response = new Response<>();
        int count = rolePermissionService.deleteByRoleId(roleId);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> deleteByPermissionId(Long permissionId) {
        Response<Integer> response = new Response<>();
        int count = rolePermissionService.deleteByPermissionId(permissionId);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> deleteByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        Response<Integer> response = new Response<>();
        int count = rolePermissionService.deleteByRoleIdAndPermissionId(roleId, permissionId);
        response.setData(count);
        return response;
    }

    private RolePermissionRsp map(TRolePermission t) {
        RolePermissionRsp r = new RolePermissionRsp();
        BeanUtils.copyProperties(t, r);
        return r;
    }


    private static List<Long> getRoleIds(List<TRolePermission> list) {
        List<Long> ids = new ArrayList<>();
        for (TRolePermission t : list) {
            ids.add(t.getRoleId());
        }
        return ids;
    }

    private static List<Long> getPermissionIds(List<TRolePermission> list) {
        List<Long> ids = new ArrayList<>();
        for (TRolePermission t : list) {
            ids.add(t.getPermissionId());
        }
        return ids;
    }
}
