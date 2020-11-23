package com.money.split.controller;

import com.money.split.common.HttpHelper;
import com.money.split.common.Response;
import com.money.split.service.SearchService;
import com.money.split.dto.SearchDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search")
    public Response<SearchDTO> searchSplit(@RequestParam("token") String token, HttpServletRequest request) {
        log.info("search request parameter : {}", token);
        return Response.ok(searchService.getSplitInfo(token, HttpHelper.extraHeader(request)));
    }
}
