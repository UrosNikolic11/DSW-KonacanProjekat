package dsw.gerudok.app.repository;

import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

import java.util.List;

public class Document extends RuNodeComposite {


    public Document(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Page){
            Page page = (Page) child;
            if (!this.getChildren().contains(page)){
                this.getChildren().add(page);
                this.notifySubscriber(child);
            }
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child != null && child instanceof Page){
            this.getChildren().remove(child);
            this.brisanje(child);
        }
    }
}
