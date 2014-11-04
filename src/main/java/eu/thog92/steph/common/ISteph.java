package eu.thog92.steph.common;

import eu.thog92.steph.common.exceptions.InvalidConfigException;

import java.util.List;
import java.util.Map;

public interface ISteph {
    public void sendMessage(String message, String channel);
    public void sendPrivateMessage(String message, String user);

    public ChatEvent getCurrentEvent();
    public boolean   moveNextEvent();

    public List<String> getChannels();

    public void joinChannel(String channel);
    public void leaveChannel(String channel);

    public void   setMainChannel(String channel);
    public String getMainChannel();

    public void connect();
    public void disconnect();

    public String getName();

    public void                setConfig(Map<String, String> config) throws InvalidConfigException;
    public Map<String, String> getConfig();

    public void setController(StephController controller);
}
