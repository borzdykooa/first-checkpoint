package com.borzdykooa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessController extends BaseController {

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
