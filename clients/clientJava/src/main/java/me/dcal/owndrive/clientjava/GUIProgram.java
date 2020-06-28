package me.dcal.owndrive.clientjava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GUIProgram extends JFrame {

    private JList liste = null;
    public GUIProgram(client main) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Choose file");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JPanel paneloffile = new JPanel(); // the panel is not visible in output
        //JTextField tf = new JTextField(10); // accepts upto 10 characters

        List<String> files = update_file_list(main);
        liste = new JList(files.toArray());

        liste.addListSelectionListener(evt -> {
            System.out.println("\nOutput: \n" + liste.getSelectedValue());
            main.download(liste.getSelectedValue()+"");
        });

        m11.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(null);
                main.upload(fc.getSelectedFile());
                DefaultListModel listModel = (DefaultListModel)liste.getModel();
                listModel.clear();
                List<String> file = update_file_list(main);
                for(String fileName : file)
                {
                    listModel.addElement(fileName);

                }
            }
        });

        //Adding Components to the frame.
        this.getContentPane().add(BorderLayout.SOUTH, panel);
        this.getContentPane().add(BorderLayout.NORTH, mb);
        this.getContentPane().add(BorderLayout.CENTER, new JScrollPane(liste));
        this.setVisible(true);
    }

    public static List<String> update_file_list(client main){
        String repertoire = main.callURL("http://127.0.0.1:8080/filename");
        String []files = repertoire.substring(1,repertoire.length()-1).split(",");

        ArrayList<String> name_file = new ArrayList<>();
        int i =0 ;
        for (String strfile : files){
            File file= new File(strfile.substring(1, strfile.length()-1));
            String fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('/') + 1, file.getAbsolutePath().length());
            name_file.add(fileName);
            i++;
        }
        return name_file;
    }
    public static void main(String[] args){

        client main = new client();
        new GUIProgram(main);
    }
}
