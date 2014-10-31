package eu.thog92.steph.irc;

import eu.thog92.steph.common.StephController;

/**
 * Created by rx14 on 30/10/14.
 */
public class Main {
    public static void main(String[] args) {
        String configFile = args[0];

        StephController stephController = new StephController(configFile, new IRCSteph());
        stephController.startPoll();
    }
}
