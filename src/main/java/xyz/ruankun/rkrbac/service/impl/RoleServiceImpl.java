package xyz.ruankun.rkrbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ruankun.rkrbac.mapper.RoleMapper;
import xyz.ruankun.rkrbac.model.Role;
import xyz.ruankun.rkrbac.server.ServerResponse;
import xyz.ruankun.rkrbac.service.IRoleService;

import java.util.List;

/**
 * @author: mrruan
 * @date: 2019-09-16 23:17
 * @description:
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public ServerResponse listRole() {
        List<Role> roleList = roleMapper.selectByExample(null);
        return ServerResponse.success("查询成功", roleList);
    }

    @Override
    public ServerResponse insertRole(Role role) {
        int count = roleMapper.insertSelective(role);
        if (count > 0) {
            return ServerResponse.success("添加成功");
        }
        return ServerResponse.error("插入失败");
    }

    @Override
    public ServerResponse updateRole(Role role) {
        int count = roleMapper.updateByPrimaryKeySelective(role);
        if (count > 0) {
            return ServerResponse.success("更新成功");
        }
        return ServerResponse.error("更新失败");
    }

    @Override
    public ServerResponse deleteRole(String ids) {
        int count = roleMapper.deleteByPrimaryKey(1);
        if (count > 0) {
            return ServerResponse.success("删除成功");
        }
        return ServerResponse.error("删除失败");
    }
}
