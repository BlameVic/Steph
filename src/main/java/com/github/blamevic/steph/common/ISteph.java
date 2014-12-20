package com.github.blamevic.steph.common;

import com.github.blamevic.event.IEvent;

import java.util.List;
import java.util.Map;

public interface ISteph
{
    public void sendMessage(String message, String channel);
    public void sendPrivateMessage(String message, String user);

    public IEvent  getCurrentEvent();
    public boolean moveNextEvent();

    public List<String> getChannels();

    public void joinChannel(String channel);
    public void leaveChannel(String channel);

    public boolean connect();
    public void disconnect();

    public void setConfig(Map<String, Object> config) throws InvalidConfigException;
    public Map<String, Object> getConfig();

    public void setController(StephController controller);
}
