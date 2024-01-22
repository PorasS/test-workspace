package com.example.test.gitplugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ScriptExecutor implements AutoCloseable {

    private ProcessBuilder processBuilder;
    private Process process;

    public ScriptExecutor() {
        processBuilder = new ProcessBuilder();
    }

    public ScriptExecutor setDirectory(String filePath) {
        processBuilder.directory(new File(filePath));
        return this;
    }

    public ScriptExecutor executeScript(String command) throws IOException {
        process = processBuilder.command(command).redirectErrorStream(true).start();
        return this;
    }

    public ScriptExecutor executeScript(List<String> commands) throws IOException {
        process = processBuilder.command(commands).redirectErrorStream(true).start();
        return this;
    }

    public void printScriptLogs() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    @Override
    public void close() throws Exception {
        process.destroy();
    }
}
