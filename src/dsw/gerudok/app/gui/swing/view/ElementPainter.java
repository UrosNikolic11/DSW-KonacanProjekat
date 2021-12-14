package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.repository.Slot;

import java.awt.*;

public abstract class ElementPainter {
    public ElementPainter(Slot slot) {

    }

    public abstract void paint(Graphics2D g, Slot slot);

    public abstract boolean elementAt(Slot slot, Point position);

    public abstract boolean multiSelekcija(Slot slot, Shape shape);

    public abstract String cosak(Point position, Slot slot);
}
