/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PLANETFOOD.dao;

import PLANETFOOD.dbutil.DBConnection;
import PLANETFOOD.pojo.Employees;
import PLANETFOOD.pojo.Product;
import PLANETFOOD.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Asus
 */
public class UserDao {
    public static String validateUser(User u)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="Select username from Users where  USERID=? and PASSWORD=? and USERTYPE=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,u.getUserId());
        ps.setString(2,u.getPassword());
        ps.setString(3,u.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next())
        {
          username=rs.getString(1);
        }
          return username;
    }
public static ArrayList <User>getUserById(String UserId)throws SQLException
{
 Connection conn=DBConnection.getConnection();
 String qry="Select * from Users where UserID=?";
 PreparedStatement ps=conn.prepareStatement(qry);
 ArrayList <User> UserList=new ArrayList<User> ();
 ps.setString(1,UserId);
 ResultSet rs=ps.executeQuery();
 while(rs.next())
 {
     User u=new User();
       u.setUserId(rs.getString(1));
       u.setUserName(rs.getString(2));
       u.setEmpId(rs.getString(3));    
     UserList.add(u);
 }
 return UserList;
}
public static boolean deleteUser(String UserId) throws SQLException
{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from Users where USERID=?");
    ps.setString(1,UserId);
    int count=ps.executeUpdate();
    if(count==0)
    return false;
    else 
        return true;
}
 public static boolean setData(User u,String username,String empid)throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
     ps.setString(1,u.getUserId());
     ps.setString(2, username);
     ps.setString(3,u.getPassword());
     ps.setString(4, empid);
     ps.setString(5,u.getUserType());
   int count=  ps.executeUpdate();
   if(count!=0)
       return true;
   else 
       return false;
    }
  public static boolean removeCashier(String userid)throws SQLException
    {
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("delete from users where userid=?");
    ps.setString(1,userid);
    int count=ps.executeUpdate();
    if(count==0)
        return false;
    else
        return true;
    }
}
