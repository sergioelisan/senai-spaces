package mapa;

import javax.swing.*;

import commons.*;

import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

/*
 * 
 * Autores: Felipe de Souza Araujo e Srgio Eduardo
 * 
 * Classe Mapa v1.0 (Tela v2.0 - reformada para conter novas abas)
 * 
 * MUDANCAS EM RELACAO A ANTIGA CLASSE "TELA"
 * 
 * 	- A imagem agora esta dentro do .JAR, entao agora tem
 *  total portabilidade
 * 
 *  - A Classe agora herda de JFrame;
 *  
 *  - Agora eh mais facil adicionar abas ao Frame;
 *  
 *  - A Classe Mapa depende da classe ImagePanel para exibir
 *  o mapa ao fundo da aba, mas os metodos sao os mesmos;
 * 
 *  - Codigo resumido e corrigidos alguns bugs e melhorada a legibilidade,
 *  mas sua logica interna e seus algoritmos se mantem inalterados
 *  
 *  - Adicao de constantes Rectangle[] para definir o tamanho e a posicao dos
 *  botoes no mapa.
 * 
 */

@SuppressWarnings("serial")
public class Mapa extends JFrame {
	
	private ImagePanel manha, tarde, noite;
	private final Rectangle[] r = new Rectangle[20];
	
	// Metodo executavel
	public static void main(String[] args) {
		new Mapa().start();
	} // FIM DO METODO EXECUTAVEL

	// Contrutor da classe, insere um titulo, define o tamanho da janela e a visibilidade
	private Mapa() {
		super("Mapa do SENAI Areias");		
		criaRetangulos();		
			
		Toolkit kit = Toolkit.getDefaultToolkit();
		this.setLayout(new GridBagLayout() );
		this.setBounds(0, 0, kit.getScreenSize().width, kit.getScreenSize().height);
		this.setExtendedState(MAXIMIZED_BOTH);
		
	} // FIM DO CONSTRUTOR
	
	private void start() {		
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(5, 5, 5, 5);
		
		// Super Panel que ira comportar as abas
		JTabbedPane aba = new JTabbedPane();
		
		// Panels que irao comportar os mapas e os botoes
		manha = new ImagePanel();		
		tarde = new ImagePanel();	
		noite = new ImagePanel();
		
		aba.addTab("Manha", manha);
		criabotaoManha();
		
		aba.addTab("Tarde", tarde);
		criabotaoTarde();
		
		aba.addTab("Noite", noite);
		criabotaoNoite();
		
		// Criacao da aba de Pesquisa, que instancia um objeto da classe Pesquisa
		aba.addTab("Pesquisa", new Pesquisa().getPanel() );

		// Criacao da aba de Preferencias, que instancia um objeto da classe Preferencias
		aba.addTab("Preferencias", new Preferencias().getPanel() );
		
		this.getContentPane().add(aba, cons);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	} // FIM DO METODO START
	
	
	/*
	 * Metodo responsavel por setar os tamanhos e as posicoes dos JButtons
	 */
	
	private void criaRetangulos() {
		
		// Posicoes dos botoes do predio de TI
		r[0] = new Rectangle(359, 598, 88, 65);
		r[1] = new Rectangle(450, 598, 85, 65);
		r[2] = new Rectangle(537, 598, 71, 65);
		r[3] = new Rectangle(679, 598, 115, 65);
		r[4] = new Rectangle(799, 577, 128, 87);
		r[5] = new Rectangle(827, 490, 85, 35);
		r[6] = new Rectangle(712, 425, 60, 120);
		r[7] = new Rectangle(827, 377, 85, 110);
		r[8] = new Rectangle(712, 286, 60, 110);
		r[9] = new Rectangle(712, 181, 62, 104);
		r[10] = new Rectangle(712, 399, 60, 24);
		r[11] = new Rectangle(827, 212, 85, 165);
		
		// Posicoes dos botoes do predio de CT
		r[12] = new Rectangle(11, 488, 80, 100);
		r[13] = new Rectangle(11, 393, 80, 95);
		r[14] = new Rectangle(11, 278, 80, 115);
		r[15] = new Rectangle(11, 163, 80, 116);
		r[16] = new Rectangle(11, 98, 102, 63);
		r[17] = new Rectangle(11, 34, 102, 63);
		r[18] = new Rectangle(154, 34, 72, 61);
        r[19] = new Rectangle(228, 34, 82, 61);
	} // FIM DO METODO DE CRIACAO DOS RETANGULOS
	
	
	/*
	 *  METODOS QUE CRIAM OS BOTOES DA INTERFACE
	 */

	// CRIA OS BOTOES DA ABA MANHA
	
	private void criabotaoManha() {
		
		// Cria o "handler" que captura o evento gerado pelo botao
		Acao acao = new Acao("TB_MANHA");
		
		JButton[] botao = new JButton[20];
		
		// Cria botoes que simbolizam as salas do predio de Informatica
		for(int i = 0; i < 12; i++)	{
			botao[i] = new JButton( (i+1) + "-TI");
			botao[i].setBackground(Color.WHITE);
			botao[i].setBounds(r[i]);
		}			
		
		// Cria botoes que simbolizam as salas do predio do Curso Tecnico
		for(int i = 12; i < 20; i++) {
			botao[i] = new JButton((i - 11) + "-CT");
			botao[i].setBackground(Color.WHITE);
			botao[i].setBounds(r[i]);
		}		
		
		// Coloca os nomes das salas especiais
		botao[0].setText("Professores");
		botao[5].setText("Suporte");
		botao[10].setText("Serv");		
		botao[11].setText("Auditorio");
				
		for(int j = 0; j < 20; j++)	{
			botao[j].addActionListener(acao);
			manha.add(botao[j]);
		}
	} // FIM DO METODO QUE CRIA OS BOTOES DA ABA MANHA

	
	// CRIA OS BOTOES DA ABA TARDE
	
	private void criabotaoTarde() {

		// Cria o "handler" que captura o evento gerado pelo botao
		Acao acao = new Acao("TB_TARDE");
		
		JButton[] botao = new JButton[20];
				
		// Cria botoes que simbolizam as salas do predio de Informatica
		for(int i = 0; i < 12; i++)	{
			botao[i] = new JButton( (i+1) + "-TI");
			botao[i].setBackground(Color.WHITE);
			botao[i].setBounds(r[i]);
		}			
		
		// Cria botoes que simbolizam as salas do predio do Curso Tecnico
		for(int i = 12; i < 20; i++) {
			botao[i] = new JButton((i - 11) + "-CT");
			botao[i].setBackground(Color.WHITE);
			botao[i].setBounds(r[i]);
		}		
		
		// Coloca os nomes das salas especiais
		botao[0].setText("Professores");
		botao[5].setText("Suporte");
		botao[10].setText("Serv");		
		botao[11].setText("Auditorio");
				
		for(int j = 0; j < 20; j++)	{
			botao[j].addActionListener(acao);
			tarde.add(botao[j]);
		}
	} // FIM DO METODO QUE CRIA OS BOTOES DA ABA TARDE
	
	
	// CRIA OS BOTOES DA ABA NOITE
	
	private void criabotaoNoite() {

		// Cria o "handler" que captura o evento gerado pelo botao
		Acao acao = new Acao("TB_NOITE");
		
		JButton[] botao = new JButton[20];
				
		// Cria botoes que simbolizam as salas do predio de Informatica
		for(int i = 0; i < 12; i++)	{
			botao[i] = new JButton( (i+1) + "-TI");
			botao[i].setBackground(Color.WHITE);
			botao[i].setBounds(r[i]);
		}			
		
		// Cria botoes que simbolizam as salas do predio do Curso Tecnico
		for(int i = 12; i < 20; i++) {
			botao[i] = new JButton((i - 11) + "-CT");
			botao[i].setBackground(Color.WHITE);
			botao[i].setBounds(r[i]);
		}		
		
		// Coloca os nomes das salas especiais
		botao[0].setText("Professores");
		botao[5].setText("Suporte");
		botao[10].setText("Serv");		
		botao[11].setText("Auditorio");
				
		for(int j = 0; j < 20; j++)	{
			botao[j].addActionListener(acao);
			noite.add(botao[j]);
		}
	} // FIM DO METODO DE CRIACO DOS BOTOES DA ABA NOITE
	
	
	// CRIA A JANELINHA DE POPUP QUE IRA EXIBIR AS INFORMACOES DA SALA
	
	private void criaPopup(String info) {
		StringTokenizer token = new StringTokenizer(info);
		String matricula = token.nextToken("#");
		String nome = token.nextToken("#");
		String disciplina = token.nextToken("#");;
		String turma = token.nextToken("#");
		String sala = token.nextToken("#");
		
		GridBagConstraints cons = new GridBagConstraints();
		
		JFrame popup = new JFrame("Sala  " + sala);
		popup.setLayout(new GridBagLayout() );
		popup.setBounds(300, 300, 300, 210);
		popup.setVisible(true);
		popup.setResizable(false);
		
		JLabel l_matricula = new JLabel("Matricula:    " + matricula);
		JLabel l_nome = new JLabel("Nome:          " + nome);
		JLabel l_disciplina = new JLabel("Disciplina:   " + disciplina);
		JLabel l_turma = new JLabel("Turma:        " + turma);
		
		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(7, 7, 7, 7);
		
		cons.gridx = 0;
		
		cons.gridy = 0;
		popup.getContentPane().add(l_matricula, cons);
		
		cons.gridy = 1;		
		popup.getContentPane().add(l_nome, cons);
		
		cons.gridy = 2;		
		popup.getContentPane().add(l_disciplina, cons);
		
		cons.gridy = 3;		
		popup.getContentPane().add(l_turma, cons);
	} // FIM DO METODO CRIAPOPUP
		
	/*
	 * Acoes acionadas pelo botoes da Interface 
	 */
	
	private class Acao implements ActionListener {
		
		private String tabela;

		public Acao(String tabela) {
			this.tabela = tabela;
		}
		
		public void actionPerformed(ActionEvent evento) {
			String info = new Cliente().start("SELECT * FROM "
					+ tabela + " WHERE sala='" + ((JButton) evento.getSource()).getText() + "'");	
			criaPopup(info);
		}
	}	
}