package com.example.demo.userRolePermission.controller.impl;

import com.example.demo.userRolePermission.controller.RolePermissionController;
import com.example.demo.userRolePermission.domain.req.RolePermissionReq;
import com.example.demo.userRolePermission.domain.req.query.QueryRolePermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.RolePermissionRsp;
import com.example.demo.userRolePermission.facade.RolePermissionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/rolePermission")
public class RolePermissionControllerImpl implements RolePermissionController {

    private static final Logger logger = LoggerFactory.getLogger(RolePermissionControllerImpl.class);

    @Resource
    private RolePermissionFacade rolePermissionFacade;

    @Override
    @GetMapping("/query")
    public Response<PageModel<RolePermissionRsp>> query(QueryRolePermissionReq req) {
        try {
            return rolePermissionFacade.query(req);
        } catch (Exception e) {
            logger.error("query failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @GetMapping("/getByRoleIdAndPermissionId")
    public Response<RolePermissionRsp> getByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        try {
            return rolePermissionFacade.getByRoleIdAndPermissionId(roleId, permissionId);
        } catch (Exception e) {
            logger.error("getByRoleIdAndPermissionId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @GetMapping("/getAllByRoleId")
    public Response<List<RolePermissionRsp>> getAllByRoleId(Long roleId) {
        try {
            return rolePermissionFacade.getAllByRoleId(roleId);
        } catch (Exception e) {
            logger.error("getAllByRoleId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @GetMapping("/getAllByPermissionId")
    public Response<List<RolePermissionRsp>> getAllByPermissionId(Long permissionId) {
        try {
            return rolePermissionFacade.getAllByPermissionId(permissionId);
        } catch (Exception e) {
            logger.error("getAllByPermissionId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/create")
    public Response<Integer> create(RolePermissionReq req) {
        try {
            return rolePermissionFacade.create(req);
        } catch (Exception e) {
            logger.error("create failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/deleteByRoleId")
    public Response<Integer> deleteByRoleId(Long roleId) {
        try {
            return rolePermissionFacade.deleteByRoleId(roleId);
        } catch (Exception e) {
            logger.error("deleteByRoleId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/deleteByPermissionId")
    public Response<Integer> deleteByPermissionId(Long permissionId) {
        try {
            return rolePermissionFacade.deleteByPermissionId(permissionId);
        } catch (Exception e) {
            logger.error("deleteByPermissionId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/deleteByRoleIdAndPermissionId")
    public Response<Integer> deleteByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        try {
            return rolePermissionFacade.deleteByRoleIdAndPermissionId(roleId, permissionId);
        } catch (Exception e) {
            logger.error("deleteByRoleIdAndPermissionId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }
}
