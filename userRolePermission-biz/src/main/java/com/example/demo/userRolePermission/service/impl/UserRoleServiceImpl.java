package com.example.demo.userRolePermission.service.impl;

import com.example.demo.userRolePermission.dao.TUserRole;
import com.example.demo.userRolePermission.dao.TUserRoleExample;
import com.example.demo.userRolePermission.dao.TUserRoleMapper;
import com.example.demo.userRolePermission.domain.req.UserRoleReq;
import com.example.demo.userRolePermission.domain.req.query.QueryUserRoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.service.UserRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Resource
    private TUserRoleMapper userRoleMapper;

    @Override
    public PageModel<TUserRole> query(QueryUserRoleReq req) {
        Page<TUserRole> page = PageHelper.startPage(req.getPageNumber(), req.getPageSize());

        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria();

        List<TUserRole> userRoles = userRoleMapper.selectByExample(example);

        PageModel<TUserRole> pageModel = new PageModel<>((int) page.getTotal(), page.getPageNum(), page.getPageSize());
        pageModel.setPageData(userRoles);
        return pageModel;
    }

    @Override
    public List<TUserRole> getAllByUserId(Long userId) {
        if (userId == null) {
            throw new NullPointerException("userRole parameter userId must not be null");
        }
        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return userRoleMapper.selectByExample(example);
    }

    @Override
    public List<TUserRole> getAllByRoleId(Long roleId) {
        if (roleId == null) {
            throw new NullPointerException("userRole parameter roleId must not be null");
        }
        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria()
                .andRoleIdEqualTo(roleId);
        return userRoleMapper.selectByExample(example);
    }

    @Override
    public TUserRole getByUserIdAndRoleId(Long userId, Long roleId) {
        if (userId == null) {
            throw new NullPointerException("userRole parameter userId must not be null");
        }
        if (roleId == null) {
            throw new NullPointerException("userRole parameter roleId must not be null");
        }
        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andRoleIdEqualTo(roleId);
        List<TUserRole> userRoles = userRoleMapper.selectByExample(example);
        return userRoles.size() > 0 ? userRoles.get(0) : null;
    }

    @Override
    public int create(UserRoleReq req) {
        if (req == null) {
            throw new NullPointerException("userRoleReq must not be null");
        }
        if (getByUserIdAndRoleId(req.getUserId(), req.getRoleId()) != null) {
            throw new RuntimeException("userRole already exists");
        }
        TUserRole userRole = new TUserRole();
        userRole.setUserId(req.getUserId());
        userRole.setRoleId(req.getRoleId());
        Date date = new Date();
        userRole.setCreatedDate(date);
        userRole.setLastUpdatedDate(date);
        userRole.setFlag((short) 1);
        return userRoleMapper.insertSelective(userRole);
    }

    @Override
    public int deleteByUserId(Long userId) {
        if (userId == null) {
            throw new NullPointerException("userRole parameter userId must not be null");
        }
        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return userRoleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        if (roleId == null) {
            throw new NullPointerException("userRole parameter roleId must not be null");
        }
        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria()
                .andRoleIdEqualTo(roleId);
        return userRoleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByUserIdAndRoleId(Long userId, Long roleId) {
        if (userId == null) {
            throw new NullPointerException("userRole parameter userId must not be null");
        }
        if (roleId == null) {
            throw new NullPointerException("userRole parameter roleId must not be null");
        }
        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andRoleIdEqualTo(roleId);
        return userRoleMapper.deleteByExample(example);
    }
}
