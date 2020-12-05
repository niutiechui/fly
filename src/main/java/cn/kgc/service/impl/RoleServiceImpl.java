package cn.kgc.service.impl;

import cn.kgc.dao.RoleMapper;
import cn.kgc.entity.Role;
import cn.kgc.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<Role> queryRoleAll(){
        return roleMapper.queryRoleList();
    }
}
