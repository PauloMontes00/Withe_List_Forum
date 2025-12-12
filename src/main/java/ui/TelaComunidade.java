package ui;

import model.Comunidade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaComunidade extends JFrame {

    private JTextField campoNome;
    private JCheckBox checkPrivada;
    private JButton btnCriar;
    private JButton btnListar;
    private JTextArea areaResultado;

    public TelaComunidade() {
        setTitle("Comunidades");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        campoNome = new JTextField(20);
        checkPrivada = new JCheckBox("Comunidade privada");
        btnCriar = new JButton("Criar comunidade");
        btnListar = new JButton("Listar comunidades");
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        painel.add(new JLabel("Nome da comunidade:"));
        painel.add(campoNome);
        painel.add(checkPrivada);
        painel.add(btnCriar);
        painel.add(btnListar);
        painel.add(new JScrollPane(areaResultado));

        add(painel);
    }

    // GETTERS PARA O CONTROLLER

    public String getNomeDigitado() {
        return campoNome.getText();
    }

    public boolean isPrivadaMarcada() {
        return checkPrivada.isSelected();
    }

    public void addAcaoCriar(ActionListener listener) {
        btnCriar.addActionListener(listener);
    }

    public void addAcaoListar(ActionListener listener) {
        btnListar.addActionListener(listener);
    }

    public void mostrarResultado(String texto) {
        areaResultado.setText(texto);
    }
}
