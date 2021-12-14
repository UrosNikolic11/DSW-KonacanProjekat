package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.SlotCircle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class CirclePainter extends SlotPainter{
    public CirclePainter(Slot slot) {
        super(slot);
        SlotCircle circle = (SlotCircle)slot;
        System.out.println(circle.getPosition().getX());
        shape = new GeneralPath();
        shape =new Ellipse2D.Double(Math.abs(circle.getPosition().getX()), Math.abs(circle.getPosition().getY()), Math.abs(circle.getSize().getWidth()), Math.abs(circle.getSize().getWidth()));
    }

}
