package com.github.blamevic.steph.common.handlers.messages;

import vic.mod.chat.api.bot.IBotHandler;
import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HiMessage extends AbstractMessageHandler
{

    public HiMessage(IBotHandler botHandler)
    {
        super(botHandler);
    }

    @Override
    public List<String> getMessageNames()
    {
        ArrayList<String> temp = new ArrayList<String>();
        Collections.addAll(temp, new String[]{"hi", "hey"});
        return temp;
    }

    @Override
    public void processMessage(String message, String match, IChatEntity sender,
                               IChannelBase channel)
    {

        String name = "Master";
        if (sender.getNickname() == null || (!sender.isOperator())) name = sender.getUsername();
        else if (!(sender.getNickname().equals("Vic") || (sender.getNickname().equals("Jibril"))))
            name = sender.getNickname();
        this.botHandler.sendGlobalMessage("Hey " + name + "!");

    }

}