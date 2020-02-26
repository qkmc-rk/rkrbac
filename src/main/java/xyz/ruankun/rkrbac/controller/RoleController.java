package xyz.ruankun.rkrbac.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.rkrbac.model.Role;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IRoleService;

/**
 * @author: mrruan
 * @date: 2019-02-04 00:55
 * @description:
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

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
}
