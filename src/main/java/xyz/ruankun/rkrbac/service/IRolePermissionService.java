package xyz.ruankun.rkrbac.service;

import xyz.ruankun.rkrbac.model.RolePermission;
import xyz.ruankun.rkrbac.server.ServerResponse;

public interface IRolePermissionService {
    ServerResponse isertRolePermission(RolePermission rolePermission);

    ServerResponse listRolePermission(Integer roleId);

    ServerResponse deleteRolePermission(Integer roleId, Integer permissionId);
}
