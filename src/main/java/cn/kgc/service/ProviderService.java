package cn.kgc.service;

import cn.kgc.commons.ServerResponse;
import cn.kgc.entity.Provider;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface ProviderService {

    PageInfo selectProviderList(Integer pageIndex, String proCode, String proName);

    List<Provider> selectProviderList();

    Provider queryProviderById(Integer id);

    Integer updateProviderById(Provider provider);

    Integer addProvider(Provider provider);

    ServerResponse deleteProviderById(Integer id);
}
