package dsw.gerudok.app.gui.swing.command;

import com.sun.tools.javac.Main;
import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.view.MainFrame;

import java.util.ArrayList;

public class CommandManager {
    //lista koja predstavlja stek na kome se nalaze konkretne izvršene komande
    private ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>();
    //pokazivač steka, sadrži redni broj komande za undo / redo operaciju
    private int currentCommand = 0;
    private static int prethodni =0;
    //private int slotType = 0;

    /*
     * Dodaje novu komandu na stek i poziva izvršavanje komande
     */
    public void addCommand(AbstractCommand command,int slotType){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);

        if(prethodni ==0){
            commands.add(command);
            prethodni = slotType;
            doCommand();
            return;
        }
        if(prethodni == slotType){
            commands.add(command);
        }else {
            commands.clear();
            commands.add(command);
            currentCommand = 0;
            prethodni = slotType;
        }
        //commands.add(command);
        doCommand();
    }

    /*
     * Metoda koja poziva izvršavanje konkretne komande
     */
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            AppCore.getInstance().getUndoRedo().napraviUndoRedo(CommandType.UNDO_TRUE);
        }
        if(currentCommand==commands.size()){
            AppCore.getInstance().getUndoRedo().napraviUndoRedo(CommandType.REDOFALSE);
        }
    }

    /*
     * Metoda koja poziva redo konkretne komande
     */
    public void undoCommand(){
        if(currentCommand > 0){
            AppCore.getInstance().getUndoRedo().napraviUndoRedo(CommandType.REDO_TRUE);
            commands.get(--currentCommand).undoCommand();
        }
        if(currentCommand==0){
            AppCore.getInstance().getUndoRedo().napraviUndoRedo(CommandType.UNDO_FALSE);
            //MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
        }
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<AbstractCommand> commands) {
        this.commands = commands;
    }

    public int getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(int currentCommand) {
        this.currentCommand = currentCommand;
    }
}
