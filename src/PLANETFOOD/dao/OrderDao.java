/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PLANETFOOD.dao;

import PLANETFOOD.dbutil.DBConnection;
import PLANETFOOD.pojo.Order;
import PLANETFOOD.pojo.OrderDetails;
import PLANETFOOD.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class OrderDao {
    
    public static ArrayList <Order> getOrdersByDate(Date startDate,Date endDate) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from Orders where ORD_DATE between ? and ?   ");
        long ms1=startDate.getTime();
        long ms2=endDate.getTime();
        java.sql.Date d1=new java.sql.Date(ms1);
        java.sql.Date d2=new java.sql.Date(ms2);
        ps.setDate(1,d1);
        ps.setDate(2, d2);
       // String a=UserProfile.getUsername();
      //  ps.setString(3,a);
        ArrayList <Order> orderList=new ArrayList<Order>();
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Order obj=new Order();
            obj.setOrdId(rs.getString("ORD_ID"));
            java.sql.Date d=rs.getDate("ORD_DATE");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy"); 
            String dateStr=sdf.format(d);
            obj.setOrdDate(dateStr);
            obj.setGst(rs.getDouble("GST"));
            obj.setGstAmount(rs.getDouble("GST_AMOUNT"));
            obj.setDiscount(rs.getDouble("DISCOUNT"));
            obj.setGrandTotal(rs.getDouble("GRAND_TOTAL"));          
            obj.setUserId(rs.getString("USERID"));
            obj.setOrdAmount(rs.getDouble("ORD_AMOUNT"));
            orderList.add(obj);
            }
    return orderList;
}
public static ArrayList<Order>getAllOrderData()throws SQLException
{
    ArrayList <Order> orderList=new ArrayList <Order> ();
    Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
    ResultSet rs=st.executeQuery("Select * from Orders");
    while(rs.next())
    {
        Order obj=new Order();
            obj.setOrdId(rs.getString("ORD_ID"));
            java.sql.Date d=rs.getDate("ORD_DATE");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy"); 
            String dateStr=sdf.format(d);
            obj.setOrdDate(dateStr);
            obj.setGst(rs.getDouble("GST"));
            obj.setGstAmount(rs.getDouble("GST_AMOUNT"));
            obj.setDiscount(rs.getDouble("DISCOUNT"));
            obj.setGrandTotal(rs.getDouble("GRAND_TOTAL"));          
            obj.setUserId(rs.getString("USERID"));
            obj.setOrdAmount(rs.getDouble("ORD_AMOUNT"));
            orderList.add(obj); 
      
    }
    return orderList;
}
public static String getNewID()throws SQLException
{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select count(*) from Orders");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
         }
        return "OD"+id;
    }
public static boolean addOrder(Order order,ArrayList<OrderDetails> orderList)throws Exception{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?,?,?,?,?)");
        ps.setString(1, order.getOrdId());
        String dateStr=order.getOrdDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1=sdf.parse(dateStr);
        java.sql.Date d2=new java.sql.Date(d1.getTime());
        ps.setDate(2, d2);
        ps.setDouble(3,order.getGst());
        ps.setDouble(4, order.getGstAmount());
        ps.setDouble(5, order.getDiscount());
        ps.setDouble(6, order.getGrandTotal());
        ps.setString(7,order.getUserId());
        ps.setDouble(8, order.getOrdAmount());
        int x=ps.executeUpdate();
        PreparedStatement ps2=conn.prepareStatement("Insert into order_details values(?,?,?,?)");
        int count=0,y;
        for(OrderDetails detail:orderList)
        {
            ps2.setString(1, detail.getOrdId());
            ps2.setString(2, detail.getProdId());
            ps2.setDouble(3, detail.getQuantity());
            ps2.setDouble(4, detail.getCost());
            y=ps2.executeUpdate();
            count=count+y;
        }
        if(x>0 && count==orderList.size())
            return true;
        else
            return false;
    }
 public static ArrayList <Order> getOrdersByDate1(Date startDate,Date endDate) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from Orders where ORD_DATE between ? and ? and userid=?  ");
        long ms1=startDate.getTime();
        long ms2=endDate.getTime();
        java.sql.Date d1=new java.sql.Date(ms1);
        java.sql.Date d2=new java.sql.Date(ms2);
        ps.setDate(1,d1);
        ps.setDate(2, d2);
        String a=UserProfile.getUsername();
        ps.setString(3,a);
        ArrayList <Order> orderList=new ArrayList<Order>();
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Order obj=new Order();
            obj.setOrdId(rs.getString("ORD_ID"));
            java.sql.Date d=rs.getDate("ORD_DATE");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy"); 
            String dateStr=sdf.format(d);
            obj.setOrdDate(dateStr);
            obj.setGst(rs.getDouble("GST"));
            obj.setGstAmount(rs.getDouble("GST_AMOUNT"));
            obj.setDiscount(rs.getDouble("DISCOUNT"));
            obj.setGrandTotal(rs.getDouble("GRAND_TOTAL"));          
            obj.setUserId(rs.getString("USERID"));
            obj.setOrdAmount(rs.getDouble("ORD_AMOUNT"));
            orderList.add(obj);
            }
    return orderList;
}
}
