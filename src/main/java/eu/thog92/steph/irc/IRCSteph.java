package eu.thog92.steph.irc;

import eu.thog92.steph.common.ChatEvent;
import eu.thog92.steph.common.ISteph;
import eu.thog92.steph.common.exceptions.InvalidConfigException;

import java.util.List;
import java.util.Map;

/**
 * Created by rx14 on 30/10/14.
 */
public class IRCSteph implements ISteph {
    public IRCSteph() {

    }

    public void sendMessage(String message, String channel) {

    }

    public void sendPrivateMessage(String message, String user) {

    }

    public ChatEvent getNextEvent() {
        return null;
    }

    public boolean hasNextEvent() {
        return false;
    }

    public List<String> getChannels() {
        return null;
    }

    public void joinChannel(String channel) {

    }

    public void leaveChannel(String channel) {

    }

    public void setMainChannel(String channel) {

    }

    public String getMainChannel() {
        return null;
    }

    public void connect() {

    }

    public void disconnect() {

    }

    public String getName() {
        return "IRCSteph";
    }

    public void setConfig(Map<String, String> config) throws InvalidConfigException {

    }

    public Map<String, String> getConfig() {
        return null;
    }
}
