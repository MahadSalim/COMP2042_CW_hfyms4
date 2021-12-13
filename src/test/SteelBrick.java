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
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is for the SteelBricks that are used in game as Bricks
 */

public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private final Random rnd;
    private final Shape brickFace;

    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }

    /**
     *
     * @param pos makeBrickFace- gives the value for the position for making the BrickFace
     * @param size makeBrickFace- sets the size for the brick
     * @return
     */

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     *
     * @return getBrick()- this returns the shape and dimensions for the brick
     */

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     *
     * @param point setImpact- sets the point for where the impact is
     * @param dir setImpact- gives the direction of the impact
     * @return
     */

    public  boolean setImpact(Point2D point , int dir){
        if(!super.isBroken())
            return false;
        impact();
        return !super.isBroken();
    }

    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}
