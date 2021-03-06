package test;

import java.awt.*;


/**
 * Created by filippo on 04/09/16.
 *
 *
 * ClayBrick is also an extension of the class Brick
 */

public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     *
     * @param point ClayBrick- determines the position of where the ClayBrick is
     * @param size ClayBrick- determines the size of the ClayBrick
     */


    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
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

    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
