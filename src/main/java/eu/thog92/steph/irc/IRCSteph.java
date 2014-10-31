package eu.thog92.steph.irc;

import eu.thog92.irc.IRCClient;
import eu.thog92.steph.common.ChatEvent;
import eu.thog92.steph.common.ISteph;
import eu.thog92.steph.common.exceptions.InvalidConfigException;

import java.util.List;
import java.util.Map;

public class IRCSteph implements ISteph {
    IRCClient client;
    Map<String, String> config;

    public void sendMessage(String message, String channel) {
        //
    }

    public void sendPrivateMessage(String message, String user) {
        //
    }

    public ChatEvent getCurrentEvent() {
        return null;
    }

    public boolean moveNextEvent() {
        return false;
    }

    public List<String> getChannels() {
        return null;
    }

    public void joinChannel(String channel) {
        //
    }

    public void leaveChannel(String channel) {
        //
    }

    public void setMainChannel(String channel) {
        //
    }

    public String getMainChannel() {
        return null;
    }

    public void connect() {
        //
    }

    public void disconnect() {
        //
    }

    public void setConfig(Map<String, String> config) throws InvalidConfigException {
        String  hostname = config.get("hostname");
        int     port     = Integer.parseInt(config.get("port"));
        String  username = config.get("username");
        boolean debug    = Boolean.parseBoolean(config.get("debug"));

        if (hostname == null)
            throw new InvalidConfigException("Parameter hostname not set!");

        if (username == null)
            throw new InvalidConfigException("Parameter username not set!");

        this.config = config;

        this.client = new IRCClient(hostname, port, username, debug);

    }

    public String getName() {
        return "IRCSteph";
    }

    public Map<String, String> getConfig() {
        return null;
    }

    public String parseLines() {
        while (true) {
            String line = client.readLine();
            if (line != null) {
                processLine(line);
            }
        }
    }

    public ChatEvent processLine(String line) {
        if (client.processPing(line))
            return null;

        String nick = client.getNick(line);
        String message = client.getMessageBody(line);
        String channel;

        return new ChatEvent(new IRCSteph(), message, channel, nick);
    }
}
