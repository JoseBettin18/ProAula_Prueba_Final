package com.example.demo.Repository;

import com.example.demo.Model.Resena;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResenaRepository extends MongoRepository<Resena, String> {
    List<Resena> findByLugarId(String lugarId);
}
