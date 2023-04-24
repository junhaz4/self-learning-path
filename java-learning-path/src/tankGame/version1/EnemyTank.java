package tankGame.version1;

import java.util.Vector;
@SuppressWarnings({"all"})
//为了使坦克能够移动，需要使其成为线程
public class EnemyTank extends Tank implements Runnable{
    //使用vector存储所有的子弹
    Vector<Shot> shots = new Vector<>();
    //敌方坦克所有的vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
    //提供方法将myPanel的成员设置到到EnemyTank里面
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    //判断坦克是否重叠
    public boolean isTouchEnemyTank(){
        //判断当前坦克方向
        switch (this.getDirection()){
            case 0://上
                //判断是否和其他坦克碰撞
                for (int i=0; i<enemyTanks.size();i++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if(enemyTank != this){
                        //其他坦克方向上下
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+40]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+60]
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //当前坦克左上角[this.getX(),this.getY()]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+60){
                                return true;
                            }
                            //当前坦克右上角[this.getX()+40,this.getY()]
                            if(this.getX()+40 >= enemyTank.getX()
                                    && this.getX()+40 <= enemyTank.getX()+40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        //其他坦克方向左右
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+60]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+40]
                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //当前坦克左上角[this.getX(),this.getY()]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+40){
                                return true;
                            }
                            //当前坦克右上角[this.getX()+40,this.getY()]
                            if(this.getX()+40 >= enemyTank.getX()
                                    && this.getX()+40 <= enemyTank.getX()+60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                for (int i=0; i<enemyTanks.size();i++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if(enemyTank != this){
                        //其他坦克方向上下
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+40]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+60]
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //当前坦克右上角[this.getX()+60,this.getY()]
                            if(this.getX()+60 >= enemyTank.getX()
                                    && this.getX()+60 <= enemyTank.getX()+40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+60){
                                return true;
                            }
                            //当前坦克右下角[this.getX()+60,this.getY()+40]
                            if(this.getX()+60 >= enemyTank.getX()
                                    && this.getX()+60 <= enemyTank.getX()+40
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        //其他坦克方向左右
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+60]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+40]
                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //当前坦克右上角[this.getX()+60,this.getY()]
                            if(this.getX()+60 >= enemyTank.getX()
                                    && this.getX()+60 <= enemyTank.getX()+60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+40){
                                return true;
                            }
                            //当前坦克右下角[this.getX()+60,this.getY()+40]
                            if(this.getX()+60 >= enemyTank.getX()
                                    && this.getX()+60 <= enemyTank.getX()+60
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2: //下
                for (int i=0; i<enemyTanks.size();i++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if(enemyTank != this){
                        //其他坦克方向上下
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+40]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+60]
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //当前坦克左上角[this.getX(),this.getY()+60]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+40
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY()+60){
                                return true;
                            }
                            //当前坦克右下角[this.getX()+40,this.getY()+60]
                            if(this.getX()+40 >= enemyTank.getX()
                                    && this.getX()+40 <= enemyTank.getX()+40
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        //其他坦克方向左右
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+60]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+40]
                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //当前坦克左上角[this.getX(),this.getY()+60]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+60
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY()+40){
                                return true;
                            }
                            //当前坦克右下角[this.getX()+40,this.getY()+60]
                            if(this.getX()+40 >= enemyTank.getX()
                                    && this.getX()+40 <= enemyTank.getX()+60
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3: //左
                for (int i=0; i<enemyTanks.size();i++){
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //不和自己比较
                    if(enemyTank != this){
                        //其他坦克方向上下
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+40]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+60]
                        if(enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2){
                            //当前坦克左上角[this.getX(),this.getY()]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+60){
                                return true;
                            }
                            //当前坦克左下角[this.getX(),this.getY()+40]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+40
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY()+60){
                                return true;
                            }
                        }
                        //其他坦克方向左右
                        // x范围是 [enemyTank.getX(), enemyTank.getX()+60]
                        // y范围是 [enemyTank.getY(), enemyTank.getY()+40]
                        if(enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3){
                            //当前坦克左上角[this.getX(),this.getY()]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY()+40){
                                return true;
                            }
                            //当前坦克左下角[this.getX(),this.getY()+40]
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX()+60
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true){
            if (isLive && shots.size() < 1){
                Shot s = null;

                switch (getDirection()){
                    case 0:
                        s = new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s = new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s = new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s = new Shot(getX(),getY()+20,3);
                        break;
                }
                shots.add(s);
//                try {
//                    Thread.sleep(50);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
                new Thread(s).start();
            }
            //根据坦克方向来继续移动
            switch(getDirection()){
                case 0:
                    //让坦克保持一个方向走30下
                    for (int i=0; i<30; i++) {
                        if (getY()>0 && !isTouchEnemyTank()) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i=0; i<30; i++) {
                        if(getX()+60 < 1000 && !isTouchEnemyTank()) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i=0; i<30; i++) {
                        if (getY() + 60 < 750 && !isTouchEnemyTank()) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i=0; i<30; i++) {
                        if(getX() > 0 && !isTouchEnemyTank()) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }

            // 随机改变坦克方向
            setDirection((int)(Math.random()*4));
            //写多线程，必须考虑清楚线程什么时候结束
            if (!isLive){
                break;
            }
        }
    }
}
