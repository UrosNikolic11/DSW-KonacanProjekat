package dsw.gerudok.app.gui.swing.view;

import dsw.gerudok.app.repository.Page;
import dsw.gerudok.app.repository.Sadrzaj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SlikaFrame extends JDialog {
    private String name;
    String name11;
    private JPanel panel;
    private JLabel lbl;
    private JLabel slika;
    private JFileChooser jFileChooser;
    public SlikaFrame(){
        super();
        initElements();
        addElements();
    }

    private  void initElements(){
        Toolkit kit= Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHight / 2);
        setLocationRelativeTo(null);
        setTitle("Sadrzaj slota - slika");
        setVisible(true);
        lbl = new JLabel("Izaberi sliku");
        jFileChooser = new JFileChooser();
        jFileChooser.addActionListener(new BirajSliku());
        slika = new JLabel();
        panel = new JPanel();
        panel.add(lbl);
        panel.add(jFileChooser);
        panel.add(slika);

    }

    private void addElements() {

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.add(panel);
    }
    private class BirajSliku implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("ApproveSelection")){

                if(jFileChooser.getSelectedFile().getAbsolutePath().endsWith(".png") || jFileChooser.getSelectedFile().getAbsolutePath().endsWith(".jpg")){

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
                    //
                    Page page = null;
                    if(MainFrame.getInstance().getTree().getSelectedNode().getNodeModel() instanceof Page) {
                        page = (Page) MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
                    }

                    if(page.getPrethodni().getPaint().equals(Color.BLACK)){
                        proba(page.getPrethodni().getName());
                    }
                    int provera = 0;
                    String string = geter();
                    for(Sadrzaj sad: ucitaniSlotovi){
                        if(string.equals(sad.getName())){
                            sad.setSadrzaj(jFileChooser.getSelectedFile().getAbsolutePath());
                            provera = 1;
                        }
                    }
                    if(provera == 0){
                        ucitaniSlotovi.add(new Sadrzaj(geter(),"s",jFileChooser.getSelectedFile().getAbsolutePath()));
                    }
                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter("D://Downloads//dsw2020-tim_urosnikolic_nikolatrajkovic//src//dsw//gerudok//app//slot.txt");
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    for(Sadrzaj s: ucitaniSlotovi){
                        printWriter.println(s.getName() + ";" + s.getTip()+";" + s.getSadrzaj()+";");
                    }

                    printWriter.close();

                    //close();
                }
                jFileChooser.setVisible(false);
                ImageIcon ic = new ImageIcon(jFileChooser.getSelectedFile().getAbsolutePath());
                slika.setIcon(ic);
                //fun(jFileChooser.getSelectedFile().getAbsolutePath());
//            else{
//                AppCore.getInstance().getErrHandler().generateError(ErrorTypes.ACTIONERROR);
//            }

            }
            else if(e.getActionCommand().equals("CancelSelection")){
                //close();
                IzaberiFrame iz = new IzaberiFrame();
            }
        }

    }

    public void proba(String name) {
        this.setName(name);
    }
    public String geter(){
        return this.getName();
    }
    public void fun(String putanja){
        jFileChooser.setVisible(false);
        ImageIcon ic = new ImageIcon(this.getClass().getResource(putanja));
        slika.setIcon(ic);
        panel.add(slika);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getName11() {
        return name11;
    }

    public void setName11(String name11) {
        this.name11 = name11;
    }

    public JLabel getSlika() {
        return slika;
    }

    public void setSlika(JLabel slika) {
        this.slika = slika;
    }

    public JFileChooser getjFileChooser() {
        return jFileChooser;
    }

    public void setjFileChooser(JFileChooser jFileChooser) {
        this.jFileChooser = jFileChooser;
    }
}
