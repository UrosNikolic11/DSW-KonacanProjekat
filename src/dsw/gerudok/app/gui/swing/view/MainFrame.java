package dsw.gerudok.app.gui.swing.view;

import com.sun.tools.javac.Main;
import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.core.Repository;
import dsw.gerudok.app.gui.swing.command.CommandType;
import dsw.gerudok.app.gui.swing.controller.ActionManager;
import dsw.gerudok.app.gui.swing.error.Error;
import dsw.gerudok.app.gui.swing.tree.RuTree;
import dsw.gerudok.app.gui.swing.tree.view.RuTreeImplementation;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private ActionManager actionManager;
    private Repository documentRepository;
    private RuTree tree;
    private JMenuBar menu;
    private JTree workspaceTree;
    private JToolBar toolBar;
    private RuNode cvor;
    private MainPanel mainPanel;
    private JToolBar toolbar2;
    public MainFrame()  {

    }

    private void initialise(){
        actionManager= new ActionManager();
        menu= new MyMenuBar();
        toolBar= new Toolbar();
        mainPanel = MainPanel.getMainPanel();
        toolbar2 = new Toolbar2();
    }

    public void initialiseWorkspaceTree() {
        tree = new RuTreeImplementation();
        workspaceTree = tree.generateTree(documentRepository.getWorkspace());
        initElements();
    }

    private void initElements(){

        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, (int) (screenHight / 1.8));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rukovodilac dokumentima");
        setJMenuBar(menu);
        add(toolBar, BorderLayout.NORTH);
        add(toolbar2,BorderLayout.EAST);
        URL ikonicaURL = getClass().getResource("slike/ikonica.png");
        ImageIcon ikonica = new ImageIcon(ikonicaURL);
        MainFrame.getInstance().setIconImage(ikonica.getImage());

        JPanel ekran = new JPanel();

        JScrollPane scroll=new JScrollPane(workspaceTree);
        scroll.setMinimumSize(new Dimension(300,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll, mainPanel);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(300);
        split.setOneTouchExpandable(true);
    }


    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public static void setInstance(MainFrame instance) {
        MainFrame.instance = instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public void setActionManager(ActionManager actionManager) {
        this.actionManager = actionManager;
    }

    public Repository getDocumentRepository() {
        return documentRepository;
    }

    public void setDocumentRepository(Repository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public RuTree getTree() {
        return tree;
    }

    public void setTree(RuTree tree) {
        this.tree = tree;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public void setMenu(JMenuBar menu) {
        this.menu = menu;
    }

    public JTree getWorkspaceTree() {
        return workspaceTree;
    }

    public void setWorkspaceTree(JTree workspaceTree) {
        this.workspaceTree = workspaceTree;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(JToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public RuNode getCvor() {
        return cvor;
    }

    public void setCvor(RuNode cvor) {
        this.cvor = cvor;
    }

    public dsw.gerudok.app.gui.swing.view.MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(dsw.gerudok.app.gui.swing.view.MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void napisiPoruku(Error error) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(),error.getMessage());
    }

    public void undoRedo(CommandType commandType) {

        if(commandType.equals(CommandType.REDO_TRUE)){
            actionManager.getRedoAction().setEnabled(true);
        }else if(commandType.equals(CommandType.REDOFALSE)){
            actionManager.getRedoAction().setEnabled(false);
        }else if(commandType.equals(CommandType.UNDO_FALSE)){
            actionManager.getUndoAction().setEnabled(false);
        }else if(commandType.equals(CommandType.UNDO_TRUE)){
            actionManager.getUndoAction().setEnabled(true);
        }

    }
}
