package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.gui.swing.observer.Publisher;
import dsw.gerudok.app.gui.swing.observer.Subscriber;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.event.UpdateEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentView extends JPanel implements Subscriber {

    private Document document;
    private List<PageView> listaPageView;

    public DocumentView(Document document){
        this.document = document;
        this.document.addSubscriber(this);
        listaPageView = new ArrayList<>();
        this.setLayout(new GridLayout(1,2));
    }

    @Override
    public void update(Publisher publisher) {
        PageView pageView = new PageView((Page)publisher);
        listaPageView.add(pageView);
        this.add(pageView);
        updateUI();
    }
    public  void brisanjePage(Publisher publisher){
        for(PageView pw: listaPageView){
            if(pw.getPage().equals((Page)publisher)){
                this.remove(pw);
                updateUI();
                listaPageView.remove(pw);
                return;
            }
        }
    }
    public  void rename(Publisher publisher){
        for(PageView pw: listaPageView){
            if(pw.getPage().equals((Page)publisher)){
                pw.getPageName().setText(pw.getPage().getName());
                return;
            }
        }
    }

    @Override
    public void brisanjeDokumenta(Publisher publisher) {

    }

    @Override
    public void updatePreformed(UpdateEvent updateEvent) {

    }

    public Document getDocument() {
        return document;
    }
}
