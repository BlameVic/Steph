package eu.thog92.steph.irc;

import java.io.IOException;

public interface IClient {

	public void connect() throws IOException;
	
	public void login() throws IOException;
	
	public void join(String channel);
	
	public void quit(String reason);
	
	public void sendToChat(String channel, String message);
	
	public void writeToBuffer(String buffer);
	
	public String getLastLine();

	public void pong(String id, boolean format);
	
	public String getChannel();

    public void setServerPassword(String pass);
}
