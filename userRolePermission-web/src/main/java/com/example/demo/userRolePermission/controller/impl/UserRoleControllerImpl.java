package com.example.demo.userRolePermission.controller.impl;

import com.example.demo.userRolePermission.controller.UserRoleController;
import com.example.demo.userRolePermission.domain.req.QueryUserRoleReq;
import com.example.demo.userRolePermission.domain.req.UserRoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.UserRoleRsp;
import com.example.demo.userRolePermission.facade.UserRoleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/userRole")
public class UserRoleControllerImpl implements UserRoleController {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleControllerImpl.class);

    @Resource
    private UserRoleFacade userRoleFacade;

    @Override
    @GetMapping("/query")
    public Response<PageModel<UserRoleRsp>> query(QueryUserRoleReq req) {
        try {
            return userRoleFacade.query(req);
        } catch (Exception e) {
            logger.error("query failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @GetMapping("/getByUserIdAndRoleId")
    public Response<UserRoleRsp> getByUserIdAndRoleId(Long userId, Long roleId) {
        try {
            return userRoleFacade.getByUserIdAndRoleId(userId, roleId);
        } catch (Exception e) {
            logger.error("getByUserIdAndRoleId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @GetMapping("/getAllByUserId")
    public Response<List<UserRoleRsp>> getAllByUserId(Long userId) {
        try {
            return userRoleFacade.getAllByUserId(userId);
        } catch (Exception e) {
            logger.error("getAllByUserId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @GetMapping("/getAllByRoleId")
    public Response<List<UserRoleRsp>> getAllByRoleId(Long roleId) {
        try {
            return userRoleFacade.getAllByRoleId(roleId);
        } catch (Exception e) {
            logger.error("getAllByRoleId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/create")
    public Response<Integer> create(UserRoleReq req) {
        try {
            return userRoleFacade.create(req);
        } catch (Exception e) {
            logger.error("create failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/deleteByUserId")
    public Response<Integer> deleteByUserId(Long userId) {
        try {
            return userRoleFacade.deleteByUserId(userId);
        } catch (Exception e) {
            logger.error("deleteByUserId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/deleteByRoleId")
    public Response<Integer> deleteByRoleId(Long roleId) {
        try {
            return userRoleFacade.deleteByRoleId(roleId);
        } catch (Exception e) {
            logger.error("deleteByRoleId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }

    @Override
    @PostMapping("/deleteByUserIdAndRoleId")
    public Response<Integer> deleteByUserIdAndRoleId(Long userId, Long roleId) {
        try {
            return userRoleFacade.deleteByUserIdAndRoleId(userId, roleId);
        } catch (Exception e) {
            logger.error("deleteByUserIdAndRoleId failed={}", e.getMessage());
            return new Response<>(e.getMessage());
        }
    }
}
