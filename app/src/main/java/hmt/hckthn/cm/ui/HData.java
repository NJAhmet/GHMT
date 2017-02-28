package hmt.hckthn.cm.ui;


import java.io.Serializable;
import java.util.List;

public class HData implements Serializable
{
    private String inviteCode;
    private int code;
    private String msg;
    private List<HElement> elements;

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
    public List<HElement> getElements()
    {
        return elements;
    }
}
