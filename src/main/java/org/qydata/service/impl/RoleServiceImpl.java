package org.qydata.service.impl;

import org.qydata.entity.Role;
import org.qydata.entity.UserRole;
import org.qydata.mapper.RoleMapper;
import org.qydata.service.RoleService;
import org.qydata.tools.IpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/3.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole() throws Exception {
        return roleMapper.findAllRole();
    }

    @Override
    public List<UserRole> findAllRoleByUsername(Integer userId) throws Exception {
        return roleMapper.findAllRoleByUsername(userId);
    }

    @Override
    public boolean addRoleUser(Integer userId,String [] roleId) throws Exception {
        if (roleId != null && roleId.length>0) {
            Integer[] temp = IpTool.intArray(roleId);
            Map<String, Object> map = new HashMap();
            map.put("userId", userId);
            map.put("roleId", temp);
            roleMapper.deleteUserRoleByUserId(userId);
            return roleMapper.addRoleUser(map);
        }else {
            return roleMapper.deleteUserRoleByUserId(userId );
        }
    }
}
