import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Main {
   
   //static final String QUERY = "SELECT id, name, average, email FROM demo.students";
   private static Properties getConnectionData() {

       Properties props = new Properties();

       String fileName = "src/main/resources/db.properties";

       try (FileInputStream in = new FileInputStream(fileName)) {
           props.load(in);
       } catch (IOException e) {
           Logger lgr = Logger.getLogger(Main.class.getName());
           lgr.log(Level.SEVERE, e.getMessage(), e);
       }

       return props;
   }
   static Properties props = getConnectionData();

   static String url = props.getProperty("db.url");
   static String user = props.getProperty("db.user");
   static String passwd = props.getProperty("db.passwd");
   public static void main(String[] args) {
	  // Properties props = getConnectionData();

     //  String url = props.getProperty("db.url");
     //  String user = props.getProperty("db.user");
      // String passwd = props.getProperty("db.passwd");
      // Open a connection
	   int dec;
	   Scanner input=new Scanner(System.in);
	   do {
		   System.out.println("1- Add Student");
		   System.out.println("2- Update Student");
		   System.out.println("3- Delete Student");
		   System.out.println("4- Show All Students");
		   System.out.println("5- Quit");
		   System.out.println("=======================");
		   System.out.println("Please Enter your Choice (number between 1-5)");
		   
		   while(!input.hasNextInt()) {
			   System.out.println("Please Enter a number");
			   input.nextLine();
		   }
		   dec=input.nextInt();
		  // input.close();
		   switch(dec) {
		   case 1: AddRecord();break;
		   case 2: UpdateRecord();break;
		   case 3: DeleteRecord();break;
		   case 4: ShowTable();break;
		   case 5: System.out.println("Goodbye");break;
		   default: System.out.println("please make sure the number is between 1-5");
		   }
	   }while(dec!=5);
	   input.close();
      
   }
private static void DeleteRecord() {
	String Query="DELETE FROM demo.students where id=?";
	try(Connection conn = DriverManager.getConnection(url, user, passwd);
	         PreparedStatement pst = conn.prepareStatement(Query);) {
	        
	        Scanner input=new Scanner(System.in);
	        boolean isExist;
	        int id;
	        do {
	        	System.out.println("Please Enter student id that you want to delete: ");
	        while(!input.hasNextInt()) {
				   System.out.println("Please make sure ID is a number ");
				   input.nextLine();
			   }
	         id=input.nextInt();
			 isExist=checkIfIdExist(id);
			   if(!isExist)
				   System.out.println("Student whith ID= "+id+", dosent exists.");
	        }while(!isExist);
	        ShowRecord(id);
	        pst.setInt(1, id);
	        
	        
	        System.out.println("To confirm deletion press y, to cancel press any thing else :");
	        input.nextLine();
	        String flag=input.nextLine();
	       if(flag.equals("y")) { pst.executeUpdate();
	        System.out.println("Student record Deleted successfully ");}
	       else  System.out.println("proccess canceld ");
	        
	      } catch (SQLException e) {
	    	  Logger lgr = Logger.getLogger(Main.class.getName());
	          lgr.log(Level.SEVERE, e.getMessage(), e);
	      } 

	
}
private static void UpdateRecord() {
	String Query="Update demo.students Set name=?, average=?, email=? WHERE id=?";
	try(Connection conn = DriverManager.getConnection(url, user, passwd);
	         PreparedStatement pst = conn.prepareStatement(Query);) {
	        
	        Scanner input=new Scanner(System.in);
	        boolean isExist;
	        int id;
	        do {
	        	System.out.println("Please Enter student id that you want to update: ");
	        while(!input.hasNextInt()) {
				   System.out.println("Please make sure ID is a number ");
				   input.nextLine();
			   }
	         id=input.nextInt();
			 isExist=checkIfIdExist(id);
			   if(!isExist)
				   System.out.println("Student whith ID= "+id+", dosent exists");
	        }while(!isExist);
	        pst.setInt(4, id);
	        ShowRecord(id);
	        //name update
	        System.out.println("Please Enter Student name: ");
	        input.nextLine();
	        String name=input.nextLine();
	        pst.setString(1, name);
	        
	        
	       //average update
	        double average;
	        do {
	        	 System.out.println("Please Enter Student average: ");
	        while(!input.hasNextDouble()) {
				   System.out.println("Please make sure average is a number ");
				   input.nextLine();
			   }
	         average=input.nextDouble();
	       if(average<0||average>100)
	    	   System.out.println("Please make sure average is greater or equal 0, and smaller or equal 100");
	        }while(average<0||average>100);
	        pst.setDouble(2, average);
	        
	        //email update
	        System.out.println("Please Enter Student email: ");
	        input.nextLine();
	        String email=input.nextLine();
	        pst.setString(3, email);
	        
	        pst.executeUpdate();
	        System.out.println("Student record Updated successfully ");
	        
	      } catch (SQLException e) {
	    	  Logger lgr = Logger.getLogger(Main.class.getName());
	          lgr.log(Level.SEVERE, e.getMessage(), e);
	      } 

}

private static void AddRecord() {
	String Query="INSERT INTO demo.students(id,name,average,email) VALUES(?,?,?,?)";
	try(Connection conn = DriverManager.getConnection(url, user, passwd);
	         PreparedStatement pst = conn.prepareStatement(Query);) {
	        
	        Scanner input=new Scanner(System.in);
	        boolean isExist;
	        int id;
	        do {
	        	System.out.println("Please Enter student id: ");
	        while(!input.hasNextInt()) {
				   System.out.println("Please make sure ID is a number ");
				   input.nextLine();
			   }
	         id=input.nextInt();
			 isExist=checkIfIdExist(id);
			   if(isExist)
				   System.out.println("Student whith ID= "+id+", alredy exists.");
	        }while(isExist);
	        pst.setInt(1, id);
	        
	        
	        System.out.println("Please Enter Student name: ");
	        input.nextLine();
	        String name=input.nextLine();
	        pst.setString(2, name);
	        
	        
	       
	        double average;
	        do {
	        	 System.out.println("Please Enter Student average: ");
	        while(!input.hasNextDouble()) {
				   System.out.println("Please make sure average is a number ");
				   input.nextLine();
			   }
	         average=input.nextDouble();
	       if(average<0||average>100)
	    	   System.out.println("Please make sure average is greater or equal 0, and smaller or equal 100");
	        }while(average<0||average>100);
	        pst.setDouble(3, average);
	        
	        
	        System.out.println("Please Enter Student email: ");
	        input.nextLine();
	        String email=input.nextLine();
	        pst.setString(4, email);
	        
	        pst.executeUpdate();
	        System.out.println("Student record added ...");
	        
	      } catch (SQLException e) {
	    	  Logger lgr = Logger.getLogger(Main.class.getName());
	          lgr.log(Level.SEVERE, e.getMessage(), e);
	      } 
}

private static boolean checkIfIdExist(int id) throws SQLException {
	String Query= "SELECT * FROM students WHERE id="+id;
	
	
	Connection conn = DriverManager.getConnection(url, user, passwd);
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(Query); 
	
	        return(rs.next());
	      
	
}

private static void ShowTable() {
	String Query="SELECT * FROM demo.students";
	try(Connection conn = DriverManager.getConnection(url, user, passwd);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(Query);) {
	         System.out.println("All Students Records: ");
	         while (rs.next()) {
	            
	            System.out.print("ID: " + rs.getInt("id"));
	            System.out.print(", Name: " + rs.getString("name"));
	            System.out.print(", Average: " + rs.getInt("average"));
	            System.out.println(", Email: " + rs.getString("email"));
	         }
	         System.out.println("--------------------------------------------------");
	         
	      } catch (SQLException e) {
	    	  Logger lgr = Logger.getLogger(Main.class.getName());
	          lgr.log(Level.SEVERE, e.getMessage(), e);
	      } 
	
}

private static void ShowRecord(int id) {
	String Query="SELECT * FROM demo.students where id="+id;
	try(Connection conn = DriverManager.getConnection(url, user, passwd);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(Query);) {
	         System.out.println("Student id: "+id+" Record: ");
	         while (rs.next()) {
	            
	            System.out.print("ID: " + rs.getInt("id"));
	            System.out.print(", Name: " + rs.getString("name"));
	            System.out.print(", Average: " + rs.getInt("average"));
	            System.out.println(", Email: " + rs.getString("email"));
	         }
	         
	         
	      } catch (SQLException e) {
	    	  Logger lgr = Logger.getLogger(Main.class.getName());
	          lgr.log(Level.SEVERE, e.getMessage(), e);
	      } 
	
}
}