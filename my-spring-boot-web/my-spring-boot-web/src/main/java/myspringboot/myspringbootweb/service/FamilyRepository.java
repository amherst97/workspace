package myspringboot.myspringbootweb.service;

import myspringboot.myspringbootweb.model.Family;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FamilyRepository extends MongoRepository<Family, String> {
}
