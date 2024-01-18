package config;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class con{
    static Connection cnn = null;
    //public static Connection sqlCon;
    public void getCon(){
    	String db = Global.dbName;
    	String user = Global.dbUser;
    	String pass = Global.dbPass;
    	String sock = Global.dbSocket;
    	
        try{
             Class.forName("com.mysql.jdbc.Driver");
             cnn = DriverManager.getConnection("jdbc:mysql://localhost:"+sock+"/"+db,user,pass);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
