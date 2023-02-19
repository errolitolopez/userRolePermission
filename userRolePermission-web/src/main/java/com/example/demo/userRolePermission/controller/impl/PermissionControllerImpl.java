package com.example.demo.userRolePermission.controller.impl;

import com.example.demo.userRolePermission.controller.PermissionController;
import com.example.demo.userRolePermission.domain.req.PermissionReq;
import com.example.demo.userRolePermission.domain.req.query.QueryPermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.PermissionRsp;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.facade.PermissionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/permission")
public class PermissionControllerImpl implements PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionControllerImpl.class);

    @Resource
    private PermissionFacade permissionFacade;

    @Override
    @GetMapping("/query")
    public Response<PageModel<PermissionRsp>> query(QueryPermissionReq req) {
        try {
            return permissionFacade.query(req);
        } catch (Exception e) {
            logger.error("query failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/create")
    public Response<Integer> create(@RequestBody PermissionReq req) {
        try {
            return permissionFacade.create(req);
        } catch (Exception e) {
            logger.error("create failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/modify")
    public Response<Integer> modify(@RequestBody PermissionReq req) {
        try {
            return permissionFacade.modify(req);
        } catch (Exception e) {
            logger.error("modify failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/delete")
    public Response<Integer> delete(Long id) {
        try {
            return permissionFacade.deletePermissionAndRolePermissionByPermissionId(id);
        } catch (Exception e) {
            logger.error("delete failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }
}
