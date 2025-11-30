package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Usuario;
import java.io.*;
import java.util.*;

public class Storage {
    private static final String FILE = "data.json";

    public static Map<String, Usuario> carregarUsuarios() {
        try {
            File f = new File(FILE);
            if (!f.exists()) return new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            return new Gson().fromJson(br, new TypeToken<Map<String, Usuario>>(){}.getType());
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
    public static void salvarUsuarios(Map<String, Usuario> usuarios) {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(new Gson().toJson(usuarios));
        } catch (Exception ignored) {}
    }
}
