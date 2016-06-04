package cadastro;

import javax.swing.*;
import commons.*;
import cadastro.excecoes.*;
import java.awt.*;
import java.awt.event.*;

/*
 *
 * Autores: Srgio Eduardo e Felipe de Souza Araujo
 *
 * Classe Cadastro v1.2
 *
 * MUDANCAS:
 *
 *  - Melhoria na legibilidade do codigo
 *
 *  - Otimizacao dos algoritmos usados para construir a
 *  Interface Grafica
 *
 *  - Posicionamento do pacote das classes de excessoes
 *  como sub-pacote de "cadastro"
 *
 */

@SuppressWarnings("serial")
public class Cadastro extends JFrame {

	private JCheckBox radio_manha, radio_tarde, radio_noite;
	private JFormattedTextField textfield[] = new JFormattedTextField[5];

	// Metodo de execucao da aplicacao
	public static void main(String[] args) {
		new Cadastro().start();
	}

	// Metodo usado para inicializar a janela da aplicacao
	public void start() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Toolkit kit = Toolkit.getDefaultToolkit();
		int width = kit.getScreenSize().width;
		int height = kit.getScreenSize().height;

		this.setBounds(width / 8, height / 8, 740, 560);
	}

	// Construtor do Objeto Cadastro
	public Cadastro() {
		super("Formulario de Cadastro");
		this.setLayout(new GridBagLayout() );

		GridBagConstraints cons = new GridBagConstraints();

		// Cria a aba de Cadastro

		JTabbedPane aba = new JTabbedPane();
		JPanel panel_cadastro = new JPanel(new GridBagLayout() );

		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(5,5,5,5);

		cons.gridx = 0;
		cons.gridy = 0;
		panel_cadastro.add(criaFormulario(), cons);

		cons.gridy = 1;
		panel_cadastro.add(criaCheckBox(), cons);

		cons.gridy = 2;
		panel_cadastro.add(criaBotoes(), cons);

		// Abas da janela

		aba.addTab("Cadastro", panel_cadastro);
		aba.addTab("Pesquisa", new Pesquisa().getPanel() );
		aba.addTab("Preferencias", new Preferencias().getPanel() );

		aba.setPreferredSize(new Dimension(700, 500));
		this.getContentPane().add(aba, cons);
	}



	/*
	 * Metodo que cria os botoes e as suas acoes
	 */

	private JPanel criaBotoes() {
		GridBagConstraints cons = new GridBagConstraints();
		JPanel panel = new JPanel(new GridBagLayout() );

		final JButton submeter = new JButton("Submeter");
		submeter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String horario = null;

				if(radio_manha.isSelected() ) {
					horario = "TB_MANHA";
				} else if (radio_tarde.isSelected() ){
					horario = "TB_TARDE";
				} else if (radio_noite.isSelected() ) {
					horario = "TB_NOITE";
				}

				if (e.getSource() == submeter)
				{
					/*
					 * Tenta executar o mtodo subemeterDados()
					 * Se no conseguir execut-lo, trata as excees
					 */

					try {
						submeterDados(horario);
					}
					catch (MatriculaComLetrasException e0)
					{

						// Trata matriculas com letras
						JOptionPane.showMessageDialog(null, e0.getMessage());

					}
					catch (NomeComNumerosException e1)
					{

						// Trata nomes com Numeros
						JOptionPane.showMessageDialog(null, e1.getMessage());

					}
					catch (DisciplinaNaoCadastradaException e2)
					{

						// Trata disciplinas nao cadastradas
						JOptionPane.showMessageDialog(null, e2.getMessage());

					}
					catch (TurmaNaoCadastradaException e3)
					{

						// Trata turmas nao cadastradas
						JOptionPane.showMessageDialog(null, e3.getMessage());

					}
					catch (SalaNaoCadastradaException e4)
					{

						// Trata salas nao cadastradas
						JOptionPane.showMessageDialog(null, e4.getMessage());

					}
					catch (HorarioNaoCadastradoException e5)
					{

						// Trata horarios nao cadastrados
						JOptionPane.showMessageDialog(null, e5.getMessage());

					}
					catch (MatriculaNaoCadastradaException e6)
					{

						// Trata matriculas nao cadastradas
						JOptionPane.showMessageDialog(null, e6.getMessage());

					}
					catch (NomeNaoCadastradoException e7)
					{

						// Trata nomes nao cadastrados
						JOptionPane.showMessageDialog(null, e7.getMessage());
					}
				}
			} // FIM DO METODO ACTION PERFORMED


			/*
			 * Metodo submeterDados(), lana excecoes para que sejam tratadas
			 * Throws = pode lanar estas excecoes; Throw = lana nova excecao
			 */

			private void submeterDados(String horario) throws NomeComNumerosException, MatriculaComLetrasException,
			HorarioNaoCadastradoException, DisciplinaNaoCadastradaException, TurmaNaoCadastradaException,
			SalaNaoCadastradaException,	MatriculaNaoCadastradaException, NomeNaoCadastradoException
			{
				boolean letrasNaMatricula = false;

				for(int k = 0; k < textfield[0].getText().length(); k++)
				{
					int z = textfield[0].getText().charAt(k);
					if(z < 48 || z > 57)
						letrasNaMatricula = true;
				}

				boolean numerosNoNome = false;
				for(int k = 0; k < textfield[1].getText().length(); k++)
				{
					int z = textfield[1].getText().charAt(k);
					if( (z < 65 || z > 122) && z != 32)
						numerosNoNome = true;
				}

				// Testes de excees
				if(textfield[0].getText().length() <= 0)
					throw new MatriculaNaoCadastradaException("Informe a matrcula");

				else if(letrasNaMatricula)
					throw new MatriculaComLetrasException("Digite apenas caracteres numricos na matrcula");

				else if(textfield[1].getText().length() <= 0)
					throw new NomeNaoCadastradoException("Informe o nome");

				else if(numerosNoNome)
					throw new NomeComNumerosException("Digite apenas caracteres alfabticos no nome");

				else if(textfield[2].getText().length() <= 0)
					throw new DisciplinaNaoCadastradaException("Informe a disciplina");

				else if(textfield[3].getText().length() <= 0)
					throw new TurmaNaoCadastradaException("Informe a turma");

				else if(textfield[4].getText().length() <= 0)
					throw new SalaNaoCadastradaException("Informe a sala");

				else if(horario == null)
					throw new HorarioNaoCadastradoException("Selecione o horrio");
				else
				{
					// Se tudo der certo, ele envia os dados ao servidor

					String insert = ("INSERT INTO " + horario + "(matricula, nome, disciplina, turma, sala) " +
							"VALUES ('" + textfield[0].getText() + "', '" +
							textfield[1].getText() + "', '" + textfield[2].getText() + "', '" + textfield[3].getText()
							+ "', '" + textfield[4].getText() + "')");

					new Cliente().start(insert);

					// Limpa o conteudo dos textfields quando termina de submeter os dados
					for (int i = 0; i < textfield.length; i++) {
						textfield[i].setText(null);
					}
				}
			}
		});

		cons.fill = GridBagConstraints.CENTER;
		panel.add(submeter, cons);
		panel.setPreferredSize(new Dimension(500,50));
		return panel;

	}


	/*
	 *  Metodo que cria os CheckBoxes e os agrupa em Button Groups
	 */

	private JPanel criaCheckBox() {
		ButtonGroup bg = new ButtonGroup();
		GridBagConstraints cons = new GridBagConstraints();

		JPanel panel = new JPanel(new GridBagLayout() );
		panel.setBorder(BorderFactory.createTitledBorder("Perodo do Dia") );

		cons.gridy = 0;
		cons.insets = new Insets(0,20,0,20);

		radio_manha = new JCheckBox("Manh");
		cons.gridx = 1;
		panel.add(radio_manha, cons);

		radio_tarde = new JCheckBox("Tarde");
		cons.gridx = 2;
		panel.add(radio_tarde, cons);

		radio_noite = new JCheckBox("Noite");
		cons.gridx = 3;
		panel.add(radio_noite, cons);

		bg.add(radio_manha);
		bg.add(radio_noite);
		bg.add(radio_tarde);

		panel.setPreferredSize(new Dimension(500,80));
		return panel;
	}


	/*
	 *  Metodo usado para criar os labels e textfields, e organiza-los no Layout do Panel
	 */

	private JPanel criaFormulario() {
		GridBagConstraints cons = new GridBagConstraints();

		JPanel panel = new JPanel(new GridBagLayout() );
		panel.setBorder(BorderFactory.createTitledBorder("Formulrio de Cadastro") );

		cons.fill = GridBagConstraints.BOTH;
		cons.insets = new Insets(10, 10, 10, 10);

		// Cria os Labels que identificam os campos de texto

		JLabel label[] = new JLabel[5];
		String[] nomeLabel = {"Matricula", "Nome", "Disciplina", "Turma", "Sala"};

		cons.gridx = 0;
		for (int i = 0; i < label.length; i++) {
			label[i] = new JLabel(nomeLabel[i]);
			cons.gridy = i;
			panel.add(label[i], cons);
		}


		// Cria os campos de texto

		cons.gridx = 1;
		cons.weightx = 15;

		for (int i = 0; i < textfield.length; i++) {
			textfield[i] = new JFormattedTextField();
			textfield[i].setBorder(BorderFactory.createLineBorder(Color.black) );
			cons.gridy = i;

			panel.add(textfield[i], cons);
		}

		textfield[0].setDocument(new TamanhoFixo(4) );
		textfield[1].setDocument(new TamanhoFixo(200) );
		textfield[2].setDocument(new TamanhoFixo(200) );
		textfield[3].setDocument(new TamanhoFixo(10) );
		textfield[4].setDocument(new TamanhoFixo(5) );

		panel.setPreferredSize(new Dimension(600,250));
		return panel;
	}
}
