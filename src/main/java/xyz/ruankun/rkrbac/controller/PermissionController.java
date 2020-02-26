package xyz.ruankun.rkrbac.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.rkrbac.model.Permission;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IPermissionService;

/**
 * @author: mrruan
 * @date: 2019-02-04 00:55
 * @description:
 */
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;

    @GetMapping("listPermission")
    @RequiresPermissions("permission:listPermission")
    public ServerResponse listPermission() {
        return iPermissionService.listPermission();
    }

    @PostMapping("insertPermission")
    @RequiresPermissions("permission:insertPermission")
    public ServerResponse insertPermission(Permission permission) {
        return iPermissionService.insertPermission(permission);
    }

    @PutMapping("updatePermission")
    @RequiresPermissions("permission:updatePermission")
    public ServerResponse updatePermission(Permission permission) {
        return iPermissionService.updatePermission(permission);
    }

    @DeleteMapping("deletePermission")
    @RequiresPermissions("permission:deletePermission")
    public ServerResponse deletePermission(String ids) {
        return iPermissionService.deletePermission(ids);
    }
}
