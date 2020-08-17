 
import java.sql.*;
/*
 * 
 * 
 *   in mysql execute START TRANSACTION; for rollback operaton.
 * 
 * 
 */

public class BatchOperations
{
 public static void main(String args[])throws Exception
{
 	Class.forName("com.mysql.jdbc.Driver");
    System.out.println("Driver loaded...");
 
    Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","mid.ran-95");
    System.out.println("Connected to the database");

Statement stmt=con.createStatement();
con.setAutoCommit(false);
stmt.addBatch("update student2 set name='Aditya Raut' where name='Aditya Singh'");
stmt.addBatch("insert into student2 values(4,'Anil','ME',93)");
try
{
    stmt.executeBatch();    
    System.out.println("batch executed");
    con.commit();
}
catch(Exception e)
{
    try
    {
        con.rollback();
        System.out.println("batch cancelled");
        e.printStackTrace();
    }
    catch(Exception e1)
    {
        System.out.println(e1);
    }
     con.close();   
    }
}
        
}
