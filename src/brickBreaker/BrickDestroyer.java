package brickBreaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.Timer;
import javax.swing.JPanel;

public class BrickDestroyer extends JPanel implements KeyListener,ActionListener {
	private boolean play = false;
	private int score = 0;
	private int totalBricks =21;
	private Timer timer;
	private int delay = 8;
	private int paddleX3= 270;	//green middle paddle position in x and y-coordinates
	private int paddleX2 = 240;	//yellow left paddle position in x and y-coordinates
	private int paddleX1 = 210;	//blue left paddle position in x and y-coordinates
	private int paddleX4 = 300;	//yellow right paddle position in x and y-coordinates
	private int paddleX5 = 330;	//blue right paddle position in x and y-coordinates
	private int ballposX = 260;	//ball position in x-coordinates
	private int ballposY = 530;	//ball position in y-coordinates

	private int ballXdir = -1;
	private int ballYdir = -2;

	private BricksMap map;
	
	public BrickDestroyer() {
		map = new BricksMap(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//background 
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(1,1,692,592);
		
		//drawing map;
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.black);
		g.fillRect(0,0,3,592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		//scores
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,590,30);
		 
		//the paddle4
		g.setColor(Color.blue);
		g.fillRect(paddleX1,550,30,8);
		//the paddle2
		g.setColor(Color.yellow);
		g.fillRect(paddleX2,550,30,8);
		
		//the paddle3
		g.setColor(Color.yellow);
		g.fillRect(paddleX4,550,30,8);
		
		//the paddle5
		g.setColor(Color.blue);
		g.fillRect(paddleX5,550,30,8);
		
		//the paddle
		g.setColor(Color.green);
		g.fillRect(paddleX3,550,30,8);
		
		if(ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.black);
			g.setFont(new Font("serif",Font.BOLD,(int) 30));
			g.drawString("Game Over,You Lose,Scores:"+score,190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart", 230, 350);
			
			g.setColor(Color.magenta);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Game created by K.V Team", 230, 400);
			
		}
		
		//the ball
		g.setColor(Color.black);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("serif",Font.BOLD,(int) 30));
			g.drawString("You Won:"+score,260,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press Enter to Restart", 230, 350);
			
			g.setColor(Color.magenta);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Game created by K.V Team", 230, 400);
			
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		if(play) {
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(paddleX1,550,30,8)))	//intersecting of ball with blue left paddle 					
			{	
						ballXdir=3;
						ballYdir=-ballYdir;
						ballXdir=-ballXdir;// with intense speed in X-coordinate
						//the ball will move 150 degree from blue left paddle
			}
			else if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(paddleX2,550,30,8)))	//intersecting of ball with yellow left paddle 	
			{		
				ballXdir=1;
				ballYdir=-ballYdir;
				ballXdir=-ballXdir;
				//the ball move 120 degree from the yellow left paddle
			}	

			else if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(paddleX3,550,30,8)))	//intersecting of ball with green middle paddle 
			{		
						ballXdir=0;/*when the ball intersect green middle paddle then, ball should move
						opposite direction without reflecting in X-axis*/ 
						ballYdir=-ballYdir;/*when the ball intersect green middle paddle then, ball should move
						opposite direction with reflecting in Y-axis*/
						
						//the ball will move 90 degree angle from green middle paddle.
			}	
			
			else if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(paddleX4,550,30,8)))	//intersecting of ball with yellow right paddle 	
			{		
				ballXdir=-1;
				ballYdir=-ballYdir;//by this,ball will move towards positive direction()
				ballXdir=-ballXdir;
				//the ball will move 60 degree from yellow right paddle
			}	
			
			else if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(paddleX5,550,30,8)))	//intersecting of ball with blue right paddle 					
			{	
						ballXdir=-3;	// change the value 
						ballYdir=-ballYdir;
						ballXdir=-ballXdir;//so that it change direction 30 degree angle with intense speed in X-coordinate
						//the ball will move 30 degree from blue right paddle
			}
			// check map collision with the ball		
						//A: for(int i = 0; i<map.map.length; i++)
						int i = 0;
						A:	while(i<map.map.length)
						{
							for(int j =0; j<map.map[0].length; j++)
							
							{				
								if(map.map[i][j] > 0)
								{
									//scores++;
									int brickX = j * map.brickWidth + 80;
									int brickY = i * map.brickHeight + 50;
									int brickWidth = map.brickWidth;
									int brickHeight = map.brickHeight;
									
									Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
									Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
									Rectangle brickRect = rect;
									
									if(ballRect.intersects(brickRect))
									{					
										map.setBrickValue(0, i, j);
										score+=5;	
										totalBricks--;
										
										// when ball hit right or left of brick
										if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
										{
											ballXdir = -ballXdir;
										}
										// when ball hits top or bottom of brick
										else
										{
											ballYdir = -ballYdir;				
										}
										
										break A;
									}
								}
							}
							i++;
						}
			
			ballposX+= ballXdir;
			ballposY+= ballYdir;

			if(ballposX <0) { //ball while intersecting the left black wall with x-coordinate
							
				ballXdir=-ballXdir;	//ball will change direction which move to other direction after getting hit by the wall
			}
			if(ballposY <0) { //ball while intersecting at top corner of the left black wall 
			
				ballYdir=-ballYdir;	//ball will change direction which move to other direction
			}
			if(ballposX >670) {//ball while intersecting at top corner of the right black wall 
				
				ballXdir=-ballXdir; //ball will change direction which move to other direction
			}
		

		}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(paddleX3>= 590 && paddleX2 >=560 && paddleX4>=620 && paddleX1>=530 && paddleX5>=650) {
				paddleX3=590;	//green middle paddle position in x and y-coordinates
				paddleX2=560;	//yellow left paddle position in x and y-coordinates
				paddleX4=620;	//yellow right paddle position in x and y-coordinates
				paddleX1=530;	//blue left paddle position in x and y-coordinates
				paddleX5=650;	//blue right paddle position in x and y-coordinates
		  //these are limitation of paddle position when all these paddle move to right corner.
			}	else {
				moveRight();
				}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(paddleX3<= 70 && paddleX2 <=40 && paddleX4 <=100 && paddleX1 <=10 && paddleX5 <=130) {
				paddleX3=70;	//green middle paddle			
				paddleX2=40;	//yellow left paddle
				paddleX4=100;	//yellow right paddle
				paddleX1=10;    //blue left paddle
				paddleX5=130;	//blue right paddle
			}	else {
				moveLeft();
				}
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!play)
			{
				play = true;
				ballposX =260;
				ballposY =530;
				ballXdir = -1;
				ballYdir = -2;
				paddleX3= 270;
				paddleX2 = 240;
				paddleX1 = 210;
				paddleX4 = 300;
				paddleX5 = 330;
				
				score = 0;
				totalBricks = 21;
				map = new BricksMap(3,7);
				repaint();
			}
		}
	}
	public void moveRight() {//movement of paddle towards right
		play = true;
		paddleX3+=20;
		paddleX2+=20;
		paddleX4+=20;
		paddleX1+=20;
		paddleX5+=20;
	}
	
	public void moveLeft() { //movement of paddle towards left
		play = true;
		paddleX3-=20;
		paddleX2-=20;
		paddleX4-=20;
		paddleX1-=20;
		paddleX5-=20;
		
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

			
}
	
	
	
	
	
	
	


