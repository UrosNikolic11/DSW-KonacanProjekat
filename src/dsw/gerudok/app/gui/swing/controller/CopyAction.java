package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CopyAction extends AbstractRudokAction{

    private RuTreeItem selektovani;
    public CopyAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/copy.png"));
        putValue(NAME, "Copy");
        putValue(SHORT_DESCRIPTION, "Copy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Document){
            selektovani =  MainFrame.getInstance().getTree().getSelectedNode();
        }else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Workspace){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.COPY_WORKSPACE);
        }else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Project){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.COPY_PROJEKAT);
        }else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.COPY_PAGE);
        }else if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Slot){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.COPY_SLOT);
        }
        System.out.println(selektovani);
    }

    public RuTreeItem getSelektovani() {
        return selektovani;
    }

    public void setSelektovani(RuTreeItem selektovani) {
        this.selektovani = selektovani;
    }
}
