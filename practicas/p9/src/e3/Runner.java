package e3;

import javax.swing.*;

public class Runner implements Runnable {
    private String nickName;
    private int pasos;
    private String recorrido = "";
    private JTextField pista;

    public Runner(String nickName, int pasos) {
        this.nickName = nickName;
        this.pasos = pasos;
    }

    public void setPista(JTextField pista) {
        this.pista = pista;
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public String toString() {
        return "| "+ nickName + " |";
    }

    @Override
    public void run() {
        Monitor m = Monitor.INSTANCE;
        for (int i = 0; i < pasos; i++) {
                recorrido += "-";
                m.run(nickName + recorrido, pista);
            }
        }
    }

