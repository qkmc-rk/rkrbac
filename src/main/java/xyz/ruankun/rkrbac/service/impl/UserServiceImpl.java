package xyz.ruankun.rkrbac.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.rkrbac.mapper.UserMapper;
import xyz.ruankun.rkrbac.model.User;
import xyz.ruankun.rkrbac.model.UserExample;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IUserService;

import java.util.List;

/**
 * @author: mrruan
 * @date: 2019-09-15 14:15
 * @description:
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.get(0);
    }

    @Override
    public ServerResponse listUser(User user) {
        UserExample userExample = new UserExample();
        if (StringUtils.isNotBlank(user.getUsername())) {
            userExample.createCriteria().andUsernameLike("%" + user.getUsername() + "%");
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            userExample.createCriteria().andEmailLike("%" + user.getEmail() + "%");
        }
        List<User> userList = userMapper.selectByExample(userExample);
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
        int count = userMapper.deleteByPrimaryKey(1);
        if (count > 0) {
            return ServerResponse.success("删除成功");
        }
        return ServerResponse.error("删除失败");
    }
}
