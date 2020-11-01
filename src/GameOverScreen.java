import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GameOverScreen {
	BufferedImage GameOverScreen;
	BufferedImage scoredisplay;
	BufferedImage playagain;
	BufferedImage mainmenu;
	BufferedImage highscores;
	GameOverScreen(){
		
	}
	public void paintComponent(Graphics g) {
		try {
			GameOverScreen = ImageIO.read(new File("res/img/gameoverscreen.bmp"));
			scoredisplay = ImageIO.read(new File("res/img/scoredisplay.bmp"));
			playagain = ImageIO.read(new File("res/img/playagainbuton.bmp"));
			mainmenu = ImageIO.read(new File("res/img/mainmenubutton.bmp"));
			highscores = ImageIO.read(new File("res/img/viewhighscoresbutton.bmp"));


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


