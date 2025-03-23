package com.yelisieiev.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to handle the AOP demonstration page
 */
@Controller
public class AopDemoController {

    /**
     * Display the AOP features demonstration page
     * @return the name of the view template
     */
    @GetMapping("/aop-demo")
    public String showAopDemoPage() {
        return "aop-demo";
    }
}