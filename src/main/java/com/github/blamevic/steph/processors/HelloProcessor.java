package com.github.blamevic.steph.processors;

import com.github.blamevic.event.EventProcessorBase;
import com.github.blamevic.event.IEventMatcher;
import com.github.blamevic.steph.common.ChatEvent;
import com.github.blamevic.event.IEvent;
import com.github.blamevic.steph.matchers.MessageContentMatcher;

import java.util.List;

public class HelloProcessor extends ChatEventProcessorBase
{
    public HelloProcessor()
    {
        addEventMatcher(new MessageContentMatcher("Hey Steph!"));
    }

    @Override
    public void processChatEvent(ChatEvent event, List<IEventMatcher> matchedMatchers)
    {
        event.sendMessage("Hey!");
    }
}
