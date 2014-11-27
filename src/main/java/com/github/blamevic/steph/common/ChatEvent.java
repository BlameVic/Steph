package com.github.blamevic.steph.common;

import com.github.blamevic.irc.IRCMessageParser;

public class ChatEvent {
    com.github.blamevic.steph.common.ISteph receiver;
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

    @Override
    public String toString() {
        return "ChatEvent{" +
                "receiver=" + receiver +
                ", message=" + message +
                ", controller=" + controller +
                ", matcher=" + matcher +
                '}';
    }
}
