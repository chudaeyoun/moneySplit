package com.money.split.controller;

import com.money.split.common.HttpHelper;
import com.money.split.common.Response;
import com.money.split.dto.RequestSplitDTO;
import com.money.split.service.SplitService;
import com.money.split.dto.SplitDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SplitController {
    private final SplitService splitService;

    @PostMapping("/split")
    public Response<String> splitMoney(@RequestBody RequestSplitDTO requestSplitDTO, HttpServletRequest request) {
        log.info("split request parameter : {}", requestSplitDTO);
        return Response.ok(splitService.getSplit(requestSplitDTO, HttpHelper.extraHeader(request)));
    }
}
