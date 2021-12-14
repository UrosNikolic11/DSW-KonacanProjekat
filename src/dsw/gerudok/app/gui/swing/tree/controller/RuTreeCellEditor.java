package dsw.gerudok.app.gui.swing.tree.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.GetMainView;
import dsw.gerudok.app.gui.swing.view.MainView;
import dsw.gerudok.app.repository.*;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class RuTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    private Object clickedOn =null;
    private JTextField edit=null;
    private MainView mainView;

    public RuTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }
    public boolean isCellEditable(EventObject arg0) {
        mainView = new GetMainView();
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }else if(((MouseEvent)arg0).getClickCount()==2){
                if(mainView.getMainFrame().getTree().getSelectedNode().getNodeModel() instanceof Project){
                    RuNode ruNode = mainView.getMainFrame().getTree().getSelectedNode().getNodeModel();
                    ruNode.dvoklik(ruNode);
                }
            }
        return false;
    }

    public void actionPerformed(ActionEvent e){

        mainView = new GetMainView();

        if (!(clickedOn instanceof RuTreeItem))
            return;

        RuTreeItem clicked = (RuTreeItem) clickedOn;

        if (clicked.getNodeModel() instanceof Workspace){
            String s;
            s=mainView.getMainFrame().getTree().getSelectedNode().getName();
            clicked.setName(e.getActionCommand());
            ((Workspace) clicked.getNodeModel()).setName(e.getActionCommand());
            if(e.getActionCommand().equals("")){
                AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.PRAZAN_RENAME);
                clicked.setName(s);
                ((Workspace) clicked.getNodeModel()).setName(s);
                System.out.println(s);
                return;
            }

        }
        else if(clicked.getNodeModel() instanceof Project){

                String s;
                s=mainView.getMainFrame().getTree().getSelectedNode().getName();
                clicked.setName(e.getActionCommand());
                ((Project) clicked.getNodeModel()).setName(e.getActionCommand());
                if(e.getActionCommand().equals("")){
                    AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.PRAZAN_RENAME);
                    clicked.setName(s);
                    ((Project) clicked.getNodeModel()).setName(s);
                    System.out.println(s);
                    return;
                }
            }

        else if(clicked.getNodeModel() instanceof Document){

                String s;
                String stariText;
                String noviText;
                RuTreeItem nodeModel;
                nodeModel = mainView.getMainFrame().getTree().getSelectedNode();
                s=mainView.getMainFrame().getTree().getSelectedNode().getName();
                stariText = nodeModel.getName();
                if(nodeModel.getName().contains("copy")){
                    if(!(e.getActionCommand().contains("-copy"))){
                        clicked.setName(e.getActionCommand() + "-copy");
                        ((Document) clicked.getNodeModel()).setName(e.getActionCommand() + "copy");
                    }
                    else{
                        clicked.setName(e.getActionCommand());
                        ((Document) clicked.getNodeModel()).setName(e.getActionCommand());
                    }
                }else{
                    clicked.setName(e.getActionCommand());
                    ((Document) clicked.getNodeModel()).setName(e.getActionCommand());
                }

                if(e.getActionCommand().equals("")){
                    AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.PRAZAN_RENAME);
                    clicked.setName(s);
                    ((Document) clicked.getNodeModel()).setName(s);
                    System.out.println(s);
                    return;
                }
                noviText = nodeModel.getName();
                if(stariText.contains("copy")){
                    RuTreeItem root = (RuTreeItem) nodeModel.getRoot();
                    for(int i =0; i<root.getChildCount(); i++){
                        RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                        for(int j=0;j<projekat.getChildCount();j++){
                            RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                            if(dokument.getName().equals(stariText)){
                                dokument.setName(noviText);
                                ((Document) dokument.getNodeModel()).setName(noviText);
                                if(!(e.getActionCommand().contains("-copy"))){
                                    String s1;
                                    s1 = e.getActionCommand();
                                    dokument.setName(s1 + "-copy");
                                }
                            }
                        }
                    }
                    return;
                }


        }
        else if(clicked.getNodeModel() instanceof Page){


                String s;
                String stariText;
                String stariTextRoditelja;
                String noviText;
                RuTreeItem nodeModel;
                RuTreeItem roditelj;
                nodeModel = mainView.getMainFrame().getTree().getSelectedNode();
                s=mainView.getMainFrame().getTree().getSelectedNode().getName();
                stariText = nodeModel.getName();

                roditelj = (RuTreeItem) nodeModel.getParent();
                stariTextRoditelja = roditelj.getName();

                clicked.setName(e.getActionCommand());
                ((Page) clicked.getNodeModel()).setName(e.getActionCommand());

                if(e.getActionCommand().equals("")){
                    AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.PRAZAN_RENAME);
                    clicked.setName(s);
                    ((Page) clicked.getNodeModel()).setName(s);
                    System.out.println(s);
                    return;
                }


                noviText = nodeModel.getName();

                if(roditelj.getName().contains("copy")){
                    RuTreeItem root = (RuTreeItem) roditelj.getRoot();
                    for(int i =0; i<root.getChildCount(); i++){
                        RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                        for(int j=0;j<projekat.getChildCount();j++){
                            RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                            for(int k=0; k<dokument.getChildCount(); k++){
                                RuTreeItem page = (RuTreeItem) dokument.getChildAt(k);
                                if(dokument.getName().equals(stariTextRoditelja)){
                                    if(page.getName().equals(stariText)){
                                        page.setName(noviText);
                                        ((Page) page.getNodeModel()).setName(noviText);
                                    }

                                }
                            }
                        }
                    }
                    return;
                }


        }
        else if(clicked.getNodeModel() instanceof Slot){

            String s;
            String stariText;
            String stariTextRoditelja;
            String stariTextGlavnogRoditelja;
            String noviText;
            RuTreeItem nodeModel;
            RuTreeItem roditelj;
            RuTreeItem roditeljGlavni;

            nodeModel = mainView.getMainFrame().getTree().getSelectedNode();
            s=mainView.getMainFrame().getTree().getSelectedNode().getName();

            stariText = nodeModel.getName();
            roditelj = (RuTreeItem) nodeModel.getParent();
            roditeljGlavni = (RuTreeItem) roditelj.getParent();
            stariTextRoditelja = roditelj.getName();
            stariTextGlavnogRoditelja = roditeljGlavni.getName();

            clicked.setName(e.getActionCommand());
            ((Slot) clicked.getNodeModel()).setName(e.getActionCommand());

            if(e.getActionCommand().equals("")){
                AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.PRAZAN_RENAME);
                clicked.setName(s);
                ((Slot) clicked.getNodeModel()).setName(s);
                System.out.println(s);
                return;
            }

            noviText = nodeModel.getName();

            if(roditeljGlavni.getName().contains("copy")){
                RuTreeItem root = (RuTreeItem) roditelj.getRoot();
                for(int i =0; i<root.getChildCount(); i++){
                    RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                    for(int j=0;j<projekat.getChildCount();j++){
                        RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                        for(int k=0; k<dokument.getChildCount(); k++){
                            RuTreeItem page = (RuTreeItem) dokument.getChildAt(k);
                            for(int l=0; l<page.getChildCount(); l++){
                                RuTreeItem slot = (RuTreeItem) page.getChildAt(l);
                                if(dokument.getName().equals(stariTextGlavnogRoditelja)){
                                    if(page.getName().equals(stariTextRoditelja)){
                                        if(slot.getName().equals(stariText)){
                                            slot.setName(noviText);
                                            ((Slot) slot.getNodeModel()).setName(noviText);
                                        }

                                    }

                                }
                            }

                        }
                    }
                }
                return;
            }



        }
    }



}
