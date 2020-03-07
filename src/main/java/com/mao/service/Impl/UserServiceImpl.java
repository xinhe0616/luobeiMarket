package com.mao.service.Impl;

import com.mao.dao.MallUserMapper;
import com.mao.entity.MallUser;
import com.mao.service.UserService;
import com.mao.util.PageQueryUtil;
import com.mao.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MallUserMapper mallUserMapper;
    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        List<MallUser> mallUsers = mallUserMapper.findMallUserList(pageUtil);
        int total = mallUserMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(mallUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
