package commons;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


/*
 * Classe Cliente responsavel pela comunicacao da aplicacao com o Servidor, e deste com o Banco de Dados.
 * O metodo start() e o metodo principal da classe, que recebe uma String contendo um comando SQL, que sera
 * passada para o Servidor, e retorna outra String contendo as informacoes requeridas.
 * 
 * Mas embaixo veremos o funcionamento da classe.
 * 
 *  * CLASSE FUNCIONANDO PERFEITAMENTE *
 * 
 */

/**
 * @author Serginho
 */

public class Cliente {
	
	// Socket e buffers de dados
	private Socket socket = null; // OK!
	private BufferedReader input = null; // OK!
	private PrintStream output = null; // OK!	
	private String recebido = null; // OK!
	
	public static Rede net = new Rede();
	
	// Metodo start() recebe uma String contendo um comando Sql para ser executado no Banco de Dados e retorna
	// Uma String contendo as informacoes requeridas. Primeiro o metodo chama o metodo conecta(), e depois 
	// chama os metodos enviaDados() e recebeDados(), que armazena a String com as informacoes requeridas.
	// Por fim, o metodo chama a funcao de encerrar conexoes.
	public String start(String sql) {
		
		try {
			conecta();
			enviaDados(sql);
			
			Pattern p = Pattern.compile("SELECT");  
			Matcher m = p.matcher(sql);  
			  
			if ( m.find() ) recebido = recebeDados();
			
		} catch( IOException e){
			JOptionPane.showMessageDialog(null, "Problemas com a conexao de rede !");
		} finally {
			
			try {
				fechaConexao();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return recebido;
	}

	// Cria um socket de rede que se conecta com o Servidor no local especificado no Construtors do socket
	private void conecta() throws IOException {
		socket = new Socket(net.getIp() , net.getPorta());
	}
	
	// Instancia o buffer de saida que enviara o comando SQL para o Servidor
	private void enviaDados(String sql) throws IOException {
		output = new PrintStream(socket.getOutputStream() );
		output.println(sql);
	}
	
	// Instancia o buffer de leitura de dados que retornara os dados resgatados do Banco
	private String recebeDados() throws IOException {
		input = new BufferedReader(new InputStreamReader(socket.getInputStream() ) );
		return input.readLine();
	}

	// Fecha o Socket de rede
	private void fechaConexao() throws IOException {
		socket.close();
		output.close();
		input.close();
	}	
}