package cn.kgc.controller;

import cn.kgc.commons.ServerResponse;
import cn.kgc.entity.User;
import cn.kgc.service.RoleService;
import cn.kgc.service.UserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping("/gotologin")
    public String gotoLogin(){
        return "login";
    }

    @RequestMapping("login.do")
    public String userLogin(String userCode,
                          String userPassword,
                          HttpServletRequest httpServletRequest,
                          Model model){
        ServerResponse serverResponse = userService.userLogin(userCode,userPassword);
        if (serverResponse.getSuccess()){
            //登录成功
            httpServletRequest.getSession().setAttribute("userSession",serverResponse.getData());
            return "frame";
        }else {
            //登录失败
            model.addAttribute("error",serverResponse.getMsg());
            return "login";
        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){
        //在当前会话中删除相关的用户信息，表示用户退出
        request.getSession().removeAttribute("userSession");
        return "login";
    }

    @RequestMapping("/test")
    public void testE(){
        throw new RuntimeException("项目发生了预期之外的错误......");
    }

    @ExceptionHandler({Exception.class})
    public String exceptionCache(RuntimeException e,Model model){
        model.addAttribute("error",e.getMessage());
        return "error";
    }

    @RequestMapping("/userList")
    public String gotUserList(Model model,
                              @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false) String queryname,
                              @RequestParam(required = false) Integer queryUserRole){
        PageInfo pageInfo = userService.queryUserList(pageIndex,queryname,queryUserRole);
        System.out.println("前往用户信息列表里");
        model.addAttribute("roleList",roleService.queryRoleAll());
        model.addAttribute("userList",pageInfo.getList());
        model.addAttribute("totalPageCount",pageInfo.getPages());
        model.addAttribute("totalCount",pageInfo.getTotal());
        model.addAttribute("currentPageNo",pageInfo.getPageNum());
        return "userlist";
    }

    @RequestMapping("gotoadd")
    public String gotoadd(){
        return "useradd";
    }

    @RequestMapping("/ckusercode")
    @ResponseBody
    public String checkUserCode(String userCode){
        return userService.checkUserCode(userCode);
    }

    @RequestMapping("/useradd")
    public String adduser(User user){
        userService.addUserSave(user);
        return "redirect:/userList";
    }

    @RequestMapping(value = "/get/{id}",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUser(@PathVariable Integer id){
        return JSONObject.toJSONString(userService.getUserById(id));
    }

    @RequestMapping("/jsp/user.do")
    public String deleteUserById(@RequestParam String userid){
        Map<String,String> resultMap = new HashMap<>();
        if (userid == null || userid == ""){
            resultMap.put("delResult","notexist");
        }
        if (userService.deleteUserById(userid)){
            resultMap.put("delResult","true");
        }else {
            resultMap.put("delResult","false");
        }
        return JSONArray.toJSONString(resultMap);
    }
}
