package com.example.Calculator.controller;

import com.example.Calculator.model.Expression;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class Controller {
    @GetMapping("/calculate")
    public String Calculate(@RequestParam(value = "expression",defaultValue = "0")String expression){
        Expression ex = new Expression();
        return ex.resolveExpression(expression);
    }
}
