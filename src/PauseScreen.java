

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class PauseScreen extends JPanel{
	BufferedImage PauseScreen;
	JPanel pausepanel;
	JFrame pauseframe;
	PauseScreen(){
		pauseframe = new JFrame();
		pausepanel = new JPanel();
		pausepanel.setPreferredSize(new Dimension(400, 400));
		pausepanel.setOpaque(true); 
		pauseframe.setContentPane(pausepanel);
		pauseframe.pack();
		pauseframe.setVisible(true);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			PauseScreen = ImageIO.read(new File("res/img/pausescreen.PNG"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(PauseScreen,0,0,400,400,null);
	}
}


