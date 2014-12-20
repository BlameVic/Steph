package com.github.blamevic.steph.common;

import com.github.blamevic.irc.IRCMessageParser;

import static com.github.blamevic.irc.IRCMessageParser.*;

public class Message {
    public String content;
    public String target;
    public String sender;
    public Boolean isPrivateMessage;

    public Message(String content, String target, String sender, Boolean isPrivateMessage) {
        this.content = content;
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
