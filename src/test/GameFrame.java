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
import java.awt.event.WindowFocusListener;

/**
 * This is an extension for the GameFrame, it includes when focus listener which detects for focus events such as when the window of game is open or not
 */

public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private final GameBoard gameBoard;
    private final HomeMenu homeMenu;
    private final HowToPlay howToPlay;

    private boolean gaming;
    private Object HowToPlay;

    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this,new Dimension(450,300));

        howToPlay = new HowToPlay(this,new Dimension(450,300));
        
        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);

        
    }



    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * enableGameBoard- this enables the game board
     */

    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * enableHowToPlay- this enables the how to play menu
     */

    public void enableHowToPlay(){
        this.dispose();
        this.remove(homeMenu);
        this.add(howToPlay, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * enableHomeMenu- this enables the home menu
     */

    public void enableHomeMenu(){
        this.dispose();
        this.remove(howToPlay);
        this.add(homeMenu, BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     *
     * @param windowEvent windowGainedFocus- this listens for when the window of game gains focus
     */

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     *
     * @param windowEvent windowLostFocus- this listens for when the window loses focus
     */

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}
