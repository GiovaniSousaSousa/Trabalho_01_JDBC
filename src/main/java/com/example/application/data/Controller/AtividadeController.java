package com.example.application.data.Controller;

import com.example.application.data.dao.AtividadeDao;
import com.example.application.data.entity.Atividades;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class AtividadeController {
    private final AtividadeDao atividadeDao;

    public AtividadeController(AtividadeDao atividadeDao) {
        this.atividadeDao = atividadeDao;
    }

    public List<Atividades> listarAtividades() {
        return atividadeDao.listar();
    }

    public Optional<Atividades> obterAtividade(Long id) {
        return atividadeDao.obter(id);
    }

    public boolean inserirAtividade(Atividades atividade) {
        return atividadeDao.inserir(atividade);
    }

    public boolean atualizarAtividade(Atividades atividade) {
        return atividadeDao.alterar(atividade);
    }

    public boolean deletarAtividade(Atividades atividade) {
        return atividadeDao.remover(atividade);
    }
}
