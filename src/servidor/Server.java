
package servidor;

import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/*
 * Classe Server responsavel por transmitir os dados pela rede ate o cliente que solicitou a informacao.
 * Aqui sao criados os sockets de comunicacao e os streams de dados...
 * 
 * Aqui tambem existe uma instanciacao da classe DataBase que ira fazer consultas e insercoes no Banco
 * 
 * O funcionamento da classe esta detalhado logo mais.
 * 
 * * CLASSE FUNCIONANDO PERFEITAMENTE *
 * 
 */

/**
 * @author Serginho
 */

public class Server {

	// Campos que representam os sockets de comunicacao e os streams de dados
	private Socket socket = null; // OK!
	private ServerSocket server = null; // OK!
	private BufferedReader input = null; // OK!
	private PrintStream output = null; // OK!
	private int contador_de_conexoes = 0; // OK!	
	private String sql = null; // OK!
	private DataBase banco; // OK!
	
	// Metodo executavel que ira iniciar o servidor
	public static void main(String[] args) {
		new Server().start();
	}
	
	// Metodo que inicia os servicos do Servidor. Primeiro ele conecta, depois cria os Streams de dados,
	// depois ele processa a conexao e finalmente fecha conexao.
	public void start() {
		System.out.println("Servidor Iniciado!");
		
		do {
			try{
				conecta();		
				criaStreams();
				processaConexao();
			} catch (IOException e){
				System.out.println("Problemas com a conexao");
			} finally {
			
				try {
					fechaConexao();
					contador_de_conexoes++;
				} catch (IOException e) {
					System.out.println("Problemas ao fechar a conexao");
				}
			}
		} while(true);
	}
	
	// Cria os sockets para iniciar as operacoes de rede
	public void conecta() throws IOException {
		
		server = new ServerSocket(7000, 100);
		socket = server.accept();			
		System.out.println("\n=================== Conexao:" + contador_de_conexoes + "\nConectado com sucesso !");
	}
	
	// Cria os streams para a troca de dados com os Sockets
	public void criaStreams() throws IOException {
		
		input = new BufferedReader(new InputStreamReader(socket.getInputStream() ) );
		output = new PrintStream(socket.getOutputStream() );
		output.flush();
		System.out.println("Streams Criados com sucesso !");
	}
	
	// Dependendo do SQL, o metodo joga o processo para os metodos executeQuery ou insertData	
	private void processaConexao() throws IOException{
		banco = new DataBase();		
		sql = input.readLine();		
		String consultado = null;
		
		Pattern p = Pattern.compile("SELECT");  
		Matcher m = p.matcher(sql);  
		  
		if ( m.find() )
			consultado = banco.executeQuery(sql);
		else 
			banco.insertData(sql);
		
		output.print(consultado);		
		System.out.println("Operacao concluida com sucesso !");
	}
	
	// Fecha as conexoes abertas no inicio da execucao do Servidor.
	private void fechaConexao() throws IOException {
		socket.close();
		server.close();
		output.close();
		input.close();		
		System.out.println("Finalizado com sucesso !");
		System.out.println("============================\n");
	}
	
}

