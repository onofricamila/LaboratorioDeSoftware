import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    private List notes;
    private List melody;
    private List pentagramPositions;

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
        melodyField.setText(String.join(" ", melody));
    }

    public App() {

        notes = new ArrayList();
        melody = new ArrayList();
        pentagramPositions = new ArrayList();

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
                JTable target = (JTable) e.getSource();
                setCellValue(target, target.getSelectedColumn(), target.getSelectedRow());
                melody.add(pentagramPositions.get(target.getSelectedRow()).toString() + '5' + notes.get(notes.size() -1 ).toString()); // Bug
                fillMelodyField();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
                //all cells false
                return false;
            }
        };

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