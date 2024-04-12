package com.markus.springboot.project.accumuldation.trigger.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: markus
 * @date: 2024/4/12 11:27 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private long userId;
    private String username;
    private int age;
}
