package dsw.gerudok.app.gui.swing.command;

import dsw.gerudok.app.gui.swing.view.PageView;
import dsw.gerudok.app.repository.PageModel;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.SlotCircle;
import dsw.gerudok.app.repository.SlotTriangle;
import dsw.gerudok.app.repository.factory.CircleFactory;
import dsw.gerudok.app.repository.factory.SlotFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class AddDeviceCommand extends AbstractCommand{

    protected PageModel model;
    protected static Point lastPosition;
    protected Slot slot;
    protected static Point pozicija;
    protected int slotType;
    protected static int br=0;
    protected static int prethodni = 0 ;
    protected static int z = 0;
    protected CommandManager c;
    protected static double ugao;
    protected static Dimension dimenzija;
    protected static Dimension pocetnaDimenzija;
    protected static double x1,x2,y1,y2,x11,x22,y11,y22,a,b;
    protected  ArrayList<Point> pocetneVrednosti = new ArrayList<>();
    protected ArrayList<Slot> selektovaniSlotovi = new ArrayList<>();
    protected ArrayList<Point> trenutneVrednosti = new ArrayList<>();
    protected ArrayList<Dimension> pocetneDimenzije = new ArrayList<>();
    protected ArrayList<Dimension> trenutneDimenzije = new ArrayList<>();
    protected  ArrayList<Point> vratiNaStaro = new ArrayList<>();
    protected static Point pocetnaTacka;
    protected static Point tacka2;
   // MoveCommand mc;
    /*DeleteCommand dc;
    RotateCommand rc;
    MoveCommand mc;*/


    public AddDeviceCommand(PageModel model, Point pozicija, Slot slot, int slotType,CommandManager c) {
        this.model = model;
        this.pozicija = pozicija;
        this.slot = slot;
        this.slotType = slotType;
        this.c = c;
    }

    public AddDeviceCommand(PageModel model, Point pozicija, Slot slot, int slotType,CommandManager c,Double ugao) {
        this.model = model;
        this.pozicija = pozicija;
        this.slot = slot;
        this.slotType = slotType;
        this.c = c;
        this.ugao = ugao;
    }

    public AddDeviceCommand(PageModel model, Point pozicija,Point lastPosition, Slot slot, int slotType,CommandManager c,Dimension dimenzija,Dimension pocetnaDimenzija,ArrayList<Slot> selektovani,ArrayList<Point> pocetneVrednosti,ArrayList<Point> trenutneVrednosti,ArrayList<Dimension> pocetneDimenzije,ArrayList<Dimension> trenutneDimenzije) {
        this.model = model;
        this.pozicija = pozicija;
        this.lastPosition= lastPosition;
        this.slot = slot;
        this.slotType = slotType;
        this.c = c;
        this.dimenzija = dimenzija;
        this.pocetnaDimenzija = pocetnaDimenzija;
        this.selektovaniSlotovi = selektovani;
        this.pocetneVrednosti = pocetneVrednosti;
        this.trenutneVrednosti = trenutneVrednosti;
        this.pocetneDimenzije = pocetneDimenzije;
        this.trenutneDimenzije = trenutneDimenzije;
    }


    public AddDeviceCommand(PageModel model,Point lastPosition, Point pozicija, Slot slot, int slotType,CommandManager c, ArrayList<Slot> selektovani,ArrayList<Point> pocetneVrednosti,ArrayList<Point> trenutneVrednosti) {
        this.model = model;
        this.pozicija = pozicija;
        this.lastPosition= lastPosition;
        this.slot = slot;
        this.slotType = slotType;
        this.c = c;
        this.selektovaniSlotovi=selektovani;
        this.pocetneVrednosti = pocetneVrednosti;
        this.trenutneVrednosti = trenutneVrednosti;

    }

    public AddDeviceCommand(PageModel model, Point pozicija, Slot slot, int slotType,CommandManager c,Double ugao,ArrayList<Slot> selektovaniSlotovi) {
        this.model = model;
        this.pozicija = pozicija;
        this.slot = slot;
        this.slotType = slotType;
        this.c = c;
        this.ugao = ugao;
        this.selektovaniSlotovi = selektovaniSlotovi;
    }

    public AddDeviceCommand(PageModel model, Point pozicija, Slot slot, int slotType,CommandManager c,ArrayList<Slot> selektovaniSlotovi) {
        this.model = model;
        this.pozicija = pozicija;
        this.slot = slot;
        this.slotType = slotType;
        this.c = c;
        this.selektovaniSlotovi = selektovaniSlotovi;
    }





    public PageModel getModel() {
        return model;
    }

    public void setModel(PageModel model) {
        this.model = model;
    }

    public Point getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Point lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public int getSlotType() {
        return slotType;
    }

    public void setSlotType(int slotType) {
        this.slotType = slotType;
    }

    public int getPrethodni() {
        return prethodni;
    }

    public void setPrethodni(int prethodni) {
        this.prethodni = prethodni;
    }


}
