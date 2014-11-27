package com.github.blamevic.steph.common;

import java.util.List;

public interface IMessageHandler {
    public List<IEventMatcher> getEventMatchers();

    public void processEvent(ChatEvent event);
}
