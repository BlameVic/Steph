package com.github.blamevic.steph.handlers;

import com.github.blamevic.steph.common.MessageHandlerBase;
import com.github.blamevic.steph.common.ChatEvent;
import com.github.blamevic.steph.common.IMessageHandler;
import com.github.blamevic.steph.matchers.ContainsMatcher;

public class HelloHandler extends MessageHandlerBase
{
    public HelloHandler()
    {
        addEventMatcher(new ContainsMatcher("Hey Steph!"));
    }

    @Override
    public void processEvent(ChatEvent event)
    {
        event.sendMessage("Hey!");
    }
}
