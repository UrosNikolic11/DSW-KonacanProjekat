package dsw.gerudok.app.gui.swing.command;

import dsw.gerudok.app.core.IUndoRedo;
import dsw.gerudok.app.gui.swing.command.commandObs.CommandPublisher;
import dsw.gerudok.app.gui.swing.command.commandObs.CommandSubscriber;
import dsw.gerudok.app.gui.swing.error.Error;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.error.errorObs.ErrorSubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandImplementation implements IUndoRedo, CommandPublisher {

    private List<CommandSubscriber> subscribers= new ArrayList<>();

    @Override
    public void napraviUndoRedo(CommandType commandType) {

        if(commandType.equals(CommandType.REDO_TRUE)){
            notifyCommand(CommandType.REDO_TRUE);
        }else if(commandType.equals(CommandType.REDOFALSE)){
            notifyCommand(CommandType.REDOFALSE);
        }else if(commandType.equals(CommandType.UNDO_FALSE)){
            notifyCommand(CommandType.UNDO_FALSE);
        }else if(commandType.equals(CommandType.UNDO_TRUE)){
            notifyCommand(CommandType.UNDO_TRUE);
        }

    }

    @Override
    public void addCommandSub(CommandSubscriber command) {
        if(subscribers == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(command))
            return;
        this.subscribers.add(command);
    }

    @Override
    public void notifyCommand(CommandType commandType) {
        if(commandType == null || this.subscribers == null || this.subscribers.isEmpty()) {
            return;
        }
        for(CommandSubscriber listener : subscribers){
            listener.updateCommand(commandType);
        }
    }
}
