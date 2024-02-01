package tutorial_Feb1;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;
public class Ball extends JComponent {
    private int x = 150;
    private int y = 200;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double me = new Ellipse2D.Double(x,y,300,300);
        g2.setColor(Color.BLUE);
        g2.fill(me);


    }
}