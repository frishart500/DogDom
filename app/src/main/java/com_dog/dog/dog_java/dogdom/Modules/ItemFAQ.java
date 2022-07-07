package com_dog.dog.dog_java.dogdom.Modules;

public class ItemFAQ {

    private String question, answer;
    int numOfQ;

    public ItemFAQ(String question/*, String numOfQ*/, String answer) {
        this.question = question;
        numOfQ++;
        this.answer = answer;
    }

    public int getNumOfQ() {
        return numOfQ;
    }

    public void setNumOfQ(int numOfQ) {
        this.numOfQ = numOfQ;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
