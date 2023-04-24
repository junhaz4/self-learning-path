package tankGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings({"all"})
public class DrawCircle extends JFrame{//相当于窗口，画框
    //定义一个面板
    private MyPanel mp = null;
    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle(){
        mp = new MyPanel(); //初始化面板
        this.add(mp);//将面板放入画框
        this.setSize(400,300); //设置大小
        this.addKeyListener(mp); //Jframe可以监听面板发生的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//当点击窗口退出时，程序退出
        this.setVisible(true); //可以显示
    }
}

class MyPanel extends JPanel implements KeyListener { //继承Jpanel，相当于面板
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {//g相当于画笔
        super.paint(g);
        //g.drawOval(10,10,100,100);
        //Image image = Toolkit.getDefaultToolkit().getImage("src/tankGame/pg.png");
        //g.drawImage(image,10,10,300,200,this);
        g.fillOval(x,y,20,20);
    }

    @Override
    public void keyTyped(KeyEvent e) { //有字符出入触发

    }

    @Override
    public void keyPressed(KeyEvent e) {//某个键被按下时触发
        //System.out.println((char)e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_DOWN){//每个键都有一个值
            y++;
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y--;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x--;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x++;
        }
        //让面板重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {//某个键被松开时触发

    }
}