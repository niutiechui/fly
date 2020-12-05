package cn.kgc.service;

import cn.kgc.entity.Bill;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BillService {
    PageInfo selectBillList(Integer pageIndex,String productName, Integer providerId, Integer isPayment);

    Integer insertBill(Bill bill);

    Integer deleteBill(Integer id);

    List<Bill> selectBillList(Integer billId,String billCode);

    Integer updateBill(Bill bill);
}
