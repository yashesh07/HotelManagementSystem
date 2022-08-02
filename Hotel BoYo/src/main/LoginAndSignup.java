package main;

import java.sql.*;

import main.database.SqlStatementManager;

import javax.swing.*;

public class LoginAndSignup {
	SqlStatementManager sqlHelp = new SqlStatementManager("boyodb", "root", CurrentUser.sqlPassword);

    public boolean login(String role, String id, String pass) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String query = "select * from usertable";

        Connection connection = sqlHelp.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        boolean isDetailsCorrect = false;
        while(rs.next()){
            if(rs.getString(2).equals(id) && rs.getString(5).equals(pass)){
                isDetailsCorrect = true;
                break;
            }
        }
        st.close();
        connection.close();
        return isDetailsCorrect;
    }

    public boolean signin(String id, String pass, String confirmPass, String userName) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        String query = "select * from usertable";
        
        Connection connection = sqlHelp.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        boolean isDetailsCorrect = true;
        if(pass.equals(confirmPass)){
            while(rs.next()){
                if(rs.getString(2).equals(id)){
                    isDetailsCorrect = false;
                    break;
                }
            }
            if(isDetailsCorrect){
                String q = "INSERT INTO usertable(UserID, password, username, vip) VALUES(?, ?, ?, ?)";
                PreparedStatement ps = connection.prepareStatement(q);
                ps.setString(1, id);
                ps.setString(2, pass);
                ps.setString(3, userName);
                ps.setString(4, "0");
                ps.executeUpdate();
                ps.close();
            }
            else JOptionPane.showMessageDialog(new JFrame(), "This ID is already registered. Please try with different ID.", "Alert",JOptionPane.ERROR_MESSAGE);
        }
        else JOptionPane.showMessageDialog(new JFrame(), "Password and Confirm password are not matching", "Alert", JOptionPane.ERROR_MESSAGE);
        st.close();
        connection.close();
        return isDetailsCorrect;
    }
	
}
