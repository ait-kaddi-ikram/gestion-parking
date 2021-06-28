package parkingEssay;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {
	
	// jdbc params 
	
	protected Connection cnx ;
	
	protected Statement statement ;
	
	protected PreparedStatement preparedStatement ;
	
	private String url = "jdbc:mysql://127.0.0.1:3306/gestionparking" ;
	private String user = "root";
	
	private String  password = "";
	


	
	protected ResultSet resultSet ;
	
	
	public BaseDAO() throws SQLException {
		
		
		this.cnx = DriverManager.getConnection(url, user, password);
		
		
	}
	
	
	public abstract void save(T object )  throws SQLException;
	
	//public abstract void update (T object )  throws SQLException;
	
	//public abstract void delete (T object )  throws SQLException;
	
	//public abstract T getOne ( Integer id)  throws SQLException;
	
	public abstract List<T> getAll ()  throws SQLException, IOException;
	
	
	
	
	
	
	

}
