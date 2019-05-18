package application;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
	public static Connection getMyConnection() throws SQLException,
    ClassNotFoundException {
return OracleConnUtils.getOracleConnection();
}
public static void main(String[] args) throws SQLException,
    ClassNotFoundException {
		Connection conn = ConnectionUtils.getMyConnection();

		System.out.println("Get connection " + conn);

}
}
