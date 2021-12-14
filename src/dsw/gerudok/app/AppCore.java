package dsw.gerudok.app;

import dsw.gerudok.app.core.*;
import dsw.gerudok.app.gui.swing.SwingGui;
import dsw.gerudok.app.gui.swing.command.CommandImplementation;
import dsw.gerudok.app.gui.swing.error.ErrorImplementation;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.repository.RepositoryImpl;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;

    private AppCore(){

    }

    public static AppCore getInstance(){
        if(instance==null){
            instance = new AppCore();
        }
        return instance;
    }


    public void run(){
        this.gui.start();
    }

    public static void main(String[] args) {
        Repository repository = (Repository) new RepositoryImpl();
        Gui gui = new SwingGui(repository);
        IError error = new ErrorImplementation();
        IUndoRedo undoRedo = new CommandImplementation();
        ApplicationFramework appCore = AppCore.getInstance();
        appCore.initialise(gui, repository,error,undoRedo);
        appCore.run();

    }
}