package com.teamtreehouse.techdegree;

public class Answer extends Post {
    private Question question;
    private User author;
    private String text;
    private boolean isAccepted;

    public Answer(Question question, User author, String text) {
        super(author, text);
        this.question = question;
        // Double-sided relationship
        question.addAnswer(this);
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public Question getQuestion() {
        return question;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
