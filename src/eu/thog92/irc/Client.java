package eu.thog92.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements IClient {

	private String host, username;
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	private String channel;
	private int port;
	private String serverPassword;

	public Client(String host, int port, String username) {
		this.host = host;
		this.username = username;
		this.port = port;
	}

	@Override
	public void connect() throws IOException {
		this.socket = new Socket(host, port);
		this.in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		this.out = new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	}

	@Override
	public void login() throws IOException {
		System.out.println("*** Logging onto server (" + host + ":" + port
				+ ").");
		this.writeToBuffer("TWITCHCLIENT 3\r\n");
		if (this.serverPassword != null) {
			this.writeToBuffer("PASS " + this.serverPassword + "\r\n");
		}

		this.writeToBuffer("NICK " + username + "\r\n");

		//this.socket.setSoTimeout(5 * 60 * 1000);

	}

	@Override
	public void join(final String channel) {
		this.channel = channel;
		new Thread() {
			@Override
			public void run() {
				try {
					out.write("JOIN " + channel + "\r\n");
					out.flush();
				} catch (Exception e) {
					System.err.println("Error while joining " + channel);
				}
			}
		}.start();

	}

	@Override
	public void quit(String reason) {
		new Thread() {
			@Override
			public void run() {
				try {
					if (!socket.isClosed()) {
						out.write("QUIT Bye!\r\n");
						out.flush();

						socket.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	@Override
	public void sendToChat(String channel, String message) {

		try {
			out.write("PRIVMSG " + channel + " : " + message + "\r\n");
			out.flush();
		} catch (Exception e) {
			System.err.println("Error while send Chat Message " + message);
		}

	}

	@Override
	public void writeToBuffer(final String buffer) {
		new Thread() {
			@Override
			public void run() {
				try {
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					System.err.println("Error while send " + buffer);
				}
			}
		}.start();

	}

	@Override
	public String getLastLine() {
		try {
			return in.readLine();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void pong(final String id, final boolean space) {
		new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(id);
				try {
					if (space)
						out.write("PONG " + id + "\r\n");
					else
						out.write(id.replace("PING", "PONG"));
					out.flush();
				} catch (Exception e) {
					System.err.println("Error while pong " + id);
				}
			}
		}.start();

	}

	public String getChannel() {
		// TODO Auto-generated method stub
		return channel;
	}

	@Override
	public void setServerPassword(String pass) {
		this.serverPassword = pass;
	}

}
