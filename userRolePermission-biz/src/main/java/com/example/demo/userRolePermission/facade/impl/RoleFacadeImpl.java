package com.example.demo.userRolePermission.facade.impl;

import com.example.demo.userRolePermission.dao.TRole;
import com.example.demo.userRolePermission.domain.req.QueryRoleReq;
import com.example.demo.userRolePermission.domain.req.RoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.RoleRsp;
import com.example.demo.userRolePermission.facade.RoleFacade;
import com.example.demo.userRolePermission.service.RolePermissionService;
import com.example.demo.userRolePermission.service.RoleService;
import com.example.demo.userRolePermission.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleFacadeImpl implements RoleFacade {

    private static final Logger logger = LoggerFactory.getLogger(RoleFacadeImpl.class);

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public Response<PageModel<RoleRsp>> query(QueryRoleReq req) {
        Response<PageModel<RoleRsp>> response = new Response<>();
        PageModel<TRole> pModel = roleService.query(req);
        PageModel<RoleRsp> rModel = new PageModel<>(pModel.getTotal(), pModel.getPageNumber(), pModel.getPageSize());
        List<TRole> roles = pModel.getPageData();
        List<RoleRsp> rolesRsp = new ArrayList<>();
        for (TRole t : roles) {
            rolesRsp.add(map(t));
        }
        rModel.setPageData(rolesRsp);
        response.setData(rModel);
        return response;
    }

    @Override
    public Response<Integer> create(RoleReq req) {
        Response<Integer> response = new Response<>();
        int count = roleService.create(req);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> modify(RoleReq req) {
        Response<Integer> response = new Response<>();
        int count = roleService.modify(req);
        response.setData(count);
        return response;
    }

    @Override
    @Transactional
    public Response<Integer> deleteRoleAndUserRoleAndRolePermissionByRoleId(Long id) {
        Response<Integer> response = new Response<>();
        int deleteCount = 0;
        deleteCount += roleService.delete(id);
        deleteCount += userRoleService.deleteByRoleId(id);
        deleteCount += rolePermissionService.deleteByRoleId(id);
        response.setData(deleteCount);
        return response;
    }

    private static RoleRsp map(TRole t) {
        RoleRsp r = new RoleRsp();
        BeanUtils.copyProperties(t, r);
        return r;
    }
}
