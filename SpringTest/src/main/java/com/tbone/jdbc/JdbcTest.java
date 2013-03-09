package com.tbone.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSetMetaData;

public class JdbcTest {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";

	//  Database credentials
	static final String USER = "tbone";
	static final String PASS = "tbone";
	
	/*
	 	-- 'user' table create statement
	 	delimiter $$
		CREATE TABLE `user` (
		  `user_id` int(11) NOT NULL AUTO_INCREMENT,
		  `username` varchar(100) NOT NULL,
		  PRIMARY KEY (`user_id`)
		) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8$$
	 */
	/*
	 	insert into user(username) values ('tbone');
	 	insert into user(username) values ('razor');
	 	insert into user(username) values ('swatkats');
	 */

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM user";
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();
			int count=1;
			//STEP 5: Extract data from result set
			while(rs.next()){
				System.out.println("RowCount="+count++);
				for(int i=1;i<=rsmd.getColumnCount();i++){
					//Retrieve by column name
					Object obj = rs.getObject(i);
					//					int age = rs.getInt("age");
					//					String first = rs.getString("first");

					//Display values
					System.out.print("valAtIndex("+i+")="+obj+",,, ");
				}
				System.out.println();
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}//end main
}
