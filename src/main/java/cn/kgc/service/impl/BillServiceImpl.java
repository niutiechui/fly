package cn.kgc.service.impl;

import cn.kgc.dao.BillMapper;
import cn.kgc.entity.Bill;
import cn.kgc.entity.User;
import cn.kgc.service.BillService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {
    @Resource
    private BillMapper billMapper;
    @Override
    public PageInfo selectBillList(Integer pageIndex,String productName, Integer providerId, Integer isPayment) {
        PageHelper.startPage(pageIndex,5);
        List<Bill> bills = billMapper.selectBillList(productName,providerId,isPayment,null,null);
        PageInfo pageInfo = new PageInfo(bills);
        List<Bill> billList = new ArrayList<>();
        for (Bill bill : bills) {
            Bill newBill = new Bill();
            BeanUtils.copyProperties(bill,newBill);
            billList.add(newBill);
        }
        pageInfo.setList(billList);
        return pageInfo;
    }

    @Override
    public Integer insertBill(Bill bill) {
        return billMapper.insertBill(bill);
    }

    @Override
    public Integer deleteBill(Integer id) {
        return billMapper.deleteBill(id);
    }

    @Override
    public List<Bill> selectBillList(Integer billId,String billCode) {
        return billMapper.selectBillList(null,null,null,billId,billCode);
    }

    @Override
    public Integer updateBill(Bill bill) {
        return billMapper.updateBill(bill);
    }
}
