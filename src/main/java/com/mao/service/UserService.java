package com.mao.service;

import com.mao.util.PageQueryUtil;
import com.mao.util.PageResult;

public interface UserService {
    PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil);
}
