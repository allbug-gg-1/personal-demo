package com.test.common.config;

import com.test.common.code.DatabaseMode;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "demo")
public class DemoConfig {

    private int shutdownPort = 7086;

    private int databaseMode = DatabaseMode.JPA.getCode();


    public int getShutdownPort() {
        return shutdownPort;
    }

    public void setShutdownPort(int shutdownPort) {
        this.shutdownPort = shutdownPort;
    }

    public int getDatabaseMode() {
        return databaseMode;
    }

    public void setDatabaseMode(int databaseMode) {
        this.databaseMode = databaseMode;
    }
}
