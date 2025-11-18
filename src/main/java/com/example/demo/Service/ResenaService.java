package com.example.demo.Service;

import com.example.demo.Model.Resena;
import com.example.demo.Repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> listarPorLugar(String lugarId) {
        return resenaRepository.findByLugarId(lugarId);
    }

    public void guardar(Resena resena) {
        resenaRepository.save(resena);
    }

    public void eliminar(String id) {
        resenaRepository.deleteById(id);
    }
}
