package dsw.gerudok.app.repository;

import dsw.gerudok.app.gui.swing.view.RectanglePainter;
import dsw.gerudok.app.repository.node.RuNode;

import java.awt.*;

public class SLotRectangle extends Slot{
    public SLotRectangle(String name, RuNode parent, Point position) {
        super(name, parent, position);
        elementPainter = new RectanglePainter(this);
    }
}
