package com.example.test.gitplugin;

import com.example.test.gitplugin.Utils.GitHttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.ConnectionPoolTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NoHttpResponseException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.util.Map;

public class ReviewCommit {

    public static void main(String[] args) throws Exception {
        createSubmissionForBranch();
    }

    public static void createSubmissionForBranch() throws Exception {

        PostMethod postMethod = null;

            try {
                HttpClient httpClient = GitHttpClient.getHttpInstance();

                String reviewCommit = "Valid Review Commit";

                StringRequestEntity requestEntity = new StringRequestEntity("{\"body\": \""+reviewCommit+"\"}", "application/json", "UTF-8");
                postMethod = new PostMethod("https://api.github.com/repos/devpune/TestRepo/issues/113/comments");
                postMethod.setRequestHeader("Authorization", "Bearer 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");
                postMethod.setRequestEntity(requestEntity);

                httpClient.executeMethod(postMethod);

                // Map<Object,Object> result = new
                // ObjectMapper().readValue(postMethod.getResponseBodyAsString(),
                // Map.class);

                Map<Object, Object> map = new ObjectMapper().readValue(postMethod.getResponseBodyAsString(), Map.class);
                System.out.println(map.toString());

            } catch (Exception e) {
                // NoHttpResponseException =>when under heavy load
                // ConnectTimeoutException => unable to establish a connection
                // ConnectionPoolTimeoutException => can only occur when using
                // the multithreaded connection manager
                // fails to obtain a free connection from the connection pool
                if (e instanceof NoHttpResponseException || e instanceof ConnectTimeoutException || e instanceof ConnectionPoolTimeoutException) {

                } else {
                    throw e;
                }
            } finally {
                if (postMethod != null) {
                    postMethod.releaseConnection();
                }
            }


    }

}
