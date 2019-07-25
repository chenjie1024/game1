import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;



public class Snake {
//	蛇头
	private Node head = null;
//	蛇尾
	private Node tail = null;
//	蛇身
	public int size = 0;
	public int[]  speed = {500,400,300,200,100};
	
	private Node node = new Node(20,20,Direction.Up);
	private MainFrame mainFrame ;

	//定义节点 蛇身类
	private class Node{
		
		int w = MainFrame.BLOCK_SIZE;//宽
		int h = MainFrame.BLOCK_SIZE;//高
		int row,col;
		
		Direction direction = Direction.Left;
		
		Node next = null;
		Node prev = null;
		
		
		Node(int row,int col,Direction direction){
			this.row = row;
			this.col = col;
			this.direction = direction;
		}
		
		void draw(Graphics g){
			Color color = g.getColor();//调用颜色方法
			g.setColor(Color.BLUE);//设置颜色
			g.fillRect(MainFrame.BLOCK_SIZE * col,MainFrame.BLOCK_SIZE * row,w,h);//设置大小
			g.setColor(color);
		}
	}
//	初始化蛇
	public Snake(MainFrame mainFrame){
		head = node;
		tail = node;
		size = 1;
		this.mainFrame = mainFrame;
	}
//	添加到尾
//	public void addToTail(){
//		Node node = null;
//		switch(tail.direction){
//			case Up:
//				node = new Node(tail.row+1,tail.col,tail.direction);
//				break;
//			case Down:
//				node = new Node(tail.row-1,tail.col,tail.direction);
//				break;
//			case Left:
//				node = new Node(tail.row,tail.col+1,tail.direction);
//				break;
//			case Right:
//				node = new Node(tail.row,tail.col-1,tail.direction);
//				break;
//				
//		}
//		tail.next = node;
//		node.prev = tail;
//		tail = node;
//		size++;
//	}
//	添加到头
	public void addToHead(){
		Node node = null;
		switch(head.direction){
			case Up:
				node = new Node(head.row-1,head.col,head.direction);
				break;
			case Down:
				node = new Node(head.row+1,head.col,head.direction);
				break;
			case Left:
				node = new Node(head.row,head.col-1,head.direction);
				break;
			case Right:
				node = new Node(head.row,head.col+1,head.direction);
				break;
				
		}
		node.next = head;
		head.prev = node;
		head = node;
		size++;
	}
//	绘制蛇
	public  void draw(Graphics g){
		if(size <=0){
			return;
		}
//		移动
		move();
		
		for(Node node = head;node!=null;node = node.next){
			node.draw(g);
		}
	}
//	蛇方向 规则
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch (key) {
//		
			case KeyEvent.VK_LEFT:
//			不向右才可以向左
				if(head.direction != Direction.Right){
					head.direction = Direction.Left;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(head.direction != Direction.Left){
					head.direction = Direction.Right;
				}
				break;
			case KeyEvent.VK_UP:
//				不向下运动时才可以向上拐弯
				if(head.direction != Direction.Down){
					head.direction = Direction.Up;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(head.direction != Direction.Up){
					head.direction = Direction.Down;
				}
				break;	
		}
	}
//	移动
	private void move(){
		addToHead();
		deleteFromTail();
		checkDead();
	}
//	删除最后一个
	private void deleteFromTail(){
		if(size == 0){
			return;
		}
		tail = tail.prev;
		tail.next = null;
	}
//	蛇吃鸡蛋
	public void eat(Egg egg){
//		判断是否相撞
		
		
		if(this.getRectangle().intersects(egg.getRectangle())){
			egg.drawNewEgg();
			
    	    this.addToHead();
    	    
    	    mainFrame.setScore(mainFrame.getScore()+5);
		}
	}
//	获取当前蛇头坐标空间
	 public Rectangle getRectangle(){
	     Rectangle rectangle = new Rectangle(MainFrame.BLOCK_SIZE*head.col,MainFrame.BLOCK_SIZE*head.row, head.w, head.h);
         return rectangle;
	 }
//	 判读头尾是否相撞
	 private void checkDead(){
		 
		 System.out.println("蛇头坐标："+head.row+"----"+head.col);
		 
		 if(head.row<5||head.col<3||head.row>MainFrame.ROWS-4||head.col>MainFrame.COLS-5){
			 mainFrame.stop();
			
		 }
		 for(Node node = head.next;node != null;node = node.next){
    	     if(head.row == node.row && head.col == node.col){
    	    	     mainFrame.stop();
    	     }
		 }
	 }
//	 蛇速度
	 public int i = speed[0];
	 
	 public int speeds(){
		 if(mainFrame.score == 10){
			i = speed[1];
			 
		 }
		 if(mainFrame.score == 20){
			i = speed[2];
				 
		}
		if(mainFrame.score == 30){
			i = speed[3];
					 
		}
		if(mainFrame.score == 40){
			i = speed[4];
					 
		}
		return i;
	 }
}




















