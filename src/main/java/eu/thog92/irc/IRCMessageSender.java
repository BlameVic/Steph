package eu.thog92.irc;

public class IRCMessageSender {
    IRCClient client;

    public IRCMessageSender(IRCClient client) {
        this.client = client;
    }

    public void privateMessage(String message, String destination) {
        String PRIVMSG = "PRIVMSG " + destination + " :" + message;

        client.writeLine(PRIVMSG);
    }
}
