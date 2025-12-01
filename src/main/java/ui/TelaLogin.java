package ui;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;


public class TelaLogin extends JFrame {
    public TelaLogin() {
            setTitle("Login");
            setSize(300, 200);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new GridLayout(3, 1));

            JTextField login = new JTextField();
            JPasswordField senha = new JPasswordField();

            JButton entrar = new JButton("Entrar");
            JButton cadastrar = new JButton("Cadastrar");

            entrar.addActionListener(e ->
                    LoginController.login(
                            login.getText(),
                            new String(senha.getPassword()),
                            this
                    )
            );
            login.addActionListener(e -> {
                    String admLogin = JOptionPane.showInputDialog("Login do ADM:");
                    String admSenha = JOptionPane.showInputDialog("Senha do ADM:");

                    LoginController.login(admLogin, admSenha, this);
            });
            add(login);
            add(senha);

            cadastrar.addActionListener(e ->
                    LoginController.cadastrar(
                            login.getText(),
                            new String(senha.getPassword())
                    )
            );
            add(login);
            add(senha);

            JPanel botoes = new JPanel();
            botoes.add(entrar);
            botoes.add(cadastrar);
            add(botoes);
            add(login);

            setVisible(true);
    }
}


