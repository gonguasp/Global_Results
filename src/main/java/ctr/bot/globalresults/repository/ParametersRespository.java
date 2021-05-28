package ctr.bot.globalresults.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ctr.bot.globalresults.entity.ParametersSchemas;

public interface ParametersRespository extends MongoRepository<ParametersSchemas, String> {
    ParametersSchemas findByDescription(String description);
}
    
