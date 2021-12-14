package dsw.gerudok.app.gui.swing.controller;

import com.sun.tools.javac.Main;
import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.tree.RuTree;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PasteAction extends AbstractRudokAction{

    private RuTreeItem nalepljeni;
    private RuTreeItem selektovani;
    private List<RuTreeItem> decaDoc;
    private List<RuTreeItem> decaPage;
    private static int br=0;


    public PasteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/paste.png"));
        putValue(NAME, "Paste");
        putValue(SHORT_DESCRIPTION, "Paste");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            nalepljeni = MainFrame.getInstance().getActionManager().getCopyAction().getSelektovani();
            selektovani = MainFrame.getInstance().getTree().getSelectedNode();
            if(!(nalepljeni.getName().contains("copy"))){
                nalepljeni.setName(nalepljeni.getName() +"-copy");
                nalepljeni.getNodeModel().setName(nalepljeni.getName());
            }
            decaDoc = new ArrayList<>();
            decaPage = new ArrayList<>();

            int kolicina = nalepljeni.getChildCount();

            for(int i =0; i<kolicina; i++){
                decaDoc.add((RuTreeItem) nalepljeni.getChildAt(i));
            }
            RuTreeItem dokument = new RuTreeItem(nalepljeni.getNodeModel());
            selektovani.add(dokument);
            for(RuTreeItem ruTreeItem : decaDoc){
                for(int j=0 ; j<ruTreeItem.getChildCount(); j++){
                    decaPage.add((RuTreeItem) ruTreeItem.getChildAt(j));
                }
                RuTreeItem page = new RuTreeItem(ruTreeItem.getNodeModel());
                dokument.add(page);
                for(RuTreeItem ru : decaPage){
                    page.add(new RuTreeItem(ru.getNodeModel()));
                }
                decaPage.clear();
            }
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree().getTreeView());
            dokument.getNodeModel().notifySubscriber2();
        }catch(NullPointerException exception){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.PRAZAN_PASTE);
        }



    }

    public RuTreeItem getNalepljeni() {
        return nalepljeni;
    }

    public void setNalepljeni(RuTreeItem nalepljeni) {
        this.nalepljeni = nalepljeni;
    }
}
