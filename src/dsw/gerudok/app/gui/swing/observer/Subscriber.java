package dsw.gerudok.app.gui.swing.observer;

import dsw.gerudok.app.repository.event.UpdateEvent;

import java.util.EventListener;

public interface Subscriber extends EventListener {
    void update(Publisher publisher);
    void brisanjePage(Publisher publisher);
    void rename(Publisher publisher);
    void brisanjeDokumenta(Publisher publisher);
    void updatePreformed(UpdateEvent updateEvent);
}
