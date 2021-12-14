package dsw.gerudok.app.repository;

import dsw.gerudok.app.gui.swing.view.TrianglePainter;
import dsw.gerudok.app.repository.node.RuNode;

import java.awt.*;

public class SlotTriangle extends Slot{
    public SlotTriangle(String name, RuNode parent, Point position) {
        super(name, parent, position);
        elementPainter = new TrianglePainter(this);


    }
}
