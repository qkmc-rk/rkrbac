package xyz.ruankun.rkrbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.rkrbac.mapper.PermissionMapper;
import xyz.ruankun.rkrbac.mapper.RoleMapper;
import xyz.ruankun.rkrbac.mapper.RolePermissionMapper;
import xyz.ruankun.rkrbac.model.Permission;
import xyz.ruankun.rkrbac.model.RolePermission;
import xyz.ruankun.rkrbac.model.RolePermissionExample;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IRolePermissionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RolePermissionSerivceImpl implements IRolePermissionService {

    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public ServerResponse isertRolePermission(RolePermission rolePermission) {
        if (rolePermissionMapper.insert(rolePermission) > 0){
            return ServerResponse.success();
        } else {
            return ServerResponse.error();
        }
    }

    @Override
    public ServerResponse listRolePermission(Integer roleId) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        List<Permission> permissions = new ArrayList<>();
        rolePermissions.forEach(item -> {
            permissions.add(permissionMapper.selectByPrimaryKey(item.getPermissionId()));
        });
        if (permissions.size() > 0){
            Map<String, Object> rs = new HashMap<>();
            rs.put("role", roleMapper.selectByPrimaryKey(roleId));
            rs.put("permission", permissions);
            return ServerResponse.success(rs);
        } else {
            return ServerResponse.error("查询失败,或为空");
        }
    }

    @Override
    public ServerResponse deleteRolePermission(Integer roleId, Integer permissionId) {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRoleIdEqualTo(roleId).andPermissionIdEqualTo(permissionId);
        if (rolePermissionMapper.deleteByExample(rolePermissionExample) > 0){
          return ServerResponse.success();
        } else {
            return ServerResponse.error();
        }
    }
}
