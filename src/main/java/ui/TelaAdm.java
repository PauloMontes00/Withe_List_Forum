package ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TelaAdm extends JFrame {

    public TelaAdm() {

        setTitle("Painel Administrativo");
        setSize(400, 250);
        setLayout(new GridLayout(4, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton abrirUsuarios = new JButton("Abrir arquivo de usuários");
        JButton abrirPosts = new JButton("Abrir arquivo de posts");
        JButton abrirForum = new JButton("Abrir fórum (posts)");
        JButton sair = new JButton("Sair");

        abrirUsuarios.addActionListener(e ->
                abrirArquivo("usuarios.json")
        );

        abrirPosts.addActionListener(e ->
                abrirArquivo("posts.json")
        );

        abrirForum.addActionListener(e ->
                abrirArquivo("posts.json")
        );

        sair.addActionListener(e -> {
            dispose();
            new TelaLogin();
        });

        add(abrirUsuarios);
        add(abrirPosts);
        add(abrirForum);
        add(sair);

        setVisible(true);
    }

    private void abrirArquivo(String nomeArquivo) {
        try {
            Desktop.getDesktop().open(new File(nomeArquivo));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo: " + nomeArquivo);
        }
    }
}
