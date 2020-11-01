
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class theGame extends JPanel implements KeyListener, ActionListener{
	JPanel fullArea;
	theGame restartgame;
	RunGame sds;
	boolean startofgame = true;
	boolean question = false; 
	boolean pause = false;
	boolean escape = false;
	boolean mainmenu = false; 
	boolean highscores = false;
	boolean firstHold = true;
	boolean gameOver = false;
	boolean newGamePerm;
	boolean waitondrop = false;
	boolean funtimes = true;
	Graphics g;
	long time1 = 0;
	long time2 = 0;
	int holdup = 0;
	JTextField box = new JTextField();
	JTextField enterName = new JTextField();
	ImageIcon pauselogo = new ImageIcon("/Tetris Project/Tetris/res/img/pausebutton.PNG");
	ImageIcon questionlogo = new ImageIcon("/Tetris Project/Tetris/res/img/questionbutton.PNG");
	ImageIcon playlogo = new ImageIcon("/Tetris Project/Tetris/res/img/playbutton.jpg");
	ImageIcon restartlogo = new ImageIcon("/Tetris Project/Tetris/res/img/playagainbutton.jpg");
	ImageIcon mainmenulogo = new ImageIcon("/Tetris Project/Tetris/res/img/mainmenubutton.jpg");
	ImageIcon resumebuttonlogo = new ImageIcon("/Tetris Project/Tetris/res/img/resumegamebutton.PNG");
	ImageIcon viewhighscoreslogo = new ImageIcon("/Tetris Project/Tetris/res/img/viewhighscoresbutton.jpg");
	ImageIcon startgamelogo = new ImageIcon("/Tetris Project/Tetris/res/img/startgamebutton.jpg");
	ImageIcon viewcontrolslogo = new ImageIcon("/Tetris Project/Tetris/res/img/viewcontrolsbutton.jpg");
	JButton viewhighscoresbutton = new JButton (viewhighscoreslogo);
	JButton mainmenubutton = new JButton(mainmenulogo);
	JButton viewcontrolsbutton = new JButton (viewcontrolslogo);
	JButton startgamebutton = new JButton(startgamelogo);
	JButton resumebutton = new JButton (resumebuttonlogo);
	JButton pausebutton = new JButton(pauselogo);
	JButton questionbutton = new JButton(questionlogo);
	JButton playbutton = new JButton(playlogo);
	JButton restartbutton = new JButton(restartlogo);
	JPanel ContentPane;
	String direction;
	int [][] Landed = new int[18][10];
	int x = 210;
	int y = 100;
	int score = 0;
	int level = 1; 
	int totalRemoved = 0;
	int untillNextLevel = 5;
	int speed = 1000;
	boolean change = false;
	boolean startNew = true;
	int colornum = 0;
	int rank;
	boolean gameInProgress;
	boolean animatelinedeletion = false;
	ArrayList <Integer> Scores = new ArrayList(10);
	ArrayList <Integer> rowtodeletenumber = new ArrayList(10);
	ArrayList <String> Names = new ArrayList(10);
	ArrayList <Integer> rowsToDelete = new ArrayList();
	boolean endGame;
	boolean goodToGo = false;
	String playerName;
	private BufferedImage Orange;
	private BufferedImage GameOver;
	private BufferedImage Green;
	private BufferedImage Pink;
	private BufferedImage Red;
	private BufferedImage Yellow;
	private BufferedImage Blue;
	private BufferedImage DarkBlue;
	private BufferedImage Layout;
	private BufferedImage silverblocks;
	private BufferedImage blackblock;
	private BufferedImage GameOverScreen;
	private BufferedImage pausescreen;
	private BufferedImage LeaderBoardScreen; 
	private BufferedImage scoredisplay;
	private BufferedImage playagain;
	private BufferedImage MainMenuScreen;
	private BufferedImage questionscreen;
	private BufferedImage escapescreen;
	private Shape nextone;
	private Shape nexttwo;
	private Shape nextthree;
	private Shape holding;
	Timer timer;
	Shape shapetodrop; 
	Shape A = RandomShapeToDrop();
	public theGame(boolean brandnew){
		newGamePerm=brandnew;
		gameInProgress = brandnew;
		timer = new Timer(speed, this);
		timer.setInitialDelay(0);
		timer.setActionCommand("Drop One");
		timer.addActionListener(this);
		timer.start();
		for (int x = 0; x<=17; x++){
			for (int y = 0; y<=9; y++){
				Landed [x][y] = 0;
			}
		}

		this.setPreferredSize(new Dimension(700, 950));
		box.addKeyListener(this);
		setLayout(null);
		box.setBounds(0, 0, 0, 0);
		add(box);
		if (!brandnew){
			pausebutton.addActionListener(this);
			questionbutton.addActionListener(this);
			pausebutton.setHorizontalAlignment(SwingConstants.CENTER);
			questionbutton.setHorizontalAlignment(SwingConstants.CENTER);
			pausebutton.setBounds(525,25,40,40);
			questionbutton.setBounds(600,25,40,40);
			add(pausebutton);
			add(questionbutton);

		}else{
			mainmenu = true;
		}

	}
	public void step(){


	}
	public BufferedImage getImage(Shape s){
		if (s.getColor() == "Blue")
			return Blue;
		if (s.getColor() == "Dark Blue")
			return DarkBlue;
		if (s.getColor() == "Orange")
			return Orange;
		if (s.getColor() == "Yellow")
			return Yellow;
		if (s.getColor() == "Red")
			return Red;	
		if (s.getColor() == "Pink")
			return Pink;
		if (s.getColor() == "Green")
			return Green;
		return null;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage currentColor = null;
		try {
			Orange = ImageIO.read(new File("res/img/OrangeBlock.png"));
			Yellow = ImageIO.read(new File("res/img/YellowBlock.png"));
			Green = ImageIO.read(new File("res/img/GreenBlock.png"));
			Pink = ImageIO.read(new File("res/img/PinkBlock.png"));
			Red = ImageIO.read(new File("res/img/RedBlock.png"));
			Blue = ImageIO.read(new File("res/img/BlueBlock.png"));
			DarkBlue = ImageIO.read(new File("res/img/DarkBlueBlock.png"));
			silverblocks = ImageIO.read(new File("res/img/silverblocks.png"));
			
			Layout = ImageIO.read(new File("res/img/tetrislayoutfinal.png"));
			LeaderBoardScreen = ImageIO.read(new File("res/img/leaderboardscreen.png"));
			pausescreen = ImageIO.read(new File("res/img/escapescreen.png"));
			MainMenuScreen = ImageIO.read(new File("res/img/mainmenuscreen.png"));
			questionscreen = ImageIO.read(new File("res/img/controlscreen.png"));
			escapescreen = ImageIO.read(new File("res/img/escapescreen.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			GameOverScreen = ImageIO.read(new File("res/img/gameoverscreen.png"));
			escapescreen = ImageIO.read(new File("res/img/escapescreen.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//g.drawImage(GameOverScreen,0,0,750,900,null);

		if (A.getColor() == "Blue"){
			currentColor = Blue;
			colornum = 1;
		}
		else if (A.getColor() == "Dark Blue"){
			currentColor = DarkBlue;
			colornum = 2;
		}
		else if (A.getColor() == "Green"){
			currentColor = Green;
			colornum = 3;
		}
		else if (A.getColor() == "Orange"){
			currentColor = Orange;
			colornum = 4;
		}
		else if (A.getColor() == "Pink"){
			currentColor = Pink;
			colornum = 5;
		}
		else if (A.getColor() == "Red"){
			currentColor = Red;
			colornum = 6;
		}
		else if (A.getColor() == "Yellow"){
			currentColor = Yellow;
			colornum = 7;
		}

		g.drawImage(Layout,0,0,700,950,null);
		g.setColor(Color.white);
		drawLines(g);
		render(g);

		drawGrid(g);
		//		if (waitondrop){
		//			System.out.println("here");
		//			doAnimation(g);
		//		}

		if(startofgame){
			nextone = RandomShapeToDrop();
			nexttwo = RandomShapeToDrop();
			nextthree = RandomShapeToDrop();
		} 
		if(animatelinedeletion){

		}
		for (int xT = 0; xT<=3; xT++){
			for(int yT = 0; yT<=3; yT++){
				if (A.theShape[xT][yT] == 1){
					g.drawImage(currentColor,x+(xT*40),y+(yT*40),40,40,null);
				}

			}
		}
		for (int xT = 0; xT<=3; xT++){
			for(int yT = 0; yT<=3; yT++){
				if (nextone.theShape[xT][yT] == 1){
					g.drawImage(getImage(nextone),560 +(xT*20),520 +(yT*20),20,20,null);
				}

			}
		}
		for (int xT = 0; xT<=3; xT++){
			for(int yT = 0; yT<=3; yT++){
				if (nexttwo.theShape[xT][yT] == 1){
					g.drawImage(getImage(nexttwo),560 +(xT*20),600 +(yT*20),20,20,null);
				}

			}
		}
		if (firstHold);
		else{
			for (int xT = 0; xT<=3; xT++){
				for(int yT = 0; yT<=3; yT++){
					if (holding.theShape[xT][yT] == 1){
						g.drawImage(getImage(holding),550 +(xT*20),150 +(yT*20),20,20,null);
					}

				}
			}
		}
		startofgame = false; 
		if (!gameOver){
			g.setColor(Color.white);
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb);
			formatter.format((score + ""));
			StringBuilder sb2 = new StringBuilder();
			Formatter formatter2 = new Formatter(sb2);
			formatter2.format((level + ""));
			StringBuilder sb3 = new StringBuilder();
			Formatter formatter3 = new Formatter(sb3);
			formatter3.format(("Lines To Clear to Level Up: "));
			StringBuilder sb4 = new StringBuilder();
			Formatter formatter4 = new Formatter(sb4);
			formatter4.format(("Level Up: " + untillNextLevel));
			g.drawString(sb.toString(), 575, 320);
			g.drawString(sb2.toString(), 580, 420);
			g.drawString(sb3.toString(), 500, 735);
			g.drawString(sb4.toString(), 525, 765);
		}
		if(!escape){
			add(questionbutton);
			add(pausebutton);
			remove(restartbutton);
			remove(mainmenubutton);
		}
		if(!question){
			add(questionbutton);
			add(pausebutton);
			remove(resumebutton);
		}
		if(!mainmenu){
			add(questionbutton);
			add(pausebutton);
			remove(viewhighscoresbutton);
			remove(startgamebutton);
			remove(viewcontrolsbutton);

		}
		if(question){
			remove(pausebutton);
			remove(questionbutton);
			mainmenubutton.addActionListener(this);
			mainmenubutton.setHorizontalAlignment(SwingConstants.CENTER);
			mainmenubutton.setBounds(350,750,250,45);
			add(mainmenubutton);
			if (!newGamePerm&&!gameInProgress){
				remove(mainmenubutton);
				funtimes = false;
				resumebutton.addActionListener(this);
				resumebutton.setHorizontalAlignment(SwingConstants.CENTER);
				resumebutton.setBounds(62,730,341,78);
				add(resumebutton);
				mainmenubutton.addActionListener(this);
				mainmenubutton.setHorizontalAlignment(SwingConstants.CENTER);
				mainmenubutton.setBounds(460,750,230,45);
				add(mainmenubutton);
				
			}else{
				restartbutton.addActionListener(this);
				restartbutton.setHorizontalAlignment(SwingConstants.CENTER);
				restartbutton.setBounds(50,750,250,45);
				add(restartbutton);
				remove (resumebutton);
			}
			g.drawImage(questionscreen, 0,0,700,900,null);
			timer.stop();
		}
		if(escape){
			
			remove(pausebutton);
			remove(questionbutton);
			g.drawImage(escapescreen, 0, 0, 750, 900, null);
			setLayout(null);
			restartbutton.addActionListener(this);
			restartbutton.setHorizontalAlignment(SwingConstants.CENTER);
			restartbutton.setBounds(50,750,250,45);
			add(restartbutton);
			mainmenubutton.addActionListener(this);
			mainmenubutton.setHorizontalAlignment(SwingConstants.CENTER);
			mainmenubutton.setBounds(350,750,250,45);
			add(mainmenubutton);
			funtimes = false;
			resumebutton.addActionListener(this);
			resumebutton.setHorizontalAlignment(SwingConstants.CENTER);
			resumebutton.setBounds(177,650,341,78);
			add(resumebutton);
			timer.stop();
		}
		if(highscores){
			g.drawImage(LeaderBoardScreen,0,0,750,900,null);
			setLayout(null);
			if (!newGamePerm&&!gameInProgress){
				funtimes = false;
				resumebutton.addActionListener(this);
				resumebutton.setHorizontalAlignment(SwingConstants.CENTER);
				resumebutton.setBounds(62,730,341,78);
				add(resumebutton);
				mainmenubutton.addActionListener(this);
				mainmenubutton.setHorizontalAlignment(SwingConstants.CENTER);
				mainmenubutton.setBounds(445,750,230,45);
				add(mainmenubutton);
				
			}else{
				startgamebutton.addActionListener(this);
				startgamebutton.setHorizontalAlignment(SwingConstants.CENTER);
				startgamebutton.setBounds(50,700,250,45);
				add(startgamebutton);
				remove (resumebutton);
				mainmenubutton.addActionListener(this);
				mainmenubutton.setHorizontalAlignment(SwingConstants.CENTER);
				mainmenubutton.setBounds(350,700,250,45);
				add(mainmenubutton);
			}
			
			remove(viewcontrolsbutton);
			remove(viewhighscoresbutton);
			remove(questionbutton);
			remove(pausebutton);
			remove(enterName);
			drawXML(g);
		}
		if(mainmenu){
			g.drawImage(MainMenuScreen,0,0,750,900,null);
			setLayout(null);
			startgamebutton.addActionListener(this);
			startgamebutton.setHorizontalAlignment(SwingConstants.CENTER);
			startgamebutton.setBounds(220,250,260,45);
			add(startgamebutton);
			viewhighscoresbutton.addActionListener(this);
			viewhighscoresbutton.setHorizontalAlignment(SwingConstants.CENTER);
			viewhighscoresbutton.setBounds(220,350,260,45);
			add(viewhighscoresbutton);
			viewcontrolsbutton.addActionListener(this);
			viewcontrolsbutton.setHorizontalAlignment(SwingConstants.CENTER);
			viewcontrolsbutton.setBounds(220,450,260,45);
			add(viewcontrolsbutton);
			remove(questionbutton);
			remove(pausebutton);
			remove(restartbutton);
			remove(mainmenubutton);
			remove(resumebutton);
			if (!newGamePerm && !gameInProgress){
				funtimes = false;
				resumebutton.addActionListener(this);
				resumebutton.setHorizontalAlignment(SwingConstants.CENTER);
				resumebutton.setBounds(177,700,341,78);
				add(resumebutton);
			}

		}
		if (gameOver){
			remove(questionbutton);
			remove(pausebutton);
			remove(restartbutton);
			remove(restartbutton);
			g.drawImage(GameOverScreen,0,0,750,900,null);
			g.setColor(Color.white);
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb);
			formatter.format((score + ""));
			StringBuilder sb2 = new StringBuilder();
			Formatter formatter2 = new Formatter(sb2);
			formatter2.format((level + ""));
			g.drawString(sb.toString(), 540, 725);
			g.drawString(sb2.toString(), 200, 725);
		}
		if(endGame){
			gameOver = true;
			gameInProgress = true;
			endGame = false;

		}if(!endGame){

		}
		repaint();


	}
	public void drawLines(Graphics g){
		g.drawLine(50,140,450,140);
		g.drawLine(50,180,450,180);
		g.drawLine(50,220,450,220);
		g.drawLine(50,260,450,260);
		g.drawLine(50,300,450,300);
		g.drawLine(50,340,450,340);
		g.drawLine(50,380,450,380);
		g.drawLine(50,420,450,420);
		g.drawLine(50,460,450,460);
		g.drawLine(50,500,450,500);
		g.drawLine(50,540,450,540);
		g.drawLine(50,580,450,580);
		g.drawLine(50,620,450,620);
		g.drawLine(50,660,450,660);
		g.drawLine(50,700,450,700);
		g.drawLine(50,740,450,740);
		g.drawLine(50,780,450,780);
		g.drawLine(90,100,90,820);
		g.drawLine(130,100,130,820);
		g.drawLine(170,100,170,820);
		g.drawLine(210,100,210,820);
		g.drawLine(250,100,250,820);
		g.drawLine(290,100,290,820);
		g.drawLine(330,100,330,820);
		g.drawLine(370,100,370,820);
		g.drawLine(410,100,410,820);

	}
	private void render(Graphics g) {


		if (change){
			if (direction.compareTo("left")==0){
				if ((A.getColor() == "Yellow")||(A.getColor() == "Pink" && A.getRotationLevel() !=2)||(A.getColor() == "Orange" && A.getRotationLevel() !=2)||(A.getColor() == "Dark Blue" && A.getRotationLevel() !=2)||(A.getColor() == "Blue" && A.getRotationLevel() !=1)||(A.getColor() == "Green" && A.getRotationLevel() ==1)||(A.getColor() == "Red" && A.getRotationLevel() ==1)){
					if (x<90);
					else
						x-=40;
				}else{
					if (x<50);
					else
						x-=40;
				}
			}else if (direction.compareTo("right")==0){
				if (A.getColor() == "Blue" && A.getRotationLevel() == 2){
					if (x>=290);
					else
						x+=40;
				}
				else if ((A.getColor() == "Yellow")||(A.getColor() == "Pink" && A.getRotationLevel() ==4)||(A.getColor() == "Orange" && A.getRotationLevel() ==4)||(A.getColor() == "Dark Blue" && A.getRotationLevel() ==4)||(A.getColor() == "Blue" && A.getRotationLevel() == 1)){
					if (x>=370);
					else
						x+=40;
				}else{
					if (x>=330);
					else
						x+=40;
				}
			}else if (direction.compareTo("up")==0){
				if ((x<50)&&((A.getColor() == "Pink" && A.getRotationLevel() == 2)||(A.getColor() == "Orange" && A.getRotationLevel() ==2)||(A.getColor() == "Dark Blue" && A.getRotationLevel()==2)||(A.getColor() == "Blue" && A.getRotationLevel() ==1)||(A.getColor() == "Green" && A.getRotationLevel() ==2)||(A.getColor() == "Red" && A.getRotationLevel() ==2))){
					x+=40;
				}else if ((x>=370)&&((A.getColor() == "Pink" && A.getRotationLevel() ==4)||(A.getColor() == "Orange" && A.getRotationLevel() ==4)||(A.getColor() == "Dark Blue" && A.getRotationLevel() ==4))){
					x-=40;
				}else if ((x>=370)&&(A.getColor() == "Blue" && A.getRotationLevel() == 1)){
					x-=80;
				}
				A.rotateShape();
			}else if (direction.compareTo("down")==0){
				int row = calculateRow();
				int reference = calculateY(row);
				if (y>=reference){
					ProcessLanding(g);
					if (reference<100){
						endGame = true;
						endGame();
					}
				}else{
					y+=40;
				}
			}if (direction.compareTo("drop")==0){
				int row = calculateRow();
				y = calculateY(row);
				ProcessLanding(g);
				if (y<100){
					endGame = true;
					endGame();
				}
			}
			else;
			change = false;
		}else;

	}
	private int calculateRow(){
		int row = 0;
		if ((A.getColor() == "Blue")||((A.getColor() == "Dark Blue")&& ((A.getRotationLevel() == 4)||(A.getRotationLevel() == 3)))||((A.getColor() == "Orange")&& ((A.getRotationLevel() == 3)||(A.getRotationLevel() == 4)))||((A.getColor() == "Pink")&& ((A.getRotationLevel() == 3)||(A.getRotationLevel() == 4)))){
			for (int xT = 0; xT<=3; xT++){
				for(int yT = 0; yT<=3; yT++){
					if (A.theShape[xT][yT] == 1){
						row = yT+1;
					}
				}
			}
		}else{
			for (int xT = 0; xT<=3; xT++){
				for(int yT = 0; yT<=3; yT++){
					if (A.theShape[xT][yT] == 1){
						row = xT+1;
					}
				}
			}
		}
		return row;
	}

	private int calculateY (int row){
		//
		//
		//CALCULATE Y FOR ROW 2'S
		//
		//
		int yR = 0;
		if (row == 2){
			ArrayList <Integer> BValues = new ArrayList();
			BValues.add(18);
			boolean needY = true;
			if (A.getColor() == "Blue"){
				for (int a = 0; a<4; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							BValues.add(b);
							needY = false;
						}else;
					}
				}
				Collections.sort(BValues);
				int yB = BValues.get(0);
				yR = (yB*40)-40;
			}else if (A.getColor() == "Yellow"){
				for (int a = 0; a<2; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							BValues.add(b);
							needY = false;
						}else;
					}
				}
				Collections.sort(BValues);
				int yB = BValues.get(0);
				yR = (yB*40)-40;
			}
			else{
				for (int a = 0; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							BValues.add(b);
							needY = false;
						}else;
					}
				}
				Collections.sort(BValues);
				int yB = BValues.get(0);
				yR = (yB*40)-40;
			}
			if (needY)
				yR = 740;
			else;

			//
			//
			//CALCULATE Y FOR ROW 3'S
			//
			//

		}else if (row == 3){
			boolean needY = true;
			ArrayList <Integer> BValues = new ArrayList();
			BValues.add(18);
			ArrayList <Integer> CValues = new ArrayList();
			CValues.add(18);



			//ORANGE


			if(A.getColor() == "Orange" && A.getRotationLevel() == 1){
				for (int a = 0; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a!=2){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}
			}else if(A.getColor() == "Orange" && A.getRotationLevel() == 2){
				for (int a = 1; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a!=1){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+2){
					yR = (yB*40)-0;
				}else{
					yR = (yC*40)-80;
				}

			}else if(A.getColor() == "Orange" && A.getRotationLevel() == 4){
				for (int a = 0; a<2; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							BValues.add(b);
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				yR = (yB*40)-80;

			}



			//DARK BLUE

			if(A.getColor() == "Dark Blue" && A.getRotationLevel() == 1){
				for (int a = 0; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a!=0){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}
			}else if(A.getColor() == "Dark Blue" && A.getRotationLevel() == 4){
				for (int a = 0; a<2; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a!=1){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+2){
					yR = (yB*40)-0;
				}else{
					yR = (yC*40)-80;
				}

			}else if(A.getColor() == "Dark Blue" && A.getRotationLevel() == 2){
				for (int a = 1; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							BValues.add(b);
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				yR = (yB*40)-80;

			}



			//GREEN

			if(A.getColor() == "Green" && A.getRotationLevel() == 1){
				for (int a = 0; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a==0){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}
			}else if((A.getColor() == "Green" && A.getRotationLevel() == 2)||(A.getColor() == "Pink" && A.getRotationLevel() == 2)){
				for (int a = 1; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a!=1){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}

			}



			//RED

			if(A.getColor() == "Red" && A.getRotationLevel() == 1){
				for (int a = 0; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a==2){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}
			}else if((A.getColor() == "Red" && A.getRotationLevel() == 2)){
				for (int a = 1; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a!=2){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}

			}



			//PINK

			if(A.getColor() == "Pink" && A.getRotationLevel() == 1){
				for (int a = 0; a<3; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a!=1){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}
			}else if(A.getColor() == "Pink" && A.getRotationLevel() == 4){
				for (int a = 0; a<2; a++){
					for (int b = (y/40)+1; b<18; b++){
						if (Landed[b][(x/40)+a-1]!=0){
							needY = false;
							if (a==0){
								BValues.add(b);
							}else{
								CValues.add(b);
							}
						}else; 
					}
				}
				Collections.sort(BValues);
				Collections.sort(CValues);
				int yB = BValues.get(0);
				int yC = CValues.get(0);
				if (yC>yB+1){
					yR = (yB*40)-40;
				}else{
					yR = (yC*40)-80;
				}
			}

			if (needY)
				yR = 700;
			else;




			//
			//
			//CALCULATE Y FOR ROW 4'S
			//
			//

		}else if (row == 4){
			ArrayList <Integer> BValues = new ArrayList();
			BValues.add(18);
			boolean needY = true;
			for (int b = (yR/40); b<18; b++){
				if (Landed[b][(x/40)]!=0){
					BValues.add(b);
					needY = false;
				}else;
			}
			Collections.sort(BValues);
			int yB = BValues.get(0);
			yR = (yB*40)-120;
			if (needY)
				yR = 660;
			else;
		}
		return yR;
	}
	private void drawGrid(Graphics g) {
		//		if (time1> time2)
		//			waitondrop = false;
		for (int xT = 0; xT<=17; xT++){
			for(int yT = 0; yT<=9; yT++){
				if (!waitondrop){
					if (Landed[xT][yT] == 1)
						g.drawImage(Blue,50+(yT*40),100+(xT*40),40,40,null);
					else if (Landed[xT][yT] == 2)
						g.drawImage(DarkBlue,50+(yT*40),100+(xT*40),40,40,null);
					else if (Landed[xT][yT] == 3)
						g.drawImage(Green,50+(yT*40),100+(xT*40),40,40,null);
					else if (Landed[xT][yT] == 4)
						g.drawImage(Orange,50+(yT*40),100+(xT*40),40,40,null);
					else if (Landed[xT][yT] == 5)
						g.drawImage(Pink,50+(yT*40),100+(xT*40),40,40,null);
					else if (Landed[xT][yT] == 6)
						g.drawImage(Red,50+(yT*40),100+(xT*40),40,40,null);
					else if (Landed[xT][yT] == 7)
						g.drawImage(Yellow,50+(yT*40),100+(xT*40),40,40,null);

				}else{
					for(int f = 0; f<rowsToDelete.size(); f++){
						int c = rowsToDelete.get(f);
						if (xT == c){
							if (Landed[xT][yT] == 1)
								g.drawImage(Blue,50+(yT*40),100+(xT*40),40,40,null);
							else if (Landed[xT][yT] == 2)
								g.drawImage(DarkBlue,50+(yT*40),100+(xT*40),40,40,null);
							else if (Landed[xT][yT] == 3)
								g.drawImage(Green,50+(yT*40),100+(xT*40),40,40,null);
							else if (Landed[xT][yT] == 4)
								g.drawImage(Orange,50+(yT*40),100+(xT*40),40,40,null);
							else if (Landed[xT][yT] == 5)
								g.drawImage(Pink,50+(yT*40),100+(xT*40),40,40,null);
							else if (Landed[xT][yT] == 6)
								g.drawImage(Red,50+(yT*40),100+(xT*40),40,40,null);
							else if (Landed[xT][yT] == 7)
								g.drawImage(Yellow,50+(yT*40),100+(xT*40),40,40,null);
						}
					}

					holdup++;
					if (holdup >200){
						waitondrop = false;
						holdup = 0;
					}
				}

			}
		}
	}
	public void doAnimation (Graphics g){
		waitondrop = true;
		ArrayList <Integer> rowsToAvoid = new ArrayList();
		rowsToAvoid.add(-1);
		rowsToAvoid.add(-1);
		rowsToAvoid.add(-1);
		rowsToAvoid.add(-1);

		for(int f = 0; f<rowsToDelete.size(); f++){
			rowsToAvoid.set(f, rowsToDelete.get(f));
		}
		for(int f = 0; f<rowsToDelete.size(); f++){
			int c = rowsToDelete.get(f);
			for (int xT = 0; xT<=17; xT++){
				for(int yT = 0; yT<=9; yT++){
					if (xT != rowsToAvoid.get(0) || xT != rowsToAvoid.get(1) || xT != rowsToAvoid.get(2) || xT != rowsToAvoid.get(3)){
						if (Landed[xT][yT] == 1)
							g.drawImage(Blue,50+(yT*40),100+(xT*40),40,40,null);
						else if (Landed[xT][yT] == 2)
							g.drawImage(DarkBlue,50+(yT*40),100+(xT*40),40,40,null);
						else if (Landed[xT][yT] == 3)
							g.drawImage(Green,50+(yT*40),100+(xT*40),40,40,null);
						else if (Landed[xT][yT] == 4)
							g.drawImage(Orange,50+(yT*40),100+(xT*40),40,40,null);
						else if (Landed[xT][yT] == 5)
							g.drawImage(Pink,50+(yT*40),100+(xT*40),40,40,null);
						else if (Landed[xT][yT] == 6)
							g.drawImage(Red,50+(yT*40),100+(xT*40),40,40,null);
						else if (Landed[xT][yT] == 7)
							g.drawImage(Yellow,50+(yT*40),100+(xT*40),40,40,null);
					}
				}
			}


		}
		for(int f = 0; f<rowsToDelete.size(); f++){
			g.drawImage(silverblocks,50,100+(rowsToDelete.get(f)*40),400,40,null);
		}
	}
	private void ProcessLanding(Graphics g) {
		int xP = (x/40);
		int yP;
		boolean notOnGround = false;
		if((A.getColor() == "Blue")&&(A.getRotationLevel() ==1|| A.getRotationLevel() ==3)){
			yP = 14;
			if (y!=660)
				notOnGround = true;
			for (int xT=0; xT<4; xT++){
				for (int yT=0; yT<4;yT++){
					if (A.theShape[xT][yT] == 1){
						if (notOnGround){

							Landed [yT+yP-(15-(y/40))][xT+xP-1] =colornum;
						}else{
							Landed [yT+yP][xT+xP-1] =colornum;
						}
					}else{
						if((yT+yP)>=10 || (xT+xP-1)>=18);
						else{
							if (notOnGround){
								Landed [yT+yP-(15-(y/40))][xT+xP-1] =0;
							}else{
								Landed [yT+yP][xT+xP-1] =0;
							}
						}
					}
				}
			}
		}else if (((A.getColor() == "Blue")&&(A.getRotationLevel() ==0 || A.getRotationLevel() ==2))){
			yP = 16;
			if (y!=740)
				notOnGround = true;
			for (int xT=0; xT<4; xT++){
				for (int yT=0; yT<2;yT++){
					if (A.theShape[xT][yT] == 1){
						if (notOnGround){

							Landed [yT+yP-(17-(y/40))][xT+xP-1] =colornum;
						}else{
							Landed [yT+yP][xT+xP-1] =colornum;
						}
					}else{
						if((yT+yP)>=10 || (xT+xP-1)>=18);
						else{
							if (notOnGround){
								Landed [yT+yP-(17-(y/40))][xT+xP-1] =0;
							}else{
								Landed [yT+yP][xT+xP-1] =0;
							}
						}
					}
				}
			}
		}else if (((A.getColor() == "Yellow"))){
			yP = 16;
			if (y!=740)
				notOnGround = true;
			for (int xT=0; xT<2; xT++){
				for (int yT=0; yT<2;yT++){
					if (A.theShape[xT][yT] == 1){
						if (notOnGround){
							Landed [yT+yP-(17-(y/40))][xT+xP-1] =colornum;
						}else{
							Landed [yT+yP][xT+xP-1] =colornum;
						}
					}else{
						if((yT+yP)>=10 || (xT+xP-1)>=18);
						else{
							if (notOnGround){
								Landed [yT+yP-(17-(y/40))][xT+xP-1] =0;
							}else{
								Landed [yT+yP][xT+xP-1] =0;
							}
						}
					}
				}
			}
		}else if ((((A.getColor() == "Dark Blue"))&&(A.getRotationLevel()==3)) ||(((A.getColor() == "Orange"))&&(A.getRotationLevel()==3))||(((A.getColor() == "Pink"))&&(A.getRotationLevel()==3))){
			yP = 16;
			if (y!=740)
				notOnGround = true;
			for (int xT=0; xT<3; xT++){
				for (int yT=0; yT<2;yT++){
					if (A.theShape[xT][yT] == 1){
						if (notOnGround){

							Landed [yT+yP-(17-(y/40))][xT+xP-1] =colornum;
						}else{
							Landed [yT+yP][xT+xP-1] =colornum;
						}
					}else{
						if((yT+yP)>=10 || (xT+xP-1)>=18);
						else{
							if (notOnGround){
								Landed [yT+yP-(17-(y/40))][xT+xP-1] =0;
							}else{
								Landed [yT+yP][xT+xP-1] =0;
							}
						}
					}
				}
			}
		}else{
			yP = 15;
			if (y!=700)
				notOnGround = true;
			for (int xT=0; xT<3; xT++){
				for (int yT=0; yT<3;yT++){
					if (A.theShape[xT][yT] == 1){
						if (notOnGround){

							Landed [yT+yP-(16-(y/40))][xT+xP-1] =colornum;
						}else{
							Landed [yT+yP][xT+xP-1] =colornum;
						}
					}else{
						if((yT+yP)>=10 || (xT+xP-1)>=18);
						else{
							if (notOnGround){
								Landed [yT+yP-(16-(y/40))][xT+xP-1] =0;
							}else{
								Landed [yT+yP][xT+xP-1] =0;
							}
						}
					}
				}
			}
		}
		for (int x=0; x<=3; x++){
			for (int y=0; y<=3;y++){
				if (A.theShape[x][y] == 1){
					A.theShape[x][y] = 0;
				}
			}
		}

		for (int x = 0; x<=17; x++){
			for (int y = 0; y<=9; y++){
				if (Landed [x][y] != 0){
					if (y==9){
						rowsToDelete.add(x);
						waitondrop = true;
					}
				}else{
					y = 10;
				}
			}
		}
		if (rowsToDelete.size() ==0);
		else{
			doAnimation(g);
			Collections.sort(rowsToDelete);
			
			for (int x = 0; x<=rowsToDelete.size()-1; x++){
				int y = rowsToDelete.get(x);
				for (int p = 0; p<=9; p++){
					Landed[y][p] = 0;
				}
			}

		}
		if (rowsToDelete.size() == 1)
			score+=(100*level);
		else if (rowsToDelete.size() == 2)
			score+=(300*level);
		else if (rowsToDelete.size() == 3)
			score+=(500*level);
		else if (rowsToDelete.size() == 4)
			score+=(800*level);
		if(score>(level*1000)){
			goodToGo = true;
		}
		boolean contine = true;
		if (rowsToDelete.size() == 1){
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==0){
				levelUp(0);
				contine = false;
			}else
				untillNextLevel--;
			if (untillNextLevel==0&&contine){
				levelUp(0);
			}
			totalRemoved++;
		}else if (rowsToDelete.size() == 2){
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==1){
				levelUp(1);
				contine = false;
			}else if (contine)
				untillNextLevel--;
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==0 && contine){
				levelUp(0);
			}else if (contine)
				untillNextLevel--;
			totalRemoved+=2;
		}else if (rowsToDelete.size() == 3){
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==2){
				levelUp(2);
				contine = false;
			}else if (contine)
				untillNextLevel--;
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==1&& contine){
				levelUp(1);
				contine = false;
			}else if (contine)
				untillNextLevel--;
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==0&& contine){
				levelUp(0);
			}else if (contine)
				untillNextLevel--;
			totalRemoved+=3;
		}else if (rowsToDelete.size() == 4){
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==3){
				levelUp(3);
				contine = false;
			}else if (contine)
				untillNextLevel--;
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==2&&contine){
				levelUp(2);
				contine = false;
			}else if (contine)
				untillNextLevel--;
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==1&&contine){
				levelUp(1);
				contine = false;
			}else if (contine)
				untillNextLevel--;
			deleteRow(rowsToDelete, g);
			rowsToDelete.remove(0);
			if (untillNextLevel==0&&contine){
				levelUp(0);
			}else if (contine)
				untillNextLevel--;
			totalRemoved+=4;
		}
		if (untillNextLevel<=0){
			levelUp(0);
			untillNextLevel=(level*5);
		}

		A = nextone; 
		nextone = nexttwo;
		nexttwo = nextthree;
		nextthree = RandomShapeToDrop();


	}
	public void levelUp(int extraLines){
		level++;
		untillNextLevel = level*5;
		untillNextLevel -=extraLines;
		speed-=100;
		if (speed<1)
			speed = 0;
		timer.setDelay(speed);
	}
	public void deleteRow(ArrayList <Integer> rowsToDelete, Graphics g){

		for (int x = 0; x!=1; x++){
			for (int z =rowsToDelete.get(x); z!=rowsToDelete.size(); z--){
				for (int p = 0; p<=9; p++){
					Landed[z-x][p] = Landed [z-x-1][p];
				}
			}
		}
		time1 = System.currentTimeMillis();
		time2 = System.currentTimeMillis()+1;
	}

	public void keyPressed(KeyEvent e) {
		if (endGame){
			if(e.getKeyCode() == 10 ){
				//System.out.println("here");
			}
		}else{
			if (pause && escape);
			else{
				if(e.getKeyCode() == 37 ){
					direction = "left";
					change = true;
				}else if(e.getKeyCode() == 38){
					direction = "up";
					change = true;
				}else if(e.getKeyCode() == 39){
					direction = "right";
					change = true;
				}else if(e.getKeyCode() == 40 ){
					direction = "down";
					change = true;
				}
				else if(e.getKeyCode() == 32){
					direction = "drop";
					change = true;
				}
				else if(e.getKeyCode() == 16){
					holdShape();
				}
			}
			if(e.getKeyCode() == 27){
				changeEscapeState();
			}
		}

	}
	private void holdShape() {
		if (firstHold){
			holding = A;
			A = nextone;
			nextone = nexttwo;
			nexttwo = nextthree;
			nextthree = RandomShapeToDrop();
			firstHold = false;
		}else{
			Shape preHeld = holding;
			holding = A;
			A = preHeld;
			x=210;
			y=100;
		}

	}

	private void changeEscapeState() {
		escape = !escape;
		if (escape){
			timer.stop();
		}else{
			timer.restart();
		}

	}
	private void endGame() {
		ArrayList <Integer> Scores = new ArrayList(10);
		enterName.setBounds(220, 570, 345, 33);
		enterName.setActionCommand("Enter Name");
		enterName.addActionListener(this);
		add(enterName);
		pause = true;
		timer.stop();	

	}
	private void doScores(){
		boolean scoreDone = false;
		try {
			Scores.add(score);
			File fXmlFile = new File("res/xml/HighScores.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();


			NodeList nList = doc.getElementsByTagName("entry");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					//System.out.println(eElement.getElementsByTagName("Score").item(0).getTextContent());
					String tempScore = eElement.getElementsByTagName("Score").item(0).getTextContent();
					//String tempName = eElement.getElementsByTagName("Name").item(0).getTextContent();
					int addThis = Integer.parseInt(tempScore);
					//String addThis2 = tempName;
					Scores.add(addThis);
					//Names.add(addThis2);
				}
			}
			Collections.sort(Scores);
			Scores.remove(0);
			Collections.sort(Scores);
			rank = 0;
			int q = 1;
			for (int w = 0; w !=10; w++) {
				Node nNode = nList.item(w);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String tempName = eElement.getElementsByTagName("Name").item(0).getTextContent();
					String addThis2 = tempName;
					Names.add(addThis2);
					//System.out.println(tempName);
				}
			}

			for (int a = 9; a !=-1; a--) {
				if (Scores.get(a)>=score){
					q++;
				}else{
					rank = q-1;
					a = 0;
					break;
				}
			}
			int temp = 0;
			for (int a = 9; a !=-1; a--) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				if (Scores.get(a) == Integer.parseInt(eElement.getElementsByTagName("Score").item(0).getTextContent())){
				}else if (Scores.get(a) == score){
					shiftScoresDown(rank, doc);
					Node Highscores = doc.getElementsByTagName("entry").item(temp);
					NodeList list = Highscores.getChildNodes();
					for (int i = 0; i < list.getLength(); i++) {
						Node node = list.item(i);
						if ("Score".equals(node.getNodeName())) {
							node.setTextContent(score + "");
						}
						if ("Name".equals(node.getNodeName())) {
							node.setTextContent(playerName);
						}
						if ("Rank".equals(node.getNodeName())) {
							node.setTextContent(rank + "");
						}
					}for (int i = 1; rank+i!=11; i++){
						Highscores = doc.getElementsByTagName("entry").item(temp+i);
						list = Highscores.getChildNodes();
						for (int f = 0; f < list.getLength(); f++) {
							Node node = list.item(f);
							if ("Score".equals(node.getNodeName())) {
								node.setTextContent(Scores.get(a-i) + "");
							}
							if ("Name".equals(node.getNodeName())) {
								if((temp+i)!=10){
									node.setTextContent(Names.get(temp+i-1));
								}
							}
							if ("Rank".equals(node.getNodeName())) {
								node.setTextContent(rank+i + "");
							}
						}
					}
				}else{

				}
				temp++;

			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("res/xml/HighScores.xml"));
			transformer.transform(source, result);
			for (int f = 0; f < nList.getLength(); f++) {

				Node nNode = nList.item(f);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void shiftScoresDown(int whatitson, Document A) {
		Node Highscores = A.getElementsByTagName("entry").item(whatitson);;
		if ("Score".equals(Highscores.getNodeName())) {
			Highscores.setTextContent(Scores.get(whatitson) + "");
		}
		if ("Name".equals(Highscores.getNodeName())) {
			Highscores.setTextContent(Names.get(whatitson));
		}
		if ("Rank".equals(Highscores.getNodeName())) {
			Highscores.setTextContent(whatitson+1 + "");
		}
	}
	public void drawXML(Graphics g){
		File fXmlFile = new File("res/xml/HighScores.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			try {
				dBuilder = dbFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList nList = doc.getElementsByTagName("entry");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				g.drawString(eElement.getElementsByTagName("Name").item(0).getTextContent(), 320, (int) (410+(temp*27.2)));
				g.drawString(eElement.getElementsByTagName("Score").item(0).getTextContent(), 600, (int) (410+(temp*27.2)));
			}

		}
	}

	public boolean getEndGame(){
		return endGame;
	}
	public void keyReleased(KeyEvent arg0) {

	}
	public void keyTyped(KeyEvent arg0) {

	}
	public Shape RandomShapeToDrop(){
		int shapenum = (int) ((7) * Math.random() + 1);
		if(shapenum == 1)
			shapetodrop = new BlueShape();
		if(shapenum == 2)
			shapetodrop = new DarkBlueShape();
		if(shapenum == 3)
			shapetodrop = new GreenShape();
		if(shapenum == 4)
			shapetodrop = new OrangeShape();
		if(shapenum == 5)
			shapetodrop = new PinkShape();
		if(shapenum == 6)
			shapetodrop = new RedShape(); 
		if(shapenum == 7)
			shapetodrop = new YellowShape();
		x=210;
		y=100;


		return shapetodrop; 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String eventName = arg0.getActionCommand();
		Object src = arg0.getSource();
		if(src == pausebutton){
			escape = !escape;

		}
		if(src == restartbutton){
			escape = false;
			if (startNew){
				RunGame.getFrame().dispose();
				setVisible(false);
				sds = new RunGame(false);
				startNew = false;

			}
			if (funtimes){
				box.setBounds(0, 0, 0, 0);
				add(box);
				funtimes = false;
			}
		}
		if(src == questionbutton){
			mainmenu = false; 
			pause = false;
			escape = false;
			question = true;
			highscores = false;
		}
		if(src == mainmenubutton){
			mainmenu = true; 
			pause = false;
			escape = false;
			question = false;
			highscores = false;
		}
		if(src == viewhighscoresbutton){
			mainmenu = false;
			pause = false;
			escape = false;
			question = false;
			highscores = true;
		}
		if(src == startgamebutton){
			mainmenu = false;
			pause = false;
			escape = false;
			question = false;
			highscores = false;
			gameInProgress = true;

			if (startNew){
				RunGame.getFrame().dispose();
				setVisible(false);
				sds = new RunGame(false);
				startNew = false;
			}
			timer.restart();
		}
		if(src == viewcontrolsbutton){
			mainmenu = false;
			pause = false;
			escape = false;
			question = true;
			highscores = false;
			gameOver = false;
			setLayout(null);
			resumebutton.addActionListener(this);
			resumebutton.setHorizontalAlignment(SwingConstants.CENTER);
			resumebutton.setBounds(150,740,305,70);
			add(resumebutton);
		}
		if(src == resumebutton) {
			question = false;
			mainmenu = false;
			highscores = false;
			gameOver = false;
			escape = false;
			timer.restart();
		}
		if(eventName.equals("Enter Name")){
			playerName  = enterName.getText();
			doScores();
			highscores = true;
			gameOver = false;
			pause = false;
		}if (eventName.equals("Drop One")){
			direction = "down";
			change = true;
		}
	}
}


