package cn.kgc.controller;

import cn.kgc.commons.ServerResponse;
import cn.kgc.entity.User;
import cn.kgc.service.FileService;
import cn.kgc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class TestController {
    @RequestMapping("/gototest")
    public ModelAndView gototestuser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",new User());
        modelAndView.setViewName("/test/useraddtest");
        return modelAndView;
    }

    @RequestMapping("/useraddtest")
    public ModelAndView userAddTest(@Valid User user, BindingResult result){
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()){
            modelAndView.addObject("user",user);
            modelAndView.setViewName("/test/useraddtest");
            return modelAndView;
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @Resource
    private FileService fileService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String fileupload(MultipartFile file, HttpServletRequest request){
        return fileService.fileUpload(file,request.getSession().getServletContext().getRealPath("/upload"));
    }


    @Resource
    private UserService userService;

    @RequestMapping("/getuser/{id}")
    @ResponseBody
    public ServerResponse getUser(@PathVariable Integer id){
        return ServerResponse.creatResultByData("成功",userService.getUserById(id),true);
    }
}
