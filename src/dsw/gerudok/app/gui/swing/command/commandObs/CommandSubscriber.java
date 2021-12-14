package dsw.gerudok.app.gui.swing.command.commandObs;

import dsw.gerudok.app.gui.swing.command.AddDeviceCommand;
import dsw.gerudok.app.gui.swing.command.CommandType;

public interface CommandSubscriber {
    void updateCommand(CommandType commandType);
}
