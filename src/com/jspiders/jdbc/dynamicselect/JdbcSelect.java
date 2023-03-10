package com.jspiders.jdbc.dynamicselect;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class JdbcSelect {
	
	private static String filePath="C:\\Users\\mohit\\Desktop\\FullStackPractise\\Jdbc\\resources\\db_info.properties";
	private static FileReader fileReader;
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static Scanner scanner = new Scanner(System.in);
	private static ResultSet resultSet;
	private static Properties properties;
	
	
	
	public static void main(String[] args) {
		
		try {
			
			fileReader = new FileReader(filePath);
			properties = new Properties();
			properties.load(fileReader);
			
			Class.forName(properties.getProperty("driverPath"));
			connection = DriverManager.getConnection(properties.getProperty("dbUrl"), properties);
			preparedStatement = connection.prepareStatement(properties.getProperty("query1"));
			
			System.out.println("Enter the student id : ");
			int id = scanner.nextInt();
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString(1) + " | "
						+ resultSet.getString(2) + " | "
						+ resultSet.getString(3) + " | "
						+ resultSet.getString(4) + " | "
						+ resultSet.getString(5));
			}
			
			
			
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (preparedStatement != null) {
						preparedStatement.close();
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			
		}

	}