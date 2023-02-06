package org.aitor;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.aitor.repositories.AlumnoRepositoryImpl;
import org.aitor.repositories.ModuloRepositoryImpl;
import org.aitor.repositories.ProfesorRepositoryImpl;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoManager {
    private final String uri = "mongodb://ec2-54-157-149-27.compute-1.amazonaws.com:27017/instituto";
    private final CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    private final MongoDatabase database;

    private AlumnoRepositoryImpl alumnoRepository = null;
    private ModuloRepositoryImpl moduloRepository = null;
    private ProfesorRepositoryImpl profesorRepository = null;

    public MongoManager() {
        MongoClient mongoClient = MongoClients.create(uri);
        database = mongoClient.getDatabase("instituto").withCodecRegistry(pojoCodecRegistry);
    }

    public AlumnoRepositoryImpl getAlumnoRepository() {
        return (alumnoRepository == null) ? alumnoRepository = new AlumnoRepositoryImpl(database) : alumnoRepository;
    }

    public ModuloRepositoryImpl getModuloRepository() {
        return (moduloRepository == null) ? moduloRepository = new ModuloRepositoryImpl(database) : moduloRepository;
    }

    public ProfesorRepositoryImpl getProfesorRepository() {
        return (profesorRepository == null) ? profesorRepository = new ProfesorRepositoryImpl(database) : profesorRepository;
    }
}
