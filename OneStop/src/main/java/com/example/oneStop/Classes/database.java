package com.example.oneStop.Classes;

import com.example.oneStop.connector.MySqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database extends OneStop {
    Connection connection =null;
    MySqlConnector connector;

    public database() throws SQLException {
        connector = new MySqlConnector(null);
        connection = connector.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet executeQuery(String query) {
        try {
        Connection connection = connector.getConnection();
        PreparedStatement st = connection.prepareStatement(query);
        ResultSet rs= null;
            rs = st.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void executeUpdate(String query) {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkToken(String text) {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM request WHERE token= ?");
            st.setString(1, text);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean isDegreeIssued(String text) {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM request WHERE token= '"+text+"' and degree_issued='yes'");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public boolean isTranscriptIssued(String studentId) {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM transcript WHERE studentId= '"+studentId+"' and granted='yes'");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    public boolean issueTranscript(String tkn){
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("UPDATE transcript SET granted='yes' WHERE tid= '"+tkn+"'");
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public String getStudentNameBySID(String studentId) {
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("select sName from student s inner join transcript t on s.id=t.studentId WHERE studentId= '"+studentId+"'");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("sName");
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }
  public boolean isDegreeSaved(String sid){
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from degree WHERE studentId= '"+sid+"'");
            ResultSet rs = st.executeQuery();
            if(rs.next())
                return true;
        } catch (SQLException e) {
            return false;
            //throw new RuntimeException(e);
        }
return false;
    }
    public void deleteDegreeRequest(String token) {
    try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("DELETE FROM request WHERE token= '"+token+"'");
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean getDegreeRequestStatus(String sid){
        try {
            Connection connection = connector.getConnection();
            PreparedStatement st = connection.prepareStatement("select * from request where studentId= '"+sid+"'");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
