package xyz.ruankun.rkrbac.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.ruankun.rkrbac.model.User;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IUserService;

/**
 * @author: mrruan
 * @date: 2019-02-04 00:55
 * @description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("getUser")
    public ServerResponse getUser() {
        return ServerResponse.success("查询成功");
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
}
