package eu.thog92.steph.common.handlers.messages;

import vic.mod.chat.api.bot.IBotHandler;

public abstract class AbstractMessageHandler implements IMessageHandler {

    protected IBotHandler botHandler;

    public AbstractMessageHandler(IBotHandler botHandler) {
        this.botHandler = botHandler;
    }
}
