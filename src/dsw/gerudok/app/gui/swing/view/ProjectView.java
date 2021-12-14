package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.gui.swing.observer.Publisher;
import dsw.gerudok.app.gui.swing.observer.Subscriber;
import dsw.gerudok.app.gui.swing.tree.model.RuTreeItem;
import dsw.gerudok.app.repository.Document;
import dsw.gerudok.app.repository.Project;
import dsw.gerudok.app.repository.event.UpdateEvent;

import java.util.ArrayList;
import java.util.List;

public class ProjectView implements Subscriber {
    private Project project;
    private List<DocumentView> listaDocView;

    public ProjectView(Project project) {
        this.project = project;
        this.project.addSubscriber(this);
        listaDocView = new ArrayList<>();
    }

    public void  dodajDesno(DocumentView documentView){

        listaDocView.add(documentView);
        if(project.getName().equals(MainPanel.getMainPanel().getNaslov().getText())){
            for(DocumentView dw: listaDocView){
                MainPanel.getMainPanel().getTabbedPane().addTab(dw.getDocument().getName(),dw);
            }
        }
    }
    @Override
    public void update(Publisher publisher) {
        if(publisher instanceof  Document){
            DocumentView documentView = new DocumentView((Document)publisher);
            listaDocView.add(documentView);
            if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel().getName().equals(MainPanel.getMainPanel().getNaslov().getText())){
                MainPanel.getMainPanel().getTabbedPane().addTab(((Document) publisher).getName(),documentView);
            }
        }
        if(publisher instanceof Project){
            if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel().getName().equals(MainPanel.getMainPanel().getNaslov().getText())){
                MainPanel.getMainPanel().getNaslov().setText("");
                MainPanel.getMainPanel().getTabbedPane().removeAll();
                MainPanel.getMainPanel().getNaslov().updateUI();
            }
        }

    }

    @Override
    public void brisanjePage(Publisher publisher) {

    }


    public void rename(Publisher publisher){
        if(publisher instanceof Project){
            MainPanel.getMainPanel().getNaslov().setText(((Project)publisher).getName());
        }
        if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel().getParent().getName().equals(MainPanel.getMainPanel().getNaslov().getText())){
            if(publisher instanceof Document){
                int i = 0;
                for(DocumentView dw: listaDocView){
                    if(dw.getDocument().equals((Document)publisher)){
                        MainPanel.getMainPanel().getTabbedPane().setTitleAt(i,((Document)publisher).getName());
                        return;
                    }
                    i++;
                }
            }
        }
        if(publisher instanceof Document){
            int i = 0;
            if(((Document)publisher).getParent().getName().equals(MainPanel.getMainPanel().getNaslov().getText())) {


                for (DocumentView dw : listaDocView) {
                    if (dw.getDocument().equals((Document) publisher)) {
                        MainPanel.getMainPanel().getTabbedPane().setTitleAt(i, ((Document) publisher).getName());
                        return;
                    }
                    i++;
                }
            }
        }
    }

    @Override
    public void brisanjeDokumenta(Publisher publisher) {
        RuTreeItem projekat = (RuTreeItem) MainFrame.getInstance().getTree().getSelectedNode().getParent();
        for(DocumentView dw: listaDocView){
            System.out.println(projekat.getName());
            if(dw.getDocument().equals((Document)publisher)){
                if(projekat.getName().equals(MainPanel.getMainPanel().getNaslov().getText())){
                    MainPanel.getMainPanel().getTabbedPane().remove(dw);
                    MainPanel.getMainPanel().getTabbedPane().updateUI();
                }
                else if(this.project.getName().equals(MainPanel.getMainPanel().getNaslov().getText())){
                    MainPanel.getMainPanel().getTabbedPane().remove(dw);
                    MainPanel.getMainPanel().getTabbedPane().updateUI();
                }


                listaDocView.remove(dw);
                return;
            }
        }
    }

    @Override
    public void updatePreformed(UpdateEvent updateEvent) {

    }

    public void dvoklik2(){
        MainPanel.getMainPanel().getTabbedPane().removeAll();
        MainPanel.getMainPanel().getNaslov().setText("");
        for(DocumentView documentView: listaDocView){
            MainPanel.getMainPanel().getTabbedPane().addTab(documentView.getDocument().getName(),documentView);
        }
        MainPanel.getMainPanel().getNaslov().setText(this.project.getName());
    }

    public Project getProject() {
        return project;
    }
}
