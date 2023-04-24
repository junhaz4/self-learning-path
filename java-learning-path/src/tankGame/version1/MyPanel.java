package tankGame.version1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;
@SuppressWarnings({"all"})
//为了让Panel不停绘制子弹，需要将MyPanle 改成线程
public class MyPanel extends JPanel implements KeyListener,Runnable {
    Hero hero = null;
    //定义敌人坦克，用vector存储，线程安全
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankNum = 3;
    //定义vector用于存放炸弹
    //当子弹击中坦克时，就加入到bombs
    Vector<Bomb> bombs = new Vector<>();
    //定义三个炸弹图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    //定义一个Node vector，用于恢复敌人坦克坐标
    Vector<Node> nodes = new Vector<>();
    public MyPanel(String key){
        //判断文件是否存在，如果存在，正常进行；如果不存在，只能开始新游戏
        File file = new File(Recorder.getRecordFile());
        if(file.exists()) {
            nodes = Recorder.getNodesAndEnemyTank();
        }else{
            System.out.println("文件不存在，只能开启新游戏");
            key = "1";
        }
        //将enemyTanks赋给recorder
        Recorder.setEnemyTanks(enemyTanks);
        hero = new Hero(500,100);
        // hero.setSpeed(10); //调整速度
        switch (key){
            case "1":
                //初始化坦克
                for (int i=0; i<enemyTankNum; i++){
                    //创建敌方坦克对象
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                    //将enemyTanks 设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirection(2);//设置方向
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //创建子弹并加入vector
                    Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirection());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start(); //启动子弹
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                //初始化坦克
                for (int i=0; i<nodes.size(); i++){
                    Node node = nodes.get(i);
                    //创建敌方坦克对象
                    EnemyTank enemyTank = new EnemyTank(node.getX(),node.getY());
                    //将enemyTanks 设置给enemyTank
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirection(node.getDirection());//设置方向
                    //启动敌人坦克线程
                    new Thread(enemyTank).start();
                    //创建子弹并加入vector
                    Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirection());
                    enemyTank.shots.add(shot);
                    new Thread(shot).start(); //启动子弹
                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("输入有错，请重新输入");
        }

        //初始化图片
        image1 = Toolkit.getDefaultToolkit().getImage("src/tankGame/version1/image/bomb1.gif");
        image2 = Toolkit.getDefaultToolkit().getImage("src/tankGame/version1/image/bomb2.gif");
        image3 = Toolkit.getDefaultToolkit().getImage("src/tankGame/version1/image/bomb3.gif");
    }

    //显示我方坦克作战信息
    public void showInfo(Graphics g){
        g.setColor(Color.BLACK);
        Font font = new Font("宋体",Font.BOLD,25);
        g.setFont(font);
        g.drawString("累计击毁敌方坦克数量",1020,30);
        drawTank(1020,60,g,0,0);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750); //填充颜色
        showInfo(g);
        //画出自己的坦克
        if(hero!=null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 1);
        }
        //画出敌人的坦克
        for (EnemyTank enemyTank: enemyTanks){
            if (enemyTank.isLive) {//当敌人坦克存活才画出
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 0);
                //画出敌人子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

        //如果bombs有对象
        for (int i=0; i<bombs.size(); i++){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if (bomb.life > 3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else{
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //让炸弹生命值减少
            bomb.lifeDown();
            //判断生命值，如果为0就删除
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }

        //画出子弹
//        if (hero.shot != null && hero.shot.isLive == true){
//            g.draw3DRect(hero.shot.x, hero.shot.y,1,1,false);
//        }
        for(int i=0; i<hero.shots.size();i++){
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive){
                g.draw3DRect(shot.x, shot.y,1,1,false);
            }else{
                hero.shots.remove(shot);
            }
        }
    }

    /**
     *
     * @param x： 坦克左上角x坐标
     * @param y： 坦克左上角y坐标
     * @param g： 画笔
     * @param direction： 坦克方向
     * @param type: 坦克类型
     */
    public void drawTank(int x, int y,Graphics g, int direction, int type){
        switch (type){ //给坦克设置颜色
            case 0: //敌方
                g.setColor(Color.cyan);
                break;
            case 1: //己方
                g.setColor(Color.yellow);
                break;
        }
        switch (direction){//0：上 1：右 2：下 3：左
            case 0: //表示向上
                g.fill3DRect(x,y,10,60,false); //左侧
                g.fill3DRect(x+30,y,10,60,false);//右侧
                g.fill3DRect(x+10,y+10,20,40,false);//中间
                g.fillOval(x+10,y+20, 20, 20);//画出圆形
                g.drawLine(x+20,y+30, x+20,y); //炮筒
                break;
            case 1: //表示向右
                g.fill3DRect(x,y,60,10,false); //左侧
                g.fill3DRect(x,y+30,60,10,false);//右侧
                g.fill3DRect(x+10,y+10,40,20,false);//中间
                g.fillOval(x+20,y+10,20,20);//画出圆形
                g.drawLine(x+30,y+20,x+60,y+20); //炮筒
                break;
            case 2: //表示向下
                g.fill3DRect(x,y,10,60,false); //左侧
                g.fill3DRect(x+30,y,10,60,false);//右侧
                g.fill3DRect(x+10,y+10,20,40,false);//中间
                g.fillOval(x+10,y+20, 20, 20);//画出圆形
                g.drawLine(x+20,y+30, x+20,y+60); //炮筒
                break;
            case 3: //表示向左
                g.fill3DRect(x,y,60,10,false); //左侧
                g.fill3DRect(x,y+30,60,10,false);//右侧
                g.fill3DRect(x+10,y+10,40,20,false);//中间
                g.fillOval(x+20,y+10,20,20);//画出圆形
                g.drawLine(x+30,y+20,x,y+20); //炮筒
                break;
            default:
        }
    }

    //判断是否击中坦克
    public void hitTank(Shot s, Tank enemyTank){
        switch (enemyTank.getDirection()){
            case 0: // 坦克向上
            case 2: // 坦克向下
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX()+40 &&
                        s.y > enemyTank.getY() && s.y < enemyTank.getY()+60){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    //击中坦克时，从集合中删掉
                    enemyTanks.remove(enemyTank);
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象，加入bombs里面
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1: // 坦克向右
            case 3: // 坦克向左
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX()+60 &&
                        s.y > enemyTank.getY() && s.y < enemyTank.getY()+40){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    enemyTanks.remove(enemyTank);
                    if(enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    //创建Bomb对象，加入bombs里面
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }
    //如果发出来多个子弹，在判断是否击中敌人时，需要把子弹集合中所有的子弹取出，进行判断
    public void hitEnemyTank(){
        //判断是否击中坦克
        for (int j=0; j<hero.shots.size();j++) {
            Shot shot = hero.shots.get(j);
            if (shot != null && shot.isLive) {//当前我方子弹在有效
                //遍历所有敌方坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }
    }
    
    //判断我方是否被击中
    public void hitHero(){
        for(int i=0; i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j=0; j<enemyTank.shots.size(); j++){
                Shot shot = enemyTank.shots.get(j);
                if(hero.isLive && shot.isLive){
                    hitTank(shot,hero);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {//处理wasd键
        if (e.getKeyCode() == KeyEvent.VK_W){
            hero.setDirection(0);
            if(hero.getY() > 0) {
                hero.moveUp();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            hero.setDirection(3);
            if(hero.getX() > 0) {
                hero.moveLeft();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            hero.setDirection(2);
            if (hero.getY()+60 < 750) {
                hero.moveDown();
            }
        }else if (e.getKeyCode() == KeyEvent.VK_D){
            hero.setDirection(1);
            if (hero.getX()+60 < 1000) {
                hero.moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            //判断当前子弹是否消亡
//            if(hero.shot == null || !hero.shot.isLive) {
//                hero.shotEnemy();
//            }
            hero.shotEnemy();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {//每隔100毫秒，重绘区域
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            //判断是否击中坦克
//            if (hero.shot != null && hero.shot.isLive){//当前我方子弹在有效
//                //遍历所有敌方坦克
//                for (int i=0; i<enemyTanks.size(); i++){
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot,enemyTank);
//                }
//            }
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }
}
