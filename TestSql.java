package com.company; //包名

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSql{
    public static void main(String []args )throws Exception{
        Connection con = null;
        Statement stmt = null;

        try {
            String driverClassName = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            String password = "123";

            Class.forName(driverClassName);
            con = DriverManager.getConnection(url, username, password);

            stmt = con.createStatement();
            String sql = "INSERT INTO student VALUES('1009','小白', 20)";
            int r = stmt.executeUpdate(sql);
            System.out.println(r);
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
             if(stmt != null){
                 stmt.close();
             }
             if(con != null){
                 con.close();
             }
        }

    }

}

