import java.util.*;
import javax.swing.*;
public class App{
    public static void main(String args[]) throws Exception
    {
        int boardwidth=600; //600-pixcels
        int boardheight=boardwidth;
        
        
        JFrame frame=new JFrame("Snake");// creates a window
        frame.setVisible(true);//making frame visible
        frame.setSize(boardwidth, boardheight);//making window size 600-pixcel
        frame.setLocationRelativeTo(null);// this open up window at center of the screen;
        frame.setResizable(false);// ensure that window size does not change by dragging the corners or borders
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//this makes programe terminate when user clicks on the X button on the window.
        // now i created a window now i need to have a JPanel to draw my game for that I'll create a new class(SnakeGame).
        // now create instance of this SnakeGame class.

        SnakeGame snakeGame = new SnakeGame(boardwidth, boardheight);
        frame.add(snakeGame); // adds JPanel snakeGmae(wth background black) to this frame(Snake).?? this will add components to frame.
        frame.pack();// as title bar is there the panel is not 600,600 pixcel to make it 600,600 along four dimensions
        snakeGame.requestFocus(); //now our sanke game going to listen to key presses.
    }
} 
