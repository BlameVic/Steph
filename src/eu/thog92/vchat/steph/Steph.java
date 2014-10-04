package eu.thog92.vchat.steph;

import java.util.ArrayList;
import java.util.List;

import eu.thog92.vchat.steph.messages.HelpMessage;
import eu.thog92.vchat.steph.messages.HiMessage;
import eu.thog92.vchat.steph.messages.IMessageHandler;
import eu.thog92.vchat.steph.messages.MojangStatusMessage;
import vic.mod.chat.api.bot.IBotHandler;
import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatBot;
import vic.mod.chat.api.bot.IChatEntity;

public class Steph implements IChatBot {

	private IBotHandler handler;
	private List<IMessageHandler> messageHandlers;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Steph";
	}

	@Override
	public void onLoad(IBotHandler botHandler) {

		this.handler = botHandler;
		this.messageHandlers = new ArrayList<IMessageHandler>();
		this.messageHandlers.add(new HelpMessage(botHandler));
		this.messageHandlers.add(new HiMessage(botHandler));
		this.messageHandlers.add(new MojangStatusMessage(botHandler));
	}

	@Override
	public void onMessage(String message, IChatEntity sender,
			IChannelBase channel) {

		for (IMessageHandler m : messageHandlers) {
			for (String entry : m.getMessageNames()) {
				if (message.toLowerCase()
						.contains(this.getName().toLowerCase())
						&& message.toLowerCase().contains(entry)) {
					m.processMessage(message, sender, channel);
					break;
				}
			}

		}
		// handler.sendGlobalMessage(message);
	}

	@Override
	public void onPrivateMessage(String message, IChatEntity sender) {
		// handler.sendPrivateMessage(arg1, arg0);
	}

	@Override
	public void onServerLoad() {
		// TODO Auto-generated method stub
		System.out.println("[Steph] Server loading ...");
	}

	@Override
	public void onServerUnload() {
		// TODO Auto-generated method stub

	}

}
