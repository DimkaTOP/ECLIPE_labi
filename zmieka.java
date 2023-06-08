package zmieka;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class zmieka {

	public static void main(String[] args) {
		myFrame okno = new myFrame();

	}

}
class myFrame extends JFrame 
{
	public myFrame()
	{
		myPanel pan = new myPanel();
		Container cont = getContentPane();
		cont.add(pan);
		setTitle("Игра\"Orange Snake\"");
		setBounds(0, 0, 800, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
class myPanel extends JPanel
{
	private myPanel pan;
	private game myGame;
	private Timer tmDraw, tmUpdate;
	private Image fontss,telo,golova,eda,gameover;
	private JLabel lb;
	private JButton btn1,btn2;
	
	private class myKey implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			if (key==KeyEvent.VK_LEFT) myGame.new_napr=0;
			else if (key==KeyEvent.VK_UP)myGame.new_napr=1;
			else if (key==KeyEvent.VK_RIGHT)myGame.new_napr=2;
			else if (key==KeyEvent.VK_DOWN)myGame.new_napr=3;
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}

	public myPanel()
	{
		pan = this;
		this.addKeyListener(new myKey());
		this.setFocusable(true);
		try 
		{	
			fontss = ImageIO.read(new File("Z:\\ГРУППЫ\\ПРОГРАММИСТЫ\\ИП22\\Безматерных Дмитрий Ип-22\\картинки для змейки\\fon.jpg"));
			telo = ImageIO.read(new File("Z:\\ГРУППЫ\\ПРОГРАММИСТЫ\\ИП22\\Безматерных Дмитрий Ип-22\\картинки для змейки\\telo.png"));
			golova = ImageIO.read(new File("Z:\\ГРУППЫ\\ПРОГРАММИСТЫ\\ИП22\\Безматерных Дмитрий Ип-22\\картинки для змейки\\gova.png"));
			eda = ImageIO.read(new File("Z:\\ГРУППЫ\\ПРОГРАММИСТЫ\\ИП22\\Безматерных Дмитрий Ип-22\\картинки для змейки\\eda.png"));
			gameover = ImageIO.read(new File("Z:\\ГРУППЫ\\ПРОГРАММИСТЫ\\ИП22\\Безматерных Дмитрий Ип-22\\картинки для змейки\\gameove.png"));
		}
		catch (Exception ex) {}
		myGame = new game();
		myGame.start();
		tmDraw = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});
		tmDraw.start();
		tmUpdate = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (myGame.gameover==false)
				{
					myGame.perem();
				}
				lb.setText("Итог:  " + myGame.kol);
			}
		});
		tmUpdate.start();
		setLayout(null);
		lb = new JLabel("Убито: 0");
		lb.setForeground(Color.BLACK);
		lb.setBackground(Color.DARK_GRAY);
		lb.setFont(new Font("serif",0,30));
		lb.setBounds(630,200,150,50);
		add(lb);
		btn1 = new JButton();
		btn1.setText("Занаво");
		btn1.setForeground(Color.orange);
		btn1.setBackground(Color.DARK_GRAY);
		btn1.setFont(new Font("serif",0,20));
		btn1.setBounds(630,30,150,50);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myGame.start();
				btn1.setFocusable(false);
				btn2.setFocusable(false);
				pan.setFocusable(true);
			}
	});
		add(btn1);
		btn2 = new JButton();
		btn2.setText("Стоп ");
		btn2.setForeground(Color.orange);
		btn2.setBackground(Color.DARK_GRAY);
		btn2.setFont(new Font ("serif",0,20));
		btn2.setBounds(630, 100, 150, 50);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		add(btn2);
	}
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		gr.drawImage(fontss,0,0,800,600,null);
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				if(myGame.mas[i][j]!=0)
				{
					if(myGame.mas[i][j]==1)
					{
						gr.drawImage(golova, 10+j*20, 10+i*20,20,20,null);
					}
					else if (myGame.mas[i][j]==-1)
					{
						gr.drawImage(eda, 10+j*20, 10+i*20,20,20,null);
					}
					else if (myGame.mas[i][j]>=2)
					{
						gr.drawImage(telo,10+j*20, 10+i*20,20,20,null);
					}
				}
			}
		}
		gr.setColor(Color.BLACK);
		for(int i = 0; i <= 30; i++)
		{ 
			gr.drawLine(10+i*20, 10, 10+i*20, 610);
			gr.drawLine(10, 10+i*20, 610, 10+i*20);
		}
		if (myGame.gameover==true)
		{
			gr.drawImage(gameover,300,200,200,100,null);
		}
	}
}
