import java.lang.reflect.Array;
import java.util.ArrayList;

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
        x--;
        y--;
        if(turn == 1) {
            if((x >= 0) && (x <= 2) && (y >= 0) && (y <= 2)) { //click must be in gamefield
                if(field[x][y] == 0) {
                    field[x][y] = 1; //add mark to array
                    int xnew = (x+1) * 100; //middle of square
                    int ynew = 50 + (y+1) * 100; //middle of square
                    gf.addcross(xnew, ynew);
                }
            }
            turn = 2;

            checkwin(x, y);
            react();
        }
    }

    private boolean checkwin(int x, int y) { //check if player {player} won
        int sum = 0;

        for(int i : field[y]) {
            sum += i;
        }

        if(Math.abs(sum) == 3) { //check if someone won
            System.out.println("win");
            turn = 0;
            return true;
        }

        sum = 0;

        for(int i : field[x]) {
            sum += i;
        }

        if(Math.abs(sum) == 3) { //check if someone won
            System.out.println("win");
            turn = 0;
            return true;
        }

        sum = 0;

        if(y == x) {
            sum = field[0][0] + field[1][1] + field[2][2];
        }

        if(Math.abs(sum) == 3) { //check if someone won
            System.out.println("win");
            turn = 0;
            return true;
        }

        return false;
    }

    private ArrayList<Integer> getnums() {
        ArrayList<Integer> listofnums = new ArrayList<Integer>();

        for (int x = 0; x < 3; x++) {
            int sum = 0;
            for (int y = 0; y < 3; y++) {
                sum += field[x][y];
            }
            listofnums.add(sum);
        }

        for (int y = 0; y < 3; y ++) {
            int sum = 0;
            for (int x = 0; x < 3; x++) {
                sum += field[x][y];
            }
            listofnums.add(sum);
        }

        listofnums.add(field[0][0] + field[1][1] + field[2][2]);
        listofnums.add(field[0][2] + field[1][1] + field[2][0]);

        return listofnums;
    }



    private void react() {
        System.out.println(getnums());

        ArrayList<Integer> nums = getnums();

        int pos = 0;

        int search = 0;

        if (nums.contains(-2)) { //check if pc can win
            search = -2;
        } else {
            if(nums.contains(2)) { //check if enemy can win
                search = 2;
            } else {
                if(nums.contains(-1)) { //check if can build opportunity to win
                    search = -1;
                }
            }
        }

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) == search) {
                pos = i;
                break;
            }
        }

        int x = pos % 3;
        int y = 0;

        int div = pos / 3;

        if (div == 0) {
            for (y = 0; y < 3; y++) {
                if (field[x][y] == 0) {
                    break;
                }
            }
        } else {
            if (div == 1) {
                y = x;
                for (x = 0; x < 3; x++) {
                    if (field[x][y] == 0) {
                        break;
                    }
                }
            } else {
                if(pos % 3 == 0) {
                    if(field[0][0] == 0) {
                        x = 0;
                        y = 0;
                    } else {
                        if(field[1][1] == 0) {
                            x = 1;
                            y = 1;
                        } else {
                            if(field[2][2] == 0) {
                                x = 2;
                                y = 2;
                            }
                        }
                    }
                } else {
                    if(field[0][2] == 0) {
                        x = 0;
                        y = 2;
                    } else {
                        if(field[1][1] == 0) {
                            x = 1;
                            y = 1;
                        } else {
                            if(field[2][0] == 0) {
                                x = 2;
                                y = 0;
                            }
                        }
                    }
                }


            }
        }

        System.out.println(x + "/" + y);

        field[x][y] = -1; //add mark to array
        int xnew = (x+1) * 100; //middle of square
        int ynew = 50 + (y+1) * 100; //middle of square
        gf.addcircle(xnew, ynew);
        turn = 1;
        checkwin(x,y);

    }
}
