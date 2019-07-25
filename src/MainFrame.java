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
	
	public static final int ROWS = 50;//行``````````
	public static final int COLS = 50;//列

	
	private PaintThread paintthread = new PaintThread();
	
	private static BufferedImage bufferedImage = null;
	private static String imagePath = "C:\\Users\\Administrator\\Desktop\\image\\background.png";
//	private static String
//	初始化一个空图片
	Image image1 = null;
//	创建sanke实例
	Snake snake = new Snake(this);
//	创建Egg
	Egg egg = new Egg();
//	游戏结束
	private boolean gameOver;
//	游戏得分
	public int score = 0;
	
	static {
		try {
			bufferedImage = ImageIO.read(new FileInputStream(imagePath));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
//	初始化窗口
	public void init(){
		
//		窗口起始位置
		this.setLocation(500,100);
//		窗口图标
		
//		窗口大小
		this.setSize(COLS * BLOCK_SIZE - 15, ROWS * BLOCK_SIZE - 2);
//		窗口可视
		this.setVisible(true);
//		窗口默认关闭
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
//		增加监听事件
		this.addKeyListener(new KeyMonitor());
//		启动线程重新绘制
		new Thread(paintthread).start();
		
	}
//	画图 背景
	public void paint(Graphics g){
		g.drawImage(bufferedImage, 0, 0, null);
		
//		画得分
		if(gameOver){
			g.setFont(new Font("宋体",Font.BOLD,50));
			g.drawString("游戏结束", 140, 180);
			paintthread.gameOver();
			
		}
		g.setFont(new Font("宋体",Font.BOLD,20));
		g.drawString("分数:"+score, 15, 50);
		snake.eat(egg);
		
		
		
		snake.draw(g);
		egg.drawEgg(g);
		
	}
//	键盘监听事件
	private class KeyMonitor extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
		
			snake.keyPressed(e);
		}
		
	}
//	重新绘制
	private class PaintThread implements Runnable{
		private boolean running = true;
		public void run(){
			while(running){
				repaint();//重新绘制
				try {
					
					Thread.sleep(snake.speeds());//设置蛇移动速度
					
					System.out.println("蛇速度："+snake.speeds());
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		游戏结束
		public void gameOver(){
     	    running = false;
     }
	}
//	解决闪烁问题
	
	
	public void update(Graphics g){
		if(image1 == null){
			image1 = this.createImage(COLS*BLOCK_SIZE,ROWS*BLOCK_SIZE);
		}
		Graphics gsGraphics = image1.getGraphics();
		paint(gsGraphics);
		g.drawImage(image1, 0, 0, null);
	}
//	游戏得分
	public int getScore(){
		return score;
	}
	public void setScore(int score){
		this.score = score;
		
	}
	
//	主方法
	public static void main(String[] args) {
		new MainFrame().init();
	}
//	游戏结束
	public void stop() {
		gameOver = true;
		
	}
	

}
