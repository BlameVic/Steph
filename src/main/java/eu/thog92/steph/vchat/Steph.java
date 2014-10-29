package eu.thog92.vchat.steph;

import java.util.ArrayList;
import java.util.List;

import eu.thog92.irc.IClient;
import eu.thog92.vchat.steph.messages.BridgeMessage;
import eu.thog92.vchat.steph.messages.HelpMessage;
import eu.thog92.vchat.steph.messages.HiMessage;
import eu.thog92.vchat.steph.messages.IMessageHandler;
import eu.thog92.vchat.steph.messages.MojangStatusMessage;
import eu.thog92.vchat.steph.messages.ToIRCMessage;
import vic.mod.chat.api.bot.IBotHandler;
import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatBot;
import vic.mod.chat.api.bot.IChatEntity;

public class Steph implements IChatBot {

	private IBotHandler handler;
	private IRCHandler ircHandler;
	private List<IMessageHandler> messageHandlers;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Steph";
	}

	@Override
	public void onLoad(IBotHandler botHandler) {

		this.handler = botHandler;
		this.ircHandler = new IRCHandler();
		this.messageHandlers = new ArrayList<IMessageHandler>();
		this.messageHandlers.add(new HelpMessage(botHandler));
		this.messageHandlers.add(new HiMessage(botHandler));
		this.messageHandlers.add(new ToIRCMessage(botHandler, ircHandler));
		this.messageHandlers.add(new BridgeMessage(botHandler, ircHandler));
		
		this.messageHandlers.add(new MojangStatusMessage(botHandler));
		
	}

	@Override
	public void onMessage(String message, IChatEntity sender,
			IChannelBase channel) {

		for (IMessageHandler m : messageHandlers) {
			for (String entry : m.getMessageNames()) {
				if (message.toLowerCase().contains(entry)) {
					m.processMessage(message, entry, sender, channel);
					break;
				}
			}

		}
		// handler.sendGlobalMessage(message);
	}

	@Override
	public void onPrivateMessage(String message, IChatEntity sender) {
		
		for (IMessageHandler m : messageHandlers) {
			for (String entry : m.getMessageNames()) {
				if (message.toLowerCase().contains(entry)) {
					m.processMessage(message, entry, sender, null);
					break;
				}
			}

		}
	}

	@Override
	public void onServerLoad() {
		// TODO Auto-generated method stub
		new Thread(){
			@Override
			public void run() {
				ircHandler.connect();
			}
		}.start();
	}

	@Override
	public void onServerUnload() {
		// TODO Auto-generated method stub
		this.ircHandler.disconnect();
	}

	@Override
	public void onCommandMessage(String command, String[] args, String message) {
		// TODO Auto-generated method stub
		
	}

}