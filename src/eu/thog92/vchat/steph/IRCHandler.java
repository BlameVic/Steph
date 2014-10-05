package eu.thog92.vchat.steph;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import eu.thog92.irc.Client;
import eu.thog92.irc.IClient;
import eu.thog92.irc.cmd.HiCommand;
import eu.thog92.irc.cmd.ICommand;
import eu.thog92.irc.util.ClassUtil;

public class IRCHandler {
	
	private HashMap<String, ICommand> commands;
	private Client irc;
	private String master;
	private String nick;
	
	public IRCHandler()
	{
		commands = new HashMap<String, ICommand>();
		nick = "botsteph";
		irc = new Client("irc.twitch.tv", 6667, nick);
		HiCommand hi = new HiCommand(irc);
		commands.put(hi.getCommandName(), hi);
		System.out.println(commands);
	}
	
	public void connect()
	{
		
		irc.setServerPassword("oauth:MASTER");

		try {
			irc.connect();
			irc.login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
		}

		String line;
		while ((line = irc.getLastLine()) != null) {
			System.out.println(line);
			if (line.indexOf("004") >= 0) {
				
				// We are now logged in.
				break;
			} else if (line.startsWith("PING:")) {
				irc.pong(line.substring(5), true);
			} else if (line.indexOf("433") >= 0) {
				System.out.println("Nickname is already in use.");
				return;
			}
		}
		System.out.println("ICI");
		master = "#copygirl";
		irc.join("#copygirl");
		

		// Keep reading lines from the server.
		while ((line = irc.getLastLine()) != null) {
			if (line.toLowerCase().contains("ping")) {
				// We must respond to PINGs to avoid being disconnected.
				irc.pong(line, false);
			}else if (line.startsWith("PING:")) {
				irc.pong(line.substring(5), true);
			}
			else if(line.contains("JOIN"))
			{
				String username = line.substring(1, line.indexOf("!"));
				if(username.toLowerCase().equals("botsteph"))
					irc.sendToChat(master, "Hi");
				
				System.out.println(username);
			}
			else if (line.contains("PRIVMSG " + master)
					|| line.contains("PRIVMSG " + nick)) {
				System.out.println(line);
				String username = line.substring(1, line.indexOf("!"));
				String str = "PRIVMSG " + master + " :";
				str = line.substring(line.indexOf(str) + str.length());
				if (str.startsWith("?") || str.toLowerCase().contains("steph")) {

					ICommand command = null;
					for (Entry<String, ICommand> c : commands.entrySet()) {
						if (str.toLowerCase().contains(c.getKey())) {
							command = c.getValue();
							break;
						}
					}
					if (command != null)
						command.processCommand(username.toLowerCase(), new String [] {str});
//					else
//						irc.sendToChat(channel, "What do you mean?");

				}
				System.out.println("<" + username + "> " + str);
			} else {
				// Print the raw line received by the bot.
				System.out.println(line);
			}
		}
	}
	
	public void disconnect()
	{
		irc.quit("Quit");
	}

	public void sendToGlobalChat(String message) 
	{
		irc.sendToChat(master, message);
	}
	
}

