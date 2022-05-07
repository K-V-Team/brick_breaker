package brickBreaker;

import javax.swing.JFrame;

public class MainFile {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		BrickDestroyer brickBall = new BrickDestroyer();
		obj.setBounds(10,10,700,600);
		obj.setTitle("Brick Breaker");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(brickBall);
	}
   
}
