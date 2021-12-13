package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * Cement brick is an extension of the class Brick
 */

public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(135, 134, 129);
    private static final Color DEF_BORDER = new Color(135, 134, 129);
    private static final int CEMENT_STRENGTH = 2;

    private final Crack crack;
    private Shape brickFace;

    /**
     *
     * @param point CementBrick- specifies the position of the CementBrick
     * @param size CementBrick- determines the size of the CementBrick
     */

    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
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
     * @param point setImpact- sets the point for where the impact is
     * @param dir setImpact- gives the direction of the impact
     * @return
     */

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(!super.isBroken())
            return false;
        super.impact();
        if(super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     *
     * @return getBrick- gives back the information of the appearance of the class brick (brickFace)
     */

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    private void updateBrick(){
        if(super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
