package eu.thog92.steph.common.handlers.messages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.thog92.steph.vchat.IRCHandler;
import vic.mod.chat.api.bot.IBotHandler;
import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

public class BridgeMessage extends AbstractMessageHandler {

	private IRCHandler ircHandler;
	
	public BridgeMessage(IBotHandler botHandler, IRCHandler ircHandler) {
		super(botHandler);
		this.ircHandler = ircHandler;
	}
	@Override
	public List<String> getMessageNames() {
		ArrayList<String> temp = new ArrayList<String>();
		Collections.addAll(temp, new String[] {"?bridge", "bridge"});
		return temp;
	}

	@Override
	public void processMessage(String message, String match,
			IChatEntity sender, IChannelBase channel) {
		// TODO Auto-generated method stub
		String txt = message.substring(message.indexOf(match) + match.length());
		
		if(txt.contains("reconnect") && sender.isOperator())
		{
			ircHandler.disconnect();
			new Thread() {
				public void run() 
				{
					ircHandler.disconnect();
					ircHandler.connect();
				}
			}.start();
		}
	}

}
