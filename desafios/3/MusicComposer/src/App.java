import org.jfugue.player.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class App {
    private JButton redondaButton;
    private JButton blancaButton;
    private JButton negraButton;
    private JButton corcheaButton;
    private JButton semicorcheaButton;
    private JButton fusaButton;
    private JButton semifusaButton;
    private JPanel pentagramPanel;
    private JTable pentagramTable;
    private JPanel mainPanel;
    private JPanel notesPanel;
    private JTextField melodyField;
    private JButton playButton;
    private JButton undoButton;
    private JButton resetButton;
    private JPanel pentagramMelody;
    private JTextField melodyComposerField;
    private JButton playButton1;
    private JPanel instructionsPanel;
    private List notes;
    private Map<PentagramPosition, String> melody;
    private List pentagramPositions;
    private Player player;

    // when a button is clicked, the corresponding letter that represent the note is attached to it
    private void addActionListener(JButton jButton, List list){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.add(e.getActionCommand());
            }
        });
    }

    // each letter of the american cipher referring a 'level' in the pentagram
    private void initializePentagramPositions(List pentagramPositions){
        pentagramPositions.add('C');
        pentagramPositions.add('D');
        pentagramPositions.add('E');
        pentagramPositions.add('F');
        pentagramPositions.add('G');
        pentagramPositions.add('A');
        pentagramPositions.add('B');
    }

    // set melody read-only field value according to pentagram
    public void fillMelodyField(){
        melodyField.setText(String.join(" ", melody.values()));
    }

    public App() {

        notes = new ArrayList();
        melody = new LinkedHashMap<>();
        pentagramPositions = new ArrayList();
        player = new Player();

        this.initializePentagramPositions(pentagramPositions);

        // setActionCommand establishes a name for the button, apart from the label
        redondaButton.setActionCommand("w");
        blancaButton.setActionCommand("h");
        negraButton.setActionCommand("q");
        corcheaButton.setActionCommand("i");
        semicorcheaButton.setActionCommand("s");
        fusaButton.setActionCommand("t");
        semifusaButton.setActionCommand("x");

        // adding click listener to the buttons that represent the musical notes
        this.addActionListener(redondaButton, notes);
        this.addActionListener(blancaButton, notes);
        this.addActionListener(negraButton, notes);
        this.addActionListener(corcheaButton, notes);
        this.addActionListener(semicorcheaButton, notes);
        this.addActionListener(fusaButton, notes);
        this.addActionListener(semifusaButton, notes);

        // handling the pentagram
        pentagramTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!notes.isEmpty()) {
                    JTable target = (JTable) e.getSource();
                    setCellValue(target, target.getSelectedColumn(), target.getSelectedRow());
                    melody.put(new PentagramPosition(target.getSelectedRow(), target.getSelectedColumn()), pentagramPositions.get(target.getSelectedRow()).toString() + notes.get(notes.size() - 1).toString());
                    fillMelodyField();
                }
            }
        });

        //  player buttons listeners

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.play(String.join(" ", melody.values()));
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!melody.isEmpty()) {
                    List<Map.Entry<PentagramPosition, String>> entryList = new ArrayList<Map.Entry<PentagramPosition, String>>(melody.entrySet());
                    Map.Entry<PentagramPosition, String> lastEntry = entryList.get(entryList.size()-1);
                    melody.remove(lastEntry.getKey());
                    fillMelodyField();
                    pentagramTable.setValueAt("", lastEntry.getKey().getRow(), lastEntry.getKey().getColumn());
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPentagramTable();
                melody.clear();
                fillMelodyField();
            }
        });

        playButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.play(melodyComposerField.getText());
            }
        });
    }

    // methods for managing the pentagram table

    private void resetPentagramTable(){
        for(Map.Entry<PentagramPosition, String> entry : melody.entrySet()) {
            PentagramPosition key = entry.getKey();
            pentagramTable.setValueAt("", key.getRow(), key.getColumn());
        }
    }

    private void setCellValue(JTable target, Integer row, Integer column) {
        if(notes.size() > 0){
            target.setValueAt(notes.get(notes.size() -1 ), column, row);
        }
    }

    // when the class is compiled, the method $$$setupUI$$$ is added, and that's the place createUIComponents is called
    private void createUIComponents() {
        pentagramPanel = new JPanel();

        final ImageIcon icon = new ImageIcon("penta4.png");

        Integer rows = 7;
        Integer columns = 20;
        pentagramTable = new JTable(rows, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            // show img as table background. We tried but img does not match with our table cells
            /*@Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.setColor(Color.RED);
                g2d.drawLine(0, 0, getWidth(), getHeight());
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
                super.paintComponent(g2d); //To change body of generated methods, choose Tools | Templates.
                g2d.dispose();
            }*/
        };


        // table background transparent
        pentagramTable.setOpaque(false);
        ((DefaultTableCellRenderer)pentagramTable.getDefaultRenderer(Object.class)).setOpaque(false);
                  // hide table grid
//                pentagramTable.setShowGrid(false);

        pentagramPanel.add(pentagramTable);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
