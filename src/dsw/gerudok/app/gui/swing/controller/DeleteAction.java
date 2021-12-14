package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.GetMainView;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.MainView;
import dsw.gerudok.app.repository.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends  AbstractRudokAction {

    private MainView mainView;

    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_DELETE, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
        mainView =new GetMainView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuTreeItem cvor = null;

        try{
            cvor = mainView.getMainFrame().getTree().getSelectedNode();
            if(cvor.getNodeModel() instanceof Workspace){
                AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.WORKSPACE_DELETE);
                return;
            }
            MainFrame.getInstance().getTree().obrisiElement(cvor);

            mainView.getMainFrame().getTree().setSelectedNode(null);

        }catch (NullPointerException exception){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.DELETE_PRAZNO);
        }




    }


}
