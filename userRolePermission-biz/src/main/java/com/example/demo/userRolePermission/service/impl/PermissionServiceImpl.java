package com.example.demo.userRolePermission.service.impl;

import com.example.demo.userRolePermission.dao.TPermission;
import com.example.demo.userRolePermission.dao.TPermissionExample;
import com.example.demo.userRolePermission.dao.TPermissionMapper;
import com.example.demo.userRolePermission.domain.req.PermissionReq;
import com.example.demo.userRolePermission.domain.req.QueryPermissionReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.service.PermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Resource
    private TPermissionMapper permissionMapper;

    @Override
    public PageModel<TPermission> query(QueryPermissionReq req) {
        Page<TPermission> page = PageHelper.startPage(req.getPageNumber(), req.getPageSize());

        TPermissionExample example = new TPermissionExample();
        example.createCriteria();

        List<TPermission> permissions = permissionMapper.selectByExample(example);

        PageModel<TPermission> pageModel = new PageModel<>((int) page.getTotal(), page.getPageNum(), page.getPageSize());
        pageModel.setPageData(permissions);
        return pageModel;
    }

    @Override
    public TPermission get(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new NullPointerException("permission parameter id must not be null");
        }
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TPermission> getAllByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new NullPointerException("permission parameter ids must not be null or empty");
        }
        TPermissionExample example = new TPermissionExample();
        example.createCriteria()
                .andIdIn(ids);
        return permissionMapper.selectByExample(example);
    }

    @Override
    public TPermission getByPermissionCode(String permissionCode) {
        if (StringUtils.isEmpty(permissionCode)) {
            throw new NullPointerException("permission parameter permissionCode must not be null");
        }
        TPermissionExample example = new TPermissionExample();
        example.createCriteria()
                .andPermissionCodeEqualTo(permissionCode);
        List<TPermission> permissions = permissionMapper
                .selectByExample(example);
        return permissions.size() > 0 ? permissions.get(0) : null;
    }

    @Override
    public Map<Long, String> getPermissionIdPermissionCodeMapByPermissionIds(List<Long> permissionIds) {
        List<TPermission> permissions = getAllByIds(permissionIds);
        Map<Long, String> permissionIdPermissionCodeMap = new HashMap<>();
        for (TPermission t : permissions) {
            permissionIdPermissionCodeMap.put(t.getId(), t.getPermissionCode());
        }
        return permissionIdPermissionCodeMap;
    }

    @Override
    public int create(PermissionReq req) {
        if (req == null) {
            throw new NullPointerException("permissionReq must not be null");
        }
        final String permissionCode = req.getPermissionCode();
        if (getByPermissionCode(permissionCode) != null) {
            throw new RuntimeException("permissionCode already exists");
        }
        final Date date = new Date();
        TPermission permission = new TPermission();
        permission.setPermissionCode(permissionCode);
        permission.setCreatedDate(date);
        permission.setLastUpdatedDate(date);
        permission.setFlag((short) 1);
        return permissionMapper.insertSelective(permission);
    }

    @Override
    public int modify(PermissionReq req) {
        if (req == null) {
            throw new NullPointerException("permissionReq must not be null");
        }
        final TPermission permission = get(req.getId());
        if (permission == null) {
            throw new RuntimeException("record not found");
        }
        final String permissionCode = req.getPermissionCode();
        if (StringUtils.isNotEmpty(permissionCode) && !permissionCode.equalsIgnoreCase(permission.getPermissionCode())) {
            if (getByPermissionCode(permissionCode) != null) {
                throw new RuntimeException("permissionCode already exists");
            }

            permission.setPermissionCode(permissionCode);
            permission.setLastUpdatedDate(new Date());
            return permissionMapper.updateByPrimaryKeySelective(permission);
        }
        logger.info("PermissionServiceImpl modify no changes found");
        return 0;
    }

    @Override
    public int delete(Long id) {
        if (get(id) == null) {
            throw new RuntimeException("record not found");
        }
        return permissionMapper.deleteByPrimaryKey(id);
    }
}
