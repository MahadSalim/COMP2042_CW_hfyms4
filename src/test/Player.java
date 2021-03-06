/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import java.awt.*;

/**
 *Includes the  controls and functions for the player
 */

public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private final Rectangle playerFace;
    private final Point ballPoint;
    private int moveAmount;
    private final int min;
    private final int max;

    /**
     *
     * @param ballPoint Player- this gets the points of the ball
     * @param width Player- this is the width set for the panel(which the player controls)
     * @param height Player- this is the height set for the panel(which the player controls)
     ** @param container Player- this is the rectangle of the panel
     */

    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     *
     * @param width makeRectangle-  this sets the width for the rectangle
     * @param height makeRectangle- this sets the height for the rectangle
     * @return
     */

    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     *
     * @param b impact- this is used to specify the ball used in game
     * @return impact- this returns the value of positions for the ball
     */


    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }


    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    public void stop(){
        moveAmount = 0;
    }

    /**
     *
     * @return getPlayerFace- this returns the face(shape/look) of the player
     */

    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     *
     * @param p moveTo- this point p is used to specify the location of the ball
     */

    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
