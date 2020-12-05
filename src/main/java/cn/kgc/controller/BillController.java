package cn.kgc.controller;

import cn.kgc.entity.Bill;
import cn.kgc.entity.Provider;
import cn.kgc.service.BillService;
import cn.kgc.service.ProviderService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BillController {
    @Resource
    private BillService billService;
    @Resource
    private ProviderService providerService;

    @RequestMapping("/billList")
    public String billList(Model model,
                           @RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                           @RequestParam(required = false) String queryProductName,
                           @RequestParam(required = false) Integer queryProviderId,
                           @RequestParam(required = false) Integer queryIsPayment){
        PageInfo pageInfo = billService.selectBillList(pageIndex,queryProductName,queryProviderId,queryIsPayment);
        model.addAttribute("providerList",providerService.selectProviderList());
        model.addAttribute("billList",pageInfo.getList());
        model.addAttribute("totalPageCount",pageInfo.getPages());
        model.addAttribute("totalCount",pageInfo.getTotal());
        model.addAttribute("currentPageNo",pageInfo.getPageNum());
        return "billlist";
    }

    @RequestMapping("/gotAddBill")
    public String gotAddBill(){
        return "billadd";
    }

    @RequestMapping("/addBill")
    public String addBill(Bill bill){
        if(billService.insertBill(bill)>0){
            System.out.println("添加成功！");
            return "redirect:/billList";
        }
        return "billlist";
    }

    @RequestMapping("/proName")
    @ResponseBody
    public List<Provider> providerList(){
        return providerService.selectProviderList();
    }

    @RequestMapping("/deleteBill")
    @ResponseBody
    public String deleteBill(Integer billid){
        if (billService.deleteBill(billid)>0){
            return "true";
        }
        return "false";
    }

    @RequestMapping({"/billModify","/billView"})
    public String ModifyAndView(Integer billid, Model model, HttpServletRequest request){
        String method = request.getParameter("method");
        model.addAttribute("bill",billService.selectBillList(billid,null).get(0));
        if (method.equals("modify")){
            return "billmodify";
        }else {
            return "billview";
        }
    }

    @RequestMapping("/update")
    public String updateBill(Bill bill){

        if (billService.updateBill(bill)>0){
            return "redirect:/billList";
        }
        return "billmodify";
    }

    @RequestMapping("billCode")
    @ResponseBody
    public String billCode(String billCode){
        System.out.println("-----------------------------------"+billCode);
        if (billService.selectBillList(null,billCode)!=null){
            return "exist";
        }
        return "true";
    }
}
