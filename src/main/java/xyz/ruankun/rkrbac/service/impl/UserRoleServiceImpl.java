package xyz.ruankun.rkrbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.rkrbac.mapper.RoleMapper;
import xyz.ruankun.rkrbac.mapper.UserMapper;
import xyz.ruankun.rkrbac.mapper.UserRoleMapper;
import xyz.ruankun.rkrbac.model.Role;
import xyz.ruankun.rkrbac.model.User;
import xyz.ruankun.rkrbac.model.UserRole;
import xyz.ruankun.rkrbac.model.UserRoleExample;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IUserRoleService;

import java.util.*;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse insertUserRole(UserRole userRole) {
        userRole.setCreateTime(new Date());
        userRole.setUpdateTime(new Date());
        if (userRoleMapper.insert(userRole) > 0){
            return ServerResponse.success("添加成功");
        } else {
            return ServerResponse.error("添加失败");
        }
    }

    @Override
    public ServerResponse listUserRole(Integer userId) {

        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        List<Role> roles = new ArrayList<>();
        userRoles.forEach(item -> {
            roles.add(roleMapper.selectByPrimaryKey(item.getRoleId()));
        });
        if (roles.size() > 0){
            User user = userMapper.selectByPrimaryKey(userId);
            Map<String, Object> rs = new HashMap<>();
            rs.put("user",user);
            rs.put("role",roles);
            return ServerResponse.success(rs);
        } else {
            return ServerResponse.error("查询失败");
        }
    }


    @Override
    public ServerResponse deleteUserRole(Integer userId, Integer roleId) {
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andUserIdEqualTo(userId).andRoleIdEqualTo(roleId);
        if (userRoleMapper.deleteByExample(userRoleExample) > 0){
            return ServerResponse.success();
        }else {
            return ServerResponse.error();
        }
    }
}
