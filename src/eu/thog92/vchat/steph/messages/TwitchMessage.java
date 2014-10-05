package eu.thog92.vchat.steph.messages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.thog92.vchat.steph.IRCHandler;
import vic.mod.chat.api.bot.IBotHandler;
import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

public class TwitchMessage extends AbstractMessageHandler {

	private IRCHandler ircHandler;
	
	public TwitchMessage(IBotHandler botHandler, IRCHandler ircHandler) {
		super(botHandler);
		this.ircHandler = ircHandler;
	}

	@Override
	public List<String> getMessageNames() {
		ArrayList<String> temp = new ArrayList<String>();
		Collections.addAll(temp, new String[] {"?twitch"});
		return temp;
	}

	@Override
	public void processMessage(String message, String match, IChatEntity sender,
			IChannelBase channel) {
		
		String txt = message.substring(message.indexOf(match) + match.length());
		String name = "Master";
		if(sender.getNickname() == null) name = sender.getUsername();
		else name = sender.getNickname();
		ircHandler.sendToGlobalChat("<" + name + ">:" +  txt);
	}

}
