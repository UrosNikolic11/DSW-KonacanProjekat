package dsw.gerudok.app.repository.state;

import dsw.gerudok.app.gui.swing.view.SlotPainter;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.slotHandler.SlotHandler;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState extends State{
    SlotHandler slotHandler;
    private Page med; //save the Mediator
    public MoveState(Page md) {
        med = md;
        slotHandler = new SlotHandler();
    }


    public void mousePressed(MouseEvent e) {
        slotHandler.movePressed(e,med);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        slotHandler.moveDragged(e,med);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       slotHandler.moveRelesed(e,med);
    }
}