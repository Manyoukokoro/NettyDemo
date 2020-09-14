package com.nekotori.other;

import ch.qos.logback.core.net.SyslogOutputStream;

public class Solution2 {
    public String reverseWords(String s) {
        String[] ss= s.trim().split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(String sss:ss){
            if(!sss.equals(""))stringBuilder.append(sss).append(" ");
        }
        stringBuilder.replace(stringBuilder.lastIndexOf(" "),stringBuilder.lastIndexOf(" ")+1,"");
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().reverseWords("this is a student."));
    }
}
