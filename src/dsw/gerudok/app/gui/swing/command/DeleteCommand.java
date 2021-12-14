package dsw.gerudok.app.gui.swing.command;

import dsw.gerudok.app.repository.PageModel;
import dsw.gerudok.app.repository.Slot;

import java.awt.*;
import java.util.ArrayList;

public class DeleteCommand extends AddDeviceCommand{


    public DeleteCommand(PageModel model, Point pozicija, Slot slot, int slotType, CommandManager c, ArrayList<Slot> selektovaniSlotovi) {
        super(model, pozicija, slot, slotType, c, selektovaniSlotovi);
    }

    public void doCommand(){
        if(selektovaniSlotovi.size() == 0){
            model.removeDiagramElements(slot);
        }else{
            for(Slot s: selektovaniSlotovi){
                model.removeDiagramElements(s);
            }
        }
    }

    public void undoCommand(){
        if(selektovaniSlotovi.size() == 0){
            model.addDiagramElements(slot);
        }else{
            for(Slot s: selektovaniSlotovi){
                model.addDiagramElements(s);
            }
        }
    }

}
