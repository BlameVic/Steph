package eu.thog92.steph.common;

import eu.thog92.irc.IRCMessageParser;

public class ChatEvent {
    ISteph                   receiver;
    IRCMessageParser.Message message;
    StephController          controller;
    IEventMatcher            matcher;

    public ChatEvent(ISteph receiver, IRCMessageParser.Message message, StephController controller, IEventMatcher matcher) {
        this.receiver = receiver;
        this.message = message;
        this.controller = controller;
        this.matcher = matcher;
    }

    public void sendMessage(String message, String channel) {
        receiver.sendMessage(message, channel);
    }

    public void sendMessage(String message) {
        //sendMessage(message, this.message.);
    }
}
