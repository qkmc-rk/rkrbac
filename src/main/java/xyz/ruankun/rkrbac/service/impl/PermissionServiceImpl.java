package xyz.ruankun.rkrbac.service.impl;

import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.rkrbac.mapper.PermissionMapper;
import xyz.ruankun.rkrbac.mapper.RolePermissionMapper;
import xyz.ruankun.rkrbac.mapper.UserRoleMapper;
import xyz.ruankun.rkrbac.model.*;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IPermissionService;
import xyz.ruankun.rkrbac.util.CliUtils;
import xyz.ruankun.rkrbac.vo.PermissionVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: mrruan
 * @date: 2019-09-16 23:15
 * @description:
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<String> listPermissionUrl(Integer userId) {
        if (userId != null) {
            // 根据userId查询该用户的所有角色
            UserRoleExample userRoleExample = new UserRoleExample();
            userRoleExample.createCriteria().andUserIdEqualTo(userId);
            List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);
            // 根据roleId查询该角色的所有权限
            List<Integer> permissionIdList = new ArrayList<>();
            for (UserRole userRole : userRoleList) {
                RolePermissionExample rolePermissionExample = new RolePermissionExample();
                rolePermissionExample.createCriteria().andRoleIdEqualTo(userRole.getRoleId());
                List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
                for (RolePermission rolePermission : rolePermissionList) {
                    permissionIdList.add(rolePermission.getPermissionId());
                }
            }
            // 根据permissionId查询所有的权限URL
            List<String> urlList = new ArrayList<>();
            for (Integer permissionId : permissionIdList) {
                PermissionExample permissionExample = new PermissionExample();
                permissionExample.createCriteria().andIdEqualTo(permissionId);
                Permission permission = permissionMapper.selectByExample(permissionExample).get(0);
                urlList.add(permission.getUrl());
            }
            return urlList;
        }
        List<String> urlList = new ArrayList<>();
        List<Permission> permissionList = permissionMapper.selectByExample(null);
        for (Permission permission : permissionList) {
            urlList.add(permission.getUrl());
        }
        return urlList;
    }

    @Override
    public ServerResponse listPermission() {
        List<Permission> permissionList = permissionMapper.selectByExample(null);
        System.out.println("哈儿permissionList： " + permissionList.toString());
        List<PermissionVo> permissionVoList = listPermissionTree(permissionList, 0);
        System.out.println("哈儿permissionVOList： " + permissionVoList.toString());
        return ServerResponse.success("查询成功", permissionVoList);
    }

    private List<PermissionVo> listPermissionTree(List<Permission> permissionList, Integer parentId) {
        List<PermissionVo> permissionVoList = new ArrayList<>();
        for (Permission permission : permissionList) {
            if (null != permission.getParentId() && permission.getParentId().equals(parentId)) {
                PermissionVo permissionVo = new PermissionVo();
                CliUtils.copyProperties(permission, permissionVo);
                permissionVoList.add(permissionVo);
            }
        }
        for (PermissionVo permissionVo : permissionVoList) {
            permissionVo.setChildren(listPermissionTree(permissionList, permissionVo.getId()));
        }
        return permissionVoList;
    }

    @Override
    public ServerResponse insertPermission(Permission permission) {
        int count = permissionMapper.insertSelective(permission);
        if (count > 0) {
            return ServerResponse.success("添加成功");
        }
        return ServerResponse.error("插入失败");
    }

    @Override
    public ServerResponse updatePermission(Permission permission) {
        int count = permissionMapper.updateByPrimaryKeySelective(permission);
        if (count > 0) {
            return ServerResponse.success("更新成功");
        }
        return ServerResponse.error("更新失败");
    }

    @Override
    public ServerResponse deletePermission(String ids) {
        int count = permissionMapper.deleteByPrimaryKey(1);
        if (count > 0) {
            return ServerResponse.success("删除成功");
        }
        return ServerResponse.error("删除失败");
    }
}
