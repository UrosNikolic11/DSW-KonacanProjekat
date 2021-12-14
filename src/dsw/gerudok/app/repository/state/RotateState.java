package dsw.gerudok.app.repository.state;

import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.slotHandler.SlotHandler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class RotateState extends State{
    private Page med; //save the Mediator
    private double xa,xb,xc,ya,yb,yc,k1,k2,u;
    Point pocetnaTacka;
    Slot slot;
    SlotHandler slotHandler;
    private int pogodjen=0;
    public RotateState(Page md) {
        med = md;
        slotHandler = new SlotHandler();
    }


    public void mousePressed(MouseEvent e) {
        slotHandler.rotatePressed(e, med);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        slotHandler.rotateDragged(e ,med);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        slotHandler.rotateReleased(e, med);
    }
}
