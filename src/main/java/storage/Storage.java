package storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Post;
import model.Usuario;

import java.io.*;
import java.util.*;

public class Storage {
    private static final String FILE_USERS = "usuarios.json";
    private static final String FILE_POSTS = "posts.json";

    private static final Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
    private static final Gson gson = new Gson();

    // ===== Usuários =====
    public static Map<String, Usuario> carregarUsuarios() {
        try {
            File f = new File(FILE_USERS);
            if (!f.exists()) return new HashMap<>();

            BufferedReader br = new BufferedReader(new FileReader(FILE_USERS));
            return gson.fromJson(br, new TypeToken<Map<String, Usuario>>(){}.getType());

        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public static void salvarUsuarios(Map<String, Usuario> usuarios) {
        try (FileWriter fw = new FileWriter(FILE_USERS)) {
            fw.write(gsonPretty.toJson(usuarios));  // 🔥 JSON organizado
        } catch (Exception ignored) {}
    }


    // ===== Posts =====
    public static List<Post> carregarPosts() {
        try {
            File f = new File(FILE_POSTS);
            if (!f.exists()) return new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(FILE_POSTS));
            return gson.fromJson(br, new TypeToken<List<Post>>(){}.getType());

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static void salvarPost(Post p) {
        List<Post> posts = carregarPosts();
        posts.add(p);

        try (FileWriter fw = new FileWriter(FILE_POSTS)) {
            fw.write(gsonPretty.toJson(posts));  // 🔥 JSON bonito
        } catch (Exception ignored) {}
    }
}


