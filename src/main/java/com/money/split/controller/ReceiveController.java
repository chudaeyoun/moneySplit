package com.money.split.controller;

import com.money.split.common.HttpHelper;
import com.money.split.common.Response;
import com.money.split.dto.ReceiveDTO;
import com.money.split.service.ReceiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReceiveController {
    private final ReceiveService receiveService;

    @GetMapping("/receive")
    public Response<ReceiveDTO> receiveMoney(@RequestParam("token") String token, HttpServletRequest request) {
        log.info("receive request parameter : {}", token);
        return Response.ok(receiveService.getMoney(token, HttpHelper.extraHeader(request)));
    }
}
