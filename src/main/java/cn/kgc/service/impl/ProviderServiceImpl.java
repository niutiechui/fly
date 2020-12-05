package cn.kgc.service.impl;

import cn.kgc.commons.ServerResponse;
import cn.kgc.dao.ProviderMapper;
import cn.kgc.entity.Provider;
import cn.kgc.service.ProviderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public PageInfo selectProviderList(Integer pageIdex,String proCode,String proName) {
        PageHelper.startPage(pageIdex,5);
        List<Provider> providers = providerMapper.selectProviderList(proCode,proName);
        PageInfo pageInfo = new PageInfo(providers);
        List<Provider>providerList = new ArrayList<>();
        for (Provider provider : providers) {
            Provider pro = new Provider();
            BeanUtils.copyProperties(provider,pro);
            providerList.add(pro);
        }
        pageInfo.setList(providerList);
        return pageInfo;
    }

    @Override
    public List<Provider> selectProviderList() {
        return providerMapper.selectProviderList(null,null);
    }

    @Override
    public Provider queryProviderById(Integer id) {
        return providerMapper.queryProviderById(id);
    }

    @Override
    public Integer updateProviderById(Provider provider) {
        return providerMapper.updateProviderById(provider);
    }

    @Override
    public Integer addProvider(Provider provider) {
        return providerMapper.addProvider(provider);
    }

    @Override
    public ServerResponse deleteProviderById(Integer id) {
        if (providerMapper.queryProviderById(id) == null){
            return ServerResponse.creatResultByMsg("notexist",false);
        }
        if (providerMapper.deleteProviderById(id) == 0){
            return ServerResponse.creatResultByMsg("false",false);
        }
        return ServerResponse.creatResultByMsg("true",false);
    }
}
