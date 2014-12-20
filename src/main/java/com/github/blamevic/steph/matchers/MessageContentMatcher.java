package com.github.blamevic.steph.matchers;

import com.github.blamevic.event.EventMatcherBase;
import com.github.blamevic.event.IEvent;
import com.github.blamevic.steph.common.ChatEvent;

public class MessageContentMatcher extends EventMatcherBase {
    String match;

    public MessageContentMatcher(String match) {
        this.match = match;

        addEventMatcher(new ChatMessageMatcher());
    }

    @Override
    public boolean subMatch(IEvent event) {
        return ((ChatEvent)event).message.content.contains(this.match);
    }
}
