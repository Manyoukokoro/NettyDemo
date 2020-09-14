package com.nekotori.other;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public static void main(String[] args) {
        String[] s= {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        new Solution().evalRPN(s);
    }

    public int evalRPN(String[] tokens) {

        Deque<Integer> stack = new ArrayDeque<Integer>();
        for(String s:tokens){
            char ch = s.charAt(0);
                switch(ch){
                    case '+' : stack.push(Solution.add(stack.pop(),stack.pop()));
                        break;
                    case '-' :
                        if(s.length()==1)
                            stack.push(Solution.minu(stack.pop(),stack.pop()));
                        else
                            stack.push(Integer.valueOf(s));
                        break;
                    case '*' : stack.push(Solution.mul(stack.pop(),stack.pop()));
                        break;
                    case '/' : stack.push(Solution.dev(stack.pop(),stack.pop()));
                        break;
                    default : stack.push(Integer.valueOf(s));
                        break;
            }
        }
        return stack.pop();
    }

    public static int add(int a, int b){
        return a+b;
    }

    public static int minu(int a, int b){
        return b-a;
    }

    public static int mul(int a, int b){
        return a*b;
    }

    public static int dev(int a, int b){
        return b/a;
    }
}