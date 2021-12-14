package dsw.gerudok.app.core;

import dsw.gerudok.app.gui.swing.command.CommandType;
import dsw.gerudok.app.gui.swing.command.commandObs.CommandPublisher;

public interface IUndoRedo extends CommandPublisher {
    void napraviUndoRedo(CommandType commandType);
}
