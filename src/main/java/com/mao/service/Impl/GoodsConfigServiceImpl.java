package com.mao.service.Impl;


import com.mao.common.ServiceResultEnum;
import com.mao.dao.GoodsConfigMapper;
import com.mao.dao.GoodsMapper;
import com.mao.entity.Goods;
import com.mao.entity.GoodsConfig;
import com.mao.service.GoodsConfigService;
import com.mao.util.BeanUtil;
import com.mao.util.PageQueryUtil;
import com.mao.util.PageResult;
import com.mao.vo.GoodsConfigVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsConfigServiceImpl implements GoodsConfigService {

    @Resource
    private GoodsConfigMapper goodsConfigMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public PageResult getConfigsPage(PageQueryUtil pageUtil) {
        List<GoodsConfig> indexConfigs = goodsConfigMapper.findIndexConfigList(pageUtil);
        int total = goodsConfigMapper.getTotalIndexConfigs(pageUtil);
        PageResult pageResult = new PageResult(indexConfigs, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveIndexConfig(GoodsConfig indexConfig) {
        //todo 判断是否存在该商品
        if (goodsConfigMapper.insertSelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateIndexConfig(GoodsConfig indexConfig) {
        //todo 判断是否存在该商品
        GoodsConfig temp = goodsConfigMapper.selectByPrimaryKey(indexConfig.getConfigId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if (goodsConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public GoodsConfig getIndexConfigById(Long id) {
        return null;
    }

    @Override
    public List<GoodsConfigVo> getConfigGoodsForIndex(int configType, int number) {
        List<GoodsConfigVo> newBeeMallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<GoodsConfig> indexConfigs = goodsConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //取出所有的goodsId
            List<Long> goodsIds = indexConfigs.stream().map(GoodsConfig::getGoodsId).collect(Collectors.toList());
            List<Goods> newBeeMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            newBeeMallIndexConfigGoodsVOS = BeanUtil.copyList(newBeeMallGoods, GoodsConfigVo.class);
            for (GoodsConfigVo newBeeMallIndexConfigGoodsVO : newBeeMallIndexConfigGoodsVOS) {
                String goodsName = newBeeMallIndexConfigGoodsVO.getGoodsName();
                String goodsIntro = newBeeMallIndexConfigGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    newBeeMallIndexConfigGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return newBeeMallIndexConfigGoodsVOS;
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除数据
        return goodsConfigMapper.deleteBatch(ids) > 0;
    }
}