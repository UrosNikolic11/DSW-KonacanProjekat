package dsw.gerudok.app.repository.node;

import dsw.gerudok.app.gui.swing.observer.Publisher;
import dsw.gerudok.app.gui.swing.observer.Subscriber;
import dsw.gerudok.app.gui.swing.view.DocumentView;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.ProjectView;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.Workspace;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements Publisher {

    private String name;
    private RuNode parent;
    private static List<Subscriber> listaSubscribera = new ArrayList<>();
    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof RuNode) {
            RuNode otherObj = (RuNode) obj;
            return this.getName().equals(otherObj.getName());
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public RuNode getParent() {
        return parent;
    }

    @Override
    public void rename(Publisher publisher) {
        if(publisher instanceof Workspace)
        {
            return;
        }
        for(Subscriber s: listaSubscribera){
            if(s instanceof ProjectView){
                if(this.getParent().equals(((ProjectView)s).getProject())){
                    ((ProjectView) s).rename(this);
                }
                else if(this.equals(((ProjectView)s).getProject())){
                    ((ProjectView) s).rename(this);
                }
            }
            if(s instanceof DocumentView){
                if(this.getParent().equals(((DocumentView)s).getDocument())){
                    ((DocumentView)s).rename(this);
                }
            }
        }
    }

    public void setName(String name) {
        this.name = name;
        this.rename(this);
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    @Override
    public void addSubscriber(Subscriber s) {
        listaSubscribera.add(s);
    }

    @Override
    public void notifySubscriber(Publisher publisher) {

        for(Subscriber subscriber: listaSubscribera){
            if(publisher instanceof Document){
                if(subscriber instanceof ProjectView){
                    if(((Document)publisher).getParent().equals(((ProjectView) subscriber).getProject())){
                        subscriber.update(publisher);
                        return;
                    }
                }
            }
            if(publisher instanceof Project)
            {
                if(subscriber instanceof ProjectView){
                    if(((Project)publisher).equals(((ProjectView) subscriber).getProject())){
                        subscriber.update(publisher);
                        return;
                    }
                }
                return;
            }
            if(subscriber instanceof DocumentView){
                if(publisher instanceof Page)
                {
                    if(((Page)publisher).getParent().equals(((DocumentView) subscriber).getDocument())){
                        subscriber.update(publisher);
                        return;
                    }
                }

            }
        }
    }

    @Override
    public void brisanje(Publisher publisher) {
        for(Subscriber s: listaSubscribera){
            if(publisher instanceof Document){
                if(s instanceof ProjectView){

                    s.brisanjeDokumenta(publisher);


                }
            }

            if(publisher instanceof Page){
                if(s instanceof DocumentView){
                    if(((DocumentView) s).getDocument().equals(((Page)publisher).getParent())){
                         s.brisanjePage(publisher);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void dvoklik(Publisher publisher) {
        for(Subscriber subscriber: listaSubscribera){
            if(subscriber instanceof ProjectView){
                if(((Project)publisher).equals(((ProjectView) subscriber).getProject())){
                    ((ProjectView) subscriber).dvoklik2();
                }
            }
        }
    }

    @Override
    public void notifySubscriber2() {
        DocumentView dw=null;
        for(Subscriber s: listaSubscribera){
            if(s instanceof DocumentView){
                if(((DocumentView)s).getDocument().equals(this)){
                    dw = (DocumentView) s;
                }
            }
        }
        for(Subscriber sub: listaSubscribera){
            if(sub instanceof  ProjectView){
                if(((ProjectView)sub).getProject().equals(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel())){
                    ((ProjectView) sub).dodajDesno(dw);
                }
            }
        }
    }

}

