package com.example.test.gitplugin.Utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

public class GitHttpClient {

    private static HttpClient client = null;

    private GitHttpClient() {

    }

    public static HttpClient getHttpInstance() {
        if(client == null) {
            //default
            //max total connection = 20
            //max connection per host = 2
            MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
            client = new HttpClient(connectionManager);
        }

        return client;
    }

}
