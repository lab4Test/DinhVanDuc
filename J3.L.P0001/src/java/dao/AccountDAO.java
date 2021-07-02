package dao;

import context.DBConnection;
import entity.AccountEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dinhd
 */
public class AccountDAO {

    Connection con;

    public AccountDAO() {
        con = new DBConnection().getConn();
    }

    //Check if the account exists or not
    public AccountEntity checkLogin(String userName, String passWord) {
        AccountEntity account = null;
        String sql = "SELECT * FROM dbo.[User] WHERE username = ? AND password=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, passWord);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new AccountEntity();
                account.setId(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassWord(rs.getString(3));
                account.setRoleUser(rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    //check user name exist or not to Register
    public AccountEntity checkLogin(String userName) {
        AccountEntity account = null;
        String sql = "SELECT * FROM dbo.[User] WHERE username = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new AccountEntity();
                account.setId(rs.getInt(1));
                account.setUserName(rs.getString(2));
                account.setPassWord(rs.getString(3));
                account.setRoleUser(rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    //Register a new account
    public int signUp(AccountEntity account) {
        int n = 0;
        String sql = "INSERT INTO dbo.[User] ( username, password, Role, Email )\n"
                + "VALUES  (?,?,?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getPassWord());
            ps.setInt(3, account.getRoleUser());
            ps.setString(4, account.getEmail());
            n = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    
    //check account already exist or not to create new account
    public boolean checkAccountExist(String userName) {
        AccountEntity account = new AccountDAO().checkLogin(userName);
        if (account==null) {
            return false;
        }
        return true;
    }

}
