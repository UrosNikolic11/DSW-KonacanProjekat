package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;

public class Toolbar extends JToolBar {

    public Toolbar() {
        super(HORIZONTAL);
        setFloatable(false);
        this.add(MainFrame.getInstance().getActionManager().getExitAction());
        this.add(MainFrame.getInstance().getActionManager().getOpenAction());
        this.add(MainFrame.getInstance().getActionManager().getSaveAction());
        this.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        this.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        this.add(MainFrame.getInstance().getActionManager().getAboutAction());
        this.add(MainFrame.getInstance().getActionManager().getCopyAction());
        this.add(MainFrame.getInstance().getActionManager().getPasteAction());
        this.add(MainFrame.getInstance().getActionManager().getUndoAction());
        this.add(MainFrame.getInstance().getActionManager().getRedoAction());
        this.add(MainFrame.getInstance().getActionManager().getSlotAction());
    }
}