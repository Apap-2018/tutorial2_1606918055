package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class PageController {

    @RequestMapping("/viral")
    public String index() {
        return "viral";
    }

//    @RequestMapping("/challenge")
//    public String challenge(@RequestParam(value = "name") String name, Model model) {
//        model.addAttribute("name", name);
//        return "challenge";
//    }

    @RequestMapping(value = {"/challenge", "/challenge/{name}"})
    public String challengePath(@PathVariable Optional<String> name, Model model) {
        if(name.isPresent()) {
            model.addAttribute("name", name.get());
        } else {
            model.addAttribute("name", "KB");
        }
        return "challenge";
    }

    @RequestMapping("/generator")
    public String generator(@RequestParam(value="a", defaultValue = "0", required = false) String a, @RequestParam(value = "b", defaultValue = "0", required = false) String b, Model model) {
        Map<String, String> response = new HashMap<>();
        response.put("a", a);
        response.put("b", b);

        // create hm
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append('h');
        int mMultiplier = Integer.parseInt(a);

        while (mMultiplier-- > 0 || Integer.parseInt(a) == 0) {
            strBuilder.append('m');
        }
        strBuilder.append(' ');

        // create multiple hm hm
        final String HM = strBuilder.toString();
        int hmMultiplier = Integer.parseInt(b);
        while(--hmMultiplier > 0) {
            strBuilder.append(HM);
        }

        response.put("hm", strBuilder.toString());

        model.addAllAttributes(response);
        return "generator";
    }
}
