/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBConnection;
import entity.AnswerEntity;
import entity.QuestionEntity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinhd
 */
public class QuestionDAO {

    Connection con;

    public QuestionDAO() {
        con = new DBConnection().getConn();
    }

    public Vector<QuestionEntity> getListQuesion(int id) {
        Vector<QuestionEntity> listQuestion = new Vector<>();
        String sql = "SELECT * FROM dbo.Question WHERE userID = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuestionEntity question = new QuestionEntity();
                question.setId(rs.getInt(1));
                question.setContent(rs.getString(2));
                question.setUserId(rs.getInt(3));
                Date date = rs.getDate(4);
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormat = format.format(date);
                question.setDate(dateFormat);
                listQuestion.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (QuestionEntity questions : listQuestion) {
            questions.setAnswer(new QuestionDAO().getAnswer(questions.getId()));
        }
        return listQuestion;
    }
    
    public int getTotalQuestion() {
        Vector<QuestionEntity> listQuestion = new Vector<>();
        int total = 0;
        String sql = "SELECT * FROM dbo.Question";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total++;
                QuestionEntity question = new QuestionEntity();
                question.setId(rs.getInt(1));
                question.setContent(rs.getString(2));
                question.setUserId(rs.getInt(3));
                Date date = rs.getDate(4);
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormat = format.format(date);
                question.setDate(dateFormat);
                listQuestion.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (QuestionEntity questions : listQuestion) {
            questions.setAnswer(new QuestionDAO().getAnswer(questions.getId()));
        }
        return total;
    }

    
       public QuestionEntity getQuestion(int positionQuestion) {

        //declare new string query to get question dependent condition
        String sql = "SELECT * FROM dbo.Question ORDER BY id OFFSET ? ROWS FETCH NEXT 1 ROW ONLY";

        QuestionEntity question = new QuestionEntity();
        try {

            //prepare a statement to get question
            PreparedStatement ps = con.prepareStatement(sql);
         
            ps.setInt(1, positionQuestion);

            //use resultset to save datas got after excute
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //set all attribute to qustion enity
                question.setId(rs.getInt(1));
                question.setContent(rs.getString(2));
                question.setUserId(rs.getInt(3));
                Date date = rs.getDate(4);
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                String dateFormat = format.format(date);
                question.setDate(dateFormat);

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //push list answer to question
        question.setAnswer(new QuestionDAO().getAnswer(question.getId()));
        System.out.println("questionID DAO "+question.getId());
        return question;
    }

    public Vector<AnswerEntity> getAnswer(int questionId) {
        Vector<AnswerEntity> listAnswer = new Vector<>();

        //declare new string query to get answer dependent condition
        String sql = "SELECT * FROM dbo.Answer WHERE questionID = ?";
        try {
            //prepare a statement to get answer
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);

            //use resultset to save datas got after excute
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                //set all attribute to qustion enity
                AnswerEntity answer = new AnswerEntity();
                answer.setQuesionId(rs.getInt(1));
                answer.setContent(rs.getString(2));

                if (rs.getInt(3) == 1) {
                    answer.setValue(true);
                } else {
                    answer.setValue(false);
                }

                answer.setId(rs.getInt(4));
                //add anwer after set all attribute to list
                listAnswer.add(answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAnswer;
    }

    public boolean checkResult(int questionId, String resultContent) {
        boolean check = false;
        System.out.println("id: "+questionId);
        System.out.println("content "+resultContent);
        String sql = "SELECT value FROM dbo.Answer WHERE questionID = ? AND content=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            ps.setString(2, resultContent);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return check;
    }

    public static void main(String[] args) {
        QuestionDAO q = new QuestionDAO();
        boolean e =q.checkResult(10, "one");
        System.out.println(e);
    }

    
    public void pushQuestionToDB(QuestionEntity question, int id) {

        //create new string query to push question to database
        String sqlPushQues = "INSERT INTO dbo.Question(content, userID, Date ) "
                + "VALUES  (?,  ?,  GETDATE())";
        try {
            //prepare a statement to execute query and this statment help return
            //id of last question 
            PreparedStatement ps = con.prepareStatement(sqlPushQues, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, question.getContent());
            ps.setInt(2, id);
            ps.execute();

            //After execute query, get id of question has been returned
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question.setId(generatedKeys.getInt(1));
                    System.out.println("question ID: " + generatedKeys.getInt(1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        //create new string query to push answer to database
        String sqlPushAnswer = "INSERT INTO dbo.Answer( questionID, content, value )"
                + " VALUES  ( ?, ?, ?)";
        try {
            //prepare a statement to execute query
            PreparedStatement ps2 = con.prepareStatement(sqlPushAnswer);
            for (AnswerEntity e : question.getAnswer()) {
                ps2.setInt(1, question.getId());
                ps2.setString(2, e.getContent());
                int value = e.isValue() ? 1 : 0;
                ps2.setInt(3, value);

                //use batch help push multiple record after prepare all query
                ps2.addBatch();
            }

            //excute all record in batch
            ps2.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
