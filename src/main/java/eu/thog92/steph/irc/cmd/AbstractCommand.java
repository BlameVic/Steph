package eu.thog92.steph.irc.cmd;

import eu.thog92.steph.irc.Client;

public abstract class AbstractCommand implements ICommand {
	
	protected Client irc;
	
	public AbstractCommand(Client irc)
	{
		this.irc = irc;
	}
}
