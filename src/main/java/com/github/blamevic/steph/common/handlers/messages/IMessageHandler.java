package com.github.blamevic.steph.common.handlers.messages;

import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

import java.util.List;

public interface IMessageHandler {

    public List<String> getMessageNames();

    public void processMessage(String message, String match, IChatEntity sender,
                               IChannelBase channel);
}
