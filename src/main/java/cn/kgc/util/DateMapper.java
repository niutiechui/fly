package cn.kgc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class DateMapper extends ObjectMapper {
    public DateMapper(){
        //关闭默认格式化日期
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        //设置新的格式化内容
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }
}
