package xyz.ruankun.rkrbac.service;

import xyz.ruankun.rkrbac.model.UserRole;
import xyz.ruankun.rkrbac.server.ServerResponse;

public interface IUserRoleService {

    ServerResponse insertUserRole(UserRole userRole);

    ServerResponse listUserRole(Integer userId);

    ServerResponse deleteUserRole(Integer userId, Integer roleId);
}
