package Factory;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private DataSource datasource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPoolDataSource = new ComboPooledDataSource();
		comboPoolDataSource.setJdbcUrl("jdbc:mysql://localhost/hotelalura?useTimezone=True&serverTimezone=UTC");
		comboPoolDataSource.setUser("root");
		comboPoolDataSource.setPassword("");
		
		this.datasource = comboPoolDataSource;
	}
	
	public Connection recuperarConexao() {
		try {
			return this.datasource.getConnection();
		} catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
