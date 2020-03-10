package com.mao.controller;

import com.mao.common.Constants;
import com.mao.entity.AdminUser;
import com.mao.service.AdminUserService;
import com.mao.service.CarouselService;
import com.mao.service.CategoryService;
import com.mao.vo.CarouselVo;
import com.mao.vo.CategoryVo;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private CarouselService carouselService;
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/starter")
    public String starter(){
        System.out.println("进入start从1");
        return "/starter";
    }
    @GetMapping({"/index", "/"})
    public String indexPage(HttpServletRequest request) {
        List<CategoryVo> categories = categoryService.getCategoriesForIndex();
        for (CategoryVo categorie:categories){
            System.out.println(categorie);
        }

        if (CollectionUtils.isEmpty(categories)) {
            return "error/error_5xx";
        }
        List<CarouselVo> carousels = carouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
////        List<ConfigGoodsVo> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
////        List<ConfigGoodsVo> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
////        List<ConfigGoodsVo> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
        request.setAttribute("categories", categories);//分类数据
        request.setAttribute("carousels", carousels);//轮播图
////        request.setAttribute("hotGoodses", hotGoodses);//热销商品
////        request.setAttribute("newGoodses", newGoodses);//新品
////        request.setAttribute("recommendGoodses", recommendGoodses);//推荐商品
        return "index";
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping(value = "/login")
    public String adminLogin(
        @RequestParam("name") String name,
        @RequestParam("pwd") String pwd,
        @RequestParam("verifyCode") String verify,
        HttpSession session
    ){

        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd)) {
            session.setAttribute("loginError","用户名或者密码为空");
            System.out.println("用户名或者密码为空");
            return "/login";
        }
        Object verifyGet = session.getAttribute("verify");
        if (!verify.equals(verifyGet))
        {
            session.setAttribute("loginError","验证码错误");
            System.out.println("验证码错误");
            return "/login";
        }
        AdminUser user = adminUserService.loginMysql(name, pwd);
        System.out.println(user);
        if (user==null){
                session.setAttribute("loginError","账号或者密码错误");
                System.out.println("账号或者密码错误");
                return "/login";
        }
        session.setAttribute("loginUser", user.getLoginUserName());
        System.out.println("进入start从2");

//        session.setAttribute("loginUser","admin");
        return "redirect:/starter";
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(Constants.MALL_USER_SESSION_KEY);
        return "login";
    }
}
