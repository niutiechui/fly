package cn.kgc.controller;
import cn.kgc.commons.ServerResponse;
import cn.kgc.entity.Provider;
import cn.kgc.entity.User;
import cn.kgc.service.ProviderService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Controller
public class ProviderController {
    @Resource
    private ProviderService providerService;

    @RequestMapping("/providerList")
    public String providerList(Model model,
                               @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                               @RequestParam(required = false)String queryProCode,
                               @RequestParam(required = false)String queryProName){
        PageInfo pageInfo = providerService.selectProviderList(pageIndex,queryProCode,queryProName);
        model.addAttribute("providerList",pageInfo.getList());
        model.addAttribute("totalPageCount",pageInfo.getPages());
        model.addAttribute("totalCount",pageInfo.getTotal());
        model.addAttribute("currentPageNo",pageInfo.getPageNum());
        return "providerlist";
    }

    @RequestMapping("/provideradd")
    public String provideradd(){
        return "provideradd";
    }

    @RequestMapping("/addProvider")
    public String addProvider(Provider provider, HttpServletRequest request){
        User userSession = (User)request.getSession().getAttribute("userSession");
        provider.setModifyBy(userSession.getId());
        if (providerService.addProvider(provider) > 0){
            return "redirect:/providerList";
        }else{
            return "provideradd";
        }
    }

    @RequestMapping("/providerview")
    public String providerview(Integer proid,Model model){
        model.addAttribute("provider",providerService.queryProviderById(proid));
        return "providerview";
    }

    @RequestMapping("/providermodify")
    public String providermodify(Integer proid,Model model){
        model.addAttribute("provider",providerService.queryProviderById(proid));
        return "providermodify";
    }

    @RequestMapping("/updateProvider")
    public String updateProvider(Provider provider, HttpServletRequest request,Integer proid){
        User userSession = (User)request.getSession().getAttribute("userSession");
        provider.setModifyBy(userSession.getId());
        provider.setId(proid);
        Integer integer = providerService.updateProviderById(provider);
        System.out.println(integer);
        if (integer > 0){
            return "redirect:/providerList";
        }
        return "providermodify";
    }

    @RequestMapping("/providerdelete")
    @ResponseBody
    public Map<String,String> providerdelete(Integer proid){
        Map<String,String> map = new HashMap<>();
        ServerResponse respone = providerService.deleteProviderById(proid);
        map.put("delResult",respone.getMsg());
        return map;
    }

    @RequestMapping("/providerupdate")
    public String providerupdate(Model model,Integer proid){
        model.addAttribute("provider",providerService.queryProviderById(proid));
        return "providermodify";
    }
}
