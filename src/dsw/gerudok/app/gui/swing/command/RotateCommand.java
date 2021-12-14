package dsw.gerudok.app.gui.swing.command;

import dsw.gerudok.app.repository.PageModel;
import dsw.gerudok.app.repository.Slot;

import java.awt.*;
import java.util.ArrayList;

public class RotateCommand extends AddDeviceCommand{


    public RotateCommand(PageModel model, Point pozicija, Slot slot, int slotType, CommandManager c, Double ugao, ArrayList<Slot> selektovaniSlotovi) {
        super(model, pozicija, slot, slotType, c, ugao, selektovaniSlotovi);
    }

    public void doCommand(){
        if(selektovaniSlotovi.size() == 0){
            model.removeDiagramElements(slot);
            slot.setUgao(ugao);
            slot.napraviRpeinter();
            model.addDiagramElements(slot);
        }else{
            for(Slot s: selektovaniSlotovi){
                model.removeDiagramElements(s);
                s.setUgao(ugao);
                s.napraviRpeinter();
                model.addDiagramElements(s);
            }
        }
    }

    public void undoCommand(){
        if(selektovaniSlotovi.size() == 0){
            model.removeDiagramElements(slot);
            slot.setUgao(0.0);
            slot.napraviRpeinter();
            model.addDiagramElements(slot);
        }else{
            for(Slot s: selektovaniSlotovi){
                model.removeDiagramElements(s);
                s.setUgao(0.0);
                s.napraviRpeinter();
                model.addDiagramElements(s);
            }
        }
    }

}
