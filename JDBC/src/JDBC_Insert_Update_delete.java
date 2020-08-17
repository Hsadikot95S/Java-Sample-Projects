 
//package database;
import java.sql.*;
public class JDBC_Insert_Update_delete 
{
  public static void main(String args[])throws Exception 
  {

  	//Class.forName("oracle.jdbc.driver.OracleDriver");
  	Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded...");
   
      Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","mid.ran-95");
      System.out.println("Connected to the database");
  	
      Statement st=con.createStatement();

     System.out.println("Before creating the table...");
      st.execute("CREATE TABLE `student2` (`id` INT(11) NULL DEFAULT NULL,`name` VARCHAR(25) NULL DEFAULT NULL,	`class` VARCHAR(25) NULL DEFAULT NULL,	`marks` INT(11) NULL DEFAULT NULL)");
      System.out.println("table created");
       
               
      st.executeUpdate("insert into student2 values(2, 'Aditya','B.Tech', 98)");
       System.out.println("  row inserted");
       
       st.executeUpdate("insert into student2 values(3, 'Sayali','B.COM', 96)");
       System.out.println("  row inserted");

      
      
      st.executeUpdate("update student2 set name='Aditya Singh' where name='Aditya'");
      System.out.println("row updated");
       
      
      st.executeUpdate("delete from student2 where name='Sayali'");
      System.out.println("row deleted");
      
      con.close();
      System.out.println("Connection closed...");
      
  }    
}





