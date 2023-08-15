package com.test.common;

import com.test.common.config.DemoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import javax.management.MBeanServer;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@EnableConfigurationProperties({DemoConfig.class})
abstract public class ApplicationStarter {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationStarter.class);

    private ConfigurableApplicationContext applicationContext;

    public void start(String[] args) {
        try {
            applicationContext = SpringApplication.run(getClass(), args);

            Runtime.getRuntime().addShutdownHook(new ShutdownHook());

            waitForCommand();
        } catch (Exception e) {
            logger.error("system start error:{}", e.getMessage());
        }
    }

    public void waitForCommand() throws IOException {
        final ServerSocket serverSocket;
        DemoConfig demoConfig = applicationContext.getBean(DemoConfig.class);
        try {
            serverSocket = new ServerSocket(demoConfig.getShutdownPort(), 1, InetAddress.getByName("127.0.0.1"));
            logger.info("Waiting for shutdown command on 127.0.0.1, port: {}", demoConfig.getShutdownPort());
        } catch (IOException e) {
            logger.error("system server socket start error:{}", e.getMessage());
            System.exit(1);
            return;
        }

//        String shutdownCmd = testProperties.getShutdownCommand();
//        String aliveCmd = typhoonConfig.getAliveCommand();
//        String restartCmd = typhoonConfig.getRestartCommand();
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket = null;
            InputStream stream = null;
            try {
                socket = serverSocket.accept();
                socket.setSoTimeout(10 * 1000);
                stream = socket.getInputStream();
            } catch (IOException e) {
//                LOGGER.error("Cannot establish connection.", e);
                System.exit(1);
            }

            StringBuilder buff = new StringBuilder();
            int expected = 1024; // Cut off to avoid DoS attack
            while (expected > 0) {
                int ch = -1;
                try {
                    ch = stream.read();
                } catch (IOException e) {
//                    LOGGER.error("Server.await: read: ", e);
                }
                if (ch < 32) {// Control character or EOF terminates loop
                    break;
                }
                buff.append((char) ch);
                expected--;
            }
            try {
                stream.close();
                socket.close();
            } catch (IOException ignore) {
            }

            String command = buff.toString();
            if (command.equals("stop")) {
                MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

                serverSocket.close();
                break;
            } else {
                logger.info("Invalid command: '{}' received ", command);
            }
        }

    }

    public static class ShutdownHook extends Thread {
        @Override
        public void run() {
            logger.info("system exit by shutdownHook");
        }
    }
}
