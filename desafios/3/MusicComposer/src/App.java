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
    private JTextPane INSTRUCCIONESLaTablaDeTextPane;
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

    // each letter of the american cipher referring a 'level' in the pentagram. Since the first row from above the table
    // has 0 as an index, the letters have to be added in inverse order to the list to be able to represent the
    // pentagram properly
    private void initializePentagramPositions(List pentagramPositions){
        pentagramPositions.add('B');
        pentagramPositions.add('A');
        pentagramPositions.add('G');
        pentagramPositions.add('F');
        pentagramPositions.add('E');
        pentagramPositions.add('D');
        pentagramPositions.add('C');
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

        Integer rows = 7;
        Integer columns = 20;
        pentagramTable = new JTable(rows, columns){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        pentagramTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 & row != 6 ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });

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
