package dsw.gerudok.app.gui.swing.tree;

import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.tree.view.RuTreeView;
import dsw.gerudok.app.repository.*;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.tree.TreePath;

public interface RuTree {

    JTree generateTree(Workspace workspace);
    void addProject(Project project);
    void addDocument(Document document);
    void addPage(Page page);
    void addSlot(Slot slot);
    RuTreeItem getSelectedNode();
    void obrisiElement(RuTreeItem ruTreeItem);
    void setSelectedNode(TreePath treePath);
    void brisanjeSlota(RuTreeItem ruTreeItem);
    RuTreeView getTreeView();
}
