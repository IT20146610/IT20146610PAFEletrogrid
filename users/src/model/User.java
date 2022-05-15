package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/management", "root", "y1o2h3a4n5@#");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String userName, String userAddress, String userAccount, String userContact, String userEmail) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into USERDATA(`ID`,`USERNAME`,`USERADDRESS`,`USERACCOUNT`,`USERCONTACT`,`USEREMAIL`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, userAddress);
			preparedStmt.setString(4, userAccount);
			preparedStmt.setString(5, userContact);
			preparedStmt.setString(6, userEmail);

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUser = readUser();
			output = "{\"status\":\"Inserted successfully\",\"data\":\""+newUser+"\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the User.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"class=\"table\"><tr><th>User Name</th>"
					+ "<th>User Address</th>"
					+ "<th>User Account No</th>"
					+ "<th>User Contact No</th>"
					+ "<th>Email</th>"
					+ "<th>Update</th>"
					+ "<th>Remove</th></tr>";
			
			String query = "select * from USERDATA";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String userName = rs.getString("userName");
				String userAddress = rs.getString("userAddress");
				String userAccount = rs.getString("userAccount");
				String userContact = rs.getString("userContact");
				String userEmail = rs.getString("userEmail");
				
				output += "<tr><td><input id='hidUser_IDUpdate' name='hidUser_IDUpdate' type='hidden' value='"+id+"'>"+userName+"</td>"; 
				output += "<td>" + userAddress + "</td>";
				output += "<td>" + userAccount + "</td>";
				output += "<td>" + userContact + "</td>";
				output += "<td>" + userEmail + "</td>";
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						 + "class='btnUpdate btn btn-secondary' data-iD='" + id + "'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' "
						 + "class='btnRemove btn btn-danger' data-iD='" + id + "'></td></tr>"; 
				 
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String id, String userName, String userAddress, String userAccount, String userContact, String userEmail) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE USERDATA SET USERNAME=?,USERADDRESS=?,USERACCOUNT=?,USERCONTACT=?,USEREMAIL=? WHERE ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, userAddress);
			preparedStmt.setString(3, userAccount);
			preparedStmt.setString(4, userContact);
			preparedStmt.setString(5, userEmail);
			preparedStmt.setInt(6, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newUser = readUser();
			output = "{\"status\":\"Updated successfully\",\"data\":\""+newUser+"\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the User.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from USERDATA where ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newUser = readUser();
			output = "{\"status\":\"Deleted successfully\",\"data\":\""+newUser+"\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the User.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
