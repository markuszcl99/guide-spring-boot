package com.markus.accumulation.service.user.service.user;

import com.markus.accumulation.api.exception.ExceptionUtil;
import com.markus.accumulation.api.vo.PageResult;
import com.markus.accumulation.api.vo.Response;
import com.markus.accumulation.api.vo.constants.StatusEnum;
import com.markus.accumulation.api.vo.user.UserInfoSaveReq;
import com.markus.accumulation.api.vo.user.UserPageRequest;
import com.markus.accumulation.api.vo.user.dto.UserInfoDTO;
import com.markus.accumulation.service.user.repository.dao.UserDAO;
import com.markus.accumulation.service.user.repository.entity.UserInfoDO;
import com.markus.accumulation.service.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.markus.accumulation.service.user.converter.UserConverter.toDO;

/**
 * @author: markus
 * @date: 2024/4/14 3:14 PM
 * @Description: 用户服务实现类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
public class UserService implements IUserService {

    @Resource
    private UserDAO userDAO;

    @Override
    @Transactional
    public void saveUserInfo(UserInfoSaveReq userInfoSaveReq) {
        UserInfoDO userInfoDO = toDO(userInfoSaveReq);
        userDAO.save(userInfoDO);
//        throw ExceptionUtil.of(StatusEnum.FORBID_ERROR);
    }

    @Override
    public Response<PageResult<UserInfoDTO>> findPage(UserPageRequest userPageRequest) {
        // 采用分页插件完成分页
//        userDAO.page()
        return null;
    }
}
