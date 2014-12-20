package com.github.blamevic.steph.processors;

import com.github.blamevic.event.EventProcessorBase;
import com.github.blamevic.steph.common.ChatEvent;
import com.github.blamevic.event.IEvent;

public class HelloProcessor extends EventProcessorBase
{
    public HelloProcessor()
    {
        //addEventMatcher(new ContainsMatcher("Hey Steph!"));
    }

    @Override
    public void processEvent(IEvent event)
    {
        //event.sendMessage("Hey!");
    }
}
