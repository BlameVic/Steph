package com.github.blamevic.steph.irc;

import com.github.blamevic.steph.common.StephController;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        String configFile = args[0];

        StephController controller = new StephController(configFile);
        controller.startPoll();
    }
}
