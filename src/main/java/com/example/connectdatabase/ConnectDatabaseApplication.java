package com.example.connectdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class ConnectDatabaseApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbc;

	public static void main(String[] args) {
		SpringApplication.run(ConnectDatabaseApplication.class, args);
	}

	public void run(String... args) throws Exception {
		//create table sql
		String sql = "CREATE TABLE IF NOT EXISTS messages (" +
				"id int UNIQUE NOT NULL AUTO_INCREMENT," +
				"message VARCHAR(100) DEFAULT 'Default Message'," +
				"time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
				"PRIMARY KEY (id)" +
				");";

		//run sql, check for exceptions
		try {
			jdbc.execute(sql);
		} catch (Exception e) {
			System.out.println("Exception caught during table creation: "+e.getMessage());
		}

		sql = "SHOW TABLES;";

		//print the tables in the database
		try {
			//return a list of Table objects
			List<Table> res = jdbc.query (sql, new RowMapper<Table>(){
				//save the data from the sql results into individual Table objects
				public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
					//create new Table object
					Table table = new Table();
					//save the name of the table to the Table object
					table.setTables_in_db(rs.getString("Tables_in_test_db"));
					//return a Table
					return table;
				}
			});
			//enchanted for loop
            for (Table tb : res) {
                System.out.println(tb.getTables_in_db());
            }
		} catch (Exception e) {
			System.out.println("Exception caught: "+e.getMessage());
		}

		//let the user add 3 messages
		makeMessage();
		makeMessage();
		makeMessage();

		sql = "select * from messages";

		//print the messages from the message table
		try {
			//return a list of Table objects
			List<Message> res = jdbc.query (sql, new RowMapper<Message>(){
				//save the data from the sql results into individual Message objects
				public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
					//create new Table object
					Message message = new Message();
					//save the values to the Message object
					message.setMessage(rs.getString("message"));
					message.setId(rs.getInt("id"));
					message.setTime(rs.getString("time"));
					//return a Message
					return message;
				}
			});
			//enchanted for loop
			for (Message msg : res) {
				System.out.println(msg.toString());
			}
		} catch (Exception e) {
			System.out.println("Exception caught: "+e.getMessage());
		}

		System.out.println("Program finished running");
	}

	public void makeMessage() {
		//create the object needed to read user input from the console

		String message = null;

		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(System.in));

			//save user input
			System.out.println("Type out a message to add: ");
			message = inputReader.readLine();
		} catch (IOException e) {
			System.out.println("IOException caught: "+e);
		}

		try {
			//insert message into message table
			String sql = "INSERT INTO messages (message) VALUES (?);";
			int res = jdbc.update(sql, message);
			System.out.println(res+" row updated");
		} catch (Exception e) {
			System.out.println("Error inserting into messages table: " + e);
		}
	}
}
