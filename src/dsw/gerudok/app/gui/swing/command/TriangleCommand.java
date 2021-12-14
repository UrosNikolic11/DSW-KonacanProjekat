package dsw.gerudok.app.gui.swing.command;

import dsw.gerudok.app.repository.PageModel;
import dsw.gerudok.app.repository.Slot;

import java.awt.*;

public class TriangleCommand extends AddDeviceCommand{


    public TriangleCommand(PageModel model, Point pozicija, Slot slot, int slotType, CommandManager c) {
        super(model, pozicija, slot, slotType, c);
    }

    public void doCommand(){
        model.addDiagramElements(slot);
    }

    public void undoCommand(){
        model.removeDiagramElements(slot);

    }
}
