package dsw.gerudok.app.repository.factory;

import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.SlotTriangle;
import dsw.gerudok.app.repository.node.RuNode;

import java.awt.*;

public class TriangleFactory extends SlotFactory{
    @Override
    protected Slot createSlot(String name, RuNode parent, Point position) {
        return new SlotTriangle(name , parent , position);
    }
}
