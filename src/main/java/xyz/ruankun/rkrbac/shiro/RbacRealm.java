package xyz.ruankun.rkrbac.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.ruankun.rkrbac.model.User;
import xyz.ruankun.rkrbac.server.Const;
import xyz.ruankun.rkrbac.service.IPermissionService;
import xyz.ruankun.rkrbac.service.IUserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: mrruan
 * @description:
 */
@Slf4j
public class RbacRealm extends AuthorizingRealm {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IPermissionService iPermissionService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("Shiro认证开始...");
        String username = authenticationToken.getPrincipal().toString();
        User user = iUserService.getUserByUsername(username);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("Shiro授权开始...");
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<String> permissionUrlList;
        // 超级管理员则放行全部请求
        if (Const.ADMIN_USERNAME.equals(user.getUsername())) {
            permissionUrlList = iPermissionService.listPermissionUrl(null);
        } else {
            permissionUrlList = iPermissionService.listPermissionUrl(user.getId());
        }
        // 权限去重
        Set<String> permissionUrlSet = new HashSet<>(permissionUrlList);
        log.info("权限列表:{}", permissionUrlSet);
        System.out.println("没去空之前");
        System.out.println(permissionUrlSet);
        permissionUrlSet.remove(null);
        permissionUrlSet.remove("");
        System.out.println("去空后");
        System.out.println(permissionUrlSet);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(permissionUrlSet);
        return authorizationInfo;
    }
}
