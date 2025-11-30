package controller;

import storage.Storage;
import model.Usuario;
import javax.swing.*;
import java.util.*;

public class LoginController {
    public static void login(String login, String senha) {
        Map<String, Usuario> usuarios = Storage.carregarUsuarios();
        if (usuarios.containsKey(login) && usuarios.get(login).getSenha().equals(senha)) {
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Login inválido.");
        }
    }


    public static void cadastrar(String login, String senha) {
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
