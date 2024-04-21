package com.markus.accumulation.service.email.service;

import com.markus.accumulation.api.vo.Response;

/**
 * @author: markus
 * @date: 2024/4/21 9:10 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public interface IEmailService {
    public Response<String> sendMessage(String emailId, String message);

    public Response<String> readMessage(String emailId);
}
