package xyz.ruankun.rkrbac.service;

import xyz.ruankun.rkrbac.model.Permission;
import xyz.ruankun.rkrbac.server.ServerResponse;

import java.util.List;

/**
 * @author: mrruan
 * @description:
 */
public interface IPermissionService {

    /**
     * 查询所有的权限URL集合
     *
     * @param userId
     * @return
     */
    List<String> listPermissionUrl(Integer userId);

    /**
     * 查询所有权限信息
     *
     * @return
     */
    ServerResponse listPermission(Permission permission);

    /**
     * 插入权限
     *
     * @param permission
     * @return
     */
    ServerResponse insertPermission(Permission permission);

    /**
     * 更新权限
     *
     * @param permission
     * @return
     */
    ServerResponse updatePermission(Permission permission);

    /**
     * 删除权限
     *
     * @param ids
     * @return
     */
    ServerResponse deletePermission(String ids);
}
