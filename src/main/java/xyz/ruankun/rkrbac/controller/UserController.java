package xyz.ruankun.rkrbac.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.rkrbac.model.User;
import xyz.ruankun.rkrbac.model.UserRole;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IUserRoleService;
import xyz.ruankun.rkrbac.service.IUserService;

/**
 * @author: mrruan
 * @description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping("getUser")
    public ServerResponse getUser() {
        User principal = (User)SecurityUtils.getSubject().getPrincipal();
        return ServerResponse.success(principal);
    }

    @GetMapping("listUser")
    @RequiresPermissions("user:listUser")
    public ServerResponse listUser(User user) {
        return iUserService.listUser(user);
    }

    @PostMapping("insertUser")
    @RequiresPermissions("user:insertUser")
    public ServerResponse insertUser(User user) {
        return iUserService.insertUser(user);
    }

    @PutMapping("updateUser")
    @RequiresPermissions("user:updateUser")
    public ServerResponse updateUser(User user) {
        return iUserService.updateUser(user);
    }

    @RequiresPermissions("user:deleteUser")
    @DeleteMapping("deleteUser")
    public ServerResponse deleteUser(String ids) {
        return iUserService.deleteUser(ids);
    }

    // 给予user权限相当于修改user
    @PostMapping("insertUserRole")
    @RequiresPermissions("user:updateUser")
    public ServerResponse insertUserRole(UserRole userRole){
        return userRoleService.insertUserRole(userRole);
    }

    @GetMapping("listUserRole")
    @RequiresPermissions("user:listUser")
    public ServerResponse listUserRole(Integer userId){
        return userRoleService.listUserRole(userId);
    }

    @DeleteMapping("deleteUserRole")
    @RequiresPermissions("user:deleteUser")
    public ServerResponse deleteUserRole(Integer userId, Integer roleId){
        return userRoleService.deleteUserRole(userId, roleId);
    }
}
