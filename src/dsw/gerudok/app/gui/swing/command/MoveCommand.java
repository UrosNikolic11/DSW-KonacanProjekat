package dsw.gerudok.app.gui.swing.command;

import dsw.gerudok.app.repository.PageModel;
import dsw.gerudok.app.repository.Slot;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand extends AddDeviceCommand{


    public MoveCommand(PageModel model, Point lastPosition, Point pozicija, Slot slot, int slotType, CommandManager c, ArrayList<Slot> selektovani, ArrayList<Point> pocetneVrednosti, ArrayList<Point> trenutneVrednosti) {
        super(model, lastPosition, pozicija, slot, slotType, c, selektovani, pocetneVrednosti, trenutneVrednosti);
    }

    @Override
    public void doCommand() {

        if(selektovaniSlotovi.size() == 0){

            model.removeDiagramElements(slot);
            slot.setPosition(pozicija);
            slot.napraviRpeinter();
            model.addDiagramElements(slot);

        }else{
            for(int i=0; i<selektovaniSlotovi.size() ; i++){
                model.removeDiagramElements(selektovaniSlotovi.get(i));
                selektovaniSlotovi.get(i).setPosition(trenutneVrednosti.get(i));
                selektovaniSlotovi.get(i).napraviRpeinter();
                model.addDiagramElements(selektovaniSlotovi.get(i));
            }
        }

    }

    @Override
    public void undoCommand() {

        if(selektovaniSlotovi.size() == 0){

            model.removeDiagramElements(slot);
            slot.setPosition(lastPosition);
            slot.napraviRpeinter();
            model.addDiagramElements(slot);

        }else{
            for(int i=0; i<selektovaniSlotovi.size() ; i++){
                model.removeDiagramElements(selektovaniSlotovi.get(i));
                selektovaniSlotovi.get(i).setPosition(pocetneVrednosti.get(i));
                selektovaniSlotovi.get(i).napraviRpeinter();
                model.addDiagramElements(selektovaniSlotovi.get(i));
            }
        }
    }






}
