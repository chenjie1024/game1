import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	
	
//	定义鸡蛋坐标
	int row,col;
//	大小 
	int w = MainFrame.BLOCK_SIZE;
	int h = MainFrame.BLOCK_SIZE;
//	color
	private Color color = Color.YELLOW;
//	随机数
	private static Random random = new Random();
	
//	实例化Egg对象
//	接收x，y 整数   有参数
	public Egg(int row,int col){
		this.row = row;
		this.col = col;
	}
//	无参数
	public Egg(){
		this(random.nextInt(MainFrame.ROWS-11)+6,random.nextInt(MainFrame.COLS-8)+3);
	}
//	画鸡蛋
	public void drawEgg(Graphics g){
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(MainFrame.BLOCK_SIZE*col, MainFrame.BLOCK_SIZE*row, w, h);//绘制鸡蛋
		g.setColor(c);
		if(color == Color.YELLOW){
			color = Color.RED;
		}else {
			color = Color.YELLOW;
		}
	}
	
//	获取当前鸡蛋坐标
	public Rectangle getRectangle(){
		Rectangle rectangle = new Rectangle(MainFrame.BLOCK_SIZE*col,MainFrame.BLOCK_SIZE*row, w, h);
		return rectangle;
		
	}
//	显示新的鸡蛋
	public void drawNewEgg(){
		this.row = random.nextInt(MainFrame.ROWS-11)+6;
	    this.col= random.nextInt(MainFrame.COLS-8)+3;
	}	
	
}







