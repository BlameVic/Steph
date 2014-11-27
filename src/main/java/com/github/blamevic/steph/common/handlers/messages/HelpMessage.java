package com.github.blamevic.steph.common.handlers.messages;

import vic.mod.chat.api.bot.IBotHandler;
import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpMessage extends AbstractMessageHandler {

    public HelpMessage(IBotHandler botHandler) {
        super(botHandler);
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<String> getMessageNames() {
        // TODO Auto-generated method stub
        ArrayList<String> temp = new ArrayList<String>();
        Collections.addAll(temp, new String[]{"help"});
        return temp;
    }

    @Override
    public void processMessage(String message, String match, IChatEntity sender,
                               IChannelBase channel) {
        botHandler.sendGlobalMessage("Help ? Not yet sorry");
    }

}
