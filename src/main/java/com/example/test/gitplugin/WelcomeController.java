package com.example.test.gitplugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private RetryMechanismTest retryMechanismTest;

    @GetMapping("/msg")
    public String welcomeMessage(){
        try {
            retryMechanismTest.retryMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "welcome!!!!!";
    }

}
