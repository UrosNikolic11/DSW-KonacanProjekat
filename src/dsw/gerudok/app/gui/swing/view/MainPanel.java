package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.Workspace;
import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;
import dsw.gerudok.app.gui.swing.view.DocumentTab;

import javax.swing.*;
import javax.swing.JTabbedPane;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JLabel naslov;
    private static MainPanel instance = null;

    private MainPanel() {
        tabbedPane =new JTabbedPane();
        naslov = new JLabel("",SwingConstants.CENTER);
        this.setLayout(new BorderLayout());
        this.add(naslov, BorderLayout.NORTH);
        this.add(tabbedPane, BorderLayout.CENTER);
    }
    public static MainPanel getMainPanel(){
        if(instance==null){
            instance = new MainPanel();
        }
        return instance;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JLabel getNaslov() {
        return naslov;
    }
}
