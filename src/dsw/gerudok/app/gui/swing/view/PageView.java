package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.gui.swing.command.CommandManager;
import dsw.gerudok.app.gui.swing.observer.Publisher;
import dsw.gerudok.app.gui.swing.observer.Subscriber;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.event.UpdateEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class PageView extends JPanel implements Subscriber {
    private Page page;
    private JLabel pageName;
    private static final int krugState = 1;
    private static int trougaoState = 2;
    private static int pravougaonikState = 3;
    private static int moveState = 4;
    private static int resizeState = 5;
    private static int rotateState = 6;
    private static int deleteState = 7;

    public  PageView(Page page){
        DiagramController diagramController= new DiagramController();
        this.addMouseListener(diagramController);
        this.addMouseMotionListener(diagramController);
        this.page = page;
        this.page.addSubscriber(this);
        this.setBorder(BorderFactory.createLineBorder(Color.RED,5));
        pageName = new JLabel(page.getName());
        this.add(pageName);
        this.page.getModel().addUpdateListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        Iterator<Slot> it = page.getModel().getDeviceIterator();
        while(it.hasNext()){
            Slot d = (Slot) it.next();
            ElementPainter painter = d.getElementPainter();
            painter.paint(g2, d);
        }
    }

    public Page getPage() {
        return page;
    }


    private class DiagramController extends MouseAdapter implements MouseMotionListener {

        public void mousePressed(MouseEvent e) {
            if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page){
                try{
                    page.getStateManager().getCurrentState().mousePressed(e);

                }catch (NullPointerException exception){

                }
            }
        }

        public void mouseReleased(MouseEvent e) {
            if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page){
                try{
                    page.getStateManager().getCurrentState().mouseReleased(e);

                }catch (NullPointerException exception){

                }

            }
        }

        public void mouseDragged(MouseEvent e ){
            if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page){
                try{
                    page.getStateManager().getCurrentState().mouseDragged(e);

                }catch(NullPointerException exception){

                }

            }
        }

    }

    public static int getKrugState() {
        return krugState;
    }

    public static int getTrougaoState() {
        return trougaoState;
    }

    public static int getPravougaonikState() {
        return pravougaonikState;
    }

    public static int getMoveState() {
        return moveState;
    }

    public static int getResizeState() {
        return resizeState;
    }

    public static int getRotateState() {
        return rotateState;
    }

    public static int getDeleteState() {
        return deleteState;
    }

    @Override
    public void update(Publisher publisher) {

    }

    @Override
    public void brisanjePage(Publisher publisher) {

    }

    @Override
    public void rename(Publisher publisher) {

    }

    @Override
    public void brisanjeDokumenta(Publisher publisher) {

    }

    @Override
    public void updatePreformed(UpdateEvent updateEvent) {
        repaint();
    }

    public JLabel getPageName() {
        return pageName;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setPageName(JLabel pageName) {
        this.pageName = pageName;
    }


}
