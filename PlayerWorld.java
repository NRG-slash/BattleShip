import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * Write a description of class PlayerWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayerWorld extends World
{
    private int [][] board = new int [10][10];
    
    private boolean firstTurn = true;
    private boolean playerOneTurn;
    private boolean messageShown = false;
    
    private int shipCounter = 0;
    
    private boolean addedToGrid = false;
    
    private Ship toAdd;
    
    private int refreshCounter = 10;
    
    /**
     * PlaerWorld is thee Constructor for objects of class PlayerWorld.
     * 
     * @param there are none
     * @return objects of type PlayerWorld
     */
    public PlayerWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(720, 720, 1);
        
        drawLines();
        
        for( int r= 0; r < board.length; r++)
        {
            for( int c =0; c < board[r].length; c++)
            {
                board[r][c] = 0;
            }
        }
    }
    
    /**
     * drawLines draws 18 lines on the world to make a 10 by 10 grid where we can place ships for the game
     * 
     * @param there are no paramiters
     * @return nothing is returned
     */
    private void drawLines()
    {
        getBackground().setColor( Color.BLACK );
        
        for( int i = 72; i < getWidth(); i += 72 )
        {
            getBackground().drawLine(i, 0, i, getHeight() );
            getBackground().drawLine(0, i, getWidth(), i);
        }
    }
    
    /**
     * act is the act cycle
     * 
     * @param there are no paramiters
     * @return nothing is returned
     */
    public void act()
    {
        Ship currentShip;
        GreenfootImage transparentShip;
        
        displayTurn();
        
        if( firstTurn == true )
        {
            addShips();
        }
        else
        {
            for(int i = 0; i < getObjects(Ship.class).size(); i++ )
            {
                currentShip = getObjects(Ship.class).get(i);
                transparentShip = new GreenfootImage( currentShip.getImage().getWidth(),currentShip.getImage().getHeight() );
                currentShip.setImage( transparentShip );
                
            }
            Greenfoot.stop();
        }
        
    }
    
    /**
     * displayTurn Shows different messages at different points of the game
     * 
     * @param there are no paramiters
     * @return Nothing is returned
     */
    private void displayTurn()
    {
        
        if( firstTurn == true )
        {
            if(messageShown == false)
            {
                JOptionPane.showMessageDialog( null, "player One, please select where your should be placed", "PLEASE PLACE YOUR SHIPS", JOptionPane.PLAIN_MESSAGE );
                
                messageShown = true;
            }
        }
        else
        {
            if(messageShown == false)
            {
                JOptionPane.showMessageDialog( null, "playerTwo, please choose a place to open fire on", "Attack Ships",JOptionPane.PLAIN_MESSAGE );
                
                messageShown = true;
            }
        }
    }
    
    /**
     * addShips
     * 
     * @param there are no paramiters
     * @return Nothing is returned
     */
    private void addShips()
    {
        if(shipCounter >= 5 )
        {
            firstTurn = false;
            messageShown = false;
            fillBoard();
            displayBoard();
        }
        else
        {
            if( addedToGrid == false )
            {
                toAdd = checkShip();
                //adds the ship at a defalt location because the code in Ship makes the...
                addObject( toAdd, 0, 0);
                
                addedToGrid = true;
            }
            
            checkKeyPress( toAdd );
            if( Greenfoot.mouseClicked(null) )
            {
                shipCounter++;
                
                addedToGrid = false;
            }
        }
    }
    
    /**
     * checkShip Checks what ship is added to grid
     * 
     * @param there are no paramiters
     * @return next Ship that will be added to the world
     */
    private Ship checkShip()
    {
       Ship current = new Ship();
       
       if( shipCounter == 0)
       {
           current = new Carrier();
       }
       
       if( shipCounter == 1)
       {
           current = new Battleship();
       }
       
       if( shipCounter == 2)
       {
           current = new Sub();
       }
       
       if( shipCounter == 3)
       {
           current = new Destroyer();
       }
       
       if( shipCounter == 4)
       {
           current = new Patrol();
       }
       
       return current;
    }
    
    private void checkKeyPress( Ship current )
    {
        if(Greenfoot.isKeyDown("shift") && refreshCounter > 10 )
        {
            current.setRotation( current.getRotation() + 90);
            refreshCounter = 0;
        }
        else
        {
            refreshCounter++;
        }
    }
    
    /**
     * fillboard fills the numaric representation of the board with ones where the ships are located
     * 
     * @param There are no paramiters
     * @return Nothing is returned
     */
    private void fillBoard()
    {
        Ship toAdd;
        
        int currentX;
        int currentY;
        int rotation;
        
        for( int i = 0; i < getObjects(Ship.class).size(); i++)
        {
            toAdd = getObjects(Ship.class).get(i);
            
            currentX = toAdd.getX();
            currentY = toAdd.getY();
            rotation = toAdd.getRotation();
            
            for( int j = 0; j < (toAdd.getSize() +1) / 2; j++ )
            {
                if( toAdd.getSize() % 2 == 1)
                {
                    if( rotation == 0 || rotation == 180)
                    {
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 - j ] = 1;
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 + j ] = 1;
                    }
                    else
                    {
                        board[ (currentY - 36) / 72 - j ] [ (currentX - 36) / 72 ] = 1;
                        board[ (currentY - 36) / 72 + j ] [ (currentX - 36) / 72 ] = 1;
                    }
                }
                else
                {
                    if( rotation == 0 || rotation == 180)
                    {
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 - j ] = 1;
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 + j ] = 1;
                        board[ (currentY - 36) / 72 ] [ (currentX - 36) / 72 + j + 1 ] = 1;
                    }
                    else
                    {
                        board[ (currentY - 36) / 72 - j ] [ (currentX - 36) / 72 ] = 1;
                        board[ (currentY - 36) / 72 + j ] [ (currentX - 36) / 72 ] = 1;
                        board[ (currentY - 36) / 72 + j + i ] [ (currentX - 36) / 72 + j + 1 ] = 1;
                    }
                }
            }
        }
    }
    
    /**
     * displayBoard Displays the numarical representaion of the gameboard where 1's indicate that a ship was places at that location and 0's
     * indecate that no ship is placed
     * 
     * @param There are no paramiters
     * @return Nothing is returned
     */
    private void displayBoard()
    {
        for( int r = 0; r < board.length; r++ )
        {
            for( int c = 0; c < board[r].length; c++)
            {
                System.out.print( board[r] [c] + "\t" );
            }
            
            System.out.println();
        }
        
        System.out.println();
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
