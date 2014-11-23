package eu.thog92.steph.irc;

import eu.thog92.irc.IRCClient;
import eu.thog92.irc.IRCMessageParser;
import eu.thog92.steph.common.StephController;

public class Main {
    public static void main(String[] args) throws Exception {
        String configFile = args[0];

        StephController controller = new StephController(configFile, new IRCSteph());
        controller.startPoll();
    }
}
