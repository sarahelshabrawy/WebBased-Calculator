package com.example.Calculator.model;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Expression{

    public String resolveExpression(String expression)  {
        // char[] exp = expression.toCharArray();
        char operation = '#';
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();

        String[] split = expression.trim().split("");

        for (String s : split) {
            if (s.matches("-?\\d+(\\.\\d+)?")) {
                if(operation=='#')
                    num1.append(s);
                else num2.append(s);
            } else if (s.matches("[-/+*^%]")) {
                if(operation=='#')
                    operation = s.toCharArray()[0];
                else
                    return "Ea";
            }
            else if(!s.matches("\\s+"))
                return "Ef";
        }
        if(operation=='#')
            return num1.toString();
        return String.valueOf(operation(Double.parseDouble(num1.toString()),Double.parseDouble(num2.toString()),operation));
    }
    private double operation(double a,double b ,char operation){
        switch (operation){
            case '+' : return a+b;
            case '-' : return a-b;
            case '*' : return a*b;
            case '/' : return a/b;
            case '^' : return Math.pow(a,b);
            case '%' : return a%b;
        }
        return 0;
    }

    public static void main(String[] args) {
        Expression ex = new Expression();
        System.out.println(ex.resolveExpression("8+9"));
        System.out.println(URLEncoder.encode("6 % 9", StandardCharsets.UTF_8));
    }
}
