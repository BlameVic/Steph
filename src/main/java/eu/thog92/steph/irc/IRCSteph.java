package eu.thog92.steph.irc;

import eu.thog92.irc.IRCClient;
import eu.thog92.irc.IRCMessageParser;
import eu.thog92.steph.common.ChatEvent;
import eu.thog92.steph.common.ISteph;
import eu.thog92.steph.common.StephController;
import eu.thog92.steph.common.exceptions.InvalidConfigException;

import java.util.List;
import java.util.Map;

public class IRCSteph implements ISteph {
    IRCClient           client;
    Map<String, String> config;
    StephController controller = null;

    public void sendMessage(String message, String channel) {
        client.sendMessage(message, channel);
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
        String  realname = config.get("realname");
        boolean debug    = Boolean.parseBoolean(config.get("debug"));

        if (hostname == null)
            throw new InvalidConfigException("Parameter hostname not set!");

        if (username == null)
            throw new InvalidConfigException("Parameter username not set!");

        if (realname == null)
            throw new InvalidConfigException("Parameter realname not set!");

        this.config = config;

        this.client = new IRCClient(hostname, port, username, realname, debug);

    }

    public String getName() {
        return "IRCSteph";
    }

    public Map<String, String> getConfig() {
        return null;
    }

    public void setController(StephController controller) {
        if (this.controller == null) {
            this.controller = controller;
        } else {
            throw new IllegalStateException("This Steph allready has a set controller");
        }
    }

    public void parseLines() {
        while (true) {
            String line = client.readLine();
            if (line != null) {
                processLine(line);
            } else {
                break;
            }
        }
    }

    public ChatEvent processLine(String line) {
        if (client.processPing(line))
            return null;

        IRCMessageParser.Message message = IRCMessageParser.parseMessage(line);

        return new ChatEvent(this, message, this.controller, null);
    }
}
