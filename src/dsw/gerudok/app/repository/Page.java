package dsw.gerudok.app.repository;

import dsw.gerudok.app.gui.swing.command.CommandManager;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;
import dsw.gerudok.app.repository.state.StateManager;

import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Page extends RuNodeComposite {
    private PageModel model=new PageModel();
    private static Slot prethodni = null;
    protected static ArrayList<Slot> selektovani = new ArrayList<>();
    protected static ArrayList<Point> pocetneVrednosti = new ArrayList<>();
    protected static ArrayList<Point> trenutneVrednosti = new ArrayList<>();
    protected static ArrayList<Dimension> pocetneDimenzije = new ArrayList<>();
    protected static ArrayList<Dimension> trenutneDimenzije = new ArrayList<>();
    private CommandManager commandManager = new CommandManager();
    private StateManager stateManager = new StateManager(this);
    public Page(String name, RuNode parent) {
        super(name, parent);
    }

    public void startCircleState() {
        stateManager.setCircleState();
        System.out.println("cState");
    }
    public void startSelectState() {
        stateManager.setSelectState();
    }
    public void startRectangleState(){ stateManager.setRectangleState();}
    public void startTriangleState(){stateManager.setTriangleState();}
    public void startMoveState(){stateManager.setMoveState();}
    public void startResizeState(){stateManager.setResizeState();}
    public void startRotateState(){stateManager.setRotateState();}
    public void startDeleteState(){stateManager.setDeleteState();}

    public StateManager getStateManager() {
        return stateManager;
    }

    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Slot){
            Slot slot = (Slot) child;
            if (!this.getChildren().contains(slot)){
                this.getChildren().add(slot);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child != null && child instanceof Slot){
            this.getChildren().remove(child);
        }
    }

    public PageModel getModel() {
        return model;
    }

    public void setModel(PageModel model) {
        this.model = model;
    }

    public  Slot getPrethodni() {
        return prethodni;
    }

    public  void setPrethodni(Slot prethodni) {
        Page.prethodni = prethodni;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public static ArrayList<Slot> getSelektovani() {
        return selektovani;
    }

    public  void setSelektovani(ArrayList<Slot> selektovani) {
        Page.selektovani = selektovani;
    }

    public static ArrayList<Point> getPocetneVrednosti() {
        return pocetneVrednosti;
    }

    public static void setPocetneVrednosti(ArrayList<Point> pocetneVrednosti) {
        Page.pocetneVrednosti = pocetneVrednosti;
    }

    public static ArrayList<Point> getTrenutneVrednosti() {
        return trenutneVrednosti;
    }

    public static void setTrenutneVrednosti(ArrayList<Point> trenutneVrednosti) {
        Page.trenutneVrednosti = trenutneVrednosti;
    }

    public static ArrayList<Dimension> getPocetneDimenzije() {
        return pocetneDimenzije;
    }

    public static void setPocetneDimenzije(ArrayList<Dimension> pocetneDimenzije) {
        Page.pocetneDimenzije = pocetneDimenzije;
    }

    public static ArrayList<Dimension> getTrenutneDimenzije() {
        return trenutneDimenzije;
    }

    public static void setTrenutneDimenzije(ArrayList<Dimension> trenutneDimenzije) {
        Page.trenutneDimenzije = trenutneDimenzije;
    }
}
