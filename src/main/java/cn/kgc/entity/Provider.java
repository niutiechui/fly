package cn.kgc.entity;

import lombok.Data;

@Data
public class Provider {

  private long id;
  private String proCode;
  private String proName;
  private String proDesc;
  private String proContact;
  private String proPhone;
  private String proAddress;
  private String proFax;
  private long createdBy;
  private java.sql.Timestamp creationDate;
  private java.sql.Timestamp modifyDate;
  private long modifyBy;
}
