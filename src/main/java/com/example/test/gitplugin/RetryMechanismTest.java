package com.example.test.gitplugin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RetryMechanismTest {

    int retryCount = 0;
    @Retryable(retryFor = { IOException.class},
            maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public void retryMethod() throws Exception {
        System.out.println("Retry Count: "+(++retryCount));
        throw new IOException();
    }
}



