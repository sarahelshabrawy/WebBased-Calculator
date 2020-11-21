package com.example.Calculator.model;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Expression{

    public String resolveExpression(String expression)  {
        char operation = '#';
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        String[] split = expression.trim().split("\\s+");
        for (String s : split) {
            if (s.matches("-?\\d+(\\.\\d+)?")) {
                if(operation=='#')
                    num1.append(s);
                else num2.append(s);
            } else if (s.matches("[-÷+x^%]")) {
                if(operation=='#'||operation == '-') {
                    operation = s.toCharArray()[0];
                }
                else
                    return "E";
            }
            else if(s.contains("e+")){
                //for huge numbers, handling the exponential symbol
                String[] pow = s.split("e+");
                s = String.valueOf(Double.parseDouble(pow[0]) * Math.pow(10, Double.parseDouble(pow[1])));
                if(operation=='#')
                    num1.append(s);
                else num2.append(s);
            }
            else
                return "E";
        }
        if(operation=='#')
            return num1.toString();
        else if(num2.length() == 0 || num1.length()==0)
            return "E";
        if((operation == '÷'|| operation == '%')&& num2.toString().equals("0"))
            return "E";

        return String.valueOf(
                operation(Double.parseDouble(num1.toString())
                        ,Double.parseDouble(num2.toString())
                        , operation));
    }
    private double operation(double a,double b ,char operation){
        switch (operation){
            case '+' : return a+b;
            case '-' : return a-b;
            case 'x' : return a*b;
            case '÷' : return a/b;
            case '^' : return Math.pow(a,b);
            case '%' : return a%b;
        }
        return 0;
    }

}
