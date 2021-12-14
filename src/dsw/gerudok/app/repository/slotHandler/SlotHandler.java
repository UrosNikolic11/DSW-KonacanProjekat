package dsw.gerudok.app.repository.slotHandler;

import dsw.gerudok.app.gui.swing.command.*;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.SlotPainter;
import dsw.gerudok.app.repository.*;
import dsw.gerudok.app.repository.factory.CircleFactory;
import dsw.gerudok.app.repository.factory.RectangleFactory;
import dsw.gerudok.app.repository.factory.SlotFactory;
import dsw.gerudok.app.repository.factory.TriangleFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SlotHandler implements ISlotHandle{

    Point pocetnaTacka;
    Slot slot;
    private  int pogodjen=0;
    private double  x1,x2,x11,x22,y1,y2,y11,y22,k1,k2,a,b,x3,y3,u,x4,y4;
    private ArrayList<Point> pocetneVrednosti = new ArrayList<>();
    private ArrayList<Slot> selektovaniSlotovi = new ArrayList<>();
    private ArrayList<Point> trenutneVrednosti = new ArrayList<>();
    private ArrayList<Dimension> pocetneDimenzije = new ArrayList<>();
    private ArrayList<Dimension> trenutneDimenzije = new ArrayList<>();
    static double i = 0.0;
    private String tip;
    Point pocetnaPozicija;
    Dimension pocetnaDimenzija;


    @Override
    public void movePressed(MouseEvent e, Page med) {

        pocetnaTacka= e.getPoint();
        for(int i=med.getModel().getDeviceCount()-1;i>=0;i--){
            slot = med.getModel().getDeviceAt(i);
            if(slot.getPaint().equals(Color.BLACK)){
                if(slot.getElementPainter().elementAt(slot,pocetnaTacka)) {
                    pocetnaPozicija = slot.getPosition();
                    x1 = slot.getPosition().getX();
                    y1 = slot.getPosition().getY();
                    x2 = pocetnaTacka.getX();
                    y2 = pocetnaTacka.getY();
                    a = x2-x1;
                    b= y2-y1;
                    pogodjen = 1;
                    break;
                }
            }
        }
    }

    @Override
    public void moveDragged(MouseEvent e, Page med) {
        if(pogodjen == 1){
            if(med.getSelektovani().size() == 0)
            {

                Point tacka = e.getPoint();
                x22 = tacka.getX();
                y22 = tacka.getY();
                x11 = x22-a;
                y11 = y22-b;
                Point xy = new Point((int)x11,(int)y11);
                slot.setPosition(xy);
                med.getModel().removeDiagramElements(slot);
                slot.napraviRpeinter();
                med.getModel().addDiagramElements(slot);
            }
            if(med.getSelektovani().size() != 0){
                for(Slot s: med.getSelektovani()){
                    x1 = s.getPosition().getX();
                    y1 = s.getPosition().getY();
                    x2 = pocetnaTacka.getX();
                    y2 = pocetnaTacka.getY();
                    a = x2-x1;
                    b= y2-y1;
                    Point tacka = e.getPoint();
                    x22 = tacka.getX();
                    y22 = tacka.getY();
                    x11 = x22-a;
                    y11 = y22-b;

                    Point xy = new Point((int)x11,(int)y11);
                    s.setPosition(xy);
                    med.getModel().removeDiagramElements(s);
                    s.napraviRpeinter();
                    med.getModel().addDiagramElements(s);
                }
                pocetnaTacka.setLocation(e.getPoint());

            }
        }
    }

    @Override
    public void moveRelesed(MouseEvent e, Page med) {
        med.getTrenutneVrednosti().clear();
        pogodjen = 0;
        for(int i =0 ; i< med.getSelektovani().size(); i++){
            trenutneVrednosti.add(med.getSelektovani().get(i).getPosition());
        }
        med.setTrenutneVrednosti(trenutneVrednosti);
        med.getCommandManager().addCommand(new MoveCommand(med.getModel(),pocetnaPozicija,slot.getPosition(),slot,4,med.getCommandManager(),med.getSelektovani(),med.getPocetneVrednosti(),med.getTrenutneVrednosti()),4);
    }

    @Override
    public void deletePresses(MouseEvent e, Page med) {

        med.setPrethodni(null);
        Point position = e.getPoint();
        for(int i=med.getModel().getDeviceCount()-1;i>=0;i--){
            Slot slot = med.getModel().getDeviceAt(i);
            if(med.getSelektovani().size() == 0 && slot.getPaint().equals(Color.BLACK)){
                if(slot.getElementPainter().elementAt(slot,position)) {
                    med.getCommandManager().addCommand(new DeleteCommand(med.getModel(),slot.getPosition(),slot,7, med.getCommandManager(),med.getSelektovani()),7);
                    RuTreeItem p = MainFrame.getInstance().getTree().getSelectedNode();
                    for(int j = 0;j<p.getChildCount();j++){
                        RuTreeItem s = (RuTreeItem) p.getChildAt(j);
                        if(s.getName().equals(slot.getName())){
                            MainFrame.getInstance().getTree().brisanjeSlota(s);
                            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree().getTreeView());
                        }
                    }

                    med.getModel().fireUpdatePerformed();
                }
            }
            else if(slot.getPaint().equals(Color.BLACK) && med.getSelektovani().size() != 0){
                med.getCommandManager().addCommand(new DeleteCommand(med.getModel(),slot.getPosition(),slot,7, med.getCommandManager(),med.getSelektovani()),7);
                for(Slot s: med.getSelektovani()){
                    //med.getCommandManager().addCommand(new AddDeviceCommand(med.getModel(),slot.getPosition(),slot,10,med.getCommandManager(),med.getSelektovani()),10);
                    //med.getModel().removeDiagramElements(s);
                    RuTreeItem p = MainFrame.getInstance().getTree().getSelectedNode();
                    for(int k = 0;k<p.getChildCount();k++){
                        RuTreeItem ru = (RuTreeItem) p.getChildAt(k);
                        if(ru.getName().equals(s.getName())){
                            MainFrame.getInstance().getTree().brisanjeSlota(ru);
                            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree().getTreeView());
                        }
                    }
                    med.getModel().fireUpdatePerformed();
                }
                //med.getSelektovani().clear();
                return;
              }

            }
        }


    private double xp, yp;
    private Slot laso;
    private Point gLevi;
    @Override
    public void selectPressed(MouseEvent e, Page med) {
        pocetnaTacka = e.getPoint();
        gLevi = e.getPoint();
        if(selektovaniSlotovi.size() != 0){
            for(Slot s: selektovaniSlotovi){
                s.setPaint(Color.BLUE);
                med.getModel().fireUpdatePerformed();
            }
            med.getPrethodni().setPaint(Color.BLUE);
            med.getModel().fireUpdatePerformed();
            med.setPrethodni(null);
        }
        if(med.getSelektovani().size() == 0 && med.getPrethodni() != null){
            for(Slot s: selektovaniSlotovi){
                s.setPaint(Color.BLUE);
                med.getModel().fireUpdatePerformed();
            }
            med.getPrethodni().setPaint(Color.BLUE);
            med.getModel().fireUpdatePerformed();
        }
        for(int i=med.getModel().getDeviceCount()-1;i>=0;i--){
            Slot slot = med.getModel().getDeviceAt(i);
            if(slot.getElementPainter().elementAt(slot,pocetnaTacka)) {
                if (med.getPrethodni() != null) {
                    med.getPrethodni().setPaint(Color.BLUE);
                    ((SlotPainter) med.getPrethodni().getElementPainter()).setSlot(med.getPrethodni());
                }
                slot.setPaint(Color.BLACK);
                ((SlotPainter) slot.getElementPainter()).setSlot(slot);
                med.setPrethodni(slot);
                med.getModel().fireUpdatePerformed();
            }
        }
        laso = new SLotRectangle("laso", med, pocetnaTacka);//treba factory
        laso.setSize(new Dimension(0,0));
        laso.setMultiselekcija(1);
        laso.napraviRpeinter();
        med.getModel().addDiagramElements(laso);
        selektovaniSlotovi.clear();
    }
private Slot slot2;


    @Override
    public void selectDragged(MouseEvent e, Page med) {
             med.getModel().removeDiagramElements(laso);
             Point drag = e.getPoint();
             x1 = drag.getX() - gLevi.getX();
             y1 = drag.getY() - gLevi.getY();
             gLevi.setLocation((int)drag.getX(),(int)drag.getY());
             double width = laso.getSize().width + x1;
             double hight = laso.getSize().height + y1;
             laso.setSize(new Dimension((int)width, (int)hight));
             laso.napraviRpeinter();
             med.getModel().addDiagramElements(laso);

        }


    @Override
    public void selectRelesed(MouseEvent e, Page med) {
        med.getSelektovani().clear();
        med.getPocetneVrednosti().clear();
        med.getPocetneDimenzije().clear();
        System.out.println(med.getModel().getDeviceCount());
        for (int i = med.getModel().getDeviceCount() - 1; i >= 0; i--) {
            Slot slot = med.getModel().getDeviceAt(i);
            if (slot != laso) {
                if (((SlotPainter) slot.getElementPainter()).getShape().intersects(laso.getPosition().getX(), laso.getPosition().getY(), laso.getSize().width, laso.getSize().height)) {
                    slot.setPaint(Color.BLACK);
                    ((SlotPainter) slot.getElementPainter()).setSlot(slot);
                    med.setPrethodni(slot);
                    med.getModel().fireUpdatePerformed();
                    selektovaniSlotovi.add(slot);
                    pocetneVrednosti.add(slot.getPosition());
                    pocetneDimenzije.add(slot.getSize());
                }
            }
            med.getModel().removeDiagramElements(laso);
            med.setSelektovani(selektovaniSlotovi);
            med.setPocetneVrednosti(pocetneVrednosti);
            med.setPocetneDimenzije(pocetneDimenzije);
        }
    }

private double pX,pY,px2,py2,px3,py3;
    @Override
    public void resizePressed(MouseEvent e, Page med) {
        pocetnaTacka = e.getPoint();

        for(int i=med.getModel().getDeviceCount()-1;i>=0;i--) {
            Slot s = med.getModel().getDeviceAt(i);
            if(s.getElementPainter().elementAt(s,pocetnaTacka) && s.getPaint().equals(Color.BLACK)){
                if(s.getElementPainter().cosak(e.getPoint(),s) != ""){
                    slot = s;
                    pocetnaDimenzija = slot.getSize();
                    pocetnaPozicija = slot.getPosition();
                    tip = slot.getElementPainter().cosak(e.getPoint(), slot);
                    pX = slot.getPosition().getX();
                    pY = slot.getPosition().getY();
                    px2 =pX + slot.getSize().width;
                    py2 = pY+slot.getSize().height;
                    px3 = pX - slot.getSize().width;
                    py3 = pY+slot.getSize().height;
                    pogodjen = 1;
                }
                if(s instanceof SlotCircle){
                    slot = s;
                    pocetnaDimenzija = slot.getSize();
                    pogodjen = 2;
                }
            }
        }
    }
    @Override
    public void resizeDragged(MouseEvent e, Page med) {
        if(pogodjen == 1){
            Point t = slot.getPosition();
            Dimension d = slot.getSize();
            if(med.getSelektovani().size() == 0){
                Point tacka = e.getPoint();
                if(slot instanceof SLotRectangle){
                    if(tip.equals("gornjiLevi")){
                        double x = tacka.getX() - pocetnaTacka.getX();
                        double y = tacka.getY() - pocetnaTacka.getY();
                        x1 = slot.getPosition().getX() + x;
                        y1 = slot.getPosition().getY() + y;
                        pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        double width = slot.getSize().width - (x1 - slot.getPosition().getX());
                        double hight = slot.getSize().height - (y1 - slot.getPosition().getY());
                         t = new Point((int)x1,(int)y1);
                        d = new Dimension((int)width, (int)hight);
                    }

                    if(tip.equals("gornjiDesni")){
                        double x = tacka.getX() - pocetnaTacka.getX();
                        double y = tacka.getY() - pocetnaTacka.getY();
                        pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        y1 = slot.getPosition().getY() + y;
                        double width = slot.getSize().width + x;
                        double hight = slot.getSize().height - (y1 - slot.getPosition().getY());
                        t = new Point((int)slot.getPosition().getX(), (int)y1);
                        d = new Dimension((int)width, (int)hight);
                    }

                    if(tip.equals("donjiDesni")){
                        double x = tacka.getX() - pocetnaTacka.getX();
                        double y = tacka.getY() - pocetnaTacka.getY();
                        pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        double width = slot.getSize().width + x;
                        double hight = slot.getSize().height + y;
                        d = new Dimension((int)width, (int)hight);
                    }

                    if(tip.equals("donjiiLevi")){
                        double x = tacka.getX() - pocetnaTacka.getX();
                        double y = tacka.getY() - pocetnaTacka.getY();
                        pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        x1 = slot.getPosition().getX() + x;
                        y1 = slot.getPosition().getY() + y;
                        double width = slot.getSize().width - (x1 - slot.getPosition().getX());
                        double hight = slot.getSize().height + (y1 - slot.getPosition().getY());
                        t = new Point((int)x1, (int)slot.getPosition().getY());
                        d = new Dimension((int)width, (int)hight);
                    }
                    med.getModel().removeDiagramElements(slot);
                    slot.setSize(d);
                    slot.setPosition(t);
                    slot.napraviRpeinter();
                    med.getModel().addDiagramElements(slot);
                    //med.getCommandManager().addCommand(new AddDeviceCommand(med.getModel(),t,slot,5,med.getCommandManager(),d),5);
                }
                else if(slot instanceof SlotTriangle){
                    Dimension d1 = slot.getSize();
                    Point p = slot.getPosition();
                    x1 = slot.getPosition().getX();
                    y1 = slot.getPosition().getY();
                    x2 = px2;
                    y2 = py2;
                    x3 = px3;
                    y3 = py3;
                    x11 = tacka.getX() - pocetnaTacka.getX();
                    y11 = tacka.getY() - pocetnaTacka.getY();
                    pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                    if(tip.equals("gornjiDesni")){
                        double width = slot.getSize().width;
                        double hight = slot.getSize().height - y11;
                        x11 = (int)slot.getPosition().getX();
                        y11 = (y1 + y11);
                        d1 = new Dimension((int)width, (int)hight);
                        p = new Point((int)x11,(int)y11);
                    }

                    if(tip.equals("donjiDesni")){
                        double width = slot.getSize().width + x11;
                        double hight = slot.getSize().height + y11;
                        d1 = new Dimension((int)width, (int)hight);
                    }

                    if(tip.equals("donjiiLevi")){
                        double width = slot.getSize().width - x11;
                        double hight = slot.getSize().height + y11;
                        d1 = new Dimension((int)width, (int)hight);
                    }
                    med.getModel().removeDiagramElements(slot);
                    slot.setSize(d1);
                    slot.setPosition(p);
                    slot.napraviRpeinter();
                    med.getModel().addDiagramElements(slot);
                   //med.getCommandManager().addCommand(new AddDeviceCommand(med.getModel(),p,slot,5,med.getCommandManager(),d1),5);
                }
            }
            else if(med.getSelektovani().size() != 0){
                    Point tacka = e.getPoint();
                    if(slot instanceof SLotRectangle){
                        if(tip.equals("gornjiLevi")){
                            double x = tacka.getX() - pocetnaTacka.getX();
                            double y = tacka.getY() - pocetnaTacka.getY();
                            for(Slot s: med.getSelektovani()){
                                x1 = s.getPosition().getX() + x;
                                y1 = s.getPosition().getY() + y;
                                double width = s.getSize().width - (x1 - s.getPosition().getX());
                                double hight = s.getSize().height - (y1 - s.getPosition().getY());
                                med.getModel().removeDiagramElements(s);
                                t = new Point((int)x1,(int)y1);
                                s.setPosition(t);
                                d = new Dimension((int)width, (int)hight);
                                s.setSize(d);
                                s.napraviRpeinter();
                                med.getModel().addDiagramElements(s);
                            }

                            pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        }

                        if(tip.equals("gornjiDesni")){
                            double x = tacka.getX() - pocetnaTacka.getX();
                            double y = tacka.getY() - pocetnaTacka.getY();
                            for(Slot s: med.getSelektovani()){
                                y1 = s.getPosition().getY() + y;
                                double width = s.getSize().width + x;
                                double hight = s.getSize().height - (y1 - s.getPosition().getY());
                                med.getModel().removeDiagramElements(s);
                                t = new Point((int)s.getPosition().getX(), (int)y1);
                                d = new Dimension((int)width, (int)hight);
                                s.setPosition(t);
                                s.setSize(d);
                                s.napraviRpeinter();
                                med.getModel().addDiagramElements(s);
                            }

                            pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        }

                        if(tip.equals("donjiDesni")){
                            double x = tacka.getX() - pocetnaTacka.getX();
                            double y = tacka.getY() - pocetnaTacka.getY();
                            for(Slot s: med.getSelektovani()){
                                double width = s.getSize().width + x;
                                double hight = s.getSize().height + y;
                                med.getModel().removeDiagramElements(s);
                                d = new Dimension((int)width, (int)hight);
                                s.setSize(d);
                                s.napraviRpeinter();
                                med.getModel().addDiagramElements(s);
                            }
                            pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        }

                        if(tip.equals("donjiiLevi")){
                            double x = tacka.getX() - pocetnaTacka.getX();
                            double y = tacka.getY() - pocetnaTacka.getY();
                            for(Slot s: med.getSelektovani()){
                                x1 = s.getPosition().getX() + x;
                                y1 = s.getPosition().getY() + y;
                                double width = s.getSize().width - (x1 - s.getPosition().getX());
                                double hight = s.getSize().height + (y1 - s.getPosition().getY());
                                med.getModel().removeDiagramElements(s);
                                t = new Point((int)x1, (int)s.getPosition().getY());
                                d = new Dimension((int)width, (int)hight);
                                s.setPosition(t);
                                s.setSize(d);
                                s.napraviRpeinter();
                                med.getModel().addDiagramElements(s);
                            }
                            pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        }
                    }
                    else if(slot instanceof SlotTriangle){
                        if(tip.equals("gornjiDesni")){
                            x11 = tacka.getX() - pocetnaTacka.getX();
                            y11 = tacka.getY() - pocetnaTacka.getY();
                            for(Slot s:med.getSelektovani()){
                                x1 = s.getPosition().getX();
                                y1 = s.getPosition().getY();
                                double width = s.getSize().width;
                                double hight = s.getSize().height - y11;
                                x11 = (int)s.getPosition().getX();
                                y11 = (y1 + y11);
                                med.getModel().removeDiagramElements(s);
                                s.setPosition(new Point((int)x11,(int)y11));
                                s.setSize(new Dimension((int)width, (int)hight));
                                s.napraviRpeinter();
                                med.getModel().addDiagramElements(s);
                            }
                            pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        }

                        if(tip.equals("donjiDesni")){
                            x11 = tacka.getX() - pocetnaTacka.getX();
                            y11 = tacka.getY() - pocetnaTacka.getY();
                            for(Slot s:med.getSelektovani()){
                                double width = s.getSize().width + x11;
                                double hight = s.getSize().height + y11;
                                med.getModel().removeDiagramElements(s);
                                s.setSize(new Dimension((int)width, (int)hight));
                                s.napraviRpeinter();
                                med.getModel().addDiagramElements(s);
                            }
                            pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        }

                        if(tip.equals("donjiiLevi")){
                            x11 = tacka.getX() - pocetnaTacka.getX();
                            y11 = tacka.getY() - pocetnaTacka.getY();
                            for(Slot s:med.getSelektovani()){
                                double width = s.getSize().width - x11;
                                double hight = s.getSize().height + y11;
                                med.getModel().removeDiagramElements(s);
                                s.setSize(new Dimension((int)width, (int)hight));
                                s.napraviRpeinter();
                                med.getModel().addDiagramElements(s);
                            }
                            pocetnaTacka.setLocation((int)tacka.getX(),(int)tacka.getY());
                        }
                    }
                }
            }


        if(pogodjen == 2){
            Point tacka = e.getPoint();
            if(med.getSelektovani().size() == 0){
                x1 = tacka.getX() - pocetnaTacka.getX();
                int width = (int) (slot.getSize().width + x1);
                Dimension d = new Dimension((int)width,(int)slot.getSize().height);
                pocetnaTacka = tacka;
                med.getModel().removeDiagramElements(slot);
                slot.setSize(d);
                slot.napraviRpeinter();
                med.getModel().addDiagramElements(slot);
                //med.getCommandManager().addCommand(new AddDeviceCommand(med.getModel(),slot.getPosition(),slot,5,med.getCommandManager(),d),5);
            }
            if(med.getSelektovani().size() != 0){
                x1 = tacka.getX() - pocetnaTacka.getX();
                for(Slot s: med.getSelektovani()){
                    int width = (int) (s.getSize().width + x1);
                    Dimension d = new Dimension((int)width,(int)s.getSize().height);
                    med.getModel().removeDiagramElements(s);
                    s.setSize(d);
                    s.napraviRpeinter();
                    med.getModel().addDiagramElements(s);
                }
                pocetnaTacka = tacka;
            }
        }
    }

    @Override
    public void resizeReleased(MouseEvent e, Page med) {
        med.getTrenutneVrednosti().clear();
        med.getTrenutneDimenzije().clear();
        pogodjen = 0;
        for(int i =0 ; i< med.getSelektovani().size(); i++){
            trenutneVrednosti.add(med.getSelektovani().get(i).getPosition());
            trenutneDimenzije.add(med.getSelektovani().get(i).getSize());
        }
        med.setTrenutneVrednosti(trenutneVrednosti);
        med.setTrenutneDimenzije(trenutneDimenzije);
        med.getCommandManager().addCommand(new ResizeCommand(med.getModel(),slot.getPosition(),pocetnaPozicija,slot,5,med.getCommandManager(),slot.getSize(),pocetnaDimenzija,med.getSelektovani(),med.getPocetneVrednosti(),med.getTrenutneVrednosti(),med.getPocetneDimenzije(),med.getTrenutneDimenzije()),5);
    }

    @Override
    public void rotatePressed(MouseEvent e, Page med) {
        pocetnaTacka = e.getPoint();
        for(int i=med.getModel().getDeviceCount()-1;i>=0;i--){
            slot = med.getModel().getDeviceAt(i);
            if(slot.getElementPainter().elementAt(slot,pocetnaTacka)) {
                if(slot.getPaint().equals(Color.BLACK)){
                    x1 = pocetnaTacka.getX();
                    pogodjen = 1;
                    break;
                }
            }
        }
    }

    @Override
    public void rotateDragged(MouseEvent e, Page med) {
        if(pogodjen == 1){
            if(med.getSelektovani().size() == 0){
                Point krajnjaTacka = e.getPoint();
                x2 = krajnjaTacka.getX();
                if((krajnjaTacka.getY() < slot.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() > 0) || (krajnjaTacka.getY() > slot.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() < 0)){
                    i = i + 0.1;
                }
                else if((krajnjaTacka.getY() > slot.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() > 0) || (krajnjaTacka.getY() < slot.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() < 0)){
                    i = i - 0.1;
                }

                pocetnaTacka = krajnjaTacka;

                med.getModel().removeDiagramElements(slot);
                slot.setUgao(i);
                med.getModel().addDiagramElements(slot);
                //med.getCommandManager().addCommand(new AddDeviceCommand(med.getModel(),slot.getPosition(),slot,6,med.getCommandManager(),i),6);
            }
            if(med.getSelektovani().size() != 0){
                for(Slot s: med.getSelektovani()){
                    Point krajnjaTacka = e.getPoint();
                    x2 = krajnjaTacka.getX();
                    if((krajnjaTacka.getY() < s.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() > 0) || (krajnjaTacka.getY() > s.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() < 0)){
                        i = i + 0.1;
                    }
                    else if((krajnjaTacka.getY() > s.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() > 0) || (krajnjaTacka.getY() < s.getPosition().getY() && krajnjaTacka.getX() - pocetnaTacka.getX() < 0)){
                        i = i - 0.1;
                    }

                    pocetnaTacka = krajnjaTacka;
                    //med.getCommandManager().addCommand(new AddDeviceCommand(med.getModel(),slot.getPosition(),slot,11,med.getCommandManager(),i,med.getSelektovani()),11);
                   // s.setUgao(i);
                    med.getModel().removeDiagramElements(s);
                    s.setUgao(i);
                    med.getModel().addDiagramElements(s);
                    s.napraviRpeinter();
                }
            }
        }
    }

    @Override
    public void rotateReleased(MouseEvent e, Page med) {
        med.getCommandManager().addCommand(new RotateCommand(med.getModel(),slot.getPosition(),slot,8,med.getCommandManager(),i,med.getSelektovani()),8);
        pogodjen = 0;
    }

    @Override
    public void circlePressed(MouseEvent e, Page med) {
        Point position = e.getPoint();

        SlotFactory slot = new CircleFactory();
        Slot slot2= slot.napraviSlot("kurg",med,position);
        med.getCommandManager().addCommand(new CircleCommand(med.getModel(),position,slot2,1,med.getCommandManager()),1);
        Page page = (Page) slot2.getParent();
        page.addChild(slot2);
        MainFrame.getInstance().getTree().addSlot(slot2);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree().getTreeView());
    }

    @Override
    public void trianglePresed(MouseEvent e, Page med) {
        Point position = e.getPoint();
        SlotFactory slot2 = new TriangleFactory();
        Slot slot = slot2.napraviSlot("trougao",med,position);
        med.getCommandManager().addCommand(new TriangleCommand(med.getModel(),position,slot,2,med.getCommandManager()),2);
        Page page = (Page) slot.getParent();
        page.addChild(slot);
        MainFrame.getInstance().getTree().addSlot(slot);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree().getTreeView());
    }

    @Override
    public void rectanglePressed(MouseEvent e, Page med) {

        Point position = e.getPoint();
        SlotFactory slot2 = new RectangleFactory();
        Slot slot = slot2.napraviSlot("pravougaonik",med,position);
        med.getCommandManager().addCommand(new RectangleCommand(med.getModel(),position,slot,3,med.getCommandManager()),3);
        Page page = (Page) slot.getParent();
        page.addChild(slot);
        MainFrame.getInstance().getTree().addSlot(slot);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree().getTreeView());
    }

}
