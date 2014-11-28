package com.github.blamevic.steph.matchers;

import com.github.blamevic.irc.IRCMessageParser;
import com.github.blamevic.steph.common.ChatEvent;
import com.github.blamevic.steph.common.IEventMatcher;

public class ContainsMatcher implements IEventMatcher
{
    String match;

    public ContainsMatcher(String match)
    {
        this.match = match;
    }

    @Override
    public boolean match(ChatEvent event)
    {
        return IRCMessageParser.parsePrivateMessage(event.message).message.contains(match);
    }
}
