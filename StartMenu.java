import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Name Garrett Laturski
 * Course: CS40S
 * Teacher: Mr. Hardman
 * Lab #1, program # 0
 * Date last modified: Feb 22nd, 2018
 */
public class StartMenu extends World
{
    private GreenfootImage startBackground = new GreenfootImage( "Battleship.jpg" );
    /**
     * StartMenu is the Constructor for objects of class StartMenu.
     * 
     * @param there are no paramiters
     * @return objects of StartMenu
     */
    public StartMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1080, 720, 1);
        
        startBackground.scale(getWidth(), getHeight() );
        
        setBackground( startBackground );
        
        addObject( new StartButton() , getWidth() /2, getHeight() - 100 );
        
        
    }
}
