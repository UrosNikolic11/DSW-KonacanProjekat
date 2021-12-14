package dsw.gerudok.app.repository;

import dsw.gerudok.app.gui.swing.observer.Subscriber;
import dsw.gerudok.app.repository.event.UpdateEvent;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class PageModel {
    private static int count=0;
    private String name;

    protected ArrayList<Slot> slotovi =new ArrayList<Slot>();
    EventListenerList listenerList = new EventListenerList();
    UpdateEvent updateEvent = null;


    public int getDeviceCount(){
        return slotovi.size();
    }

    public Slot getDeviceAt(int i){
        return slotovi.get(i);
    }

    public static int getCount() {
        return count;
    }


    public static void setCount(int count) {
        PageModel.count = count;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public int getElementCount(){
        return slotovi.size();
    }

    public Iterator<Slot> getDeviceIterator(){
        return slotovi.iterator();
    }

    public void addDiagramElements(Slot device){
        slotovi.add(device);
        fireUpdatePerformed();
    }

    public void removeDiagramElements(Slot device){
        slotovi.remove(device);
        fireUpdatePerformed();
    }

    public void addUpdateListener(Subscriber l) {
        listenerList.add(Subscriber.class, l);
    }

    public void removeUpdateListener(Subscriber l) {
        listenerList.remove(Subscriber.class, l);
    }

    public void fireUpdatePerformed() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length-1; i>=0; i-=1) {
            if (listeners[i]==Subscriber.class) {
                if (updateEvent == null)
                    updateEvent = new UpdateEvent(this);
                ((Subscriber)listeners[i+1]).updatePreformed(updateEvent);
            }
        }
    }

    public ArrayList<Slot> getSlotovi() {
        return slotovi;
    }

    public void setSlotovi(ArrayList<Slot> slotovi) {
        this.slotovi = slotovi;
    }
}
