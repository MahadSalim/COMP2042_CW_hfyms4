package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 *
 * represents the class for the bricks that have to be destroyed by ball
 */

abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;
    private String name;

    /**
     * This method determines the crack on the brick depending on where the impact is from
     */

    public class Crack{

        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;


        private final GeneralPath crack;

        private final int crackDepth;
        private final int steps;

        /**
         *
         * @param crackDepth Crack- The integer defined above, defines how much a brick has cracked
         * @param steps Crack- defines the step of stage at which broken-brick is
         */

        public Crack(int crackDepth, int steps){

            crack = new GeneralPath();
            this.crackDepth = crackDepth;
            this.steps = steps;

        }

        /**
         *
         * @return GeneralPath- gives the value of the step at which the crack of the brick is
         */

        public GeneralPath draw(){

            return crack;
        }


        public void reset(){
            crack.reset();
        }

        /**
         *
         * @param point makeCrack- this calls for the method to paint the crack at a specific point
         * @param direction makeCrack- this specifies the direction in which it is drawn
         */

        protected void makeCrack(Point2D point, int direction){
            Rectangle bounds = Brick.this.brickFace.getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();


            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;

            }
        }

        /**
         *
         * @param start makeCrack- this sets the start location for the point
         * @param end makeCrack- this sets the end location for point
         */

        protected void makeCrack(Point start, Point end){

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x,start.y);

            double w = (end.x - start.x) / (double)steps;
            double h = (end.y - start.y) / (double)steps;

            int bound = crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < steps;i++){

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);

            }

            path.lineTo(end.x,end.y);
            crack.append(path,true);
        }


        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return rnd.nextInt(n) - bound;
        }

        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

        private int jumps(int bound,double probability){

            if(rnd.nextDouble() > probability)
                return randomInBounds(bound);
            return  0;

        }


        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int pos;

            switch (direction) {
                case HORIZONTAL -> {
                    pos = rnd.nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos, to.y);
                }
                case VERTICAL -> {
                    pos = rnd.nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x, pos);
                }
            }
            return out;
        }

    }



    private static Random rnd;

    Shape brickFace;

    private final Color border;
    private final Color inner;

    private final int fullStrength;
    private int strength;

    private boolean broken;

    /**
     *
     * @param name Brick- gives a name to the different cracked-bricks
     * @param pos Brick- defines the position for the brick
     * @param size Brick- sets the size for the brick
     * @param border Brick- defines the borders for the cracked brick
     * @param inner Brick- the color for the inside of brick
     * @param strength Brick- defines the strength of brick:- cracked brick = less strength
     */

    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        this.name = name;
        rnd = new Random();
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     *
     * @param pos makeBrickFace- gives the value for the position for making the BrickFace
     * @param size makeBrickFace- sets the size for the brick
     * @return makeBrickFace- in this instance gives back the value - if the brick is broken or not
     */

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;


    }

    /**
     *
     * @return gives back the value for the border, inside color, or if the brick is broken (return border, return inner, return broken)
     *
     */

    public abstract Shape getBrick();

    /**
     *
     * @return returns the border color for the brick
     */

    public Color getBorderColor(){
        return  border;
    }

    /**
     *
     * @return gives the inner color for brick
     */

    public Color getInnerColor(){
        return inner;
    }

    /**
     *
     * @param b this sets the instructions for the impact on brick when it touches left,right,up or side of brick
     * @return
     */

    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;
        return out;
    }

    /**
     *
     * @return gives the value if the brick is broken or not
     */

    public final boolean isBroken(){
        return !broken;
    }


    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * sets the impact on the brick, and if it breaks or not
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }



}





