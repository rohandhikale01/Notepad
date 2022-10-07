import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class notePad extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenu help = new JMenu("Help");
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem exit = new JMenuItem("Exit");
    JMenuItem print = new JMenuItem("Print");

    JMenuItem cut = new JMenuItem("Cut");
    JMenuItem Copy = new JMenuItem("Copy");
    JMenuItem paste = new JMenuItem("Paste");
    JMenuItem selectAll = new JMenuItem("Select All");

    JMenuItem about = new JMenuItem("About");

    JTextArea textarea = new JTextArea();

    notePad() {
        setTitle("Notepad");
        setBounds(300, 150, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("npad.jpg"));
        setIconImage(icon.getImage());

        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        file.add(newFile);
        file.add(openFile);
        file.add(save);
        file.add(exit);
        file.add(print);

        edit.add(cut);
        edit.add(Copy);
        edit.add(paste);
        edit.add(selectAll);

        help.add(about);

        JScrollPane pane = new JScrollPane(textarea);

        textarea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        add(pane);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        print.addActionListener(this);
        cut.addActionListener(this);
        Copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        about.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equalsIgnoreCase("New")) {
            textarea.setText(null);

        } else if (e.getActionCommand().equalsIgnoreCase("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);
            int action = fileChooser.showSaveDialog(null);
            System.out.println(action);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                String fileName = fileChooser.getSelectedFile().getAbsolutePath().toString();
                if (!fileName.contains(".text")) {
                    fileName = fileName + ".text";
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

                    textarea.write(writer);
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }

        } else if (e.getActionCommand().equalsIgnoreCase("open")) {

            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter textFilter = new FileNameExtensionFilter("only Text Files(.txt)", "txt");
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.addChoosableFileFilter(textFilter);

            int action = fileChooser.showOpenDialog(null);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            } else {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));

                    textarea.read(br, null);
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }

        } else if (e.getActionCommand().equalsIgnoreCase("Print")) {
            try {
                textarea.print();
            } catch (PrinterException e1) {
                e1.printStackTrace();
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Exit")) {
            System.exit(0);

        } else if (e.getActionCommand().equalsIgnoreCase("Cut")) {
            textarea.cut();

        } else if (e.getActionCommand().equalsIgnoreCase("Copy")) {
            textarea.copy();
        } else if (e.getActionCommand().equalsIgnoreCase("Paste")) {
            textarea.paste();

        } else if (e.getActionCommand().equalsIgnoreCase("Select All")) {
            textarea.selectAll();

        } else if (e.getActionCommand().equalsIgnoreCase("About")) {
           // new about();

        }

    }
    public static void main(String[] args) {
        new notePad();
    }

}