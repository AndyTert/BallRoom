package ballRoom;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.applet.*;

import javax.swing.*;

public class BallRoom extends JApplet{
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
}*/
	public static final int WIDTH = 350;
	public static final int HEIGHT = 300;
	
	private PaintSurface canvas;
	
	public void init() {
		this.setSize(WIDTH, HEIGHT);
		canvas = new PaintSurface();
		this.add(canvas, BorderLayout.CENTER);
		
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
		executor.scheduleAtFixedRate(new AnimationThread(this), 0L, 20L, TimeUnit.MILLISECONDS);
	}

}

class AnimationThread implements Runnable{
	
	JApplet c;
	
	public AnimationThread(JApplet c) {
		this.c = c;
	}
	
	public void run() {
		c.repaint();
	}
}

//new PaintSurface class
class PaintSurface extends JComponent{
	
	public ArrayList<Ball> balls = new ArrayList<Ball>();
	
	public PaintSurface() {
		for(int i = 0; i < 20; i++) {
			balls.add(new Ball(20));
		}
	}
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Color.RED);
			for(Ball ball : balls) {
				ball.move();
				g2.fill(ball);
		}
	}
}
//old PaintSurface class
//class PaintSurface extends JComponent{
//	int x_pos = 0; // The starting X position
//	int y_pos = 150; // The starting Y position
//	int x_speed = 1; // The speed the ball travels in the X direction
//	int y_speed = 2; // The speed the ball travels in the Y direction
//	int d = 20; // The diameter of the ball
//	int width = BallRoom.WIDTH;
//	int height = BallRoom.HEIGHT;
//	public void paint(Graphics g) {
//		Graphics2D g2 = (Graphics2D)g;
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		
//		if(x_pos < 0 || x_pos > width - d) {
//			x_speed = -x_speed;
//		}
//		if(y_pos < 0 || y_pos > width - d) {
//			y_speed = -y_speed;
//		}
//		
//		x_pos += x_speed; 
//		y_pos += y_speed; 
//		
//		Shape ball = new Ellipse2D.Float(x_pos, y_pos, d, d);
//		g2.setColor(Color.RED);
//		g2.fill(ball);
//	}
//}

class Ball extends Ellipse2D.Float{
	
	private int x_speed, y_speed;
	private int d;
	private int width = BallRoom.WIDTH;
	private int height = BallRoom.HEIGHT;

	public Ball(int diameter) {
		super(
				(int)(Math.random() * (BallRoom.WIDTH - 20)+1), 
				(int)(Math.random() * (BallRoom.HEIGHT - 20)+1), diameter, diameter
				);
		this.d =diameter;
		this.x_speed = (int) (Math.random() * 5 + 1);
		this.y_speed = (int) (Math.random() * 5 + 1);
	}
	
	public void move() {
		if(super.x < 0 || super.x > width - d) {
			x_speed = -x_speed;
		}
		if(super.y < 0 || super.y > width - d) {
			y_speed = -y_speed;
		}
		super.x += x_speed;
		super.y += y_speed;
	}
}