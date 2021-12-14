package dsw.gerudok.app.gui.swing.controller;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.gui.swing.view.IzaberiFrame;
import dsw.gerudok.app.gui.swing.view.MainFrame;
import dsw.gerudok.app.gui.swing.view.SlikaFrame;
import dsw.gerudok.app.gui.swing.view.TextFrame;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Sadrzaj;
import dsw.gerudok.app.repository.Slot;
import dsw.gerudok.app.repository.node.RuNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SlotAction extends AbstractRudokAction{

    public SlotAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_Z);
        putValue(SMALL_ICON, loadIcon("images/slot.png"));
        putValue(NAME, "Open slot");
        putValue(SHORT_DESCRIPTION, "Open slot");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Page page = null;
        RuNode selektovani = null;
        try{
          selektovani = MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
        }
        catch (NullPointerException exception){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.NESELEKTOVAN_SLOT);
            return;
        }
        if(selektovani instanceof Page) {
            page = (Page) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
        }else{
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.NESELEKTOVAN_SLOT);
            return;
        }
        ArrayList<Sadrzaj> ucitaniSlotovi = new ArrayList<>();
        FileReader fr= null;
        try {
            fr = new FileReader("D://Downloads//dsw2020-tim_urosnikolic_nikolatrajkovic//src//dsw//gerudok//app//slot.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        try {
            Scanner s = new Scanner(fr);
            if(s.nextLine().equals(null)){
                IzaberiFrame iz1 = new IzaberiFrame();
            }
            else{
                while(s.hasNextLine())
                {
                    String[] split = s.nextLine().split(";");

                    ucitaniSlotovi.add(new Sadrzaj(split[0],split[1],split[2]));
                }
            }

            s.close();
            fr.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        int provera = 0;
        int i = 1;
        Slot slot = page.getPrethodni();

        if(page.getSelektovani().size() != 0){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.VISE_SELEKTOVANI_SLOTOVI);
            return;
        }
        if(page.getSelektovani().size() == 0 && page.getPrethodni() !=null){//jedan selektovan

            for(Sadrzaj sad: ucitaniSlotovi){
                if(slot.getName().equals(sad.getName())){
                    if(sad.getTip().equals("t")){
                        TextFrame tf = new TextFrame();
                        tf.getTextPane().setText(sad.getSadrzaj());
                        tf.setName(sad.getName());
                        return;
                    }
                    if(sad.getTip().equals("s")){
                        SlikaFrame sf = new SlikaFrame();
                        sf.getSlika().setIcon(new ImageIcon(sad.getSadrzaj()));
                        sf.getjFileChooser().setVisible(false);
                        return;
                    }
                }

            }
            IzaberiFrame iz = new IzaberiFrame();
            return;


        }else{
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.NESELEKTOVAN_SLOT);
        }


    }
}
