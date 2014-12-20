package com.github.blamevic.steph.common;

import com.github.blamevic.event.IEvent;
import com.github.blamevic.event.IEventMatcher;

public class ChatEvent implements IEvent
{
    public ISteph receiver;
    public Message message;
    public StephController controller;
    public IEventMatcher matcher;

    public ChatEvent(ISteph receiver, Message message, StephController controller, IEventMatcher matcher)
    {
        this.receiver = receiver;
        this.message = message;
        this.controller = controller;
        this.matcher = matcher;
    }

    public void sendMessage(String message, String target)
    {
        receiver.sendMessage(message, target);
    }

    public void sendMessage(String message)
    {
        sendMessage(message, this.message.getReplyAddress());
    }

    @Override
    public String toString()
    {
        return "ChatEvent{" +
                "receiver=" + receiver +
                ", message=" + message +
                ", controller=" + controller +
                ", matcher=" + matcher +
                '}';
    }
}
