public class Main {
    public static void main(String[] args) {
        //迷宫问题
        int[][] map = new int[8][7]; //0可以走，1障碍物
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        T t1 = new T();
        t1.findWay(map, 0, 0);
    }
}

class T {
    public boolean findWay(int[][] map, int i, int j) {
        // starting position (1,1), ending position (6,5)
        // 1：障碍物， 2：可以走， 3：走过, 如果map[6][5]==2，说明完成
        // 搜索方向：下->右->上->左
        //Scanner scanner = new Scanner(System.in);
        int a = 1 + 2 + 3;
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else { //map[i][j] = 1,2,3
                return false;
            }
        }
    }
}