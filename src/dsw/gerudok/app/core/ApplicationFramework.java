package dsw.gerudok.app.core;

import dsw.gerudok.app.gui.swing.command.commandObs.CommandSubscriber;

public abstract class ApplicationFramework {

    protected Gui gui;
    protected Repository repository;
    private IError errorHandler;
    private IUndoRedo undoRedo;

    public abstract void run();
    public void initialise(Gui gui, Repository repository,IError errorHandler, IUndoRedo undoRedo){
        this.gui = gui;
        this.repository = repository;
        this.errorHandler = errorHandler;
        this.undoRedo = undoRedo;
        errorHandler.addErrorSub(gui);
        undoRedo.addCommandSub((CommandSubscriber) gui);
    }

    public Gui getGui() {
        return gui;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public IError getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(IError errorHandler) {
        this.errorHandler = errorHandler;
    }

    public IUndoRedo getUndoRedo() {
        return undoRedo;
    }

    public void setUndoRedo(IUndoRedo undoRedo) {
        this.undoRedo = undoRedo;
    }
}
