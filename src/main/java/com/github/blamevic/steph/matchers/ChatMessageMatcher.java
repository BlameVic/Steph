package com.github.blamevic.steph.matchers;

import com.github.blamevic.event.EventMatcherBase;
import com.github.blamevic.event.IEvent;
import com.github.blamevic.steph.common.ChatEvent;

public class ChatMessageMatcher extends EventMatcherBase {
    public ChatMessageMatcher() {
        addEventMatcher(new ChatEventMatcher());
    }

    @Override
    public boolean subMatch(IEvent event) {
        return ((ChatEvent)event).message != null;
    }
}
