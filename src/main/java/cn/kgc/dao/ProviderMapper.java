package cn.kgc.dao;

import cn.kgc.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface ProviderMapper {

    List<Provider> selectProviderList(@Param("proCode") String proCode, @Param("proName") String proName);

    Provider queryProviderById(@Param("id") Integer id);

    Integer updateProviderById(Provider provider);

    Integer addProvider(Provider provider);


    Integer deleteProviderById(@Param("id") Integer id);
}
