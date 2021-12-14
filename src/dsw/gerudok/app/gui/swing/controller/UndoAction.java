package dsw.gerudok.app.gui.swing.controller;

import com.sun.tools.javac.Main;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.PageView;
import dsw.gerudok.app.repository.Page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractRudokAction{

    PageView pageView;

    UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
        putValue(SMALL_ICON, loadIcon("images/undo.jpg"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    public void actionPerformed(ActionEvent e) {
        Page page = (Page) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
        page.getCommandManager().undoCommand();
    }


}