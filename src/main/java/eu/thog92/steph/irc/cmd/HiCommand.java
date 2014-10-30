package eu.thog92.steph.irc.cmd;

import eu.thog92.steph.irc.Client;



public class HiCommand extends AbstractCommand {

	public HiCommand(Client irc) {
		super(irc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "hi";
	}

	@Override
	public void processCommand(String username, String[] args) {
		if(username.startsWith("VicNightfall"))
		{
			irc.sendToChat(irc.getChannel(), "Hey master :3");
		}
		else
		{
			irc.sendToChat(irc.getChannel(), "Hey " + username);
		}
	}

}
