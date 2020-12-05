package cn.kgc.dao;

import cn.kgc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 验证用户名是否存在
     * @param userCode
     * @return 返回数据大于零表示当前用户存在，0 表示当前用户名不存在
     */
    Integer queryUserByUserCode(String userCode);

    /**
     * 根据用户名和密码查询用户是否存在
     * @param userCode 用户名
     * @param userPassword 密码
     * @return null表示用户不存在或者密码不正确
     */
    User login(@Param("userCode") String userCode,
               @Param("userPassword") String userPassword);

    /**
     * 查询用户
     * @return 用户列表
     */
    List<User> queryAllUserList(@Param("userName") String userName,
                                @Param("roleId") Integer roleId);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    Integer addUserSave(User user);

    Integer deleteUserById(@Param("id") String id);

    User getUserById(Integer id);
}
