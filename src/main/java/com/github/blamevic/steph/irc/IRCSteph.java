package com.github.blamevic.steph.irc;

import com.github.blamevic.event.IEvent;
import com.github.blamevic.irc.IRCClient;
import com.github.blamevic.irc.IRCMessageParser;
import com.github.blamevic.steph.common.ChatEvent;
import com.github.blamevic.steph.common.ISteph;
import com.github.blamevic.steph.common.InvalidConfigException;
import com.github.blamevic.steph.common.StephController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IRCSteph implements ISteph
{
    StephController controller = null;
    Map<String, Object> config;

    IRCClient client;
    String mainChannel;

    List<ChatEvent> eventQueue = new ArrayList<>();

    public void sendMessage(String message, String channel)
    {
        client.sender.privateMessage(message, channel);
    }

    public void sendPrivateMessage(String message, String user)
    {
        client.sender.privateMessage(message, user);
    }

    public IEvent getCurrentEvent()
    {
        if (eventQueue.size() == 0) return null;
        return eventQueue.get(0);
    }

    public boolean moveNextEvent()
    {
        if (eventQueue.size() == 0) parseLines();
        else eventQueue.remove(0);

        return eventQueue.size() > 0;
    }

    public List<String> getChannels()
    {
        return client.channels;
    }

    public void joinChannel(String channel)
    {
        client.joinChannel(channel);
    }

    public void leaveChannel(String channel)
    {
        client.leaveChannel(channel);
    }

    public boolean connect()
    {
        try
        {
            client.connect();
            client.login();
            return true;
        } catch (IOException e)
        {
            return false;
        }
    }

    public void disconnect()
    {
        client = null;
    }

    public void setConfig(Map<String, Object> config) throws InvalidConfigException
    {
        String  hostname = config.get("hostname").toString();
        int     port     = Integer.parseInt(config.get("port").toString());
        String  username = config.get("username").toString();
        String  realname = config.get("realname").toString();
        boolean debug    = Boolean.parseBoolean(config.get("debug").toString());

        if (hostname == null)
            throw new InvalidConfigException("Parameter hostname not set!");

        if (username == null)
            throw new InvalidConfigException("Parameter username not set!");

        if (realname == null)
            throw new InvalidConfigException("Parameter realname not set!");

        this.config = config;

        this.client = new IRCClient(hostname, port, username, realname, debug);

    }

    public Map<String, Object> getConfig()
    {
        return config;
    }

    public void setController(StephController controller)
    {
        if (this.controller == null)
        {
            this.controller = controller;
        } else
        {
            throw new IllegalStateException("This Steph allready has a set controller");
        }
    }

    public void parseLines()
    {
        while (true)
        {
            String line = client.readLine();
            if (line != null)
            {
                ChatEvent event = processLine(line);
                if (event != null) eventQueue.add(event);
            } else
            {
                break;
            }
        }
    }

    public ChatEvent processLine(String line)
    {
        if (client.processPing(line))
            return null;

        IRCMessageParser.Message message = IRCMessageParser.parseMessage(line);

        return new ChatEvent(this, message, this.controller, null);
    }
}
