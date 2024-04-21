package com.markus.accumulation.web.controller;

import com.markus.accumulation.api.vo.Response;
import com.markus.accumulation.service.email.service.IEmailService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: markus
 * @date: 2024/4/21 9:09 PM
 * @Description: 接入 kafka 的示例
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private IEmailService emailService;

    @PostMapping("/send")
    public Response<String> send(@RequestParam("emailId") String emailId,
                                 @RequestParam("message") String message) {
        return emailService.sendMessage(emailId, message);
    }

    @GetMapping("/read")
    public Response<String> read(@RequestParam("emailId") String emailId) {
        return emailService.readMessage(emailId);
    }
}
