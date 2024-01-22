package com.example.test.gitplugin;

import org.junit.jupiter.api.*;

//@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReadJsonConfigTest {
    ReadJsonConfig readJsonConfig = null;
    @BeforeAll
    void testMessage(){
        readJsonConfig = new ReadJsonConfig();
        System.out.println("Before All:");
    }



    @DisplayName("readJson() and getName() Test")
    @Test
    public void readJsonTestAndGetNameTest(){
//        assertEquals(expected, actual)
        boolean flag = readJsonConfig.readJson("src/main/resources/config/test.json");
        System.out.println("Flag : "+flag);
        Assertions.assertEquals(true, flag);

        String name = readJsonConfig.getName();
        System.out.println("name : "+name);
//        Assertions.assertEquals("pratik", name);
//        Assertions.assertEquals(String.class, name.getClass());
        Assertions.assertNotEquals("",name);
    }

    @AfterEach
    void afterEach(){
        System.out.println("Test Successful");
    }

    void afterAll(){
        System.out.println("All test for ReadJsonConfig is Succesful");
    }

}
