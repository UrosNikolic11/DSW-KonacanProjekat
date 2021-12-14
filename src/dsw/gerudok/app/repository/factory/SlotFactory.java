package dsw.gerudok.app.repository.factory;

import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.node.RuNode;
import javafx.scene.Parent;

import java.awt.*;

public abstract class SlotFactory {

    public Slot napraviSlot(String name, RuNode parent, Point position){
        Slot slot = createSlot(name , parent , position);
        return slot;
    }

    protected abstract Slot createSlot(String name, RuNode parent, Point position);
}
