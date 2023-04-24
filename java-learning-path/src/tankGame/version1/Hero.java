package tankGame.version1;

import java.util.Vector;

public class Hero extends Tank{
    Shot shot = null;
    //发射多个子弹
    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemy(){
        //要根据当前坦克的位置创建shot对象
        if (shots.size() == 5){
            return;
        }
        switch(getDirection()){ //得到坦克方向
            case 0:
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1:
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot = new Shot(getX(),getY()+20,3);
                break;
        }
        //把子弹加入集合
        shots.add(shot);
        //启动shot线程
        new Thread(shot).start();
    }
}
