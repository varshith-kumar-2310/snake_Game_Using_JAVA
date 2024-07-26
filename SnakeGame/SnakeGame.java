import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //this is used to store the segments of snake body.
import java.util.Random;    //this is used to get random (x,y) values to place the snake food.
import javax.swing.*;


public class SnakeGame extends JPanel  implements ActionListener, KeyListener//here we inherited JPanel to use it's prperties by SankeGame Class.
{
    private class Tail{ //keeps track of x,y  values
        int x;
        int y;
        
        Tail(int x, int y)
        {
            this.x=x;
            this.y=y;
        }
    }
    int boardwidth;
    int boardheight;
    //we divide  600*600 pickels into 24 pickels in each row and column 
    //each pickel is 25 pickel=> 25*24=600
    //if a square is placed at (5,5) then it's position is (25*5,25*5)=>(125,125).
    int tailsize=25; //each square.

    //snake
    Tail snakeHead;
    ArrayList<Tail> snakeBody;

    //food
    Tail food;
    //to place food in random location we use random.
    Random random;

    //game logic
    Timer gameloop;  // to make the snake move
    int velocityX;// +ve if moves right , -ve if moves to left.
    int velocityY;// +ve if moves down , -ve if moves
    boolean gameOver = false;

    SnakeGame(int boardwidth, int boardheight)
    {
        this.boardwidth = boardwidth; // this keyword is used to distinguish the two boardwidth's one is parameter and another is member of the class SnakeGame.
        this.boardheight = boardheight;//this.boardheight means boardheight member of this class(SnakeGame)
        setPreferredSize(new Dimension(this.boardwidth, this.boardheight));
        setBackground(Color.black);
        addKeyListener(this); 
        setFocusable(true); // to listen to key events

        snakeHead = new Tail(5,5); //initial snake size;//(5,5) is default starting place.
        // now i had tail . now i need to draw it.(used paintComponent() function.
        snakeBody = new ArrayList<Tail>(); 

        food = new Tail(10, 10); //draw it in draw();
        random= new Random();
        placeFood(); // places food at random locations rather than placing food at (10,10) every time.

        velocityX=0;
        velocityY=0;
        // since velocityX,velocitY == 0 snake is  not moving.
        //now we need to control this velocityX and Y with keys events (Arrows). for that i implemented key listener.

        gameloop = new Timer(100, this); //rus for every 100 milli sec.
        gameloop.start();


    }
    public void paintComponent(Graphics g) // Graphics g used for drawing
    {
        super.paintComponent(g);
        draw(g);// now we need define a draw function
    }

    public void draw(Graphics g)
    {
        //grid for understanding.
        // for(int i=0;i<boardwidth/tailsize;i++)
        // {
        //     // we neeed (x1,y1) & (x2,y2) to draw a line =>(x1, y1, x2, y2)
        //     g.drawLine(i*tailsize, 0, i*tailsize, boardheight); // draws a vertical line
        //     g.drawLine(0, i*tailsize, boardwidth, i*tailsize); // draws a horizontal line
        // }

        //snake Head
        g.setColor(Color.green);// snake color is set to green.
        // g.fillRect(snakeHead.x * tailsize , snakeHead.y * tailsize , tailsize , tailsize);//(x,y) for position and tailsizes for width and heigth(i.e 25pickel each sauare size).
        g.fill3DRect(snakeHead.x * tailsize , snakeHead.y * tailsize , tailsize , tailsize, true);

        //snake Body
        for(int i = 0 ; i < snakeBody.size() ; i++)// to draw the snake body
        {
            Tail snakePart = snakeBody.get(i);
            // g.fillRect(snakePart.x * tailsize, snakePart.y * tailsize, tailsize, tailsize);//draw the snake body as it eats the food.
            g.fill3DRect(snakePart.x * tailsize, snakePart.y * tailsize, tailsize, tailsize, true);
        }

        //score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if(gameOver)
        {
            g.setColor(Color.red);
            g.drawString("Game Over: " + String.valueOf(snakeBody.size()), tailsize-16, tailsize);
        }
        else{
            g.drawString("Score: "+ String.valueOf(snakeBody.size()), tailsize-16, tailsize);
        }

        //food
        g.setColor(Color.red);
        // g.fillRect(food.x * tailsize, food.y * tailsize, tailsize, tailsize);
        g.fill3DRect(food.x * tailsize, food.y * tailsize, tailsize, tailsize, true);
    }


    public void placeFood()
    {
        food.x= random.nextInt(boardwidth/tailsize);//generates x value range from(0, boardwidth/tailsize) i.e(0,24)
        food.y= random.nextInt(boardheight/tailsize); //generates y value range from(0, boardheight/tailsize)
    }

    public boolean collision(Tail tail1, Tail tail2) // to check the snake is reached it's food or not.
    {
        return tail1.x == tail2.x && tail1.y == tail2.y;
    }

    public void move()
    {
        //eat food
        if(collision(snakeHead , food))
        {
            // add new segment to snake body.
            snakeBody.add(new Tail(food.x, food.y));
            placeFood();
        }

        //snake Body
        // here snake body added from last , first tails will move then head since head is the reference point.
        for(int i=snakeBody.size()-1 ; i >= 0; i--)
        {
            Tail snakePart= snakeBody.get(i);
            if(i == 0)
            {
                snakePart.x= snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else{
                Tail prevSnakePart = snakeBody.get(i-1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        //snake head
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;

        //game over conditions
        for(int i = 0; i < snakeBody.size() ; i++)
        {
            Tail snakePart =snakeBody.get(i);
            //collide with snakeHead
            if(collision(snakeHead, snakePart))
            {
                gameOver = true;
            }
        }
         //game over if snake hit the walls.
        if(snakeHead.x * tailsize < 0 || snakeHead.x * tailsize > boardwidth || snakeHead.y * tailsize < 0 || snakeHead.y * tailsize > boardheight)
        {
            gameOver = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();// this function upadtes the x&y positions
        repaint(); // every 100 milli sec we call this repaint function that calls draw function().
        if(gameOver){
            gameloop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1)  // means we press the up key. //because snake canot move back on to it's body. velocityY!=1 means snake not moving in upward direction(here  we cnnot move up if it is giing in upward direction)
        {
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) // maens we press the down key. && snake not moving in upward direction
        {
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) // maens we press the left arrow key. && snake not moving in right direction
        {
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) // maens we press the right arrow key && snake not moving in left direction
        {
            velocityX = 1;
            velocityY = 0;
        }
    }

    // do not need this methods these 3 abstract methods are to be implemnted when we implements keyListener but we need only keyPressed. so, we implemented that only.
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
