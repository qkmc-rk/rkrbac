package xyz.ruankun.rkrbac.service;

import xyz.ruankun.rkrbac.model.Role;
import xyz.ruankun.rkrbac.server.ServerResponse;

/**
 * @author: mrruan
 * @description:
 */
public interface IRoleService {

    /**
     * 查询所有角色信息
     *
     * @return
     */
    ServerResponse listRole(Role role);

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
