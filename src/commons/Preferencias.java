package commons;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Preferencias extends JPanel {

	public Preferencias() {
		this.setLayout(new GridBagLayout() );
		
		GridBagConstraints cons = new GridBagConstraints();
		cons.insets = new Insets(5, 5, 5, 5);
		
		cons.gridx = 0;
		cons.gridy = 0;
		this.add(criaPanel1(), cons);
	} // FIM DO CONTRUTOR DA CLASSE PREFERENCIAS
	
	private JPanel criaPanel1(){
		GridBagConstraints cons = new GridBagConstraints();	
		JPanel panel = new JPanel(new GridBagLayout() );
		
		panel.setBorder(BorderFactory.createTitledBorder("Preferencias de Rede") );
		cons.insets = new Insets(15, 15, 5, 15);
				
		cons.gridx = 0;
		
		JLabel ip = new JLabel("IP");		
		cons.gridy = 0;
		panel.add(ip, cons);
		
		JLabel porta = new JLabel("Porta");
		cons.gridy = 1;
		panel.add(porta, cons);
				
		cons.gridx = 1;
		cons.weightx = 25;
		cons.fill = GridBagConstraints.BOTH;		
		
		final JFormattedTextField t_ip = new JFormattedTextField();
		cons.gridy = 0;
		panel.add(t_ip, cons);
		
		final JFormattedTextField t_porta = new JFormattedTextField();
		cons.gridy = 1;
		panel.add(t_porta, cons);
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Cliente.net.setIp(t_ip.getText() );
				int porta = Integer.parseInt(t_porta.getText() );
				Cliente.net.setPorta(porta);
				t_ip.setText(null);
				t_porta.setText(null);
			}
			
		});
		cons.fill = GridBagConstraints.CENTER;
		cons.gridy = 2;
		panel.add(ok, cons);
	
		
		panel.setPreferredSize(new Dimension(230, 200) );
		return panel;
	} // FIM DO METODO CRIAPANEL1

	
	// Metodo que retorna o Panel criado pelos metodos acima
	public JPanel getPanel() {
		return this;		
	}

}
