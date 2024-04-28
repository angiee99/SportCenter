package com.sportcenterplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/schedules")
public class SchedulesController {
    @GetMapping()
    public String showAll(@RequestParam(value = "id") Integer id){
        System.out.println(id);
        return "schedules";
    }
}
