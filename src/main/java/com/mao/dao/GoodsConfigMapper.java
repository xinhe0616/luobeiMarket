package com.mao.dao;

import com.mao.entity.GoodsConfig;
import com.mao.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsConfigMapper {
    int deleteByPrimaryKey(Long configId);

    int insert(GoodsConfig record);

    int insertSelective(GoodsConfig record);

    GoodsConfig selectByPrimaryKey(Long configId);

    int updateByPrimaryKeySelective(GoodsConfig record);

    int updateByPrimaryKey(GoodsConfig record);

    List<GoodsConfig> findIndexConfigList(PageQueryUtil pageUtil);

    int getTotalIndexConfigs(PageQueryUtil pageUtil);

    int deleteBatch(Long[] ids);

    List<GoodsConfig> findIndexConfigsByTypeAndNum(@Param("configType") int configType, @Param("number") int number);
}
