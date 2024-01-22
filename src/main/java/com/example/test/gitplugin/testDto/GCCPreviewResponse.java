package com.example.test.gitplugin.testDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GCCPreviewResponse extends GCCResponse{

    @JsonProperty("response_data")
    private ResponseData responseData;

    public static class ResponseData{
        @JsonProperty("context_url")
        private String contextUrl;

        public String getContextUrl() {
            return contextUrl;
        }

        public void setContextUrl(String contextUrl) {
            this.contextUrl = contextUrl;
        }
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
