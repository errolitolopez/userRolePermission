package com.example.demo.userRolePermission.facade.impl;

import com.example.demo.userRolePermission.dao.TUser;
import com.example.demo.userRolePermission.domain.req.QueryUserReq;
import com.example.demo.userRolePermission.domain.req.UserReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.domain.rsp.Response;
import com.example.demo.userRolePermission.domain.rsp.UserRsp;
import com.example.demo.userRolePermission.facade.UserFacade;
import com.example.demo.userRolePermission.service.UserRoleService;
import com.example.demo.userRolePermission.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {

    private static final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public Response<PageModel<UserRsp>> query(QueryUserReq req) {
        Response<PageModel<UserRsp>> response = new Response<>();
        PageModel<TUser> pModel = userService.query(req);
        PageModel<UserRsp> rModel = new PageModel<>(pModel.getTotal(), pModel.getPageNumber(), pModel.getPageSize());
        List<TUser> users = pModel.getPageData();
        List<UserRsp> usersRsp = new ArrayList<>();
        for (TUser t : users) {
            usersRsp.add(map(t));
        }
        rModel.setPageData(usersRsp);
        response.setData(rModel);
        return response;
    }

    @Override
    public Response<Integer> create(UserReq req) {
        Response<Integer> response = new Response<>();
        int count = userService.create(req);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> modify(UserReq req) {
        Response<Integer> response = new Response<>();
        int count = userService.modify(req);
        response.setData(count);
        return response;
    }

    @Override
    public Response<Integer> deleteUserAndUserRole(Long id) {
        Response<Integer> response = new Response<>();
        int count = 0;
        count += userService.delete(id);
        count += userRoleService.deleteByUserId(id);
        response.setData(count);
        return response;
    }

    private static UserRsp map(TUser u) {
        UserRsp r = new UserRsp();
        BeanUtils.copyProperties(u, r);
        return r;
    }
}
