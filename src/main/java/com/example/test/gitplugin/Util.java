package com.example.test.gitplugin;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Util {

    public static void main(String[] args) throws IOException {
        String cell = 9+"::"+8;
        String[] arg = cell.split("::");

        System.out.println(arg[0]);
        System.out.println(arg[1]);
    }

    public static void print() {

    }


}
