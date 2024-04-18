package com.markus.accumulation.service.user.converter;

import com.markus.accumulation.api.vo.user.UserInfoSaveReq;
import com.markus.accumulation.api.vo.user.dto.UserInfoDTO;
import com.markus.accumulation.service.user.repository.entity.UserInfoDO;
import com.sun.istack.internal.NotNull;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: markus
 * @date: 2024/4/14 3:20 PM
 * @Description: 用户转换类
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class UserConverter {
    /**
     * 该类转换是手动编码实现 Req to DO 的转换
     */

    public static UserInfoDO toDO(@NotNull UserInfoSaveReq userInfoSaveReq) {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setId(userInfoSaveReq.getUserId());
        userInfoDO.setUsername(userInfoSaveReq.getUsername());
        userInfoDO.setAge(userInfoSaveReq.getAge());
        return userInfoDO;
    }

    public static UserInfoDTO toDTO(@NotNull UserInfoDO userInfoDO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userInfoDO.getId());
        userInfoDTO.setAge(userInfoDO.getAge());
        userInfoDTO.setUsername(userInfoDO.getUsername());
        userInfoDTO.setCreateTime(userInfoDO.getCreateTime());
        userInfoDTO.setUpdateTime(userInfoDO.getUpdateTime());
        return userInfoDTO;
    }

    public static List<UserInfoDTO> toDTOs(List<UserInfoDO> userInfoDOS) {
        if (CollectionUtils.isEmpty(userInfoDOS)) {
            return Collections.emptyList();
        }

        List<UserInfoDTO> dtos = new ArrayList<>(userInfoDOS.size());
        for (UserInfoDO userInfoDO : userInfoDOS) {
            UserInfoDTO dto = toDTO(userInfoDO);
            dtos.add(dto);
        }
        return dtos;
    }
}
