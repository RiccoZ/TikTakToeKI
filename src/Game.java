public class Game {

    int turn = 1;
    GameFrame gf;
    int[][] field = {
            {1,2,0},
            {0,0,0},
            {0,0,0}
    };

    public Game(GameFrame gf) {
        this.gf = gf;
    }

    public void click(int x, int y) {
        if(turn == 1) {
            if((x >= 1) && (x <= 3) && (y >= 1) && (y <= 3)) { //click must be in gamefield
                if(field[x-1][y-1] == 0) {
                    x = x * 100; //middle of square
                    y = 50 + y * 100; //middle of square
                    gf.add(x, y);
                    field[x][y] = 1; //add mark to array
                }
            }
            turn = 2;
        }
        checkwin(1, x, y);
    }

    private boolean checkwin(int player, int x, int y) { //check if player {player} won
        int sum = 0;

        for(int i : field[y]) {
            sum += i;
            System.out.println(sum);
        }

        sum = 0;
        for(int i : field[x]) {
            sum += i;
            System.out.println(sum);
        }
        sum = 0;
        if(y == x) {
            sum = field[0][0] + field[1][1] + field[2][2];
        }

        return true;
    }
}
