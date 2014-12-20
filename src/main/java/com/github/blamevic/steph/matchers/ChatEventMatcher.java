package com.github.blamevic.steph.matchers;

import com.github.blamevic.event.IEvent;
import com.github.blamevic.event.IEventMatcher;
import com.github.blamevic.steph.common.ChatEvent;

public class ChatEventMatcher implements IEventMatcher {
    @Override
    public boolean match(IEvent event) {
        return event instanceof ChatEvent;
    }
}
