package dsw.gerudok.app.gui.swing.view;

import javax.swing.*;

public class Toolbar2 extends JToolBar {
    public Toolbar2() {
        super(VERTICAL);
        setFloatable(false);
        this.add(MainFrame.getInstance().getActionManager().getPaintCircleAction());
        this.add(MainFrame.getInstance().getActionManager().getPaintTriangleAction());
        this.add(MainFrame.getInstance().getActionManager().getPaintRectangleAction());
        this.add(MainFrame.getInstance().getActionManager().getPaintSelectAction());
        this.add(MainFrame.getInstance().getActionManager().getPaintMoveAction());
        this.add(MainFrame.getInstance().getActionManager().getPaintResizeAction());
        this.add(MainFrame.getInstance().getActionManager().getPaintRotateAction());
        this.add(MainFrame.getInstance().getActionManager().getPaintDeleteAction());
    }
}
