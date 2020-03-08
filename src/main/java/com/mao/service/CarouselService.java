package com.mao.service;

import com.mao.entity.Carousel;
import com.mao.util.PageQueryUtil;
import com.mao.util.PageResult;
import com.mao.vo.CarouselVo;

import java.util.List;

public interface CarouselService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getCarouselPage(PageQueryUtil pageUtil);

    String saveCarousel(Carousel carousel);

    String updateCarousel(Carousel carousel);

    Carousel getCarouselById(Integer id);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 返回固定数量的轮播图对象(首页调用)
     *
     * @param number
     * @return
     */
    List<CarouselVo> getCarouselsForIndex(int number);
}