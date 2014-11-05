package eu.thog92.irc;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static eu.thog92.irc.IRCMessageParser.*;

public class IRCClient {
    private String hostname;
    private int    port;

    private String username;

    private boolean debug;

    private Socket         sock;
    private BufferedReader in;
    private BufferedWriter out;

    private List<String> channels = new ArrayList<String>();

    /**
     * Initiates an IRCClient
     * @param hostname
     * @param port
     * @param username
     * @param debug
     */
    public IRCClient(String hostname, int port, String username, boolean debug) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.debug = debug;
    }

    public IRCClient(String hostname, int port, String username) {
        this(hostname, port, username, false);
    }

    public IRCClient(String hostname, String username, boolean debug) {
        this(hostname, 6667, username, debug);
    }

    public IRCClient(String hostname, String username) {
        this(hostname, username, false);
    }

    public void connect() throws IOException {
        this.sock = new Socket(hostname, port);
        this.in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
    }

    public void login() {
        writeLine("NICK " + username);
        writeLine("USER " + username + " 0 * :Thog & RX14's IRCClient!");
    }

    /**
     * Blocks until the next command specified
     * @param command The command to watch for e.g. PING or 001
     * @return All of the messages recieved, including the one that triggered the return
     */
    public List<String> waitForCommand(String command, boolean processping) {
        List<String> lines = new ArrayList<String>();
        while (true) {
            String line = readLine();
            if (line != null) {
                lines.add(line);
                if (processping) processPing(line);
                if (getCommand(line).equals(command))
                    break;
            }
        }
        return lines;
    }

    public List<String> waitForCommand(String command) {
        return waitForCommand(command, true);
    }

    public boolean joinChannel(String channel) {
        if (writeLine("JOIN " + channel)) {
            channels.add(channel);
            return true;
        } else {
            return false;
        }
    }

    public boolean leaveChannel(String channel) {
        writeLine("PART " + channel);
        return channels.remove(channel);
    }

    public boolean sendMessage(String message, String channel) {
        return writeLine("PRIVMSG " + channel + " :" + message);
    }

    public String getNick(String line) {
        if (line.startsWith(":")) {
            int index = line.indexOf("!");
            if (index == -1) return null; //No ! was found

            String nick = line.substring(1, index);
            debug("NICK: " + nick);
            return nick;
        } else {
            return null;
        }
    }

    public String getMessageBody(String line) {
        return null;
    }

    public String getChannel(String line) {
        return null;
    }

    /**
     * Checks a string to see if it is a PING command and if so replies to it.
     * @param message A single message with no line endings.
     * @return True if the message is a PING, else false.
     */
    public boolean processPing(String message) {
        if (message.startsWith("PING")) {
            writeLine(message.replace("PING", "PONG"));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Writes a line to IRC and then flushes the buffer
     *
     * @param text A non-terminated string
     * @return True if no IOException was thrown.
     */
    public boolean writeLine(String text) {
        try {
            out.write(text + "\r\n");
            out.flush();
            debug(">> " + text);
            return true;
        } catch (IOException e) {
            System.out.println(">!> Failed to write to socket " + hostname + " :");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Reads a line from IRC
     * @return The line read, or null if an exception was thrown or the end of the stream was reached.
     */
    public String readLine() {
        String line;
        try {
            if (in.ready()) {
                line = in.readLine();
                debug("<< " + line);
            } else {
                line = null;
            }
        } catch (IOException e) {
            line = null;
            System.out.println("<!< Failed to read from socket " + hostname + " :");
            e.printStackTrace();
        }
        return line;
    }

    public void debug(String message) {
        if (debug) System.out.println(message);
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public boolean isDebug() {
        return debug;
    }

    public List<String> getChannels() {
        return channels;
    }
}
