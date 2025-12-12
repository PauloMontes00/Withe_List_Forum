package service;

import model.Comunidade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ComunidadeService {
    private final String CAMINHO = "database/comunidades.txt";
    private Gson gson = new Gson();

    private List<Comunidade> carregar() {
        try (FileReader reader = new FileReader(CAMINHO)) {
            Type type = new TypeToken<List<Comunidade>>(){}.getType();
            List<Comunidade> lista = gson.fromJson(reader, type);
            return lista != null ? lista : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Comunidade> lista) {
        try (FileWriter writer = new FileWriter(CAMINHO)) {
            gson.toJson(lista, writer);
        } catch (Exception ignored) {}
    }

    // ------------------------
    // MÉTODOS PRINCIPAIS
    // ------------------------

    public String criarComunidade(String nome, boolean privada) {
        if (nome == null || nome.isBlank()) {
            return "❌ Nome inválido!";
        }

        List<Comunidade> lista = carregar();

        lista.add(new Comunidade(nome, privada));
        salvar(lista);

        return "✔️ Comunidade criada com sucesso!";
    }

    public String listarComunidades() {
        List<Comunidade> lista = carregar();

        if (lista.isEmpty()) {
            return "Nenhuma comunidade cadastrada.";
        }

        StringBuilder sb = new StringBuilder();
        for (Comunidade c : lista) {
            sb.append("Nome: ").append(c.getNome())
                    .append(" | Tipo: ")
                    .append(c.isPrivada() ? "Privada" : "Pública")
                    .append("\n");
        }

        return sb.toString();
    }
}
