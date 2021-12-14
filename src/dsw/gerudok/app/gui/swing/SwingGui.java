package dsw.gerudok.app.gui.swing;

import dsw.gerudok.app.core.IError;
import dsw.gerudok.app.core.Repository;
import dsw.gerudok.app.gui.swing.command.AddDeviceCommand;
import dsw.gerudok.app.gui.swing.command.CommandType;
import dsw.gerudok.app.gui.swing.error.Error;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.core.Gui;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SwingGui implements Gui {

    private MainFrame instance;
    private Repository documentRepository;

    public SwingGui(Repository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setDocumentRepository(documentRepository);
        instance.initialiseWorkspaceTree();
        instance.setVisible(true);
        instance.getActionManager().getUndoAction().setEnabled(false);
        instance.getActionManager().getRedoAction().setEnabled(false);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D://Downloads//dsw2020-tim_urosnikolic_nikolatrajkovic//src//dsw//gerudok//app//slot.txt");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("1;1;1;");

        printWriter.close();

    }


    @Override
    public void updateError(Error error) {
        MainFrame.getInstance().napisiPoruku(error);
    }

    @Override
    public void updateCommand(CommandType commandType) {
        MainFrame.getInstance().undoRedo(commandType);
    }
}
