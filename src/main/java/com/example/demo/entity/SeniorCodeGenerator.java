package com.example.demo.entity;

import java.util.Random;

public class SeniorCodeGenerator {

    public static String generate() {

        Random r = new Random();
        int num = 10000 + r.nextInt(90000);

        return "CC-" + num;
    }
}