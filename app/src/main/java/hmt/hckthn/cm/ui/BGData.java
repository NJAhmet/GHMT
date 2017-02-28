package hmt.hckthn.cm.ui;


import java.io.Serializable;
import java.util.List;

public class BGData implements Serializable
{
    private String inviteCode;
    private int code;
    private String msg;
    private List<BGElement> elements;

    public String getInviteCode()
    {
        return inviteCode;
    }
    public int getCode()
    {
        return code;
    }
    public void setCode(int code)
    {
        this.code = code;
    }
    public String getMessage()
    {
        return msg;
    }
    public void setMessage(String msg)
    {
        this.msg = msg;
    }
    public List<BGElement> getElements()
    {
        return elements;
    }
}
