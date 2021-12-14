package dsw.gerudok.app.repository;

import dsw.gerudok.app.gui.swing.view.CirclePainter;
import dsw.gerudok.app.repository.node.RuNode;

import java.awt.*;
import java.awt.geom.Point2D;

public class SlotCircle extends Slot{
    public SlotCircle(String name, RuNode parent, Point position) {
        super(name, parent, position);
        elementPainter = new CirclePainter(this);
    }


}
