package config;

import java.sql.*;

public class RecordSet {
	public ResultSet sqlRs;
	public ResultSet Open(String sqlString){
		Connection sqlCon = config.con.cnn;
		try{
			PreparedStatement sqlStm = sqlCon.prepareStatement(sqlString);
			sqlRs = sqlStm.executeQuery();
		}catch (Exception e) {
			System.out.println("SQL-ERROR:"+e);
		}
		return sqlRs;
	}
	public ResultSet Execute(String sqlString){
		Connection sqlCon = config.con.cnn;
		try{
			sqlCon.createStatement().executeUpdate(sqlString);
		}catch (Exception e) {
			System.out.println("SQL-ERROR:"+e);
		}
		return sqlRs;
	}
}
