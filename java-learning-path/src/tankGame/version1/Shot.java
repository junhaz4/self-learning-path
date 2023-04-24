package tankGame.version1;

public class Shot implements Runnable{ //子弹
    int x; // 横坐标
    int y; // 纵坐标
    int direction = 0; //方向
    int speed = 2; // 速度
    boolean isLive = true; //子弹是否存活

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 0：上 1：右 2：下 3：左
            switch (direction){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            //子弹到边界，应该销毁
            //子弹碰到敌人时，应该结束线程
            if (!(x >= 0 && x <= 1000 && y >=0 && y <= 750 && isLive)){
                isLive = false;
                break;
            }
        }
    }
}
