package com.example.test.gitplugin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateTest {

//    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
////        String jsonString = "{\"positions\":[{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNDoyNjowMC41MzVa\",\"rowNumber\":4},{\"columnNumber\":0,\"fieldList\":[{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"LastName\",\"rowNumber\":3},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNTozMzo0Ni40NDNa\",\"rowNumber\":1}],\"fieldName\":\"RmllbGRTZXRfMjAyMy0wMS0wMlQxNTozMzoyMS42NTda\",\"rowNumber\":0},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"FirstName\",\"rowNumber\":1},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNTozNDoxNC4wMjVa\",\"rowNumber\":5},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNDoyNjowMy4wNDZa\",\"rowNumber\":2},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"Email\",\"rowNumber\":3}]}";
//        String jsonString = "[{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNDoyNjowMC41MzVa\",\"rowNumber\":4},{\"columnNumber\":0,\"fieldList\":[{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"LastName\",\"rowNumber\":0},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNTozMzo0Ni40NDNa\",\"rowNumber\":1}],\"fieldName\":\"RmllbGRTZXRfMjAyMy0wMS0wMlQxNTozMzoyMS42NTda\",\"rowNumber\":0},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"FirstName\",\"rowNumber\":3},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNTozNDoxNC4wMjVa\",\"rowNumber\":5},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"SHRtbFRleHRfMjAyMy0wMS0wMlQxNDoyNjowMy4wNDZa\",\"rowNumber\":2},{\"columnNumber\":0,\"fieldList\":null,\"fieldName\":\"Email\",\"rowNumber\":1}]";
//        String uri = "https://987-ZFB-679.mktorest.com/rest/asset/v1/form/6512/reArrange.json?access_token=db7aa4d8-cd0c-49df-bd1c-856da14cfcbd:ab";
//
//        Map<String, String> map = new HashMap<>();
//        map.put("positions", jsonString);
//
//        String form = map.entrySet()
//                .stream()
//                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
//                .collect(Collectors.joining("&"));
//
//        HttpRequest filePost = HttpRequest.newBuilder().uri(new URI(uri)).POST(HttpRequest.BodyPublishers.ofString(form))
//                .setHeader("Content-Type", "application/x-www-form-urlencoded").build();
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpResponse<String> httpResponse = client.send(filePost, HttpResponse.BodyHandlers.ofString());
//    }

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        List<Map<String, String>> map = Arrays.asList(Map.of("123","245")) ;
        String uri = "https://987-ZFB-679.mktorest.com/identity/oauth/token?grant_type=client_credentials&client_id=8f2084d-95c5-41ef-91d5-d87ea86f1082&client_secret=lj95kM6mpCBcCSQDQH5V5ELTqhH8VaLm";

        HttpRequest filePost = HttpRequest.newBuilder().uri(new URI(uri)).GET()
                .setHeader("Content-Type", "application/x-www-form-urlencoded").build();
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> httpResponse = client.send(filePost, HttpResponse.BodyHandlers.ofString());

        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.body());
    }
}
