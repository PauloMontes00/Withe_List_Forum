package controller;

import model.Usuario;
import storage.Storage;
import ui.TelaHome;

import javax.swing.*;
import java.util.Map;

public class LoginController {

    public static void login(String login, String senha, JFrame telaLogin) {

        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            JOptionPane.showMessageDialog(null, "Preencha login e senha.");
            return;
        }

        Map<String, Usuario> usuarios = Storage.carregarUsuarios();

        if (usuarios.containsKey(login) && usuarios.get(login).getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

            Usuario usuario = usuarios.get(login);

            new TelaHome(usuario).setVisible(true);
            telaLogin.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Login inválido.");
        }
    }


    public static void cadastrar(String login, String senha) {

        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            JOptionPane.showMessageDialog(null, "Login e senha não podem ser vazios.");
            return;
        }

        Map<String, Usuario> usuarios = Storage.carregarUsuarios();

        if (usuarios.containsKey(login)) {
            JOptionPane.showMessageDialog(null, "Usuário já existe.");
            return;
        }

        usuarios.put(login, new Usuario(login, senha));
        Storage.salvarUsuarios(usuarios);

        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    }
}
    

