package com.example.demo.userRolePermission.controller.impl;

import com.example.demo.userRolePermission.controller.UserController;
import com.example.demo.userRolePermission.domain.req.QueryUserReq;
import com.example.demo.userRolePermission.domain.req.UserReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.UserRsp;
import com.example.demo.userRolePermission.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    @Resource
    private UserFacade userFacade;

    @Override
    @GetMapping("/query")
    public Response<PageModel<UserRsp>> query(QueryUserReq req) {
        try {
            return userFacade.query(req);
        } catch (Exception e) {
            logger.error("query failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/create")
    public Response<Integer> create(@RequestBody UserReq req) {
        try {
            return userFacade.create(req);
        } catch (Exception e) {
            logger.error("create failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/modify")
    public Response<Integer> modify(@RequestBody UserReq req) {
        try {
            return userFacade.modify(req);
        } catch (Exception e) {
            logger.error("modify failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/delete")
    public Response<Integer> delete(Long id) {
        try {
            return userFacade.deleteUserAndUserRole(id);
        } catch (Exception e) {
            logger.error("delete failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }
}
