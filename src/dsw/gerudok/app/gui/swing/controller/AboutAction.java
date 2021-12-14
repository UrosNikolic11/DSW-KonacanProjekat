package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.gui.swing.view.AboutFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutAction extends AbstractRudokAction{

    public AboutAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/about_toolbar.png"));
        putValue(NAME, "About");
        putValue(SHORT_DESCRIPTION, "About");

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        AboutFrame AboutFrame= new AboutFrame();
    }
}
