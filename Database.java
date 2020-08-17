 
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class Database {

 	private static SessionFactory factory;
	
 	
	//Obtains the session factory. 
	public static void getSessionFactory()
	   {
		
			
			try{
				Configuration conf = new Configuration().configure();						
			    StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
			    factory= conf.buildSessionFactory(builder.build());		 

			}catch (Throwable ex) { 
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex); 
			}
	   }


	public static void main(String[] args) {

		try
		{
			
			 getSessionFactory();
			 Database client_1 = new Database();

			/* Add few employee records in database */
			 client_1.InsertRecordInDatabase("Mukesh", 94);
			 client_1.InsertRecordInDatabase("Rajesh", 92);
			 client_1.InsertRecordInDatabase("Anil", 78);
			 

			/* List down all the employees */
			System.out.println("Listing employees..");
			client_1.DisplayRecords();


			/* Update employee's records */
		  	System.out.println("UPdated the record..");
		  	client_1.UpdateRecord(1, 100);
		  	client_1.DisplayRecords();


			/* Delete an employee from the database */
		 	 System.out.println("Deleted the 2nd Record...");
		 	 client_1.DeleteRecord();

			/* List down new list of the employees */
	  	System.out.println("Listing all the employees...");
		 	client_1.DisplayRecords();
		}
		catch (HibernateException e)
		{
			System.out.println("Exception is : " + e);
		}
	}

	/* Method to CREATE an employee in the database */
	public void InsertRecordInDatabase(String name, int marks) throws HibernateException 
	{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();;
 
		Student e1 = new Student(name,marks);
		session.save(e1); 
		tx.commit();

		session.close();    
		
	} 

	/* Method to  READ all the employees */
	public void DisplayRecords( ) throws HibernateException{
		Session session = factory.openSession();
		List empLst = session.createQuery("FROM Student").list(); 
		for (Iterator iterator = 
				empLst.iterator(); iterator.hasNext();){
			Student emp = (Student) iterator.next(); 
			System.out.print("id: " + emp.id); 
			System.out.print("  Name: " + emp.name); 
			System.out.println(" Marks: " + emp.marks); 

		}
		session.close(); 
	}



	/* Method to UPDATE salary for an employee */
	public void UpdateRecord(Integer StudentId, int marks1 ) throws HibernateException
	{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();;
	 
		Student st1 = 
				(Student)session.get(Student.class, StudentId); 
		st1.marks = marks1;
		session.update(st1); 
		tx.commit(); 
		session.close();  
	}


	/* Method to DELETE an employee from the records */
	public void DeleteRecord() throws HibernateException
	{
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		 String hql = "delete from Student where marks < 35"; 
		 Query query = session.createQuery(hql);
	     query.executeUpdate();
 
		tx.commit();   
		session.close(); 

	}
}