package cn.kgc.service.impl;

import cn.kgc.commons.ServerResponse;
import cn.kgc.dao.UserMapper;
import cn.kgc.entity.User;
import cn.kgc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public ServerResponse userLogin(String userCode, String userPassword){
        if (userCode == null || userPassword == null){
            return ServerResponse.creatResultByMsg("参数丢失",false);
        }
        //验证用户名是否存在
        Integer integer = userMapper.queryUserByUserCode(userCode);
        if (integer == 0){
            return ServerResponse.creatResultByMsg("用户名不正确",false);
        }
        //校验密码
        User user = userMapper.login(userCode,userPassword);
        if (user == null){
            return  ServerResponse.creatResultByMsg("密码不正确",false);
        }
        // TODO 登录成功
        return ServerResponse.creatResultByData("登录成功",user,true);
    }

    public PageInfo queryUserList(Integer pageIndex,String userName,Integer roleId){
        /*开始分页*/
        PageHelper.startPage(pageIndex,5);
        List<User> users =  userMapper.queryAllUserList(userName,roleId);
        PageInfo pageInfo = new PageInfo(users);
        List<User> resultUserList = new ArrayList<>();
        for (User user : users) {
            User newUser = new User();

            BeanUtils.copyProperties(user,newUser);
            resultUserList.add(newUser);
        }
        pageInfo.setList(resultUserList);
        System.out.println("------------");
        System.out.println(pageInfo);
        System.out.println("------------");
        return pageInfo;
    }


    public String checkUserCode(String userCode){
        if (StringUtils.isBlank(userCode)){
            return "error";
        }
        if (userMapper.queryUserByUserCode(userCode) == 0){
            return "notexists";
        }
        return "exist";
    }

    public Boolean deleteUserById(String id){
        Integer integer = userMapper.deleteUserById(id);
        if (integer >= 1){
            return true;
        }else {
            return false;
        }
    }

    public Boolean addUserSave(User user){
        Integer integer = userMapper.addUserSave(user);
        if (integer >= 1){
            return true;
        }else {
            return false;
        }
    }

    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }
}
