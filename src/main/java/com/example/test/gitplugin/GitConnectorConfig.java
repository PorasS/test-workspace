package com.example.test.gitplugin;

public class GitConnectorConfig {
    private String connector;
    private String typpe;
    private Integer status;

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getTyppe() {
        return typpe;
    }

    public void setTyppe(String typpe) {
        this.typpe = typpe;
    }

    @Override
    public String toString() {
        return "GitConnectorConfig{" +
                "connector='" + connector + '\'' +
                ", typpe='" + typpe + '\'' +
                '}';
    }
}
