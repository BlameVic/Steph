package com.github.blamevic.steph.processors;

import com.github.blamevic.event.EventProcessorBase;
import com.github.blamevic.event.IEvent;
import com.github.blamevic.event.IEventMatcher;

import java.util.List;

public class JoinProcessor extends EventProcessorBase
{
    public JoinProcessor()
    {
        addEventMatcher();
    }

    @Override
    public void processEvent(IEvent event, List<IEventMatcher> matchedMatchers)
    {

    }
}
