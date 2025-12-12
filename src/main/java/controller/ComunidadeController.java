package controller;

import service.ComunidadeService;
import ui.TelaComunidade;

public class ComunidadeController {
    private TelaComunidade ui;
    private ComunidadeService service;

    public ComunidadeController(TelaComunidade view, ComunidadeService service) {
        this.ui = view;
        this.service = service;

        view.addAcaoCriar(e -> criarComunidade());
        view.addAcaoListar(e -> listarComunidades());
    }

    private void criarComunidade() {
        String nome = ui.getNomeDigitado();
        boolean privada = ui.isPrivadaMarcada();

        String resultado = service.criarComunidade(nome, privada);
        ui.mostrarResultado(resultado);
    }

    private void listarComunidades() {
        String resultado = service.listarComunidades();
        ui.mostrarResultado(resultado);
    }
}

