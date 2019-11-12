package cori;
import java.sql.*;
public class DBConnection
{
	public static Connection con;
	public static Connection connect()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver ok");
		String url="jdbc:mysql://localhost:3306/courier";
		String user="root";
		String password="0807";
	con=DriverManager.getConnection(url,user,password);
	System.out.println("Connected");
		}
		catch(ClassNotFoundException|SQLException se)
		{
			se.printStackTrace();
		}
		return con;
	}
	public static void main(String ar[])
	{
		connect();
	}
}
