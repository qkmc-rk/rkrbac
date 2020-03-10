package xyz.ruankun.rkrbac.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ruankun.rkrbac.server.ServerResponse;

/**
 * @author: mrruan
 * @description:
 */
@RestController
public class LoginController {

    @PostMapping("login")
    public ServerResponse login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return ServerResponse.error("账号密码错误");
        }
        return ServerResponse.success("登陆成功", subject.getSession().getId());
    }

    @GetMapping("logout")
    public ServerResponse logout() {
        SecurityUtils.getSubject().logout();
        return ServerResponse.success("退出成功");
    }
}
