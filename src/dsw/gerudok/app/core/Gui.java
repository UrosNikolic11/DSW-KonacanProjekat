package dsw.gerudok.app.core;

import dsw.gerudok.app.gui.swing.command.commandObs.CommandSubscriber;
import dsw.gerudok.app.gui.swing.error.errorObs.ErrorSubscriber;

public interface Gui extends ErrorSubscriber, CommandSubscriber {
    void start();
}
