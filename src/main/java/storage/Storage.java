package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Post;
import model.Usuario;
import java.io.*;
import java.util.*;

public class Storage {
    private static final String FILE_USERS = "usuarios.json";
    private static final String FILE_POSTS = "posts.json";

    // ===== Usuários =====
    public static Map<String, Usuario> carregarUsuarios() {
        try {
            File f = new File(FILE_USERS);
            if (!f.exists()) return new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader(FILE_USERS));
            return new Gson().fromJson(br, new TypeToken<Map<String, Usuario>>(){}.getType());
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public static void salvarUsuarios(Map<String, Usuario> usuarios) {
        try (FileWriter fw = new FileWriter(FILE_USERS)) {
            fw.write(new Gson().toJson(usuarios));
        } catch (Exception ignored) {}
    }


    // ===== Posts =====
    public static List<Post> carregarPosts() {
        try {
            File f = new File(FILE_POSTS);
            if (!f.exists()) return new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(FILE_POSTS));
            return new Gson().fromJson(br, new TypeToken<List<Post>>(){}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void salvarPost(Post p) {
        List<Post> posts = carregarPosts();
        posts.add(p);

        try (FileWriter fw = new FileWriter(FILE_POSTS)) {
            fw.write(new Gson().toJson(posts));
        } catch (Exception ignored) {}
    }
}

