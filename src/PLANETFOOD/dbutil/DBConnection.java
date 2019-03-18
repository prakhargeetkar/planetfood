/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PLANETFOOD.dbutil;


import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Asus
 */
public class DBConnection {
    private static Connection conn;
    static
    {
        try
        {
         Class.forName("oracle.jdbc.OracleDriver");   
         conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-UMDL3MOA:1521/XE","myproject","abc");
         JOptionPane.showMessageDialog(null,"Connected Successfully to the Database","Success",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null,"ERROR in loading Driver class","ERROR!",JOptionPane.INFORMATION_MESSAGE);
            ex.printStackTrace();
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"SQL Error"+ex,"Error!",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }    
}
