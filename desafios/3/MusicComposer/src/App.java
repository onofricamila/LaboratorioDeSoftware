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
    private JTextField textField1;
    private JButton playButton;
    private JButton undoButton;
    private JButton resetButton;
    private List melody;

    private void addActionListener(JButton jButton, List list){
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list.add(e.getActionCommand());
            }
        });
    }

    public App() {

        melody = new ArrayList();

        redondaButton.setActionCommand("redonda");
        blancaButton.setActionCommand("blanca");
        negraButton.setActionCommand("negra");
        corcheaButton.setActionCommand("corchea");
        semicorcheaButton.setActionCommand("semicorchea");
        fusaButton.setActionCommand("fusa");
        semifusaButton.setActionCommand("semifusa");

        this.addActionListener(redondaButton, melody);
        this.addActionListener(blancaButton, melody);
        this.addActionListener(negraButton, melody);
        this.addActionListener(corcheaButton, melody);
        this.addActionListener(semicorcheaButton, melody);
        this.addActionListener(fusaButton, melody);
        this.addActionListener(semifusaButton, melody);

        pentagramTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                setCellValue(target, target.getSelectedColumn(), target.getSelectedRow());
            }
        });
    }

    private void setCellValue(JTable target, Integer row, Integer column) {
        if(melody.size() > 0){
            target.setValueAt(melody.get(melody.size() -1 ), column, row);
        }
    }

    private void createUIComponents() {
        pentagramPanel = new JPanel();
        Integer rows = 10;
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
