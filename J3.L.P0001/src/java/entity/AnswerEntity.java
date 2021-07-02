/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author dinhd
 */
public class AnswerEntity {
    private int id;
    private String content;
    private boolean value;
    private int quesionId;

    public AnswerEntity() {
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

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public int getQuesionId() {
        return quesionId;
    }

    public void setQuesionId(int quesionId) {
        this.quesionId = quesionId;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" + "id=" + id + ", content=" + content + ", value=" + value + ", quesionId=" + quesionId + '}'+"\n";
    }
    
}
