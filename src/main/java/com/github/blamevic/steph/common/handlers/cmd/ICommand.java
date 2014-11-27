package com.github.blamevic.steph.common.handlers.cmd;

public interface ICommand
{

    public String getCommandName();

    public void processCommand(String username, String[] args);
}
