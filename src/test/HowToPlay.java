package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 *  This is the menu for the HowToPlay section where the user can read the instructions of the game
 */

public class HowToPlay extends JComponent implements MouseListener, MouseMotionListener {

    private static final String Header = "How To Play BrickDestroyer";
    private static final String Instructions = "BrickDestroyer is a easy game with fairly simple game";
    private static final String Instructions2 = "controls. The aim of the game is to destroy the";
    private static final String Instructions3 = "bricks using the ball which will bounce when it";
    private static final String Instructions4 = "touches an object/wall. You can control the slider";
    private static final String Instructions5 = "and aim it to destroy the bricks. The aim of the game";
    private static final String Instructions6 = "is to destroy all the bricks without letting the ball";
    private static final String Instructions7 = "cross below the slider.You have 3 lives and then the  ";
    private static final String Instructions8 = "game is over. Use the 'a' button to move left 'd' ";
    private static final String Instructions9 = "button to move right and space button to pause game";
    private static final String Footer = "Enjoy :)";
    private static final String Return_txt = "Back";

    private static final Color BG_Color = Color.BLACK;
    private static final Color Border_Color = new Color(200,8,21);
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);
    private static final Color Text_Color = Color.WHITE;
    private static final Color CLICKED_BUTTON_COLOR = BG_Color.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int Border_Size = 5;
    private static final float[] DASHES = {12,6};

    private final Rectangle menuFace;
    private final Rectangle ReturnButton;

    private final BasicStroke borderStroke;


    private final Font HeaderFont;
    private final Font InstructionsFont;
    private final Font FooterFont;
    private final Font ReturnButtonFont;

    private final GameFrame owner;

    private boolean ReturnClicked;

    /**
     *
     * @param owner HowToPlay- this gets the owner of the GameFrame
     * @param area HowToPlay- this sets the area for the window
     */

    public HowToPlay(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width/5, area.height/12);
        ReturnButton = new Rectangle(btnDim);

        borderStroke = new BasicStroke(Border_Size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        HeaderFont = new Font("Noto Mono", Font.BOLD,30);
        InstructionsFont = new Font("Monospaced",Font.PLAIN,13);
        FooterFont = new Font("Noto Mono", Font.BOLD, 20);
        ReturnButtonFont = new Font("Monospaced",Font.PLAIN,ReturnButton.height-4);

        ;

    }

    /**
     *
     * @param g paint- this is used to draw the page when required
     */

    public void paint(Graphics g) {drawPage((Graphics2D)g); }

    public void drawPage(Graphics2D g2d) {

        drawContainer(g2d);

        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        drawText(g2d);
        drawButton(g2d);

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);

        g2d.setFont(HeaderFont);
        g2d.setColor(Color.RED);
        g2d.drawString(Header,25,40);

        g2d.setFont(InstructionsFont);
        g2d.setColor(Color.WHITE);
        g2d.drawString(Instructions,9,70);

        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions2,10,90);


        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions3,10,110);

        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions4,10,130);

        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions5,10,150);

        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions6,10,170);

        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions7,10,190);

        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions8,10,210);

        g2d.setFont(InstructionsFont);
        g2d.drawString(Instructions9,10,230);

        g2d.setFont(FooterFont);
        g2d.setColor(Color.GREEN);
        g2d.drawString(Footer,180,270);


    }

    /**
     *
     * @param g2d drawContainer- this draws the container of the HowToPlay window
     */

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_Color);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStroke);
        g2d.setColor(Border_Color);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);

    }

    /**
     *
     * @param g2d drawText- this draws the specified text onto the window
     */

    private void drawText(Graphics2D g2d) {

        g2d.setColor(Text_Color);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D headerRect = HeaderFont.getStringBounds(Header,frc);
        Rectangle2D instructionsRect = InstructionsFont.getStringBounds(Instructions,frc);
        Rectangle2D footerRect = FooterFont.getStringBounds(Footer,frc);



    }

    /**
     *
     * @param g2d drawButton- this draws the specified button onto the window
     */

    private void drawButton(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = ReturnButtonFont.getStringBounds(Return_txt,frc);

        g2d.setFont(ReturnButtonFont);

        ReturnButton.setLocation(330,261);

        if(ReturnClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(ReturnButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(Return_txt,350,280);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(ReturnButton);
            g2d.drawString(Return_txt,350,280);
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (ReturnButton.contains(p)) {
            owner.enableHomeMenu();

        }

    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (ReturnButton.contains(p)) {
            ReturnClicked = true;
            repaint(ReturnButton.x, ReturnButton.y, ReturnButton.width + 1, ReturnButton.height + 1);

        }

    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (ReturnClicked) {
            ReturnClicked = false;
            repaint(ReturnButton.x, ReturnButton.y, ReturnButton.width + 1, ReturnButton.height + 1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (ReturnButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());
    }
}


