package tutorial_Feb1;

import javax.swing.*;

public class Main {
    public static void main(String[]args){
        try{
            Main main = new Main();
            main.run();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"This is a picture of a ball");
        }
        Ball ball = new Ball();
        Frame frame = new Frame(ball);

    }
    private void run() throws Exception{
        throw new Exception();
    }
}
