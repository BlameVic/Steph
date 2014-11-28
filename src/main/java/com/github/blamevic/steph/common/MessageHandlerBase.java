package com.github.blamevic.steph.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MessageHandlerBase implements IMessageHandler
{
    List<IEventMatcher> matchers = new ArrayList<>();

    @Override
    public List<IEventMatcher> getEventMatchers()
    {
        return matchers;
    }

    protected void addEventMatcher(IEventMatcher... eventMatchers)
    {
        matchers.addAll(Arrays.asList(eventMatchers));
    }
}
