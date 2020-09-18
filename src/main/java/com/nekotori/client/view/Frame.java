package com.nekotori.client.view;

import javax.swing.*;


public class Frame extends JFrame {

    public static class builder{

        private final Frame target;

        public builder(){
            this.target = new Frame();
        }
        public builder tittle(String tittle){
            this.target.setTitle(tittle);
            return this;
        }
        public builder size(int width, int height){
            this.target.setSize(width,height);
            return this;
        }
        public builder visible(boolean isVisible){
            this.target.setVisible(isVisible);
            return this;
        }
        public Frame build(){
            return this.target;
        }
    }
}
