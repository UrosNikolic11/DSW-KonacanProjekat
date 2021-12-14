package dsw.gerudok.app.core;

import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.error.errorObs.ErrorPublisher;

public interface IError extends ErrorPublisher {
    void napraviGresku(ErrorType type);
}
