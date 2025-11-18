package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Model.Lugar;
import com.example.demo.Repository.LugarRepository;

@Service
public class LugarService {

    @Autowired
    private LugarRepository lugarRepository;

    public List<Lugar> listar() {
        return lugarRepository.findAll();
    }

    public Optional<Lugar> buscarPorId(String id) {
        return lugarRepository.findById(id);
    }

    public List<Lugar> listarPorTipo(String tipo) {
        return lugarRepository.findByTipo(tipo);
    }

    public Lugar guardar(Lugar lugar) {
        return lugarRepository.save(lugar);
    }

    public void eliminar(String id) {
        lugarRepository.deleteById(id);
    }
}
