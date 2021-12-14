package dsw.gerudok.app.gui.swing.tree.view;

import com.sun.tools.javac.Main;
import dsw.gerudok.app.gui.swing.tree.RuTree;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.*;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.Random;

public class RuTreeImplementation implements RuTree {

    private RuTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public JTree generateTree(Workspace workspace) {
        RuTreeItem root = new RuTreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new RuTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addProject(Project project) {
        RuNode nodeModel = ((RuTreeItem)treeModel.getRoot()).getNodeModel();
        ((RuTreeItem)treeModel.getRoot()).add(new RuTreeItem(project));
        ((Workspace) nodeModel).addChild(project);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addDocument(Document document) {
        RuTreeItem nodeModel = getSelectedNode();
        RuTreeItem selected = (RuTreeItem) treeView.getLastSelectedPathComponent();
        selected.add(new RuTreeItem(document));
        ((Project) nodeModel.getNodeModel()).addChild(document);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addPage(Page page) {
        RuTreeItem nodeModel = getSelectedNode();

        if(nodeModel.getName().contains("copy")){
            int label = new Random().nextInt(100);
            Page pa = new Page("Page " + label, nodeModel.getNodeModel());
            RuTreeItem root = (RuTreeItem) nodeModel.getRoot();
            for(int i =0; i<root.getChildCount(); i++){
                RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                for(int j=0;j<projekat.getChildCount();j++){
                    RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                    if(dokument.getName().equals(nodeModel.getName())){
                        dokument.add(new RuTreeItem(page));
                        ((Document) dokument.getNodeModel()).addChild(page);
                        SwingUtilities.updateComponentTreeUI(treeView);
                    }
                }
            }
            return;
        }

        RuTreeItem selected = (RuTreeItem) treeView.getLastSelectedPathComponent();
        selected.add(new RuTreeItem(page));
        ((Document) nodeModel.getNodeModel()).addChild(page);
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void addSlot(Slot slot) {

        RuTreeItem nodeModel = getSelectedNode();
        RuTreeItem roditelj= (RuTreeItem) nodeModel.getParent();

        if(roditelj.getName().contains("copy")){
            int label = new Random().nextInt(100);
            RuTreeItem root = (RuTreeItem) roditelj.getRoot();
            for(int i =0; i<root.getChildCount(); i++){
                RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                for(int j=0;j<projekat.getChildCount();j++){
                    RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                    for(int k=0; k<dokument.getChildCount(); k++){
                        RuTreeItem page = (RuTreeItem) dokument.getChildAt(k);
                        if(roditelj.getName().equals(dokument.getName())){
                            if(nodeModel.getName().equals(page.getName())){
                                page.add(new RuTreeItem(slot));
                                ((Page) page.getNodeModel()).addChild(slot);
                                SwingUtilities.updateComponentTreeUI(treeView);
                            }

                        }
                    }
                }
            }
            return;
        }


        RuTreeItem selected = (RuTreeItem) treeView.getLastSelectedPathComponent();
        selected.add(new RuTreeItem(slot));
        ((Page) nodeModel.getNodeModel()).addChild(slot);
        SwingUtilities.updateComponentTreeUI(treeView);

    }

    @Override
    public RuTreeItem getSelectedNode() {
        RuTreeItem selectedItem = (RuTreeItem) treeView.getLastSelectedPathComponent();
        return selectedItem;
    }

    @Override
    public void obrisiElement(RuTreeItem ruTreeItem) {
        if(ruTreeItem.getParent() == null)
        {
            return;
        }
        if(((RuTreeItem) ruTreeItem.getParent()).getNodeModel() instanceof Workspace){
            int k=0;
            RuTreeItem root = (RuTreeItem) ruTreeItem.getRoot();
            for(int i=0; i<root.getChildCount();i++){
                RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                for(int j=0; j<projekat.getChildCount();j++){
                    RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                    if(dokument.getName().contains("copy")){
                        if(k++ == 0){
                            ((Project) ((RuTreeItem) dokument.getParent()).getNodeModel()).removeChild(dokument.getNodeModel());
                        }
                        ((RuTreeItem) dokument.getParent()).remove(dokument);
                        SwingUtilities.updateComponentTreeUI(treeView);
                        break;
                    }
                }
            }
            ((Workspace) ((RuTreeItem) ruTreeItem.getParent()).getNodeModel()).removeChild(ruTreeItem.getNodeModel());
            ((RuTreeItem) ruTreeItem.getParent()).remove(ruTreeItem);

        }
        else if(((RuTreeItem) ruTreeItem.getParent()).getNodeModel() instanceof Project){

            if(ruTreeItem.getName().contains("copy")){
                ((Project) ((RuTreeItem) ruTreeItem.getParent()).getNodeModel()).removeChild(ruTreeItem.getNodeModel());
                RuTreeItem root = (RuTreeItem) ruTreeItem.getRoot();
                for(int i =0; i<root.getChildCount(); i++){
                    RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                    for(int j=0;j<projekat.getChildCount();j++){
                        RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                        if(dokument.getName().equals(ruTreeItem.getName())){
                            ((RuTreeItem) dokument.getParent()).remove(dokument);
                            SwingUtilities.updateComponentTreeUI(treeView);
                            break;
                        }
                    }
                }

                return;
            }
            else{
            ((Project) ((RuTreeItem) ruTreeItem.getParent()).getNodeModel()).removeChild(ruTreeItem.getNodeModel());
            ((RuTreeItem) ruTreeItem.getParent()).remove(ruTreeItem);
            }
        }
        else if(((RuTreeItem) ruTreeItem.getParent()).getNodeModel() instanceof Document){

            RuTreeItem roditelj = (RuTreeItem) ruTreeItem.getParent();
            if(roditelj.getName().contains("copy")){
                ((Document) ((RuTreeItem) ruTreeItem.getParent()).getNodeModel()).removeChild(ruTreeItem.getNodeModel());
                RuTreeItem root = (RuTreeItem) roditelj.getRoot();
                for(int i =0; i<root.getChildCount(); i++){
                    RuTreeItem projekat = (RuTreeItem) root.getChildAt(i);
                    for(int j=0;j<projekat.getChildCount();j++){
                        RuTreeItem dokument = (RuTreeItem) projekat.getChildAt(j);
                        for(int k=0; k<dokument.getChildCount(); k++){
                            RuTreeItem page = (RuTreeItem) dokument.getChildAt(k);
                            if(roditelj.getName().equals(dokument.getName())){
                                if(ruTreeItem.getName().equals(page.getName())){
                                    ((RuTreeItem) page.getParent()).remove(page);
                                    SwingUtilities.updateComponentTreeUI(treeView);
                                    break;
                                }

                            }
                        }
                    }
                }
                return;
            }
            else{
            ((Document) ((RuTreeItem) ruTreeItem.getParent()).getNodeModel()).removeChild(ruTreeItem.getNodeModel());
            ((RuTreeItem) ruTreeItem.getParent()).remove(ruTreeItem);
            }
        }
        SwingUtilities.updateComponentTreeUI(treeView);
    }


    @Override
    public void setSelectedNode(TreePath treePath) {
        treeView.setSelectionPath(treePath);
    }

    @Override
    public void brisanjeSlota(RuTreeItem ruTreeItem) {
            RuTreeItem root = (RuTreeItem) ruTreeItem.getRoot();
            RuTreeItem roditelj = (RuTreeItem) ruTreeItem.getParent();
            RuTreeItem roditelj2 = (RuTreeItem) roditelj.getParent();
            if(roditelj2.getName().contains("copy")) {
                ((Page) ((RuTreeItem) ruTreeItem.getParent()).getNodeModel()).removeChild(ruTreeItem.getNodeModel());
                for (int i = 0; i < root.getChildCount(); i++) {
                    RuTreeItem project = (RuTreeItem) root.getChildAt(i);
                    for (int j = 0; j < project.getChildCount(); j++) {
                        RuTreeItem dokument = (RuTreeItem) project.getChildAt(j);
                        if (dokument.getName().contains("copy")) {
                            for (int k = 0; k < dokument.getChildCount(); k++) {
                                RuTreeItem page = (RuTreeItem) dokument.getChildAt(k);
                                if (page.getName().equals(roditelj.getName())) {
                                   for(int l = 0; l<page.getChildCount();l++){
                                       RuTreeItem slot = (RuTreeItem) page.getChildAt(l);
                                       if(ruTreeItem.getName().equals(slot.getName())){
                                           ((RuTreeItem) slot.getParent()).remove(slot);
                                           SwingUtilities.updateComponentTreeUI(treeView);
                                           break;
                                       }
                                   }

                                }
                            }
                        }
                    }
                }
            }
            else{
                ((Page) ((RuTreeItem) ruTreeItem.getParent()).getNodeModel()).removeChild(ruTreeItem.getNodeModel());
                ((RuTreeItem) ruTreeItem.getParent()).remove(ruTreeItem);
            }
        }


    public RuTreeView getTreeView() {
        return treeView;
    }
}
