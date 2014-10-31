package eu.thog92.steph.common.handlers.cmd;

public interface ICommand {
	
	public String getCommandName();
	
	public void processCommand(String username, String[] args);
}
