import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Egg {
	
	
//	���弦������
	int row,col;
//	��С 
	int w = MainFrame.BLOCK_SIZE;
	int h = MainFrame.BLOCK_SIZE;
//	color
	private Color color = Color.YELLOW;
//	�����
	private static Random random = new Random();
	
//	ʵ����Egg����
//	����x��y ����   �в���
	public Egg(int row,int col){
		this.row = row;
		this.col = col;
	}
//	�޲���
	public Egg(){
		this(random.nextInt(MainFrame.ROWS-11)+6,random.nextInt(MainFrame.COLS-8)+3);
	}
//	������
	public void drawEgg(Graphics g){
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(MainFrame.BLOCK_SIZE*col, MainFrame.BLOCK_SIZE*row, w, h);//���Ƽ���
		g.setColor(c);
		if(color == Color.YELLOW){
			color = Color.RED;
		}else {
			color = Color.YELLOW;
		}
	}
	
//	��ȡ��ǰ��������
	public Rectangle getRectangle(){
		Rectangle rectangle = new Rectangle(MainFrame.BLOCK_SIZE*col,MainFrame.BLOCK_SIZE*row, w, h);
		return rectangle;
		
	}
//	��ʾ�µļ���
	public void drawNewEgg(){
		this.row = random.nextInt(MainFrame.ROWS-11)+6;
	    this.col= random.nextInt(MainFrame.COLS-8)+3;
	}	
	
}







