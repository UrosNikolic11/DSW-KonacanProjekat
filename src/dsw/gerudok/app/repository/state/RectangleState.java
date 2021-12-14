package dsw.gerudok.app.repository.state;

import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.SLotRectangle;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.SlotCircle;
import dsw.gerudok.app.repository.factory.RectangleFactory;
import dsw.gerudok.app.repository.factory.SlotFactory;
import dsw.gerudok.app.repository.slotHandler.SlotHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class RectangleState extends State{
    private Page med; //save the Mediator
    SlotHandler slotHandler;
    public RectangleState(Page md) {
        slotHandler = new SlotHandler();
        med = md;
    }


    public void mousePressed(MouseEvent e) {
        slotHandler.rectanglePressed(e, med);
    }
}
