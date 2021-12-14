package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.repository.Slot;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class SlotPainter extends ElementPainter{
    protected Shape shape;
    protected Shape gornjiLevi;
    protected Shape donjiiLevi;
    protected Shape gornjiDesni;
    protected Shape donjiDesni;
    private Slot slot;
    public SlotPainter(Slot slot) {
        super(slot);
    }
    @Override
    public void paint(Graphics2D g, Slot slot) {

                g.setColor(slot.getPaint());
                AffineTransform affineTransform = g.getTransform();
                g.rotate(slot.getUgao()/5, slot.getPosition().getX(), slot.getPosition().getY());
                g.draw(shape);
                g.setTransform(affineTransform);
                ((SlotPainter)slot.getElementPainter()).setShape(shape);


            if(slot.getMultiselekcija() == 1){
                g.setColor(Color.GRAY);
                g.draw(shape);
            }
    }

    @Override
    public boolean elementAt(Slot slot, Point position) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(slot.getUgao()/5, slot.getPosition().getX(),slot.getPosition().getY());
        if(affineTransform.createTransformedShape(shape).contains(position)){
            return true;
        }
        else return false;
    }

    @Override
    public boolean multiSelekcija(Slot slot, Shape shape) {
        if(shape.contains((Point2D) ((SlotPainter)slot.getElementPainter()).getShape())){
            return true;
        }
        else return false;
    }

    public String cosak(Point pos, Slot slot){
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(slot.getUgao()/5, slot.getPosition().getX(),slot.getPosition().getY());
        if(gornjiDesni != null){
            if(affineTransform.createTransformedShape(gornjiDesni).contains(pos)){
                return "gornjiDesni";
            }
        }
        if(gornjiLevi != null){
            if(affineTransform.createTransformedShape(gornjiLevi).contains(pos)){
                return "gornjiLevi";
            }
        }

        if(donjiiLevi != null){
            if(affineTransform.createTransformedShape(donjiiLevi).contains(pos)){
                return "donjiiLevi";
            }
        }
        if(donjiDesni != null){
            if(affineTransform.createTransformedShape(donjiDesni).contains(pos)){
                return "donjiDesni";
            }
        }

        return "";
    }



    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setSlot(Slot slot1){
        this.slot = slot1;
    }

    public Shape getGornjiLevi() {
        return gornjiLevi;
    }

    public void setGornjiLevi(Shape gornjiLevi) {
        this.gornjiLevi = gornjiLevi;
    }

    public Shape getDonjiiLevi() {
        return donjiiLevi;
    }

    public void setDonjiiLevi(Shape donjiiLevi) {
        this.donjiiLevi = donjiiLevi;
    }

    public Shape getGornjiDesni() {
        return gornjiDesni;
    }

    public void setGornjiDesni(Shape gornjiDesni) {
        this.gornjiDesni = gornjiDesni;
    }

    public Shape getDonjiDesni() {
        return donjiDesni;
    }

    public void setDonjiDesni(Shape donjiDesni) {
        this.donjiDesni = donjiDesni;
    }

}
