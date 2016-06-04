package servidor;

import java.sql.*; 

/*
 * Classe DataBase responsavel pela conexao e pelas consultas ao Banco de Dados
 * Retornara Strings que contem as informacoes requeridas...
 * 
 * Seu funcionamento e explicado passo a passo logo abaixo
 * 
 * * CLASSE FUNCIONANDO PERFEITAMENTE * 
 *  
 */

/**
 * @author Serginho
 */

public class DataBase {
	
	// Campos usados para a conexao, passagem de SQL para o Banco, e autenticacao.
	private Connection conexao = null; // OK!
	private Statement comando = null; // OK!
	private String driver = "com.mysql.jdbc.Driver"; // OK!
	private String url = "jdbc:mysql://localhost:3306/senai_spaces"; // OK!
	private String senha = "1nf0071"; // OK!
	private String usuario = "sspaces_server"; // OK!
	
	// Mtodo que cria a conexao
	public void conectaBanco() {
		
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, usuario, senha);
			comando = conexao.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 
	}

	// Metodo que executa os SQLs recebidos do servidor, independente de ser SELECT ou INSERT
	// Retorna uma String contendo as informacoes requeridas ou nada se somente for para insertar
	// dados no Banco
	public String executeQuery(String sql) {
		
		// Variaveis assim inicializadas para caso de erro, retornar valores padrao.
		String matricula = null;
		String nome = null;
		String disciplina = null;
		String turma = null;
		String sala = null;
		
		conectaBanco();
		
		try {
			
			// Instanciacao de uma interface que vai armazenar os dados resgatados do Banco para
			// trata-los devidamente
			ResultSet resultado = comando.executeQuery(sql);
			
			// Enquanto nao chegar ao fim do ResultSet resultado, os dados sao organizados de acordo com
			// seus tipos
			while (resultado.next() ){
				matricula = resultado.getString("matricula");
				nome = resultado.getString("nome");
				disciplina = resultado.getString("disciplina");
				turma = resultado.getString("turma");
				sala = resultado.getString("sala");
			}		
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {		
			
			// Fecha a conexao apos o termino da Consulta
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Cria um objeto String para retornar ao servidor que vai transmitir atraves da rede
		// para o cliente
		return new String(matricula + "#" + nome + "#" + disciplina + "#" + turma + "#" + sala);				
	}
	
	public void insertData(String sql) {
		conectaBanco();
		
		try {
			comando.executeUpdate(sql);

		} catch(Exception e) {
			e.printStackTrace();
		}	finally {		
			
			// Fecha a conexao apos o termino da Consulta
			try {
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}