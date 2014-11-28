package com.github.blamevic.steph.common;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StephController
{
    Map config;

    List<IMessageHandler> handlers = new ArrayList<>();
    List<ISteph> stephs = new ArrayList<>();

    public StephController(String configFileName)
    {
        this.config = loadConfig(configFileName);

        Map<String, Map<String, Object>> stephConfigs = (Map) config.get("stephs");

        for (Entry<String, Map<String, Object>> stephConfig : stephConfigs.entrySet())
        {
            ISteph steph = loadSteph(stephConfig.getKey());
            setupSteph(steph, stephConfig.getValue());
            this.stephs.add(steph);
        }

        Map<String, Map<String, Object>> matcherConfigs = (Map) config.get("handlers");

        for (Entry<String, Map<String, Object>> matcherConfig : matcherConfigs.entrySet())
        {
            IMessageHandler handler = loadHandler(matcherConfig.getKey());
            this.handlers.add(handler);
        }
    }

    public void poll()
    {
        List<ChatEvent> events = new ArrayList<>();

        for (ISteph steph : stephs)
        {
            while (steph.moveNextEvent())
            {
                events.add(steph.getCurrentEvent());
            }
        }

        for (ChatEvent event : events)
        {
            for (IMessageHandler handler : handlers)
            {
                for (IEventMatcher matcher : handler.getEventMatchers())
                {
                    if (matcher.match(event))
                    {
                        handler.processEvent(event);
                        break;
                    }
                }
            }
        }
    }

    public void startPoll()
    {
        while (true)
        {
            poll();
            try { Thread.sleep(50); } catch (InterruptedException e) {}
        }
    }

    private Map loadConfig(String filename)
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

        return (Map) yaml.load(configFileStream);
    }

    private ISteph loadSteph(String className)
    {
        try
        {
            Class stephClass = Class.forName(className);

            //If stephClass implements ISteph
            if (ISteph.class.isAssignableFrom(stephClass))
            {
                try
                {
                    return (ISteph) stephClass.newInstance();
                } catch (Exception e)
                {
                    System.err.println("Error instantiating " + className + ":");
                    e.printStackTrace();
                    System.exit(4);
                }
            } else
            {
                System.err.println("Class " + className + " dis not assignable to ISteph");
                System.exit(3);
            }
        } catch (ClassNotFoundException ex)
        {
            System.err.println("Class not found:" + className);
            System.exit(2);
        }
        return null; //This will never happen!
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
            System.err.println("Invalid config for " + steph.getName() + ":");
            e.printStackTrace();
            System.exit(5);
        }
        steph.connect();
    }

    private IMessageHandler loadHandler(String className)
    {
        try
        {
            Class handlerClass = Class.forName(className);

            //If handlerClass implements ISteph
            if (IMessageHandler.class.isAssignableFrom(handlerClass))
            {
                try
                {
                    return (IMessageHandler) handlerClass.newInstance();
                } catch (Exception e)
                {
                    System.err.println("Error instantiating " + className + ":");
                    e.printStackTrace();
                    System.exit(8);
                }
            } else
            {
                System.err.println("Class " + className + " is not assignable to IMessageHandler");
                System.exit(7);
            }
        } catch (ClassNotFoundException ex)
        {
            System.err.println("Class not found:" + className);
            System.exit(6);
        }
        return null; //This will never happen!
    }
}
