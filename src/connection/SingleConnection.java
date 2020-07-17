package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String banco = "jdbc:postgresql://localhost:5432/curso-venda?autoReconnect=true";
	private static String password = "31733787"; // "senha padr�o:  admin"
	private static String user = "postgres";
	private static Connection connection = null;	
	
	
	//Chama a fun��o conecta ao banco de dados mesmo a classe n�o sendo instanciada, j� q a classe � static
	static {
		conectar();
	}
	
	
	//Conecta ao banco de dados ao instanciar a classe 
	public SingleConnection() {
		conectar();
	}
	
	//Fun��o que Conecta ao banco de dados 
	public static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
				System.out.print("Conectado ao BD!!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar ao bando de dados");
		}

	}
	
	//Retorna conex�o 
	public static Connection getConnection() {
		return connection;
	}
	
}
