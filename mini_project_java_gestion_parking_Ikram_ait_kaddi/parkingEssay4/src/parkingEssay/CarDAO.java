package parkingEssay;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class CarDAO  extends BaseDAO<Car>{
	
	

	public CarDAO() throws SQLException {
		
		
		super();
		
	}

	@Override
	public void save(Car object)  throws SQLException {
		
		
		String rq = "insert into car (id) values (?)" ;
		
		this.preparedStatement = this.cnx.prepareStatement(rq);
		
		// MOR
		
		this.preparedStatement.setInt(1, object.getId());
	
	
		
		
		this.preparedStatement.execute();
		
		
		
	}



	

	

	@Override
	public  List<Car> getAll() throws SQLException, IOException{
		
		
		List<Car> lis = new ArrayList() ;
		
		String re = "select * from car";
		
		this.statement = this.cnx.createStatement();
		
		this.resultSet = this.statement.executeQuery(re);
		
		
		while (this.resultSet.next()) {
			
			
			lis.add(new Car(this.resultSet.getInt(1)));
			
		}
		
		
		
		
		return lis;
	}
	
	
	
	
	
	
	

}
