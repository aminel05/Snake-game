package snake;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;


public class Field implements KeyListener, ActionListener{

		JFrame frame ;
		Timer timer;
		Elements element;
		String direction="right";
		boolean firstmove=false;
		final int speed=1;
		boolean gameover = false, restart=false,start=false;
		//end declaration of atributs
		
		
		public Field() {
			
			element=new Elements();
			if(start==true) {element.start=true;timer.start();}
			//frame inesalisation
			frame = new JFrame();
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
			frame.add(element);
			frame.pack();
			frame.setVisible(true);
			frame.addKeyListener(this);
			frame.setLocationRelativeTo(null);
			frame.setTitle("sanke game(by amine)");
			
			ImageIcon icon = new ImageIcon(getClass().getResource("Snake-Game.jpg"));
			frame.setIconImage(icon.getImage());
			
			
			//timer that make frames
			
			timer = new Timer(speed,this );
			
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
			switch(e.getKeyChar()) {
			case 'w': if (!direction.equals("down")&&!direction.equals("up")) direction="up";firstmove=true;
				break;
			case 'd': if (!direction.equals("left")&&!direction.equals("right"))direction="right";firstmove=true;
				break;
			case 'a': if (!direction.equals("right")&&!direction.equals("lrft"))direction="left";firstmove=true;
				break;
			case 's': if (!direction.equals("up")&&!direction.equals("down"))direction="down";firstmove=true;
				break;
			case 'r':restart();
				break;
			case 'c':continu();
				break;
			case 'x':frame.dispose();
		}
			
 		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:if (!direction.equals("down")&&!direction.equals("up")) direction="up";firstmove=true;
				break;
			case KeyEvent.VK_RIGHT:if (!direction.equals("left")&&!direction.equals("right"))direction="right";firstmove=true;
				break;
			case KeyEvent.VK_LEFT:if (!direction.equals("right")&&!direction.equals("lrft"))direction="left";firstmove=true;
				break;
			case KeyEvent.VK_DOWN:if (!direction.equals("up")&&!direction.equals("down"))direction="down";firstmove=true;
				break;
			case KeyEvent.VK_ENTER:if(element.start==false) {element.start=true;start=true;timer.start();}
			}
		}
		public void continu() {
			if(gameover) { restart=true;
			element.gameover=false;
			new Elements();
			direction="right";
			timer.start();
			}
		}
		public void restart() {
			if(gameover) { restart=true;
			element.gameover=false;
			Elements.snake_size=20;
			new Elements();
			direction="right";
			timer.start();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			gameover=element.gameover;
			if( gameover==false) {
				
				element.move_snake(direction,firstmove);
				firstmove=false;}
			else {
				gameover=true;
			timer.stop();
			}
			
			 
			if((Elements.X[0]>=Elements.applex && Elements.X[0]<Elements.applex+element.s_b ||Elements.X[0]+element.s_b>Elements.applex && Elements.X[0]+element.s_b<Elements.applex+element.s_b)
					&&
					(Elements.Y[0]>=Elements.appley && Elements.Y[0]<Elements.appley+element.s_b||Elements.Y[0]+element.s_b>Elements.appley && Elements.Y[0]+element.s_b<Elements.appley+element.s_b)) {
				element.eat_apple(); 
				Elements.snake_size+=element.plusblocks;
				for(int i =0 ; i<=element.plusblocks;i++) {
					Elements.X[Elements.snake_size-i]=Elements.X[1];
					Elements.Y[Elements.snake_size-i]=Elements.Y[1];
				}
				Elements.X[Elements.snake_size]=-80;
				Elements.Y[Elements.snake_size]=-80;
				}
			
			element.repaint();
		}
}
