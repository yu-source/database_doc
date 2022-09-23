package com.dhj.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author gmd
 * @Description word下载controller层
 * @Date 2022-09-21 17:20:09
 */
@Controller
@RequestMapping("/form")
@RequiredArgsConstructor
public class FormConfigController {


    @GetMapping("/index")
    public String test() {
        return "form/index";
    }


    @ResponseBody
    @PostMapping(value="/value")
    public String value(HttpServletRequest request, HttpServletResponse response) {
        return "hello"+JSONObject.toJSONString(request.getParameterMap());
    }


}
