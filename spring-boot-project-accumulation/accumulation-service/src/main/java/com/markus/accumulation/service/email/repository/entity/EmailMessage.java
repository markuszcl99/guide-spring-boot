package com.markus.accumulation.service.email.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: markus
 * @date: 2024/4/21 9:47 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailMessage implements Serializable {
    private String emailId;
    private String message;
}
