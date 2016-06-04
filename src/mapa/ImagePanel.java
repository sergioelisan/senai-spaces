package mapa;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
		
	public ImagePanel() {
		super(null);
		this.setOpaque(false);		
		this.setPreferredSize(new Dimension(1000, 675) );
	}
	
	public void paintComponent(Graphics g)	{
		ClassLoader classLoader = this.getClass().getClassLoader();  
		ImageIcon mapa = new ImageIcon(classLoader.getResource("image/mapa_senai.png") );
				
		if (mapa != null) {
			g.drawImage(mapa.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		}
		
		super.paintComponent(g);
	}
}