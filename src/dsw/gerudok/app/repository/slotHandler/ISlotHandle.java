package dsw.gerudok.app.repository.slotHandler;

import dsw.gerudok.app.repository.Page;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface ISlotHandle {
     void movePressed(MouseEvent e, Page med);
     void moveDragged(MouseEvent e,Page med);
     void moveRelesed(MouseEvent e, Page med);
     void deletePresses(MouseEvent e, Page med);
     void selectPressed(MouseEvent e , Page med);
     void resizePressed(MouseEvent e, Page med);
     void resizeDragged(MouseEvent e, Page med);
     void resizeReleased(MouseEvent e , Page med);
     void rotatePressed(MouseEvent e, Page med);
     void rotateDragged(MouseEvent e, Page med);
     void rotateReleased(MouseEvent e, Page med);
     void circlePressed(MouseEvent e, Page med);
     void trianglePresed(MouseEvent e, Page med);
     void rectanglePressed(MouseEvent e, Page med);
     void selectDragged(MouseEvent e, Page med);
     void selectRelesed(MouseEvent e, Page med);
}
