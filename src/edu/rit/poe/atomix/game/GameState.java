/*
 * GameState.java
 *
 * Version:
 *      $Id$
 *
 * Copyright (c) 2009 Peter O. Erickson
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without major
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, and/or distribute copies of, but
 * under no circumstances sublicense and/or sell, the Software,
 * and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package edu.rit.poe.atomix.game;

import android.util.Log;
import edu.rit.poe.atomix.levels.Atom;
import edu.rit.poe.atomix.levels.Level;
import edu.rit.poe.atomix.levels.LevelManager;
import edu.rit.poe.atomix.levels.Square;
import java.util.EnumSet;

/**
 * A Java Bean style class to hold and persist game state for a single user.
 * 
 * @author  Peter O. Erickson
 *
 * @version $Id$
 */
public class GameState {
    
    public static enum Direction {
        
        UP,
        
        DOWN,
        
        RIGHT,
        
        LEFT;
        
    } // Direction
    
    private Level currentLevel;
    
    private Square[][] board;
    
    private Atom selected;
    
    /**
     * Constructs a new <tt>GameState</tt>.
     * 
     * @param   user    the user that this game state exists for
     */
    public GameState( User user ) {
        
        // load the initial level for game state
        LevelManager levelManager = LevelManager.getInstance();
        currentLevel = levelManager.getStartingLevel();
        
        // make a copy of the level's board, for own own use and modification
        board = currentLevel.copyBoard();
    }
    
    /**
     * Returns the game's board.
     * 
     * @return
     */
    public Square[][] getBoard() {
        return board;
    }
    
    /**
     * Moves the currently selected atom in the specified direction until an
     * obstacle is hit.
     * 
     * @param   direction       the direction to move the selected atom in
     * 
     * @throws  GameException   if there is no currently selected atom
     */
    public void moveSelected( Direction direction ) throws GameException {
        if ( selected == null ) {
            throw new GameException( "No currently selected atom." );
        }
        int endX = selected.getX();
        int endY = selected.getY();
        
        // perform an iterative search for the furthest distance the atom can
        // travel in the given direction
        int nextX = endX;
        int nextY = endY;
        boolean hit = false;
        while ( ! hit ) {
            // is next move in given direction a wall/end of board?
            switch ( direction ) {
                case RIGHT: {
                    nextX++;
                } break;
                case LEFT: {
                    nextX--;
                } break;
                case DOWN: {
                    nextY++;
                } break;
                case UP: {
                    nextY--;
                } break;
            }
            
            // hit a board boundary?
            if ( ( nextY == board.length ) || ( nextX == board[ 0 ].length ) ) {
                hit = true;
            } else if ( board[ nextY ][ nextX ] == Square.WALL ) {
                hit = true;
            } else if ( board[ nextY ][ nextX ] instanceof Atom ) {
                hit = true;
            } else {
                endX = nextX;
                endY = nextY;
            }
        }
        
        // clear the original position
        board[ selected.getY() ][ selected.getX() ] = Square.EMPTY;
        
        // enter the final position
        selected.setX( endX );
        selected.setY( endY );
        board[ endY ][ endX ] = selected;
    }
    
    /**
     * Returns the possible directions that the currently selected atom can
     * move.
     * 
     * @return  a set of directions that the selected atom can move
     */
    public EnumSet<Direction> getPossibleDirections() {
        EnumSet<Direction> directions = EnumSet.noneOf( Direction.class );
        
        if ( selected != null ) {
            int x = selected.getX();
            int y = selected.getY();
            
            // can we go left?
            if ( ( ( x - 1 ) >= 0 ) &&
                    ( board[ y ][ x - 1 ] == Square.EMPTY ) ) {
                directions.add( Direction.LEFT );
            }
            
            // can we go right?
            if ( ( ( x + 1 ) < board[ 0 ].length ) &&
                    ( board[ y ][ x + 1 ] == Square.EMPTY ) ) {
                directions.add( Direction.RIGHT );
            }
            
            // can we go up?
            if ( ( ( y - 1 ) >= 0 ) &&
                    ( board[ y - 1 ][ x ] == Square.EMPTY ) ) {
                directions.add( Direction.UP );
            }
            
            // can we go down?
            if ( ( ( y + 1 ) < board.length ) &&
                    ( board[ y + 1 ][ x ] == Square.EMPTY ) ) {
                directions.add( Direction.DOWN );
            }
        }
        Log.d( "GameState", "POSSIBLE DIRECTIONS:" );
        for ( Direction dir : directions ) {
            Log.d( "GameState", "  " + dir );
        }
        
        return directions;
    }
    
    public void select( int x, int y ) throws GameException {
        try {
            selected = ( Atom )board[ y ][ x ];
        } catch ( ClassCastException e ) {
            throw new GameException( "No atom at specified location." );
        }
    }
    
    public Atom getSelected() {
        return selected;
    }
    
} // GameState
