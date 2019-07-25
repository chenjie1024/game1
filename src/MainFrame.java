import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.PasswordAuthentication;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MainFrame extends Frame{

	public static final int BLOCK_SIZE = 10;
	
	public static final int ROWS = 50;//��``````````
	public static final int COLS = 50;//��

	
	private PaintThread paintthread = new PaintThread();
	
	private static BufferedImage bufferedImage = null;
	private static String imagePath = "C:\\Users\\Administrator\\Desktop\\image\\background.png";
//	private static String
//	��ʼ��һ����ͼƬ
	Image image1 = null;
//	����sankeʵ��
	Snake snake = new Snake(this);
//	����Egg
	Egg egg = new Egg();
//	��Ϸ����
	private boolean gameOver;
//	��Ϸ�÷�
	public int score = 0;
	
	static {
		try {
			bufferedImage = ImageIO.read(new FileInputStream(imagePath));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
//	��ʼ������
	public void init(){
		
//		������ʼλ��
		this.setLocation(500,100);
//		����ͼ��
		
//		���ڴ�С
		this.setSize(COLS * BLOCK_SIZE - 15, ROWS * BLOCK_SIZE - 2);
//		���ڿ���
		this.setVisible(true);
//		����Ĭ�Ϲر�
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
//		���Ӽ����¼�
		this.addKeyListener(new KeyMonitor());
//		�����߳����»���
		new Thread(paintthread).start();
		
	}
//	��ͼ ����
	public void paint(Graphics g){
		g.drawImage(bufferedImage, 0, 0, null);
		
//		���÷�
		if(gameOver){
			g.setFont(new Font("����",Font.BOLD,50));
			g.drawString("��Ϸ����", 140, 180);
			paintthread.gameOver();
			
		}
		g.setFont(new Font("����",Font.BOLD,20));
		g.drawString("����:"+score, 15, 50);
		snake.eat(egg);
		
		
		
		snake.draw(g);
		egg.drawEgg(g);
		
	}
//	���̼����¼�
	private class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
		
			snake.keyPressed(e);
		}
		
	}
//	���»���
	private class PaintThread implements Runnable{
		private boolean running = true;
		public void run(){
			while(running){
				repaint();//���»���
				try {
					
					Thread.sleep(snake.speeds());//�������ƶ��ٶ�
					
					System.out.println("���ٶȣ�"+snake.speeds());
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		��Ϸ����
		public void gameOver(){
     	    running = false;
     }
	}
//	�����˸����
	
	
	public void update(Graphics g){
		if(image1 == null){
			image1 = this.createImage(COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);
		}
		Graphics gsGraphics = image1.getGraphics();
		paint(gsGraphics);
		g.drawImage(image1, 0, 0, null);
	}
//	��Ϸ�÷�
	public int getScore(){
		return score;
	}
	public void setScore(int score){
		this.score = score;
		
	}
	
//	������
	public static void main(String[] args) {
		new MainFrame().init();
	}
//	��Ϸ����
	public void stop() {
		gameOver = true;
		
	}
	

}
