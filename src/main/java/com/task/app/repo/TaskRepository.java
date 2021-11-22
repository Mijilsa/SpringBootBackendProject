package com.task.app.repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;
import com.task.app.model.Tasks;

@Repository
public interface TaskRepository extends MongoRepository<Tasks, Long>{

}
