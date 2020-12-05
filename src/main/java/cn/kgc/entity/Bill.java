package cn.kgc.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Bill {
    private Integer id;                 //id
    private String billCode;            //账单编码
    private String productName;         //商品名称
    private String productDesc;         //商品描述
    private String productUnit;         //商品单位
    private Double productCount;    //商品数量
    private Double totalPrice;      //商品总额
    private Integer isPayment;          //支付状态
    private Integer createdBy;          //创建者（userId）
    private Date creationDate;          //创建时间
    private Integer modifyBy;           //更新者（userId）
    private Date modifyDate;            //更新时间
    private Integer providerId;         //供应商ID
    private String providerName;       //供应商
}


