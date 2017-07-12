package com.company;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by 黄文旗 on 2017/7/12.
 */
public class Login {
    public static boolean login(String username, String password) throws Exception {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String sqlusername = "root";
        String sqlpassword = "123";

        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, sqlusername, sqlpassword);

        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2,password);
        ResultSet rs = pstmt.executeQuery();

        return rs.next();
    }
    @Test
    public void fun() throws Exception {
        String username = "小明";
        String password = "1234";

        if(login(username, password)){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

    }



}
