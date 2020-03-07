package com.mao.controller;

import com.mao.service.UserService;
import com.mao.util.PageQueryUtil;
import com.mao.util.Result;
import com.mao.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MallUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String usersPage(HttpServletRequest request) {
        request.setAttribute("path", "users");
        return "/malluser";
    }

    /**
     * 分页功能测试
     */
    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        Result result = ResultGenerator.genSuccessResult(userService.getNewBeeMallUsersPage(pageUtil));
        System.out.println(result);
        return result;
    }
}
