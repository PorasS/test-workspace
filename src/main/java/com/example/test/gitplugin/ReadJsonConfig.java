package com.example.test.gitplugin;

import com.example.test.gitplugin.testDto.TestJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service("Reas Json Service")
public class ReadJsonConfig {

    TestJson testJson = null;

    public boolean readJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        Util.print();
        try {
            testJson = mapper.readValue(new File(filePath), TestJson.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(testJson instanceof TestJson){
            return true;
        }

        return false;
    }

    public String getName(){
        return testJson.getName();
    }
}

//class Test{
//    public static void main(String[] args) throws IOException {
//        ReadJsonConfig readJsonConfig = new ReadJsonConfig();
//        readJsonConfig.readJson("C:\\Work\\Development\\Test Files\\test.json");
//        System.out.println(readJsonConfig.getName());
//    }
//}