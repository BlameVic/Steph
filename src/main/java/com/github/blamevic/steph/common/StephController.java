package com.github.blamevic.steph.common;

import com.github.blamevic.steph.common.exceptions.InvalidConfigException;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class StephController {
    Map config;

    List<IMessageHandler> handlers;
    List<ISteph>          stephs;

    public StephController(String configFileName) {

        loadConfig(configFileName);

        Object StephConfig = config.get("stephs");
        System.out.println(StephConfig.toString());

        for (ISteph steph : stephs) {
            Map<String, Object> stephConfig = (Map<String, Object>) config.get(steph.getName());

            System.out.print("Config for: " + steph.getName() + ": ");
            System.out.println(stephConfig.toString());

            steph.setController(this);

            try {
                steph.setConfig(stephConfig);
            } catch (InvalidConfigException e) {
                System.out.println("Invalid config in \"" + configFileName + "\":");
                e.printStackTrace();
                System.exit(2);
            }
            steph.connect();
        }
    }

    /**
     * Recuring method that performs polling operations on the active steph
     *
     * @return Returns true when the bot should exit and disconnect.
     */
    public boolean poll() {
        boolean shouldStop = false;
        for (ISteph steph : stephs) {
            while (steph.moveNextEvent()) {
                ChatEvent event = steph.getCurrentEvent();
                System.out.println(event.toString());
            }
        }

        // steph.disconnect();
        // return true;
        return shouldStop;
    }

    public void startPoll() {
        while (!poll()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }
        }
        System.out.println("Shutting down...");
    }

    private void loadConfig(String filename) {
        Yaml yaml = new Yaml();
        InputStream configFileStream;

        try {
            configFileStream = new FileInputStream(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found: " + filename);
            System.exit(1);
            configFileStream = null;
        }

        this.config = (Map) yaml.load(configFileStream);
    }
}
