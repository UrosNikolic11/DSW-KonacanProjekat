package dsw.gerudok.app.repository;

import dsw.gerudok.app.repository.node.RuNode;
import dsw.gerudok.app.repository.node.RuNodeComposite;

import java.util.List;

public class Workspace extends RuNodeComposite {

    public Workspace(String name) {
        super(name, null);
    }



    @Override
    public void addChild(RuNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }


        @Override
        public void removeChild(RuNode child) {
            if(child != null && child instanceof Project){
                this.getChildren().remove(child);
                this.notifySubscriber(child);
            }
        }
    }

