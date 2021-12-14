package dsw.gerudok.app.gui.swing.command;

import dsw.gerudok.app.repository.PageModel;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.SlotCircle;

import java.awt.*;
import java.util.ArrayList;

public class ResizeCommand extends AddDeviceCommand{


    public ResizeCommand(PageModel model, Point pozicija, Point lastPosition, Slot slot, int slotType, CommandManager c, Dimension dimenzija, Dimension pocetnaDimenzija, ArrayList<Slot> selektovani, ArrayList<Point> pocetneVrednosti, ArrayList<Point> trenutneVrednosti, ArrayList<Dimension> pocetneDimenzije, ArrayList<Dimension> trenutneDimenzije) {
        super(model, pozicija, lastPosition, slot, slotType, c, dimenzija, pocetnaDimenzija, selektovani, pocetneVrednosti, trenutneVrednosti, pocetneDimenzije, trenutneDimenzije);
    }

    @Override
    public void doCommand() {
        if(selektovaniSlotovi.size() == 0){

            if(slot instanceof SlotCircle){
                model.removeDiagramElements(slot);
                slot.setSize(dimenzija);
                slot.napraviRpeinter();
                model.addDiagramElements(slot);
            }else{
                model.removeDiagramElements(slot);
                slot.setSize(dimenzija);
                slot.setPosition(pozicija);
                slot.napraviRpeinter();
                model.addDiagramElements(slot);
            }


        }else{

            for(int i =0 ; i<selektovaniSlotovi.size() ; i++){
                if(selektovaniSlotovi.get(i) instanceof SlotCircle){
                    model.removeDiagramElements(selektovaniSlotovi.get(i));
                    selektovaniSlotovi.get(i).setSize(trenutneDimenzije.get(i));
                    selektovaniSlotovi.get(i).napraviRpeinter();
                    model.addDiagramElements(selektovaniSlotovi.get(i));
                }else{
                    model.removeDiagramElements(selektovaniSlotovi.get(i));
                    selektovaniSlotovi.get(i).setSize(trenutneDimenzije.get(i));
                    selektovaniSlotovi.get(i).setPosition(trenutneVrednosti.get(i));
                    selektovaniSlotovi.get(i).napraviRpeinter();
                    model.addDiagramElements(selektovaniSlotovi.get(i));
                }
            }

        }
    }

    @Override
    public void undoCommand() {
        if(selektovaniSlotovi.size() == 0){

            if(slot instanceof SlotCircle){
                model.removeDiagramElements(slot);
                slot.setSize(pocetnaDimenzija);
                slot.napraviRpeinter();
                model.addDiagramElements(slot);
                return;
            }
            model.removeDiagramElements(slot);
            slot.setSize(pocetnaDimenzija);
            slot.setPosition(lastPosition);
            slot.napraviRpeinter();
            model.addDiagramElements(slot);

        }else{
            for(int i =0 ; i<selektovaniSlotovi.size() ; i++){
                if(selektovaniSlotovi.get(i) instanceof SlotCircle){
                    model.removeDiagramElements(selektovaniSlotovi.get(i));
                    selektovaniSlotovi.get(i).setSize(pocetneDimenzije.get(i));
                    selektovaniSlotovi.get(i).napraviRpeinter();
                    model.addDiagramElements(selektovaniSlotovi.get(i));
                }else{
                    model.removeDiagramElements(selektovaniSlotovi.get(i));
                    selektovaniSlotovi.get(i).setSize(pocetneDimenzije.get(i));
                    selektovaniSlotovi.get(i).setPosition(pocetneVrednosti.get(i));
                    selektovaniSlotovi.get(i).napraviRpeinter();
                    model.addDiagramElements(selektovaniSlotovi.get(i));
                }
            }
        }
    }
}
