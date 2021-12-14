package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.AppCore;
import dsw.gerudok.app.gui.swing.error.ErrorType;
import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Sadrzaj;
import dsw.gerudok.app.repository.Slot;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFrame extends JDialog {
    private String name;
    private JLabel text;
    private JTextPane textPane;
    private JButton italic;
    private JButton bold;
    private JButton underline;
    private JButton save;
    private JPanel panel;

    public TextFrame(){
        super();
        initElements();
        addElements();
    }

    private  void initElements(){
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 5, screenHight / 5);
        setLocationRelativeTo(null);
        setTitle("Sadrzaj slota - text");
        setVisible(true);
        text = new JLabel("");
        textPane = new JTextPane();
        italic = new JButton("Italic");
        italic.addActionListener(new italicButton());
        bold = new JButton("Bold");
        bold.addActionListener(new boldButton());
        underline = new JButton("underline");
        underline.addActionListener(new unButton());
        panel = new JPanel();
        save = new JButton("save");
        save.addActionListener(new DugmeSave());
    }

    private class italicButton extends StyledEditorKit.ItalicAction{

    }
    private class boldButton extends  StyledEditorKit.BoldAction{

    }
    private class unButton extends  StyledEditorKit.UnderlineAction{

    }

    private class DugmeSave implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            saveAction();
        }
    }
    private void addElements() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(text);
        panel.add(textPane);
        panel.add(italic);
        panel.add(bold);
        panel.add(underline);
        panel.add(save);
        this.add(panel);
    }

    public void saveAction(){
        ArrayList<Sadrzaj> ucitaniSlotovi = new ArrayList<>();
        FileReader fr= null;
        try {
            fr = new FileReader("D://Downloads//dsw2020-tim_urosnikolic_nikolatrajkovic//src//dsw//gerudok//app//slot.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        try {
            Scanner s = new Scanner(fr);
            while(s.hasNextLine())
            {
                String[] split = s.nextLine().split(";");

                ucitaniSlotovi.add(new Sadrzaj(split[0],split[1],split[2]));
            }
            s.close();
            fr.close();
        }
        catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Page page = null;
        if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page) {
            page = (Page) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
        }

        if(page.getPrethodni().getPaint().equals(Color.BLACK)){
            this.setName(page.getPrethodni().getName());
        }
        int provera = 0;
        System.out.println(this.getName() + " " );
        String t = this.getName();
        for(Sadrzaj sad: ucitaniSlotovi){
            if(t.equals(sad.getName())){
                sad.setSadrzaj(textPane.getText());
                provera = 1;
            }
        }
        if(provera == 0){
            ucitaniSlotovi.add(new Sadrzaj(this.getName(),"t",textPane.getText()));
        }
        if(textPane.getText().isEmpty()){
            AppCore.getInstance().getErrorHandler().napraviGresku(ErrorType.PRAZNI_TEXT_U_SLOTU);
            return;
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter("D://Downloads//dsw2020-tim_urosnikolic_nikolatrajkovic//src//dsw//gerudok//app//slot.txt");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        PrintWriter pw = new PrintWriter(fw);
        for(Sadrzaj s: ucitaniSlotovi){
            pw.println(s.getName() + ";" + s.getTip()+";" + s.getSadrzaj()+";");
        }

        pw.close();
        this.dispose();
    }

    public JLabel getText() {
        return text;
    }

    public void setText(JLabel text) {
        this.text = text;
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public void setTextPane(JTextPane textPane) {
        this.textPane = textPane;
    }


    public JButton getItalic() {
        return italic;
    }

    public void setItalic(JButton italic) {
        this.italic = italic;
    }

    public JButton getBold() {
        return bold;
    }

    public void setBold(JButton bold) {
        this.bold = bold;
    }

    public JButton getUnderline() {
        return underline;
    }

    public void setUnderline(JButton underline) {
        this.underline = underline;
    }

    public JButton getSave() {
        return save;
    }

    public void setSave(JButton save) {
        this.save = save;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
