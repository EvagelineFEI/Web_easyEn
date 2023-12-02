package com.easyen.easyenglish.dto;

import java.util.List;

public class gptResponse {
    private List<Choice> choices;

    // constructors, getters and setters

    public List<Choice> getChoices(){
      return choices;
    }

    public static class Choice {
        private int index;
        private Message message;
        // constructors, getters and setters
        public Choice(int index,Message meg){
            this.index = index;
            message =meg;
        }
        public int getIndex() {
            return index;
        }
        public Message getMessage() {
            return message;
        }
        public void setIndex(int index) {
            this.index = index;
        }
        public void setMessage(Message message) {
            this.message = message;
        }

    }
}
