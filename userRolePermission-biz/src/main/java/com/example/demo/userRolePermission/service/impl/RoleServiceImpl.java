package com.example.demo.userRolePermission.service.impl;

import com.example.demo.userRolePermission.dao.TRole;
import com.example.demo.userRolePermission.dao.TRoleExample;
import com.example.demo.userRolePermission.dao.TRoleMapper;
import com.example.demo.userRolePermission.domain.req.RoleReq;
import com.example.demo.userRolePermission.domain.req.query.QueryRoleReq;
import com.example.demo.userRolePermission.domain.rsp.PageModel;
import com.example.demo.userRolePermission.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private TRoleMapper roleMapper;

    @Override
    public PageModel<TRole> query(QueryRoleReq req) {
        Page<TRole> page = PageHelper.startPage(req.getPageNumber(), req.getPageSize());

        TRoleExample example = new TRoleExample();
        example.createCriteria();

        List<TRole> roles = roleMapper.selectByExample(example);

        PageModel<TRole> pageModel = new PageModel<>((int) page.getTotal(), page.getPageNum(), page.getPageSize());
        pageModel.setPageData(roles);
        return pageModel;
    }

    @Override
    public List<TRole> getAllByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new NullPointerException("role parameter ids must not be null or empty");
        }
        TRoleExample example = new TRoleExample();
        example.createCriteria()
                .andIdIn(ids);
        return roleMapper.selectByExample(example);
    }

    @Override
    public Map<Long, String> getRoleIdRoleCodeMapByRoleIds(List<Long> ids) {
        List<TRole> roles = getAllByIds(ids);
        Map<Long, String> roleIdRoleCodeMap = new HashMap<>();
        for (TRole t : roles) {
            roleIdRoleCodeMap.put(t.getId(), t.getRoleCode());
        }
        return roleIdRoleCodeMap;
    }

    @Override
    public TRole get(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new NullPointerException("role parameter id must not be null");
        }
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public TRole getByRoleCode(String roleCode) {
        if (StringUtils.isEmpty(roleCode)) {
            throw new NullPointerException("role parameter roleCode must not be null");
        }
        TRoleExample example = new TRoleExample();
        example.createCriteria()
                .andRoleCodeEqualTo(roleCode);
        List<TRole> roles = roleMapper
                .selectByExample(example);
        return roles.size() > 0 ? roles.get(0) : null;
    }

    @Override
    public int create(RoleReq req) {
        if (req == null) {
            throw new NullPointerException("roleReq must not be null");
        }
        final String roleCode = req.getRoleCode();
        if (getByRoleCode(roleCode) != null) {
            throw new RuntimeException("roleCode already exists");
        }
        final Date date = new Date();
        TRole role = new TRole();
        role.setRoleCode(roleCode);
        role.setCreatedDate(date);
        role.setLastUpdatedDate(date);
        role.setFlag((short) 1);
        return roleMapper.insertSelective(role);
    }

    @Override
    public int modify(RoleReq req) {
        if (req == null) {
            throw new NullPointerException("roleReq must not be null");
        }
        final TRole role = get(req.getId());
        if (role == null) {
            throw new RuntimeException("record not found");
        }
        final String roleCode = req.getRoleCode();
        if (StringUtils.isNotEmpty(roleCode) && !roleCode.equalsIgnoreCase(role.getRoleCode())) {
            if (getByRoleCode(roleCode) != null) {
                throw new RuntimeException("role parameter roleCode already exists");
            }
            role.setRoleCode(roleCode);
            role.setLastUpdatedDate(new Date());
            return roleMapper.insertSelective(role);
        }
        logger.info("RoleServiceImpl modify, no changes found");
        return 0;

    }

    @Override
    public int delete(Long id) {
        if (get(id) == null) {
            throw new RuntimeException("record not found");
        }
        return roleMapper.deleteByPrimaryKey(id);
    }
}
