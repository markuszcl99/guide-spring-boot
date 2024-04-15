package com.markus.accumulation.service.user.repository.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markus.accumulation.service.user.repository.entity.UserInfoDO;
import com.markus.accumulation.service.user.repository.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;

/**
 * @author: markus
 * @date: 2024/4/14 3:23 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Repository
public class UserDAO extends ServiceImpl<UserInfoMapper, UserInfoDO> {

    /**
     * 继承 ServiceImpl 复用 mybatis-plus 已经实现的通用的增删改查，从而简化代码的开发，提高开发效率
     * 在 mybatis-plus 中，对数据的操作通常分为 service 层和 mapper 层
     * service 层就是处理业务逻辑
     * mapper 层就是处理具体数据
     * dao 就可以认为成 service层
     */
}
