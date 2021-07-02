/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author dinhd
 */
public class QuestionEntity {
    private int id;
    private String content;
    private int userId;
    private String date;
    private Vector<AnswerEntity> answer;
    public QuestionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  

    public Vector
        <AnswerEntity> getAnswer() {
        return answer;
    }

    public void setAnswer(Vector<AnswerEntity> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" + "id=" + id + ", content=" + content + ", userId=" + userId + ", date=" + date + ", answer=" + answer + '}';
    }
    
}
