package ctr.bot.globalresults.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parametersschemas")
public class ParametersSchemas {
    
    @Id
    private ObjectId _id;
    String name;
    String description;

    public ParametersSchemas() {}

    public ParametersSchemas(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
}