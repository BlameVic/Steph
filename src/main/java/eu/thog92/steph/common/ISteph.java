package eu.thog92.steph.common;

public interface ISteph {
    public void sendMessage(String message, String chanel);
    public void sendPrivateMessage(String message, String user);

    public String getMessage();

    public void setMainChanel(String chanel);
    public String getMainChanel();
}

