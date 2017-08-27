package Assignment1;

/**Michael Masterson
 * E00942993
 * Assignment One - RDBMS Practice Part 1
 * COSC 471 Section 0
 * Dr. Zhang
 * October 18, 2016
 */
import java.sql.*;

public class Assignment1
{
	public static void main(String args[])
	{
		//start connection to JDBC
		Connection c = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:/Sqlite/Northwind.db");
			c.setAutoCommit(false);
			System.out.println("Opened database");

			//start creating queries, comment in or out code to execute query
			stmt = c.createStatement();

			/**This is where you can comment in if you want to get the Product Name of the
			 * 10 most expensive products.
			 */
			/**ResultSet rs = stmt.executeQuery("SELECT DISTINCT ProductName, UnitPrice "
					+ "FROM Products AS a; "
					+ "WHERE 10 >= (SELECT COUNT (DISTINCT UnitPrice)"
					+ 			     "FROM Products AS b"
					+ 			     "WHERE b.UnitPrice >= a.UnitPrice"
					+ "ORDER BY UnitPrice desc");
			while(rs.next())
			{
				String name = rs.getString("ProductName");
				float price = rs.getFloat("UnitPrice");

				System.out.println("Product Name = " + name);
				System.out.println("Unit Price = " + price);
				System.out.println();
			}**/

			/**Get the company name, contact name and fax number of all customers
			 * that have a fax number
			 */
			/**ResultSet rs = stmt.executeQuery("SELECT CompanyName, ContactName, Fax "
					+ "FROM Customers AS a; "
					+ "WHERE Fax IS NULL");
					//+ "WHERE CompanyName AND ContactName == Fax");
			while(rs.next())
			{
				String cname = rs.getString("CompanyName");
				String cuname = rs.getString("ContactName");
				String fax = rs.getString("Fax");

				System.out.println("Company Name = " + cname);
				System.out.println("Contact Name = " + cuname);
				System.out.println("Fax = " + fax);
				System.out.println();
			}**/

			/**Get the number of employees in each city in which there are at least two employees
			 */
			/**ResultSet rs = stmt.executeQuery("SELECT City, COUNT(*) "
					+ "FROM Employees"
					+ "WHERE (SELECT COUNT(*)"
					+ 		"FROM Employees"
					+ 		"WHERE EmployeeID IS NOT NULL"
					+ 		"GROUP BY City");

			while(rs.next())
			{
				String city = rs.getString("City");
				System.out.println("City = " + city);
				System.out.println();
			}**

			/**Get the number of employees and customers from each city
			 * that has employees in it.
			 */
			/**ResultSet rs = stmt.executeQuery("SELECT CustomerID, EmployeeID, City, COUNT(*) "
					+ "FROM Employees E, Customers C"
					+ "WHERE (SELECT COUNT(*)"
					+ 		"FROM Employees E, Customers C"
					+ 		"WHERE E.EmployeeID AND C.CustomerID AND CITY == EmployeeID"
					+ 		"GROUP BY City");

			while(rs.next())
			{
				String emp = rs.getString("EmployeeID");
				System.out.println("Number = " + emp);
				System.out.println();
			}**/

			/**Get the number of employees and customers from each city
			 * that has customers in it.
			 */
			ResultSet rs = stmt.executeQuery("SELECT CustomerID, EmployeeID, City, COUNT(*) "
					+ "FROM Employees E, Customers C"
					+ "WHERE (SELECT COUNT(*)"
					+ 		"FROM Employees E, Customers C"
					+ 		"WHERE E.EmployeeID AND C.CustomerID AND CITY == CustomerID"
					+ 		"GROUP BY City");

			while(rs.next())
			{
				String emp = rs.getString("EmployeeID");
				System.out.println("Number = " + emp);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e)
		{
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("opened");
	}
}