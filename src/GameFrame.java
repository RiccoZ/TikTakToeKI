import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    Game g = new Game(this);

    private ArrayList<Line2D> linelist = new ArrayList<>();

    public GameFrame() {
        initialize();
    }

    private void initialize(){
        setSize(800, 700);
        setTitle("Tik Tak Toe");
        getContentPane().setBackground(Color.lightGray);

        setVisible(true);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int  x= e.getX();
                int y = e.getY();

                x = (x + 50) / 100; //check in which square the click is
                y /= 100;

                g.click(x, y);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void add(int x, int y) {
        linelist.add(new Line2D.Float(x - 30, y - 30, x + 30, y + 30));
        linelist.add(new Line2D.Float(x + 30, y - 30, x - 30, y + 30));
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        linelist.add(new Line2D.Float(150,100,150,400));
        linelist.add(new Line2D.Float(250,100,250,400));

        linelist.add(new Line2D.Float(50,200,350,200));
        linelist.add(new Line2D.Float(50,300,350,300));


        for (Line2D line: linelist) {
            g2.draw(line);
        }
    }
}
