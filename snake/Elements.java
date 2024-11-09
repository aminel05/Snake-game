package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Elements extends JPanel{
	 private static final long serialVersionUID = 1L;
	static int snake_size = 20;
	final int s_b = 20;
	final int field_height=850;
	final int field_width=1050;
	final int x_units=field_width/s_b;
	final int y_units=field_height/s_b;
	String direction="right";
	static int[] X,Y;
	JLabel apple;
	Random random;
	static int applex,appley;
	int feet = s_b/4;
	int plusblocks=s_b/feet;
	
	boolean gameover=false,start=false;
	Elements(){
		

		//inistialtisation of the main field
		this.setPreferredSize(new Dimension(field_width,field_height));
		this.setLayout(null);
		this.setBackground(Color.BLACK);
		//zone.setBackground(Color.RED);
		

		X = new int[(x_units*y_units)*5];
		Y = new int[(x_units*y_units)*5];
		for(int i =0 ;i<snake_size;i++) {
		X[i]=field_width/2;
		Y[i]=field_height/2;}
		//for(int i = snake_size;i>0;i--) {X[i] = X[i-1];Y[i] = Y[i-1];}
			
			random = new Random();
			applex= random.nextInt(x_units)*s_b;
			appley= random.nextInt(y_units)*s_b;
			
	
	}
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2D = (Graphics2D) g;
	    if(start==false) {
	    	g2D.setColor(Color.RED);
	    	g.setFont(new Font("Courier", Font.BOLD, 60));
            g.drawString("Snake game", field_width/3-10,field_height/2-50);
            g.setFont(new Font("Courier", Font.CENTER_BASELINE, 17));
            g.setColor(Color.WHITE);
            g.drawString("presse ' enter ' to start", field_width/2-90,field_height/2);
            g.drawString("presse ' x ' to exit", field_width/2-90,field_height/2+25);
	    }
	    if(gameover==false&&start) {
	    g.setColor(Color.green);
        g.fillOval(applex,appley,s_b,s_b);

        for(int i = 0; i< snake_size;i++) {
			if(i == 0) {
				g.setColor(Color.red);
				g.fillRect(X[i], Y[i], s_b, s_b);
			}
			else {
				g.setColor(new Color(255,50,50));
				g.fillRect(X[i], Y[i], s_b, s_b);
			}			
		}
        g.setFont(new Font("Courier", Font.BOLD, 30));
        g.drawString("Score : "+String.valueOf(snake_size/plusblocks), field_width/2-60, 30);}
        if(gameover) {
        	g.setColor(Color.red);
        	g.setFont(new Font("Courier", Font.BOLD, 60));
            g.drawString("Game Over", field_width/3-10,field_height/2-50);
            g.setFont(new Font("Courier", Font.CENTER_BASELINE, 17));
            g.setColor(Color.WHITE);
            g.drawString("presse ' c ' to continue", field_width/2-90,field_height/2);
            g.drawString("presse ' r ' to restart", field_width/2-90,field_height/2+25);
            g.drawString("presse ' x ' to exit", field_width/2-90,field_height/2+50);
            
        }
	}



	public int getSize_blocks() {
		return s_b;
	}
	public int getsnake_size() {
		return snake_size;
	}

	public void move_snake(String direction,boolean firstmove) {
		
		for(int i = snake_size;i>0;i--) {X[i] = X[i-1];Y[i] = Y[i-1];}
		switch (direction){
		
		case "up" :if(firstmove) {Y[0]=Y[0]-s_b;}
		else{Y[0]=Y[0]-feet;}
			break;
			
		case "right" :if(firstmove) {X[0]=X[0]+s_b;}
		else{X[0]=X[0]+feet;}
			break;
			
		case "left" :if(firstmove) {X[0]=X[0]-s_b;}
		else{X[0]=X[0]-feet;}
			break;
			
		case "down" :if(firstmove) {Y[0]=Y[0]+s_b;}
		else{Y[0]=Y[0]+feet;}
			break;
		
		
		}
		gameover();
	}
	public void stop(Graphics g) {
		
	}
	public void eat_apple() {

		applex= (random.nextInt(x_units)*s_b);
		appley= (random.nextInt(y_units)*s_b);
		applex=applex<=s_b*2?applex+s_b:applex-s_b;
		appley=appley<=s_b*2?appley+s_b:appley-s_b;
	}
	public void gameover() {
		for (int i = 1; i < snake_size; i++) {
	        if (X[0] == X[i] && Y[0] == Y[i]) {
	            gameover = true;
	            break;
	        }
	        if(!(Elements.X[0]<field_width-s_b && Elements.X[0]>0 && Elements.Y[0]<field_height-s_b && Elements.Y[0]>0)) {
	        	gameover = true;
	        }
	    }		
	}
}

