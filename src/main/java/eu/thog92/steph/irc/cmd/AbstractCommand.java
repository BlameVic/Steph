package eu.thog92.irc.cmd;

import eu.thog92.irc.Client;

public abstract class AbstractCommand implements ICommand {
	
	protected Client irc;
	
	public AbstractCommand(Client irc)
	{
		this.irc = irc;
	}
}
