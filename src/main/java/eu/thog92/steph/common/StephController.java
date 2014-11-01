package eu.thog92.steph.common;

import eu.thog92.steph.common.exceptions.InvalidConfigException;
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

    public StephController(String configFile, ISteph... stephs) {
        for (ISteph steph : stephs) {
            this.stephs.add(steph);
        }

        Yaml yaml = new Yaml();
        InputStream configFileStream;

        try {
            configFileStream = new FileInputStream(new File(configFile));
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found: " + configFile);
            System.exit(1);
            configFileStream = null;
        }

        config = (Map)yaml.load(configFileStream);

        for (ISteph steph : stephs) {
            Map<String, String> stephConfig = (Map<String, String>)config.get(steph.getName());

            System.out.println("Config for: " + steph.getName());
            System.out.println(stephConfig.toString());

            try {
                steph.setConfig(stephConfig);
            } catch (InvalidConfigException e) {
                System.out.println("Invalid config in \"" + configFile + "\":");
                e.printStackTrace();
                System.exit(2);
            }
            steph.connect();
        }
    }

    /**
     * Recuring method that performs polling operations on the active steph
     * @return Returns true when the bot should exit and disconnect.
     */
    public boolean poll() {
        boolean shouldStop = false;
        for (ISteph steph : stephs) {
            while (steph.moveNextEvent()) {
                ChatEvent event = steph.getCurrentEvent();
            }
        }

        // steph.disconnect();
        // return true;
        return shouldStop;
    }

    public void startPoll() {
        while (poll()) {
            try { Thread.sleep(50); } catch (InterruptedException e) { }
        }
        System.out.println("Shutting down...");
    }
}
