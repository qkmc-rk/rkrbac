package xyz.ruankun.rkrbac.service;

import xyz.ruankun.rkrbac.model.User;
import xyz.ruankun.rkrbac.server.ServerResponse;

/**
 * @author: mrruan
 * @description:
 */
public interface IUserService {

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    ServerResponse login(String username, String password);

    /**
     * 根据账号查询用户信息
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    ServerResponse listUser(User user);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    ServerResponse insertUser(User user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    ServerResponse updateUser(User user);

    /**
     * 删除用户
     *
     * @param ids
     * @return
     */
    ServerResponse deleteUser(String ids);
}
