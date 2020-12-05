package cn.kgc.dao;

import cn.kgc.entity.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    List<Bill> selectBillList( @Param("productName") String ProductName,
                               @Param("providerId") Integer ProviderId,
                               @Param("isPayment") Integer isPayment,
                               @Param("billid") Integer billid,
                               @Param("billCode") String billCode);

    Integer insertBill(Bill bill);

    Integer deleteBill(Integer id);

    Integer updateBill(Bill bill);
}
