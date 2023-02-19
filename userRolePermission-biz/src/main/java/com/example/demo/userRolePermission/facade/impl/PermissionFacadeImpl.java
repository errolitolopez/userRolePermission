package com.example.demo.userRolePermission.facade.impl;

import com.example.demo.userRolePermission.dao.TPermission;
import com.example.demo.userRolePermission.domain.req.PermissionReq;
import com.example.demo.userRolePermission.domain.req.query.QueryPermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.PermissionRsp;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.facade.PermissionFacade;
import com.example.demo.userRolePermission.service.PermissionService;
import com.example.demo.userRolePermission.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionFacadeImpl implements PermissionFacade {

    private static final Logger logger = LoggerFactory.getLogger(PermissionFacadeImpl.class);

    @Resource
    private PermissionService permissionService;

    @Resource
    private RolePermissionService rolePermissionService;

    @Override
    public Response<PageModel<PermissionRsp>> query(QueryPermissionReq req) {
        Response<PageModel<PermissionRsp>> response = new Response<>();
        PageModel<TPermission> pModel = permissionService.query(req);
        PageModel<PermissionRsp> rModel = new PageModel<>(pModel.getTotal(), pModel.getPageNumber(), pModel.getPageSize());
        List<TPermission> permissions = pModel.getPageData();
        List<PermissionRsp> permissionsRsp = new ArrayList<>();
        for (TPermission t : permissions) {
            permissionsRsp.add(map(t));
        }
        rModel.setPageData(permissionsRsp);
        response.setData(rModel);
        return response;
    }

    @Override
    public Response<Integer> create(PermissionReq req) {
        Response<Integer> response = new Response<>();
        int count = permissionService.create(req);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> modify(PermissionReq req) {
        Response<Integer> response = new Response<>();
        int count = permissionService.modify(req);
        response.setData(count);
        return response;
    }

    @Override
    @Transactional
    public Response<Integer> deletePermissionAndRolePermissionByPermissionId(Long id) {
        Response<Integer> response = new Response<>();
        int deleteCount = 0;
        deleteCount += permissionService.delete(id);
        deleteCount += rolePermissionService.deleteByPermissionId(id);
        response.setData(deleteCount);
        return response;
    }

    private static PermissionRsp map(TPermission t) {
        PermissionRsp r = new PermissionRsp();
        BeanUtils.copyProperties(t, r);
        return r;
    }
}
