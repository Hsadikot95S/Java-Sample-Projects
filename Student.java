import javax.persistence.*;

/*
 *  Table in the db.
 * create table Student (id integer, name varchar(25), marks integer);
 */

@Entity
@Table(name = "Student")

public class Student {
	Student()
	{
	
	}
	public Student(String Name, int Marks) {
		 name = Name;
		 marks = Marks;
	}

	@Id @GeneratedValue
	@Column(name = "id")
	int id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "marks")
	int marks;
}
