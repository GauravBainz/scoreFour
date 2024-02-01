package tutorial_Feb1;
import javax.swing.JFrame;

public class Frame extends JFrame {
    public Frame(Ball ball){
        setSize(600,600);
        setTitle("Ball Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(ball);
        setVisible(true);

    }
}
