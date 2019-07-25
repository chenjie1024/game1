import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;



public class Snake {
//	��ͷ
	private Node head = null;
//	��β
	private Node tail = null;
//	����
	public int size = 0;
	public int[]  speed = {500,400,300,200,100};
	
	private Node node = new Node(20,20,Direction.Up);
	private MainFrame mainFrame ;

	//����ڵ� ������
	private class Node{
		
		int w = MainFrame.BLOCK_SIZE;//��
		int h = MainFrame.BLOCK_SIZE;//��
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
			Color color = g.getColor();//������ɫ����
			g.setColor(Color.BLUE);//������ɫ
			g.fillRect(MainFrame.BLOCK_SIZE * col,MainFrame.BLOCK_SIZE * row,w,h);//���ô�С
			g.setColor(color);
		}
	}
//	��ʼ����
	public Snake(MainFrame mainFrame){
		head = node;
		tail = node;
		size = 1;
		this.mainFrame = mainFrame;
	}
//	��ӵ�β
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
//	��ӵ�ͷ
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
//	������
	public  void draw(Graphics g){
		if(size <=0){
			return;
		}
//		�ƶ�
		move();
		
		for(Node node = head;node!=null;node = node.next){
			node.draw(g);
		}
	}
//	�߷��� ����
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch (key) {
//		
			case KeyEvent.VK_LEFT:
//			�����Ҳſ�������
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
//				�������˶�ʱ�ſ������Ϲ���
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
//	�ƶ�
	private void move(){
		addToHead();
		deleteFromTail();
		checkDead();
	}
//	ɾ�����һ��
	private void deleteFromTail(){
		if(size == 0){
			return;
		}
		tail = tail.prev;
		tail.next = null;
	}
//	�߳Լ���
	public void eat(Egg egg){
//		�ж��Ƿ���ײ
		
		
		if(this.getRectangle().intersects(egg.getRectangle())){
			egg.drawNewEgg();
			
    	    this.addToHead();
    	    
    	    mainFrame.setScore(mainFrame.getScore()+5);
		}
	}
//	��ȡ��ǰ��ͷ����ռ�
	 public Rectangle getRectangle(){
	     Rectangle rectangle = new Rectangle(MainFrame.BLOCK_SIZE*head.col,MainFrame.BLOCK_SIZE*head.row, head.w, head.h);
         return rectangle;
	 }
//	 �ж�ͷβ�Ƿ���ײ
	 private void checkDead(){
		 
		 System.out.println("��ͷ���꣺"+head.row+"----"+head.col);
		 
		 if(head.row<5||head.col<3||head.row>MainFrame.ROWS-4||head.col>MainFrame.COLS-5){
			 mainFrame.stop();
			
		 }
		 for(Node node = head.next;node != null;node = node.next){
    	     if(head.row == node.row && head.col == node.col){
    	    	     mainFrame.stop();
    	     }
		 }
	 }
//	 ���ٶ�
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




















