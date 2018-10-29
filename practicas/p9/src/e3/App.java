package e3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    private List<Runner> runners = new ArrayList();
    private int pasos = 10;
    private JPanel mainPanel;
    private JButton startButton;

    public App() {

        for (int i = 0; i < 5; i++) {
            Runner r = new Runner("runner"+i, pasos);
            runners.add(r);
        }

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepareRace();
                startRace();
            }
        });

    }

    private void prepareRace() {

    }

    private void startRace() {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < runners.size(); i++) {
            exec.execute(runners.get(i));
        }
        exec.shutdown();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        App app = new App();
        frame.setContentPane(app.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
