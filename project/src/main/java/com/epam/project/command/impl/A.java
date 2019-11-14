package com.epam.project.command.impl;

import javax.script.ScriptException;
import java.util.regex.Pattern;

public class A {
    public static void main(String[] args) throws ScriptException {
        javax.script.ScriptEngineManager se = new javax.script.ScriptEngineManager();
        javax.script.ScriptEngine engine = se.getEngineByName("js");

        String regExp = "/^(<script.*?>.*?<\\/script>)$/";
        engine.put("str", "<script>kjgkdjfg<script>");
        engine.eval("var rgx=" + regExp);
        Object value = engine.eval(
                "function validate(r, s){ return (r).test(s);};validate(rgx, str);");
        System.out.println(value);
        System.out.println(Pattern.matches("<script.*?>.*?</script>",
                "<script>gj</script>"));
    }
}