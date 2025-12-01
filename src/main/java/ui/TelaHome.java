package ui;

import model.Post;
import model.Usuario;
import storage.Storage;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * Tela principal (Home) do fórum.
 * Mostra a lista de posts, permite criar post, atualizar lista e deslogar.
 */
public class TelaHome extends JFrame {

    private final Usuario usuarioLogado;

    private final DefaultListModel<String> postListModel;
    private final JList<String> postList;

    public TelaHome(Usuario usuario) {
        this.usuarioLogado = usuario;

        setTitle("Home - Logado como: " + usuario.getLogin());
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // inicializa componentes
        postListModel = new DefaultListModel<>();
        postList = new JList<>(postListModel);
        initComponents();

        // carrega os posts ao abrir
        carregarPosts();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));

        // Top bar: nome do usuário e botões
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblUser = new JLabel("Usuário: " + usuarioLogado.getLogin());
        lblUser.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        topPanel.add(lblUser, BorderLayout.WEST);

        JPanel topButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        JButton btnCriarPost = new JButton("Criar Post");
        JButton btnComunidades = new JButton("Comunidades");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnLogout = new JButton("Sair");

        topButtons.add(btnCriarPost);
        topButtons.add(btnComunidades);
        topButtons.add(btnAtualizar);
        topButtons.add(btnLogout);

        topPanel.add(topButtons, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Área central: lista de posts (com scroll)
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(postList);
        add(scroll, BorderLayout.CENTER);

        // Rodapé: instruções simples
        JLabel footer = new JLabel("Clique em um post para ver (em breve).");
        footer.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        add(footer, BorderLayout.SOUTH);

        // Ações dos botões
        btnCriarPost.addActionListener(e -> abrirCriarPost());
        btnAtualizar.addActionListener(e -> carregarPosts());
        btnComunidades.addActionListener(e -> JOptionPane.showMessageDialog(this, "Tela de comunidades não implementada ainda."));
        btnLogout.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            this.dispose();
        });

        // (Opcional) clique duplo no post — mostra detalhes
        postList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int idx = postList.locationToIndex(evt.getPoint());
                    if (idx >= 0) mostrarDetalhePost(idx);
                }
            }
        });
    }

    private void carregarPosts() {
        postListModel.clear();

        List<Post> posts = Storage.carregarPosts();
        for (Post p : posts) {
            // formato simples: "autor: conteúdo (truncado se longo)"
            String conteudo = p.getConteudo();
            String display = p.getAutor() + ": " + (conteudo.length() > 120 ? conteudo.substring(0, 120) + "..." : conteudo);
            postListModel.addElement(display);
        }
    }

    private void abrirCriarPost() {
        String texto = JOptionPane.showInputDialog(this, "Escreva seu post:", "Novo Post", JOptionPane.PLAIN_MESSAGE);

        if (texto == null) return; // cancelou
        texto = texto.trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O post não pode ser vazio.");
            return;
        }

        Post novo = new Post(usuarioLogado.getLogin(), texto);
        Storage.salvarPost(novo);
        carregarPosts();
    }

    private void mostrarDetalhePost(int index) {
        List<Post> posts = Storage.carregarPosts();
        if (index < 0 || index >= posts.size()) return;

        Post p = posts.get(index);
        String titulo = "Post de " + p.getAutor();
        JTextArea area = new JTextArea(p.getConteudo());
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        area.setCaretPosition(0);
        JScrollPane sp = new JScrollPane(area);
        sp.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, sp, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
