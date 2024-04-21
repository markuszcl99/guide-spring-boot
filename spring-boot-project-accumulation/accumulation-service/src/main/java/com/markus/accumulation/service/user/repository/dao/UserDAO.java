package com.markus.accumulation.service.user.repository.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.markus.accumulation.api.vo.PageResult;
import com.markus.accumulation.api.vo.user.UserPageRequest;
import com.markus.accumulation.api.vo.user.dto.UserInfoDTO;
import com.markus.accumulation.core.util.PageUtils;
import com.markus.accumulation.service.user.converter.UserConverter;
import com.markus.accumulation.service.user.repository.entity.UserInfoDO;
import com.markus.accumulation.service.user.repository.mapper.UserInfoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public PageResult<UserInfoDTO> listUserInfoByUserPageRequest(UserPageRequest userPageRequest) {
        // 采用分页插件完成分页
        int pageNum = userPageRequest.getPageNum();
        int pageSize = userPageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        // 此处实际上是一个 Page<T> 实例
        LambdaQueryWrapper<UserInfoDO> query = Wrappers.lambdaQuery();
        query.eq(StringUtils.isNotBlank(userPageRequest.getUsername()), UserInfoDO::getUsername, userPageRequest.getUsername());
        List<UserInfoDO> userInfoDOList = list(query);
        PageInfo<UserInfoDO> pageInfo = new PageInfo<>(userInfoDOList);
        return PageUtils.getPageResult(pageInfo, UserConverter::toDTOs);
    }

    public List<UserInfoDTO> queryUserInfoByUsername(String username) {
        LambdaQueryWrapper<UserInfoDO> query = Wrappers.lambdaQuery();
        query.eq(UserInfoDO::getUsername, username);
        List<UserInfoDO> userInfoDOS = list(query);
        return UserConverter.toDTOs(userInfoDOS);
    }
}
