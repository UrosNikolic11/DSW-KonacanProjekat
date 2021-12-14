package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.*;
import dsw.gerudok.app.repository.*;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NewProjectAction extends  AbstractRudokAction {

    private MainView mainView;



    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/new_project_toolbar.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
        mainView = new GetMainView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RuTreeItem cvor = null;
        cvor = mainView.getMainFrame().getTree().getSelectedNode();

         if(cvor == null) {
            int label = new Random().nextInt(100);
            Project p = new Project("Project " + label, (RuNode) ((RuTreeItem) mainView.getMainFrame().getWorkspaceTree().getModel().getRoot()).getNodeModel());
            ProjectView pw = new ProjectView(p);
            mainView.getMainFrame().getTree().addProject(p);
        }else if(cvor.getNodeModel() instanceof Workspace)
        {
            int label = new Random().nextInt(100);
            Project p = new Project("Project " + label, (RuNode) ((RuTreeItem) mainView.getMainFrame().getWorkspaceTree().getModel().getRoot()).getNodeModel());
            ProjectView pw = new ProjectView(p);
            mainView.getMainFrame().getTree().addProject(p);
        }else if(cvor.getNodeModel() instanceof Project)
        {
            int label = new Random().nextInt(100);
            Document d = new Document("Document " + label, cvor.getNodeModel());

            mainView.getMainFrame().getTree().addDocument(d);

        }else if(cvor.getNodeModel() instanceof Document) {
        int label = new Random().nextInt(100);
        Page pa = new Page("Page " + label, cvor.getNodeModel());
        mainView.getMainFrame().getTree().addPage(pa);
        }else if(cvor.getNodeModel() instanceof Page) {
             AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.DODAVANJE_SLOTA);
         }
         else if(cvor.getNodeModel() instanceof Slot){
             AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.DODAVANJE_SLOTA);
         }

    }

}


