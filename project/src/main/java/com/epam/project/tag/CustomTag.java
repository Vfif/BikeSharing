package com.epam.project.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class CustomTag extends TagSupport {
    private String login;
    private boolean status;
    private String value;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<tr><td>" + login + "</td><td>");
            out.write("<input type=\"hidden\" name=\"login\" value=\"" + login + "\"/>");
            out.write("<input type=\"hidden\" name=\"status\" value=\"" + status + "\"/>");
            out.write("<input type=\"submit\" name=\"button\" value =\""+ value +"\" ");
            if (status) {
                out.write("style=\"background: green\"");
            } else {
                out.write("style=\"background: red\"");
            }
            out.write("</td></tr>");

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
