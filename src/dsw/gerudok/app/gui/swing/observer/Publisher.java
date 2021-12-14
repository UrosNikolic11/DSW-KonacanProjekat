package dsw.gerudok.app.gui.swing.observer;

import dsw.gerudok.app.gui.swing.view.MainFrame;

public interface Publisher {
    void addSubscriber(Subscriber s);
    void notifySubscriber(Publisher publisher);
    void dvoklik(Publisher publisher);
    void brisanje(Publisher publisher);
    void rename(Publisher publisher);
    void notifySubscriber2();
}
