package com.example.test.gitplugin;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

    public static void main(String[] args) throws IOException {
        String mapping = ".doc:DOC";
        String regex = mapping.split(":")[0];
        String nodeType = mapping.split(":")[1];
        Pattern pattern = null;
        Matcher matcher = null;
        pattern = Pattern.compile(regex);
        String fileName = "FolderB/test.doc";
        matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            System.out.println("Matched");
        }
    }
}
