package com.github.blamevic.steph.processors;

import com.github.blamevic.event.EventProcessorBase;
import com.github.blamevic.steph.common.ChatEvent;
import com.github.blamevic.event.IEvent;
import com.github.blamevic.steph.matchers.MessageContentMatcher;

public class HelloProcessor extends EventProcessorBase
{
    public HelloProcessor()
    {
        addEventMatcher(new MessageContentMatcher("Hey Steph!"));
    }

    @Override
    public void processEvent(IEvent event)
    {
        ((ChatEvent)event).sendMessage("Hey!");
    }
}
