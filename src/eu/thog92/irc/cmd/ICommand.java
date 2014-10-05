package eu.thog92.irc.cmd;

public interface ICommand {
	
	public String getCommandName();
	
	public void processCommand(String username, String[] args);
}
