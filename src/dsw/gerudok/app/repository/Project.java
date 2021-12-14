package dsw.gerudok.app.repository;

import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

import java.util.List;

public class Project extends RuNodeComposite {


    public Project(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Document){
            Document dokument = (Document) child;
            if (!this.getChildren().contains(dokument)){
                this.getChildren().add(dokument);
                notifySubscriber(child);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child != null && child instanceof Document){
            this.getChildren().remove(child);
            this.brisanje(child);
        }
    }


}
