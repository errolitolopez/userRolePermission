package com.example.demo.userRolePermission.service.impl;

import com.example.demo.userRolePermission.dao.TRolePermission;
import com.example.demo.userRolePermission.dao.TRolePermissionExample;
import com.example.demo.userRolePermission.dao.TRolePermissionMapper;
import com.example.demo.userRolePermission.domain.req.QueryRolePermissionReq;
import com.example.demo.userRolePermission.domain.req.RolePermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.service.RolePermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private static final Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    @Resource
    private TRolePermissionMapper rolePermissionMapper;

    @Override
    public PageModel<TRolePermission> query(QueryRolePermissionReq req) {
        Page<TRolePermission> page = PageHelper.startPage(req.getPageNumber(), req.getPageSize());

        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria();

        List<TRolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);

        PageModel<TRolePermission> pageModel = new PageModel<>((int) page.getTotal(), page.getPageNum(), page.getPageSize());
        pageModel.setPageData(rolePermissions);
        return pageModel;
    }

    @Override
    public List<TRolePermission> getAllByRoleId(Long roleId) {
        if (roleId == null) {
            throw new NullPointerException("rolePermission parameter roleId must not be null");
        }
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria()
                .andRoleIdEqualTo(roleId);
        return rolePermissionMapper.selectByExample(example);
    }

    @Override
    public List<TRolePermission> getAllByPermissionId(Long permissionId) {
        if (permissionId == null) {
            throw new NullPointerException("rolePermission parameter permissionId must not be null");
        }
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria()
                .andPermissionIdEqualTo(permissionId);
        return rolePermissionMapper.selectByExample(example);
    }

    @Override
    public TRolePermission getByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        if (roleId == null) {
            throw new NullPointerException("rolePermission parameter roleId must not be null");
        }
        if (permissionId == null) {
            throw new NullPointerException("rolePermission parameter permissionId must not be null");
        }
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria()
                .andRoleIdEqualTo(roleId)
                .andPermissionIdEqualTo(permissionId);
        List<TRolePermission> permissions = rolePermissionMapper.selectByExample(example);
        return permissions.size() > 0 ? permissions.get(0) : null;
    }

    @Override
    public int create(RolePermissionReq req) {
        if (req == null) {
            throw new NullPointerException("rolePermissionReq must not be null");
        }
        final Long roleId = req.getRoleId();
        final Long permissionId = req.getPermissionId();
        if (getByRoleIdAndPermissionId(roleId, permissionId) != null) {
            throw new RuntimeException("rolePermission already exists");
        }
        Date date = new Date();
        TRolePermission rolePermission = new TRolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        rolePermission.setCreatedDate(date);
        rolePermission.setLastUpdatedDate(date);
        rolePermission.setFlag((short) 1);
        return rolePermissionMapper.insertSelective(rolePermission);
    }

    @Override
    public int deleteByRoleId(Long roleId) {
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria()
                .andRoleIdEqualTo(roleId);
        return rolePermissionMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPermissionId(Long permissionId) {
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria()
                .andPermissionIdEqualTo(permissionId);
        return rolePermissionMapper.deleteByExample(example);
    }

    @Override
    public int deleteByRoleIdAndPermissionId(Long roleId, Long permissionId) {
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria()
                .andRoleIdEqualTo(roleId)
                .andPermissionIdEqualTo(permissionId);
        return rolePermissionMapper.deleteByExample(example);
    }
}
