package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.Lugar;

@Repository
public interface LugarRepository extends MongoRepository<Lugar, String> {
    List<Lugar> findByTipo(String tipo);
}
