package commons;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cadastro.TamanhoFixo;

/*
 * Classe que cria um Objeto que herda de JPanel que  geralmente usado para preencher abas 
 * em telas em que o usuario pode precisar de informacoes.
 * 
 *  Possui independencia das classes que a implementam.
 * 
 */

@SuppressWarnings("serial")
public class Pesquisa extends JPanel {
	
	private JComboBox combo;

	// Metodo que retorna o Panel criado ao longo dos metodos abaixo
	public JPanel getPanel() {
		return this;		
	}
	
	public Pesquisa() {
		this.setVisible(true);
		GridBagConstraints grid = new GridBagConstraints();
				
		this.setLayout(new GridBagLayout() );
		
		// ComboBox
		
		JPanel panel6 = new JPanel(new GridBagLayout() );
		panel6.setBorder(BorderFactory.createTitledBorder("Perodo do Dia") );
		
		String horarios[] = {"Manh", "Tarde", "Noite"}; 
		combo = new JComboBox(horarios);
		
		grid.gridx = 0;
		grid.gridy = 0;
		grid.insets = new Insets(7,115,7,115);
		panel6.add(combo, grid);
		
		
		// Painel de Pesquisa por Matricula
		
		JPanel panel4 = new JPanel(new GridBagLayout() );
		panel4.setBorder(BorderFactory.createTitledBorder("Pesquisa por Matrcula do Docente") );
				
		grid.insets = new Insets(6,70,6,6);
		grid.fill = GridBagConstraints.BOTH;
		
		JLabel l_matricula = new JLabel("Matricula");
		grid.gridy = 0;
		panel4.add(l_matricula, grid);
	
		final JFormattedTextField campo_matr = new JFormattedTextField();
		campo_matr.setDocument(new TamanhoFixo(4) );
		grid.insets = new Insets(6,6,6,70);
		grid.weightx = 1;	
		grid.gridx = 1;
		panel4.add(campo_matr, grid);
		
		final JButton b_pesquisar_matr = new JButton("Pesquisar");
		b_pesquisar_matr.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evento) {
				if (evento.getSource().equals(b_pesquisar_matr) ) {
					
					String dados = new Cliente().start("SELECT * FROM " + testaHorario() +
							" WHERE matricula='" + campo_matr.getText() + "'");
					campo_matr.setText(null);
					criaPopUp(dados);
				}
			}
			
		});
		grid.insets = new Insets(6,6,6,70);
		grid.gridy = 1;
		grid.fill = GridBagConstraints.CENTER;
		panel4.add(b_pesquisar_matr, grid);
		
		grid.gridx = 0;
		grid.gridy = 0;		
		this.add(panel4, grid);
		
		
		// Painel de Pesquisa por Sala
		
		JPanel panel5 = new JPanel(new GridBagLayout() );
		panel5.setBorder(BorderFactory.createTitledBorder("Pesquisa por Turma") );
		
		grid.insets = new Insets(6,90,6,6);
		grid.fill = GridBagConstraints.BOTH;
		
		JLabel l_sala = new JLabel("Turma");
		grid.gridx = 0;
		grid.gridy = 0;
		panel5.add(l_sala, grid);
	
		final JFormattedTextField campo_sala = new JFormattedTextField();
		campo_sala.setDocument(new TamanhoFixo(10) );
		grid.insets = new Insets(6,6,6,70);
		grid.weightx = 1;	
		grid.gridx = 1;
		grid.gridy = 0;	
		panel5.add(campo_sala, grid);
		
		final JButton b_pesquisar_sala = new JButton("Pesquisar");
		b_pesquisar_sala.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evento) {
				if (evento.getSource().equals(b_pesquisar_sala) ) {
					String dados = new Cliente().start("SELECT * FROM " + testaHorario() +
							" WHERE turma='" + campo_sala.getText() + "'");
					campo_sala.setText(null);
					criaPopUp(dados);
				}
			}
			
		});
		grid.insets = new Insets(6,6,6,70);
		grid.gridx = 1;
		grid.gridy = 1;
		grid.fill = GridBagConstraints.CENTER;
		panel5.add(b_pesquisar_sala, grid);
		
		grid.gridx = 0;
		grid.gridy = 1;		
		this.add(panel4, grid);
		
		grid.gridx = 0;
		grid.gridy = 2;
		this.add(panel5, grid);
		
		grid.gridx = 0;
		grid.gridy = 0;		
		this.add(panel6, grid);
		
	}
	
	private String testaHorario() {
		
		String horario = (String) combo.getSelectedItem();
		
		if (horario.equals("Manh") ){
			return "TB_MANHA";
		} else if (horario.equals("Tarde") ) {
			return "TB_TARDE";
		} else if (horario.equals("Noite") ) {
			return "TB_NOITE";
		}
		
		return null;  
	}
	
	public void criaPopUp(String dados) {
		GridBagConstraints cons = new GridBagConstraints();
		String matricula, nome, disciplina, turma, sala;
		
		StringTokenizer token = new StringTokenizer(dados);
		matricula = token.nextToken("#");
		nome = token.nextToken("#");
		disciplina = token.nextToken("#");;
		turma = token.nextToken("#");
		sala = token.nextToken("#");		
		
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
		
		cons.gridx = 0;
		cons.gridy = 1;		
		popup.getContentPane().add(l_nome, cons);
		
		cons.gridx = 0;
		cons.gridy = 2;		
		popup.getContentPane().add(l_disciplina, cons);
		
		cons.gridx = 0;
		cons.gridy = 3;		
		popup.getContentPane().add(l_turma, cons);
	}	
}
