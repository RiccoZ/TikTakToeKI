public class Game {

    int turn = 1;
    GameFrame gf;
    int[][] field = {
            {0,0,0},
            {0,0,0},
            {0,0,0}
    };

    public Game(GameFrame gf) {
        this.gf = gf;
    }

    public void click(int x, int y) {
        if(turn == 1) {
            if((x >= 1) && (x <= 3) && (y >= 1) && (y <= 3)) {
                if(field[x-1][y-1] == 0) {
                    x = x * 100;
                    y = 50 + y * 100;
                    gf.add(x, y);
                    field[x][y] = 1;
                }
            }
            turn = 2;
        }
    }

    
}
