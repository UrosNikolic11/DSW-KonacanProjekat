package dsw.gerudok.app.repository;

import dsw.gerudok.app.gui.swing.view.CirclePainter;
import dsw.gerudok.app.gui.swing.view.ElementPainter;
import dsw.gerudok.app.gui.swing.view.RectanglePainter;
import dsw.gerudok.app.gui.swing.view.TrianglePainter;
import dsw.gerudok.app.repository.node.RuNode;
import javafx.scene.paint.Paint;

import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.util.Random;

public abstract class Slot extends RuNode {
    private Color paint;
    private BasicStroke stroke;
    private String name;
    protected ElementPainter elementPainter;
    private Dimension size;
    private Point position;
    protected Double ugao;
    protected int multiselekcija;

    public Slot(String name, RuNode parent,Point position) {
        super(name, parent);
        this.position = position;
        this.ugao = 0.0;
        Random random = new Random();
        this.name = "Slot" + random.nextInt(100);
        this.paint = Color.BLUE;
        size = new Dimension(50,100);
    }

    public void napraviRpeinter(){
        if(this instanceof SLotRectangle){
            elementPainter = new RectanglePainter(this);
        }
        else if(this instanceof SlotCircle){
            System.out.println(">");
            elementPainter = new CirclePainter(this);
        }
        else if(this instanceof SlotTriangle){
            elementPainter = new TrianglePainter(this);
        }
    }

    public Color getPaint() {
        return paint;
    }

    public void setPaint(Color paint) {
        this.paint = paint;
    }

    public BasicStroke getStroke() {
        return stroke;
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public ElementPainter getElementPainter() {
        return elementPainter;
    }

    public void setElementPainter(ElementPainter elementPainter) {
        this.elementPainter = elementPainter;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Double getUgao() {
        return ugao;
    }

    public void setUgao(Double ugao) {
        this.ugao = ugao;
    }

    public int getMultiselekcija() {
        return multiselekcija;
    }

    public void setMultiselekcija(int multiselekcija) {
        this.multiselekcija = multiselekcija;
    }
}
