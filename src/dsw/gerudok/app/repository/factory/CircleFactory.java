package dsw.gerudok.app.repository.factory;

import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.SlotCircle;
import dsw.gerudok.app.repository.node.RuNode;

import java.awt.*;

public class CircleFactory extends SlotFactory{

    @Override
    protected Slot createSlot(String name, RuNode parent, Point position) {
        return new SlotCircle(name , parent, position);
    }
}
