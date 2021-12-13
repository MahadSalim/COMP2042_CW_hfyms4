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

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * HomeMenu of the game where the user is introduced to game with the start screen
 *                 and can choose to start or exit the game
 */

public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroyer";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String HowToPlay_TEXT = "How To Play";
    private static final String MENU_TEXT = "Exit";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private final Rectangle menuFace;
    private final Rectangle startButton;
    private final Rectangle menuButton;
    private final Rectangle HelpButton;

    private JFrame frame;
    private Dimension dimension;


    private final BasicStroke borderStoke;
    private final BasicStroke borderStoke_noDashes;

    private final Font greetingsFont;
    private final Font gameTitleFont;
    private final Font creditsFont;
    private final Font buttonFont;
    private final Font helpbuttonFont;

    private final GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean helpClicked;





    /**
     *  @param owner HomeMenu- this would specify to the user loading the game
     * @param area HomeMenu- This specifies the Dimension area (resolution) the HomeMenu would be loaded in
     */




    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);


        Dimension btnDim = new Dimension(area.width /(9/3), area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        HelpButton = new Rectangle(btnDim);


        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);
        helpbuttonFont = new Font("Monospaced",Font.PLAIN,HelpButton.height-3);
    }



    /**
     *
     * @param g paint- this is a method where the component is asked to draw itself on the screen and further
     *         sets the instructions for when the 'Start' 'Exit' button is clicked
     */

    public void paint(Graphics g){

        drawMenu((Graphics2D)g);



        Toolkit t=Toolkit.getDefaultToolkit();
        Image i=t.getImage("brickdestroyerrr.png");
        g.drawImage(i, -30,-10,this);






    }


    public void drawMenu(Graphics2D g2d){
        /**
        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //method calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);


    }

    /**
     *
     * @param g2d drawText- this feature draws the specific text onto the homeMenu
     */

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 0.8;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);





    }

    /**
     *
     * @param g2d drawButton- this method draws the specified button onto the homeMenu
     */

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D nTxtRect = buttonFont.getStringBounds(HowToPlay_TEXT, frc);


        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - (startButton.height+HelpButton.height) -100));



        /**
         * @param set.location the location for the startButton
         */

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);


        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;


        /**
         * @param set.location drawButton- the location for the menuButton
         */

        menuButton.setLocation(x,y);

        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.drawString(MENU_TEXT,x,y);
        }
        /**
         * @param set.location drawButton- the location for the HelpButton
         */


        HelpButton.setLocation(150,210);





        if (helpClicked) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(HelpButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(HowToPlay_TEXT,150,230);
            g2d.setColor(tmp);
        }
        else {
            g2d.draw(HelpButton);
            g2d.setFont(helpbuttonFont);
            g2d.drawString(HowToPlay_TEXT,150,230);
        }


    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////
    ///////////////////////////////////////
    /////////////////////////////
    //////////////////
    /////////////
    /////////
    /////
    ///
    //



    /**
     *
     * @param mouseEvent mouseClicked- in the event that the mouse button is clicked & if it is not clicked then the
     *                   system prints out 'Goodbye *username*'
     */

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
           owner.enableGameBoard();

        }
        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(HelpButton.contains(p)){
            owner.enableHowToPlay();
        }
    }

    /**
     *
     * @param mouseEvent in this case if any button is clicked then the height & width of the button
     *                   increases by +1 to highlight the button being pressed
     *
     */

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
        else if(HelpButton.contains(p)){
            helpClicked = true;
            repaint(HelpButton.x,HelpButton.y,HelpButton.width+1,HelpButton.height+1);
        }
    }

    /**
     *
     * @param mouseEvent mouseReleased- if the mouse button is released then it repaints the button
     *                                  with a different width & height
     */

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
        else if(helpClicked){
            helpClicked = false;
            repaint(HelpButton.x,HelpButton.y,HelpButton.width+1,HelpButton.height+1);
        }
    }

    /**
     *
     * @param mouseEvent mouseEntered- this method is called if the mouse button is clicked
     *
     **/

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /**
     *
     * @param mouseEvent mouseExited- this method is called when mouse button is let go
     */

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    /**
     *
     * @param mouseEvent mouseDragged- this method is called in the event that the mouse is dragged
     */

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || menuButton.contains(p) || HelpButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
