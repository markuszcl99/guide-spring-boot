package com.markus.accumulation.service.user.service.user;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.markus.accumulation.service.user.converter.UserConverter.*;

/**
 * @author: markus
 * @date: 2024/4/14 3:14 PM
 * @Description: 用户服务实现类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Service
@Slf4j
public class UserService implements IUserService {

    @Resource
    private UserDAO userDAO;

    private CacheBuilder<Object, Object> cacheBuilder =
            CacheBuilder.newBuilder()
                    .maximumSize(100) // 最大缓存条目
                    .expireAfterAccess(30, TimeUnit.MINUTES) // 缓存项在指定时间内没有被访问就过期
                    .recordStats(); // 开启统计功能

    // 本地缓存
    private LoadingCache<String, List<UserInfoDTO>> userInfoCache = cacheBuilder.build(new CacheLoader<String, List<UserInfoDTO>>() {

        @Override
        public List<UserInfoDTO> load(String username) throws Exception {
            // 当缓存中没有值时，加载对应的值并返回
            log.error("出现缓存击穿...");
            return userDAO.queryUserInfoByUsername(username);
        }
    });

    @Override
    @Transactional
    public void saveUserInfo(UserInfoSaveReq userInfoSaveReq) {
        UserInfoDO userInfoDO = toDO(userInfoSaveReq);
        userDAO.save(userInfoDO);
//        throw ExceptionUtil.of(StatusEnum.FORBID_ERROR);
    }

    @Override
    @Transactional
    // 缓存失效（后面再读取的时候，重新读一次数据库，再写入缓存）
    @CacheEvict(cacheManager = "caffeineCacheManager", cacheNames = "accumulation", key = "'user_'+#userInfoSaveReq.userId")
    // 这个使用于，不管缓存中存不存在，都设置一遍缓存。可以理解为缓存更新
//    @CachePut(cacheManager = "caffeineCacheManager", cacheNames = "accumulation", key = "'user_'+#userInfoSaveReq.userId")
    public void updateUserInfo(UserInfoSaveReq userInfoSaveReq) {
        UserInfoDO userInfoDO = toDO(userInfoSaveReq);
        userDAO.updateById(userInfoDO);
    }

    @Override
    // 缓存失效
    @CacheEvict(cacheManager = "caffeineCacheManager", cacheNames = "accumulation", key = "'user_'+#userId")
    public void deleteUserInfo(Long userId) {
        userDAO.removeById(userId);
    }

    @Override
    // 缓存读取
    @Cacheable(cacheManager = "caffeineCacheManager", cacheNames = "accumulation", key = "'user_'+#userId")
    public Response<UserInfoDTO> queryUserInfoByUserId(Long userId) {
        UserInfoDO userInfoDO = userDAO.getById(userId);
        log.error("execute this method...");
        return Response.ok(toDTO(userInfoDO));
    }

    @Override
    public Response<List<UserInfoDTO>> queryUserInfosByUsername(String username) {
        try {
            // 这里会先去本地缓存查找，如果本地缓存没有，就去数据库中查找
            return Response.ok(userInfoCache.get(username));
        } catch (ExecutionException e) {
            throw ExceptionUtil.of(StatusEnum.CACHE_READ_EXCEPTION, username);
        }
    }

    @Override
    public Response<PageResult<UserInfoDTO>> findPage(UserPageRequest userPageRequest) {
        return Response.ok(userDAO.listUserInfoByUserPageRequest(userPageRequest));
    }
}
