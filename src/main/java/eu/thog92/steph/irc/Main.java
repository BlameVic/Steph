package eu.thog92.steph.irc;

import eu.thog92.irc.IRCClient;
import eu.thog92.irc.IRCMessageParser;

public class Main {
    public static void main(String[] args) throws Exception {
        //String configFile = args[0];

        /*IRCSteph steph = new IRCSteph();
        Map<String,String> config = new HashMap<String, String>();
        config.put("hostname", "irc.esper.net");
        config.put("port", "6666");
        config.put("username", "StephanieDola");
        config.put("debug", "true");
        steph.setConfig(config);
        steph.connect();*/

        String channel = "#Thog";

        IRCClient client = new IRCClient("irc.esper.net", "StephanieDola", true);
        client.connect();
        client.login();
        client.waitForCommand("001");

        client.joinChannel(channel);
        client.sendMessage("Hello Master :3", channel);

        System.out.println("Entering Loop!");
        String line;
        while (true) {
            if ((line = client.readLine()) != null) {
                client.processPing(line);
                IRCMessageParser.Message message = IRCMessageParser.parseMessage(line);

                if (message.prefix != null) {
                    IRCMessageParser.Prefix prefix = message.prefix;

                    if (message.command.equals("PRIVMSG")) {
                        if (message.params.toLowerCase().contains("hi steph")) {
                            if (prefix.name.equals("VicNightfall")) {
                                client.sendMessage("Hello Master :3", channel);
                            } else {
                                client.sendMessage("Hey", channel);
                            }
                        }
                    }
                }
            }

            Thread.sleep(50);
        }
    }
}
