package com.github.blamevic.steph.processors;

import com.github.blamevic.event.EventProcessorBase;
import com.github.blamevic.event.IEvent;
import com.github.blamevic.event.IEventMatcher;
import com.github.blamevic.event.matchers.ANDMatcher;
import com.github.blamevic.event.matchers.ORMatcher;
import com.github.blamevic.steph.common.ChatEvent;
import com.github.blamevic.steph.matchers.ChatEventMatcher;

import java.util.Arrays;
import java.util.List;

public abstract class ChatEventProcessorBase extends EventProcessorBase {
    @Override
    public List<IEventMatcher> getEventMatchers() {
        List<IEventMatcher> eventMatchers = super.getEventMatchers();
        return Arrays.asList(
                new ANDMatcher(
                        new ChatEventMatcher(),
                        new ORMatcher(eventMatchers.toArray(new IEventMatcher[eventMatchers.size()]))
                )
        );
    }

    @Override
    public void processEvent(IEvent event, List<IEventMatcher> matchedMatchers) {
        processChatEvent((ChatEvent)event, matchedMatchers);
    }

    public abstract void processChatEvent(ChatEvent event, List<IEventMatcher> matchedMatchers);
}
