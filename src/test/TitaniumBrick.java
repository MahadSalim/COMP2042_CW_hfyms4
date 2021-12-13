package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class TitaniumBrick extends Brick {

    private static final String NAME = "Titanium Brick";
    private static final Color DEF_INNER = new Color(135, 134, 129);
    private static final Color DEF_BORDER = Color.lightGray;
    private static final int Titanium_STRENGTH = 3;
    private static final double Titanium_PROBABILITY = 0.2;

    private final Random rnd;
    private final Crack crack;
    private Shape brickFace;

    public TitaniumBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,Titanium_STRENGTH);
        rnd = new Random();
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }



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

    public void impact(){
        if(rnd.nextDouble() < Titanium_PROBABILITY){
            super.impact();
        }
    }

}



