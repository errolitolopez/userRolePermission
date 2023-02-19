package com.example.demo.userRolePermission.controller.impl;

import com.example.demo.userRolePermission.controller.RoleController;
import com.example.demo.userRolePermission.domain.req.RoleReq;
import com.example.demo.userRolePermission.domain.req.query.QueryRoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.RoleRsp;
import com.example.demo.userRolePermission.facade.RoleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/role")
public class RoleControllerImpl implements RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleControllerImpl.class);

    @Resource
    private RoleFacade roleFacade;

    @Override
    @GetMapping("/query")
    public Response<PageModel<RoleRsp>> query(QueryRoleReq req) {
        try {
            return roleFacade.query(req);
        } catch (Exception e) {
            logger.error("query failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/create")
    public Response<Integer> create(@RequestBody RoleReq req) {
        try {
            return roleFacade.create(req);
        } catch (Exception e) {
            logger.error("create failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/modify")
    public Response<Integer> modify(@RequestBody RoleReq req) {
        try {
            return roleFacade.modify(req);
        } catch (Exception e) {
            logger.error("modify failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/delete")
    public Response<Integer> delete(Long id) {
        try {
            return roleFacade.deleteRoleAndUserRoleAndRolePermissionByRoleId(id);
        } catch (Exception e) {
            logger.error("delete failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }
}
