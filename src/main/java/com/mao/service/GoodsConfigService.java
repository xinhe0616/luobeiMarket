package com.mao.service;


import com.mao.entity.GoodsConfig;
import com.mao.util.PageQueryUtil;
import com.mao.util.PageResult;
import com.mao.vo.GoodsConfigVo;

import java.util.List;

public interface GoodsConfigService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getConfigsPage(PageQueryUtil pageUtil);

    String saveIndexConfig(GoodsConfig indexConfig);

    String updateIndexConfig(GoodsConfig indexConfig);

    GoodsConfig getIndexConfigById(Long id);

    /**
     * 返回固定数量的首页配置商品对象(首页调用)
     *
     * @param number
     * @return
     */
    List<GoodsConfigVo> getConfigGoodsForIndex(int configType, int number);

    Boolean deleteBatch(Long[] ids);
}
