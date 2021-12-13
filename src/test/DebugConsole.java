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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * DebugConsole is a user interface that allows the user to view/manipulate internal state for debugging
 */


public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private final JFrame owner;
    private final DebugPanel debugPanel;
    private final GameBoard gameBoard;
    private final Wall wall;


    public DebugConsole(JFrame owner,Wall wall,GameBoard gameBoard){

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }


    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }

    /**
     *
     * @param windowEvent windowOpened- This is the case if the window is opened for DebugConsole
     */

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     *
     * @param windowEvent windowClosing- this detects if the window is closed
     */

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    /**
     *
     * @param windowEvent windowClosed- this method is for when the window is closed
     */

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    /**
     *
     * @param windowEvent windowIconified- this methods call for when the window is reduced(made smaller)
     */

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    /**
     *
     * @param windowEvent windowDeIconified- this method is for when the window is enlarged (maximised)
     */

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     *
     * @param windowEvent windowActivated- this method is for when the window is in action then the ball is set at positions
     */

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.ball;
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    /**
     *
     * @param windowEvent windowDeactivated - this is when the window is disconnected
     */

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
