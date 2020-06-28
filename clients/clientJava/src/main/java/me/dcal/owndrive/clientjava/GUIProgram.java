package me.dcal.owndrive.clientjava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GUIProgram extends JFrame {

    private static JList liste = null;
    private static JTextArea tf = null;
    private static client main =null;
    Vector filesvect =null;

    public GUIProgram() {

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
        //JPanel paneloffile = new JPanel(); // the panel is not visible in output
        panel.setVisible(true);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(this.getWidth()-panel.getWidth(), this.getHeight()-panel.getHeight()));
        tf = new JTextArea(); // accepts upto 10 characters


        JButton savebtn = new JButton("Save");
        JCheckBox savechk = new JCheckBox("Auto Save");

        savebtn.setVisible(true);
        savechk.setVisible(true);

        tf.setVisible(true);
        tf.setEditable(true);
        tf.setFocusable(true);



        filesvect = new Vector();
        for( String file: update_file_list()){
            filesvect.addElement(file);
        }

        liste = new JList(filesvect);


        liste.addMouseListener( new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                if ( SwingUtilities.isRightMouseButton(e) )
                {
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem item = new JMenuItem("Download");
                    JMenuItem item2 = new JMenuItem("Consult");
                    JMenuItem item3 = new JMenuItem("Share");
                    JMenuItem item4 = new JMenuItem("Rename");
                    JMenuItem item5 = new JMenuItem("delete");

                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            //JOptionPane.showMessageDialog(GUIProgram, "Hello "
                                    //+ liste.getSelectedValue());
                            main.download(liste.getSelectedValue()+"");
                        }
                    });
                    item2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            tf.setText(main.consult(liste.getSelectedValue()+""));
                            tf.repaint();
                        }
                    });
                    item4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JPopupMenu menu = new JPopupMenu();
                            JTextField rename = new JTextField(5);
                            JButton send = new JButton("Save");
                            rename.setText(liste.getSelectedValue()+"");
                            menu.add(rename);
                            menu.add(send);

                            send.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    rename_file(rename.getText());
                                    filesvect.remove(liste.getSelectedValue());
                                    filesvect.addElement(rename.getText());
                                    menu.setVisible(false);
                                }
                            });

                            menu.show(liste, 5, liste.getCellBounds(
                                    liste.getSelectedIndex() + 1,
                                    liste.getSelectedIndex() + 1).y);

                        }
                    });
                    menu.add(item);
                    menu.add(item2);
                    menu.add(item3);
                    menu.add(item4);
                    menu.add(item5);

                    menu.show(liste, 5, liste.getCellBounds(
                            liste.getSelectedIndex() + 1,
                            liste.getSelectedIndex() + 1).y);
                }
            }
        });

        savebtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                save_file();
            }
        });

        Thread t = new Thread() {
            public void run() {
                try {
                    Thread.sleep(60 * 1000);
                    save_file();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        savechk.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (savechk.isSelected()){
                    while(true){
                        t.start();
                    }
                }
            }
        });


        m11.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final JFileChooser fc = new JFileChooser();
                fc.showSaveDialog(null);
                main.upload(fc.getSelectedFile());
                filesvect.addElement(fc.getSelectedFile());
            }
        });


        JScrollPane scroll = new JScrollPane(tf);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll);
        panel.add(savebtn, BorderLayout.SOUTH);
        panel.add(savechk,  BorderLayout.NORTH);


        //Adding Components to the frame.
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.getContentPane().add(BorderLayout.NORTH, mb);
        this.getContentPane().add(BorderLayout.WEST, new JScrollPane(liste));
        this.setVisible(true);
    }

    public static void rename_file(String newfilename){
        main.rename(newfilename, liste.getSelectedValue()+"");
    }

    public static void save_file(){
        try {
            File temp = new File(liste.getSelectedValue()+"");

            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            out.write(tf.getText());
            out.flush();
            out.close();
            main.upload(temp);
            temp.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> update_file_list(){
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

        main = new client();
        new GUIProgram();
    }
}
