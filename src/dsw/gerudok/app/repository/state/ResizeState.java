package dsw.gerudok.app.repository.state;

import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.SLotRectangle;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.slotHandler.SlotHandler;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeState extends State{
    private Page med;
    SlotHandler slotHandler;//save the Mediator
    public ResizeState(Page md) {
        med = md;
        slotHandler = new SlotHandler();
    }


    public void mousePressed(MouseEvent e) {
            slotHandler.resizePressed(e, med);
        }




    @Override
    public void mouseDragged(MouseEvent e) {
        slotHandler.resizeDragged(e, med);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        slotHandler.resizeReleased(e, med);

    }
}
