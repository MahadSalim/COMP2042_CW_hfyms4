package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *
 * Represents the ball that is used for the game
 */


abstract public class Ball {

    private Shape ballFace;

    private final Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private final Color border;
    private final Color inner;

    private int speedX;
    private int speedY;

    /**
     *
     * @param center Ball- the centre position for the ball
     * @param radiusA Ball- the radius A of the ball
     * @param radiusB Ball-the radius B of the ball
     * @param inner Ball- the inside color of the ball
     * @param border Ball- the color of the border of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY() - (radiusB / 2));
        down.setLocation(center.getX(),center.getY() + (radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     *
     * @param center this creates the ball at the specified center
     * @param radiusA the radius position a of the ball when creating it
     * @param radiusB the radius position b of the ball
     * @return it returns by making the shape of the ball
     */

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);


        public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     *
     * @param x setSpeed-the speed at which the ball moves at x-axis
     * @param y setSpeed-the speed at which the ball moves at y-axis
     */

    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     *
     * @param s setXSpeed- this would be the speed that the ball moves at x position(left-right)
     */

    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     *
     * @param s setYSpeed- this indicates the speed of the ball at y-axis(up-down)
     */

    public void setYSpeed(int s){
        speedY = s;
    }


    public void reverseX(){
        speedX *= -1;
    }

    public void reverseY(){
        speedY *= -1;
    }

    /**
     *
     * @return getBorderColor- this method returns the border color of ball
     */

    public Color getBorderColor(){
        return border;
    }

    /**
     *
     * @return getInnerColor- this method returns inner border color of ball
     */

    public Color getInnerColor(){
        return inner;
    }

    /**
     *
     * @return getPosition- this returns the centre position of ball
     */

    public Point2D getPosition(){
        return center;
    }

    /**
     *
     * @return getBallFace- this returns the face of ball(how it looks)
     */

    public Shape getBallFace(){
        return ballFace;
    }

    /**
     *
     * @param p moveTo- represents the location in two-dimensional (x,y) coordinate
     */

    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     *
     * @param width setPoints- this gives the width of the ball which is used as setting points for ball
     * @param height setPoints- this gives the height of ball which is used to set points of location for ball
     */

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     *
     * @return speed getSpeedX- returns the value of speed for the x-axis
     */

    public int getSpeedX(){
        return speedX;
    }

    /**
     *
     * @return getSpeedY- returns the value for the speed for y-axis
     */

    public int getSpeedY(){
        return speedY;
    }


}
