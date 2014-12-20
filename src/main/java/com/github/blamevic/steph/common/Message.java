package com.github.blamevic.steph.common;

import com.github.blamevic.irc.IRCMessageParser;

import static com.github.blamevic.irc.IRCMessageParser.*;

public class Message {
    public String message;
    public String target;
    public String sender;
    public Boolean isPrivateMessage;

    public Message(String message, String target, String sender, Boolean isPrivateMessage) {
        this.message = message;
        this.target = target;
        this.sender = sender;
        this.isPrivateMessage = isPrivateMessage;
    }

    public static Message fromIRCMessage(IRCMessageParser.Message message)
    {
        PrivateMessage privateMessage = parsePrivateMessage(message);

        if (privateMessage != null)
            return fromPrivmsg(privateMessage);
        else
            return null;
    }

    public static Message fromPrivmsg(PrivateMessage privmsg)
    {
        return new Message(privmsg.message, privmsg.target, privmsg.prefix.name, !privmsg.targetIsAChannel());
    }
}
