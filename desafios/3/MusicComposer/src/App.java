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
    private List notes;
    private Map<PentagramPosition, String> melody;
    private List pentagramPositions;
    private Player player;

    private void addActionListener(JButton jButton, List list){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.add(e.getActionCommand());
            }
        });
    }

    private void initializePentagramPositions(List pentagramPositions){
        pentagramPositions.add('C');
        pentagramPositions.add('D');
        pentagramPositions.add('E');
        pentagramPositions.add('F');
        pentagramPositions.add('G');
        pentagramPositions.add('A');
        pentagramPositions.add('B');
    }

    public void fillMelodyField(){
        melodyField.setText(String.join(" ", melody.values()));
    }

    public App() {

        notes = new ArrayList();
        melody = new LinkedHashMap<>();
        pentagramPositions = new ArrayList();
        player = new Player();

        this.initializePentagramPositions(pentagramPositions);

        redondaButton.setActionCommand("w");
        blancaButton.setActionCommand("h");
        negraButton.setActionCommand("q");
        corcheaButton.setActionCommand("i");
        semicorcheaButton.setActionCommand("s");
        fusaButton.setActionCommand("t");
        semifusaButton.setActionCommand("x");

        this.addActionListener(redondaButton, notes);
        this.addActionListener(blancaButton, notes);
        this.addActionListener(negraButton, notes);
        this.addActionListener(corcheaButton, notes);
        this.addActionListener(semicorcheaButton, notes);
        this.addActionListener(fusaButton, notes);
        this.addActionListener(semifusaButton, notes);

        pentagramTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!notes.isEmpty()) {
                    JTable target = (JTable) e.getSource();
                    setCellValue(target, target.getSelectedColumn(), target.getSelectedRow());
                    melody.put(new PentagramPosition(target.getSelectedRow(), target.getSelectedColumn()), pentagramPositions.get(target.getSelectedRow()).toString() + '5' + notes.get(notes.size() - 1).toString()); // Bug
                    fillMelodyField();
                }
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.play(String.join(" ", melody.values()));
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Map.Entry<PentagramPosition, String>> entryList = new ArrayList<Map.Entry<PentagramPosition, String>>(melody.entrySet());
                Map.Entry<PentagramPosition, String> lastEntry = entryList.get(entryList.size()-1);
                melody.remove(lastEntry.getKey());
                fillMelodyField();
                pentagramTable.setValueAt("", lastEntry.getKey().getRow(), lastEntry.getKey().getColumn());
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

        // Make table background transparent
        pentagramTable.setOpaque(false);
        ((DefaultTableCellRenderer)pentagramTable.getDefaultRenderer(Object.class)).setOpaque(false);

        //        pentagramTable.setShowGrid(false);

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
