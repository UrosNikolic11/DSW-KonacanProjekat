package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.repository.SLotRectangle;
import dsw.gerudok.app.repository.Slot;

import java.awt.geom.GeneralPath;

public class RectanglePainter extends SlotPainter{
    public RectanglePainter(Slot slot) {
        super(slot);
        SLotRectangle rectangle =  (SLotRectangle) slot;

        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo(rectangle.getPosition().x,rectangle.getPosition().y);

        ((GeneralPath)shape).lineTo(rectangle.getPosition().x+(rectangle.getSize().width),rectangle.getPosition().y);

        ((GeneralPath)shape).lineTo(rectangle.getPosition().x+(rectangle.getSize().width),rectangle.getPosition().y+rectangle.getSize().height);

        ((GeneralPath)shape).lineTo(rectangle.getPosition().x,rectangle.getPosition().y+rectangle.getSize().height);

        ((GeneralPath)shape).closePath();

        gornjiLevi = new GeneralPath();
        ((GeneralPath)gornjiLevi).moveTo(rectangle.getPosition().x,rectangle.getPosition().y);

        ((GeneralPath)gornjiLevi).lineTo(rectangle.getPosition().x+5,rectangle.getPosition().y);

        ((GeneralPath)gornjiLevi).lineTo(rectangle.getPosition().x+5,rectangle.getPosition().y+5);

        ((GeneralPath)gornjiLevi).lineTo(rectangle.getPosition().x,rectangle.getPosition().y+5);

        ((GeneralPath)gornjiLevi).closePath();

        gornjiDesni = new GeneralPath();
        ((GeneralPath)gornjiDesni).moveTo(rectangle.getPosition().x+(rectangle.getSize().width)-5,rectangle.getPosition().y);

        ((GeneralPath)gornjiDesni).lineTo(rectangle.getPosition().x+(rectangle.getSize().width)-5,rectangle.getPosition().y+5);

        ((GeneralPath)gornjiDesni).lineTo(rectangle.getPosition().x+(rectangle.getSize().width),rectangle.getPosition().getY() + 5);

        ((GeneralPath)gornjiDesni).lineTo(rectangle.getPosition().x+(rectangle.getSize().width),rectangle.getPosition().y);

        ((GeneralPath)gornjiDesni).closePath();

        donjiDesni = new GeneralPath();
        ((GeneralPath)donjiDesni).moveTo(rectangle.getPosition().x+(rectangle.getSize().width)-5,rectangle.getPosition().y+rectangle.getSize().height-5);

        ((GeneralPath)donjiDesni).lineTo(rectangle.getPosition().x+(rectangle.getSize().width)-5,rectangle.getPosition().y+rectangle.getSize().height);

        ((GeneralPath)donjiDesni).lineTo(rectangle.getPosition().x+(rectangle.getSize().width),rectangle.getPosition().getY()+rectangle.getSize().height);

        ((GeneralPath)donjiDesni).lineTo(rectangle.getPosition().x+(rectangle.getSize().width),rectangle.getPosition().y+rectangle.getSize().height-5);

        ((GeneralPath)donjiDesni).closePath();

        donjiiLevi = new GeneralPath();
        ((GeneralPath)donjiiLevi).moveTo(rectangle.getPosition().x,rectangle.getPosition().y+rectangle.getSize().height-5);

        ((GeneralPath)donjiiLevi).lineTo(rectangle.getPosition().x,rectangle.getPosition().y+rectangle.getSize().height);

        ((GeneralPath)donjiiLevi).lineTo(rectangle.getPosition().x+5,rectangle.getPosition().getY()+rectangle.getSize().height);

        ((GeneralPath)donjiiLevi).lineTo(rectangle.getPosition().x+5,rectangle.getPosition().y+rectangle.getSize().height-5);

        ((GeneralPath)donjiiLevi).closePath();
    }
}
