package eu.thog92.steph.common;

import eu.thog92.steph.common.exceptions.InvalidConfigException;

import java.util.List;
import java.util.Map;

/**
 * Created by rx14 on 29/10/14.
 */
public interface ISteph {
    public void sendMessage(String message, String channel);
    public void sendPrivateMessage(String message, String user);

    public ChatEvent getNextEvent();

    public List<String> getChannels();

    public void joinChannel(String channel);
    public void leaveChannel(String channel);

    public void   setMainChannel(String channel);
    public String getMainChannel();

    public void start();
    public void stop();

    public void                setConfig(Map<String, String> config) throws InvalidConfigException;
    public Map<String, String> getConfig();
}
