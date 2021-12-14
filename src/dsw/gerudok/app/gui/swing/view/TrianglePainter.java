package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.SlotTriangle;

import java.awt.geom.GeneralPath;

public class TrianglePainter extends SlotPainter{
    public TrianglePainter(Slot slot) {
        super(slot);
        SlotTriangle triangle = (SlotTriangle) slot;
        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(triangle.getPosition().x,triangle.getPosition().y);
        ((GeneralPath)shape).lineTo(triangle.getPosition().x+triangle.getSize().width,triangle.getPosition().y+triangle.getSize().height);
        ((GeneralPath)shape).lineTo(triangle.getPosition().x-triangle.getSize().width,triangle.getPosition().y+triangle.getSize().height);
        ((GeneralPath)shape).closePath();

        gornjiDesni = new GeneralPath();
        ((GeneralPath)gornjiDesni).moveTo(triangle.getPosition().x-2.5,triangle.getPosition().y-5);
        ((GeneralPath)gornjiDesni).lineTo(triangle.getPosition().x-2.5,triangle.getPosition().y+5);
        ((GeneralPath)gornjiDesni).lineTo(triangle.getPosition().x+2.5,triangle.getPosition().y+5);
        ((GeneralPath)gornjiDesni).lineTo(triangle.getPosition().x+2.5,triangle.getPosition().y-5);
        ((GeneralPath)gornjiDesni).closePath();

        donjiDesni = new GeneralPath();
        ((GeneralPath)donjiDesni).moveTo(triangle.getPosition().x+triangle.getSize().width-2.5,triangle.getPosition().y+triangle.getSize().height-5);
        ((GeneralPath)donjiDesni).lineTo(triangle.getPosition().x+triangle.getSize().width-2.5,triangle.getPosition().y+triangle.getSize().height+5);
        ((GeneralPath)donjiDesni).lineTo(triangle.getPosition().x+triangle.getSize().width+2.5,triangle.getPosition().y+triangle.getSize().height+5);
        ((GeneralPath)donjiDesni).lineTo(triangle.getPosition().x+triangle.getSize().width+2.5,triangle.getPosition().y+triangle.getSize().height-5);
        ((GeneralPath)donjiDesni).closePath();

        donjiiLevi = new GeneralPath();
        ((GeneralPath)donjiiLevi).moveTo(triangle.getPosition().x-triangle.getSize().width-2.5,triangle.getPosition().y+triangle.getSize().height-5);
        ((GeneralPath)donjiiLevi).lineTo(triangle.getPosition().x-triangle.getSize().width-2.5,triangle.getPosition().y+triangle.getSize().height+5);
        ((GeneralPath)donjiiLevi).lineTo(triangle.getPosition().x-triangle.getSize().width+2.5,triangle.getPosition().y+triangle.getSize().height+5);
        ((GeneralPath)donjiiLevi).lineTo(triangle.getPosition().x-triangle.getSize().width+2.5,triangle.getPosition().y+triangle.getSize().height-5);
        ((GeneralPath)donjiiLevi).closePath();
    }
}
