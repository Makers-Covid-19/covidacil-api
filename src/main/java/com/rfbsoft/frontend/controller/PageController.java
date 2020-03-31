package com.rfbsoft.frontend.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(PageController.CONTROLLER_PATH)
public class PageController {
    public static final String CONTROLLER_PATH = "";


    @GetMapping("/")
    public String main() {

        return "index"; //view
    }

}
