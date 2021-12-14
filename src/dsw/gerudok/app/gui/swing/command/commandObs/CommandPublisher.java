package dsw.gerudok.app.gui.swing.command.commandObs;

import dsw.gerudok.app.gui.swing.command.AddDeviceCommand;
import dsw.gerudok.app.gui.swing.command.CommandType;

public interface CommandPublisher {
    void addCommandSub(CommandSubscriber command);
    void notifyCommand(CommandType commandType);
}
