package com.project.Xmeme.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.Xmeme.data.MemesEntity;

@Repository
public interface MemesRepository extends MongoRepository<MemesEntity, String> {
    List<MemesEntity> findByNameAndUrlAndCaption(String name , String url , String caption);
}
