package com.example.demo.userRolePermission.service.impl;

import com.example.demo.userRolePermission.dao.TUser;
import com.example.demo.userRolePermission.dao.TUserExample;
import com.example.demo.userRolePermission.dao.TUserMapper;
import com.example.demo.userRolePermission.domain.req.UserReq;
import com.example.demo.userRolePermission.domain.req.query.QueryUserReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private TUserMapper userMapper;

    @Override
    public PageModel<TUser> query(QueryUserReq req) {
        Page<TUser> page = PageHelper.startPage(req.getPageNumber(), req.getPageSize());

        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        if (ObjectUtils.isNotEmpty(req.getId())) {
            criteria.andIdEqualTo(req.getId());
        }
        if (StringUtils.isNotEmpty(req.getUsername())) {
            criteria.andUsernameEqualTo(req.getUsername());
        }
        if (StringUtils.isNotEmpty(req.getEmail())) {
            criteria.andEmailEqualTo(req.getEmail());
        }
        if (ObjectUtils.isNotEmpty(req.getFlag())) {
            criteria.andFlagEqualTo(req.getFlag());
        }

        List<TUser> permissions = userMapper.selectByExample(example);

        PageModel<TUser> pageModel = new PageModel<>((int) page.getTotal(), page.getPageNum(), page.getPageSize());
        pageModel.setPageData(permissions);
        return pageModel;
    }

    @Override
    public TUser get(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new NullPointerException("user parameter id must not be null");
        }
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public TUser getByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            throw new NullPointerException("user parameter username must not be null");
        }
        TUserExample example = new TUserExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        List<TUser> users = userMapper
                .selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public TUser getByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new NullPointerException("user parameter email must not be null");
        }
        TUserExample example = new TUserExample();
        example.createCriteria()
                .andEmailEqualTo(email);
        List<TUser> users = userMapper
                .selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public int create(UserReq req) {
        if (req == null) {
            throw new NullPointerException("userReq must not be null");
        }
        final String username = req.getUsername();
        final String email = req.getEmail();

        if (StringUtils.isEmpty(username)) {
            throw new NullPointerException("user parameter username must not be null");
        } else {
            if (getByUsername(username) != null) {
                throw new RuntimeException("username already exists");
            }
        }
        if (StringUtils.isEmpty(email)) {
            throw new NullPointerException("user parameter email must not be null");
        } else {
            if (getByEmail(email) != null) {
                throw new RuntimeException("email already exists");
            }
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            throw new NullPointerException("user parameter password must not be null");
        }
        final Date date = new Date();
        TUser user = new TUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(req.getPassword());
        user.setCreatedDate(date);
        user.setLastUpdatedDate(date);
        user.setFlag((short) 1);
        return userMapper.insertSelective(user);
    }

    @Override
    public int modify(UserReq req) {
        if (req == null) {
            throw new NullPointerException("userReq must not be null");
        }
        final String username = req.getUsername();
        final String email = req.getEmail();

        boolean isModified = false;

        TUser user = get(req.getId());
        if (user == null) {
            throw new RuntimeException("record not found");
        }

        if (StringUtils.isNotEmpty(username) && !username.equalsIgnoreCase(user.getUsername())) {
            if (getByUsername(username) != null) {
                throw new RuntimeException("username already exists");
            }
            user.setUsername(username);
            isModified = true;
        }

        if (StringUtils.isNotEmpty(email) && !email.equalsIgnoreCase(user.getEmail())) {
            TUser userByEmail = getByEmail(email);
            if (userByEmail != null) {
                throw new RuntimeException("email already exists");
            }
            user.setEmail(email);
            isModified = true;
        }

        if (StringUtils.isNotEmpty(req.getPassword()) && !user.getPassword().equalsIgnoreCase(req.getPassword())) {
            user.setPassword(req.getPassword());
            isModified = true;
        }

        if (ObjectUtils.isNotEmpty(req.getFlag())) {
            user.setFlag(req.getFlag());
            isModified = true;
        }

        if (isModified) {
            user.setLastUpdatedDate(new Date());
            return userMapper.updateByPrimaryKey(user);
        } else {
            logger.info("UserServiceImpl modify no changes found");
            return 0;
        }
    }

    @Override
    public int delete(Long id) {
        if (get(id) == null) {
            throw new RuntimeException("record not found");
        }
        return userMapper.deleteByPrimaryKey(id);
    }
}
