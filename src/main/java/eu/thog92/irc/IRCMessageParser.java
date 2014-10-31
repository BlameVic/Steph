package eu.thog92.irc;

/**
 * Created by rx14 on 31/10/14.
 */
public class IRCMessageParser {
    public static Message parseMessage(String message) {
        String prefix = null;
        String command;
        String params;

        String theRest = message;

        if (message.startsWith(":")) {
            int prefixEnd = message.indexOf(" ");
            prefix = message.substring(1, prefixEnd - 1);
            theRest = message.substring(prefixEnd + 1);
        }

        int commandEnd = theRest.indexOf(" ");
        command = theRest.substring(0, commandEnd - 1);

        params = theRest.substring(commandEnd + 1);

        return new Message(prefix, command, params);
    }

    public static class Message {
        public String prefix;
        public String command;
        public String params;

        public Message(String prefix, String command, String params) {
            this.prefix = prefix;
            this.command = command;
            this.params = params;
        }
    }

    public static Prefix parsePrefix(String prefix) {
        String name;
        String user = null;
        String host = null;

        int type = 0;
        if (prefix.contains("!")) {
            type = 1;
            if (prefix.contains("@")) {
                type = 2;
            }
        }

        switch (type) {
            case 0:
                name = prefix;
                break;

            case 2:
                int hostIndex = prefix.indexOf("@");
                host = prefix.substring(hostIndex + 1);
                prefix = prefix.substring(0, hostIndex - 1);

            case 1:
                int userIndex = prefix.indexOf("!");
                name = prefix.substring(0, userIndex - 1);
                user = prefix.substring(userIndex + 1);
                break;
        }

        return new Prefix(name, user, host);
    }

    public static class Prefix {
        String name; //Either servenername or nick
        String user;
        String host;

        public Prefix(String name, String user, String host) {
            this.name = name;
            this.user = user;
            this.host = host;
        }
    }
}
