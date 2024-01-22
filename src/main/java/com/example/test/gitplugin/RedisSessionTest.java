package com.example.test.gitplugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RedisSessionTest {
    public static void main(String[] args) throws Exception {
        System.out.println();
        Optional<String> opt = getConnectorSessionViaAdminApi();
        if(opt.isPresent()){
            System.out.println("session Object: "+getConnectorSession(opt.get()).get().toString());
        }
    }

    public static Optional<String> getConnectorSessionViaAdminApi() throws Exception {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);

//        Logger.trace("Getting Session Id From Admin Api:");

        PostMethod post = null;


//      httpClient = GitHttpClient.getHttpInstance();
        post = new PostMethod("https://connect-qa.translations.com/admin-api"+"/connector-session");
        post.setRequestHeader("api-key", "password1");

        Map<String, Object> map = new HashMap<>();
        Map<String, String> innerMap = new HashMap<>();
        innerMap.put("connector","GitHub PS1");
        innerMap.put("typpe","GitHub");

        map.put("connectorSessionObject", innerMap);
        String jsonString = new ObjectMapper().writeValueAsString(map);

//      Logger.trace("Create Session : " + jsonString);

        StringRequestEntity requestEntity = new StringRequestEntity(jsonString, "application/json", "UTF-8");
        post.setRequestEntity(requestEntity);

        httpClient.executeMethod(post);
//		Logger.trace("Admin Api Response for connector session: [" + post.getResponseBodyAsString() + "]");

        ObjectMapper mapper = new ObjectMapper();

       Map<String, Object> res = mapper.readValue(post.getResponseBodyAsString(), Map.class);

        System.out.println("status : "+res.get("status"));

//      Logger.trace("AdminApiResponseData" + res.getStatus());
        System.out.println("resMap: "+mapper.writeValueAsString(res.get("response_data")));

        Map<String, String> resMap = mapper.readValue(mapper.writeValueAsString(res.get("response_data")),Map.class);
        return Optional.ofNullable(resMap.get("sessionKey"));

//        System.out.println(post.getResponseBodyAsString());

//        return Optional.empty();
    }

    public static Optional<GitConnectorConfig> getConnectorSession(String sessionId) throws IOException {
        Map<String, Object> res = null;
        GitConnectorConfig connectorConfig = null;
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);
        GetMethod get = new GetMethod("https://connect-qa.translations.com/admin-api/connector-session/"+sessionId);
        get.setRequestHeader("api-key", "password1");
        httpClient.executeMethod(get);
//		Logger.trace("Admin Api Response for connector session: [" + post.getResponseBodyAsString() + "]");
        ObjectMapper mapper = new ObjectMapper();
        res = mapper.readValue(get.getResponseBodyAsString(), Map.class);

//      Logger.trace("AdminApiResponse for get connector session status" + res.get("status"));
        System.out.println(mapper.writeValueAsString(res.get("response_data")));
        connectorConfig = mapper.readValue(mapper.writeValueAsString(res.get("response_data")), GitConnectorConfig.class) ;

        return Optional.ofNullable(connectorConfig);
    }
}
