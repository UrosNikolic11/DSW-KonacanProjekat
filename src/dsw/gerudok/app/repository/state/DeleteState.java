package dsw.gerudok.app.repository.state;

import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.SlotPainter;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.slotHandler.SlotHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class    DeleteState extends State{
    private Page med; //save the Mediator
    public DeleteState(Page md) {
        med = md;
    }


    public void mousePressed(MouseEvent e) {
        SlotHandler slotHandler = new SlotHandler();
        slotHandler.deletePresses(e,med);
    }
}
