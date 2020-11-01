import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingConstants;


public class RunGame {
	protected static  JFrame frame;
	public JFrame newFrame;
	JComponent ContentPane;
	boolean newGame = false;
	public RunGame(boolean a){

		frame = new JFrame("Tetris");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ContentPane = new theGame(a);
		ContentPane.setOpaque(true); 
		frame.setContentPane(ContentPane);
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-350, 0);
		frame.pack();
		frame.setVisible(true);
		System.out.println("hi");
		



	}
	public static JFrame getFrame(){
		return frame;
	}

	public static void main (String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new RunGame(true);
			}
		});
	}
}















