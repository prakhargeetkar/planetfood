/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PLANETFOOD.dao;

import PLANETFOOD.dbutil.DBConnection;
import PLANETFOOD.pojo.Category;
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
public class CategoryDao 
{
 
public static HashMap <String,String> getAllCategoryId() throws SQLException 
{
Connection conn=DBConnection.getConnection();
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select CAT_NAME,CAT_ID from Categories");
HashMap <String,String> Categories=new HashMap<>();
while(rs.next())
{
    String catName=rs.getString(1);
    String catId=rs.getString(2);
    Categories.put(catName,catId);
}
return Categories;
}
public static ArrayList <Category> getAllCatData()throws SQLException
{
    ArrayList <Category> catList=new ArrayList <Category>();
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from Categories");
    while(rs.next())
    {
        Category c=new Category();
        c.setCatId(rs.getString(1));
        c.setCatName(rs.getString(2));
        catList.add(c);
    }
    return catList;
}
public static boolean updateCat(Category c)throws SQLException
{
    Connection conn=DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update Categories set CAT_NAME=? where CAT_ID=?");
    ps.setString(1,c.getCatName());
    int x=ps.executeUpdate();
    if(x>0)
    {
        return true;
    }
    else 
        return false;
}
public static HashMap <String,String> getAllCatId() throws SQLException 
{
Connection conn=DBConnection.getConnection();
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select CAT_ID,CAT_NAME from Categories");
HashMap <String,String> Categories=new HashMap<>();
while(rs.next())
{
    String catName=rs.getString(2);
    String catId=rs.getString(1);
    Categories.put(catId,catName);
}
return Categories;
}
public static boolean editCategories(Category c)throws SQLException
   {
       Connection conn = DBConnection.getConnection();
    PreparedStatement ps=conn.prepareStatement("update Categories set CAT_NAME=? where CAT_ID=?");
   ps.setString(1,c.getCatName());
   ps.setString(2,c.getCatId());
   int count=ps.executeUpdate();
   if(count!=0)
       return true;
   else
       return false;
   }
 public static boolean addCategory(Category c) throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into categories values(?,?)");
        ps.setString(1, c.getCatId());
        ps.setString(2, c.getCatName());
       
        int count = ps.executeUpdate();
        if(count>0)
            return true;
        else 
            return false;
    }
  public static String getNewId() throws SQLException{
        
        Connection conn = DBConnection.getConnection();
        Statement st = conn.createStatement();
        String str = "select count(*) from categories";
        int id = 101;
        ResultSet rs = st.executeQuery(str);
        if(rs.next()){
            id = id + rs.getInt(1);
        }
        return "C" + id;
    }
}
