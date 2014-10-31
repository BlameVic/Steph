package eu.thog92.steph.common.handlers.messages;

import java.util.List;

import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

public interface IMessageHandler {

	public List<String> getMessageNames();

	public void processMessage(String message, String match, IChatEntity sender,
			IChannelBase channel);
}
