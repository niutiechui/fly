package cn.kgc.service;

import cn.kgc.commons.ServerResponse;
import cn.kgc.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    public ServerResponse userLogin(String userCode, String userPassword);

    public PageInfo queryUserList(Integer pageIndex,String userName,Integer roleId);

    public String checkUserCode(String userCode);

    Boolean addUserSave(User user);

    public Boolean deleteUserById(String id);

    public User getUserById(Integer id);
}
