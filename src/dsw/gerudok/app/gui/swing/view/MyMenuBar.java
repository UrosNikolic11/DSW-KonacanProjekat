package dsw.gerudok.app.gui.swing.view;

import com.sun.tools.javac.Main;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {

        JMenu fileMenu= new JMenu("File");
        this.add(fileMenu);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getCopyAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getPasteAction());
        JMenu helpMenu= new JMenu("Help");
        this.add(helpMenu);
        helpMenu.add(MainFrame.getInstance().getActionManager().getAboutAction());




    }
}
