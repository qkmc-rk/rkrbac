package xyz.ruankun.rkrbac.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.ruankun.rkrbac.mapper.RoleMapper;
import xyz.ruankun.rkrbac.mapper.UserMapper;
import xyz.ruankun.rkrbac.mapper.UserRoleMapper;
import xyz.ruankun.rkrbac.model.*;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IUserRoleService;
import xyz.ruankun.rkrbac.service.IUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: mrruan
 * @description:
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public ServerResponse login(String username, String password) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.size() > 0) {
            User user = userList.get(0);
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.success("登陆成功", user);
        }
        return ServerResponse.error("账号密码错误");
    }

    @Override
    public User getUserByUsername(String username) {
        log.info("嘿嘿:{}", username);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.get(0);
    }

    @Override
    @Transactional
    public ServerResponse listUser(User user) {
        UserExample userExample = new UserExample();
        if (StringUtils.isNotBlank(user.getUsername())) {
            userExample.createCriteria().andUsernameLike("%" + user.getUsername() + "%");
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            userExample.createCriteria().andEmailLike("%" + user.getEmail() + "%");
        }
        List<User> userList = userMapper.selectByExample(userExample);
        userList.forEach(item -> {
            List<String> roles = new ArrayList<>();
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUserIdEqualTo(item.getId());
            List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
            userRoles.forEach(ur -> {
                roles.add(ur.getRemarks());
            });
            item.setRoles(roles);
        });
        userList.forEach(System.out::println);
        return ServerResponse.success("查询成功", userList);
    }
    @Override
    public ServerResponse insertUser(User user) {
        int count = userMapper.insertSelective(user);
        if (count > 0) {
            return ServerResponse.success("添加成功");
        }
        return ServerResponse.error("插入失败");
    }

    @Override
    public ServerResponse updateUser(User user) {
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count > 0) {
            return ServerResponse.success("更新成功");
        }
        return ServerResponse.error("更新失败");
    }

    @Override
    public ServerResponse deleteUser(String ids) {
        int count = userMapper.deleteByPrimaryKey(Integer.parseInt(ids));
        if (count > 0) {
            return ServerResponse.success("删除成功");
        }
        return ServerResponse.error("删除失败");
    }
}
