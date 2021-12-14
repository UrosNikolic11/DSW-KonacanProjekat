package dsw.gerudok.app.repository.state;

import dsw.gerudok.app.gui.swing.observer.Subscriber;
import dsw.gerudok.app.gui.swing.view.ElementPainter;
import dsw.gerudok.app.gui.swing.view.PageView;
import dsw.gerudok.app.gui.swing.view.RectanglePainter;
import dsw.gerudok.app.gui.swing.view.SlotPainter;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.SLotRectangle;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.slotHandler.SlotHandler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class SelectState extends State{
    private Page med;
    SlotHandler slotHandler;
    public SelectState(Page md) {
        med = md;
        slotHandler = new SlotHandler();
    }


    public void mousePressed(MouseEvent e) {
        slotHandler.selectPressed(e,med);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        slotHandler.selectDragged(e, med);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        slotHandler.selectRelesed(e, med);
    }
}