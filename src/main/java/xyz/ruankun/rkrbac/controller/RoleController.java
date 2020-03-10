package xyz.ruankun.rkrbac.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.rkrbac.model.Role;
import xyz.ruankun.rkrbac.model.RolePermission;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IRolePermissionService;
import xyz.ruankun.rkrbac.service.IRoleService;

/**
 * @author: mrruan
 * @description:
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IRolePermissionService rolePermissionService;

    @GetMapping("listRole")
    @RequiresPermissions("role:listRole")
    public ServerResponse listRole() {
        return iRoleService.listRole();
    }

    @PostMapping("insertRole")
    @RequiresPermissions("role:insertRole")
    public ServerResponse insertRole(Role role) {
        return iRoleService.insertRole(role);
    }

    @PutMapping("updateRole")
    @RequiresPermissions("role:updateRole")
    public ServerResponse updateRole(Role role) {
        return iRoleService.updateRole(role);
    }

    @DeleteMapping("deleteRole")
    @RequiresPermissions("role:deleteRole")
    public ServerResponse deleteRole(String ids) {
        return iRoleService.deleteRole(ids);
    }

    // 增加权限
    @PostMapping("insertRolePermission")
    @RequiresPermissions("role:insertRole")
    public ServerResponse isertRolePermission(RolePermission rolePermission){
        return rolePermissionService.isertRolePermission(rolePermission);
    }
    // 删除权限
    @GetMapping("listRolePermission")
    @RequiresPermissions("role:listRole")
    public ServerResponse listRolePermission(Integer roleId){
        return rolePermissionService.listRolePermission(roleId);
    }

    @DeleteMapping("deleteRolePermission")
    @RequiresPermissions("role:deleteRole")
    public ServerResponse deleteUserRole(Integer roleId, Integer permissionId){
        return rolePermissionService.deleteRolePermission(roleId, permissionId);
    }
    // 查询权限
}
