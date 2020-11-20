package com.example.Calculator.controller;

import com.example.Calculator.model.Expression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
public class Controller {
    @GetMapping("/calculate")
    public String Calculate(@RequestParam(value = "expression",defaultValue = "0")String expression){
        Expression ex = new Expression();
        expression = URLDecoder.decode(expression, StandardCharsets.UTF_8);
        return ex.resolveExpression(expression);
    }
}
