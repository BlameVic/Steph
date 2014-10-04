package eu.thog92.vchat.steph.messages;

import java.util.List;

import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

public interface IMessageHandler {

	public List<String> getMessageNames();

	public void processMessage(String message, IChatEntity sender,
			IChannelBase channel);
}
