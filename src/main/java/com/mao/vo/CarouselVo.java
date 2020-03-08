package com.mao.vo;

import java.io.Serializable;

public class CarouselVo implements Serializable{
    private String carouselUrl;

    private String redirectUrl;

    public String getCarouselUrl() {
        return carouselUrl;
    }

    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }
}
