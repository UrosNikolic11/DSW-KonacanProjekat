package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.MainPanel;
import dsw.gerudok.app.gui.swing.view.PageView;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PaintCircleAction extends AbstractRudokAction{

    public PaintCircleAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/circle.png"));
        putValue(SHORT_DESCRIPTION, "Circle");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page){
            Page page = (Page) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
            page.startCircleState();
        }else{
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.NESELEKTOVAN_PAGE);
        }

    }
}
