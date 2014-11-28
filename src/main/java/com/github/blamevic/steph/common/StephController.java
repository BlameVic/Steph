package com.github.blamevic.steph.common;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StephController
{
    Map config;

    List<IMessageHandler> handlers;
    List<ISteph> stephs;

    public StephController(String configFileName)
    {

        loadConfig(configFileName);

        loadStephs();
    }

    /**
     * Recuring method that performs polling operations on the active steph
     *
     * @return Returns true when the bot should exit and disconnect.
     */
    public boolean poll()
    {
        boolean shouldStop = false;
        for (ISteph steph : stephs)
        {
            while (steph.moveNextEvent())
            {
                ChatEvent event = steph.getCurrentEvent();
                System.out.println(event.toString());
            }
        }

        // steph.disconnect();
        // return true;
        return shouldStop;
    }

    public void startPoll()
    {
        while (!poll())
        {
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
            }
        }
        System.out.println("Shutting down...");
    }

    private void loadConfig(String filename)
    {
        Yaml yaml = new Yaml();
        InputStream configFileStream;

        try
        {
            configFileStream = new FileInputStream(new File(filename));
        } catch (FileNotFoundException e)
        {
            System.out.println("Config file not found: " + filename);
            System.exit(1);
            configFileStream = null;
        }

        this.config = (Map) yaml.load(configFileStream);
    }

    private void loadStephs()
    {
        Map<String, Map<String, Object>> stephConfig = (Map) config.get("stephs");

        for (Entry<String, Map<String, Object>> steph : stephConfig.entrySet())
        {
            String className = steph.getKey();
            try
            {
                Class stephClass = Class.forName(className);
                //If stephClass implements ISteph
                if (Arrays.asList(stephClass.getInterfaces()).contains(ISteph.class))
                {
                    try
                    {
                        ISteph stephInstance = (ISteph) stephClass.newInstance();

                        setupSteph(stephInstance, steph.getValue());

                        this.stephs.add(stephInstance);
                    } catch (Exception e)
                    {
                        System.err.println("Error initialising " + className + ":");
                        e.printStackTrace();
                    }
                }
            } catch (ClassNotFoundException ex)
            {
                System.out.println("Class not found:" + className);
            }
        }
    }

    private void setupSteph(ISteph steph, Map<String, Object> config)
    {
        System.out.print("Config for: " + steph.getName() + ": ");
        System.out.println(config.toString());

        steph.setController(this);

        try
        {
            steph.setConfig(config);
        } catch (InvalidConfigException e)
        {
            System.out.println("Invalid config for " + steph.getName() + ":");
            e.printStackTrace();
            System.exit(2);
        }
        steph.connect();
    }
}
