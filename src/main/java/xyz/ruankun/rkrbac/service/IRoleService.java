package xyz.ruankun.rkrbac.service;

import xyz.ruankun.rkrbac.model.Role;
import xyz.ruankun.rkrbac.server.ServerResponse;

/**
 * @author: mrruan
 * @date: 2019-09-16 23:16
 * @description:
 */
public interface IRoleService {

    /**
     * 查询所有角色信息
     *
     * @return
     */
    ServerResponse listRole();

    /**
     * 插入角色
     *
     * @param role
     * @return
     */
    ServerResponse insertRole(Role role);

    /**
     * 更新角色
     *
     * @param role
     * @return
     */
    ServerResponse updateRole(Role role);

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    ServerResponse deleteRole(String ids);
}