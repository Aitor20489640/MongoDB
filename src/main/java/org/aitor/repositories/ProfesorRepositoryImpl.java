package org.aitor.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.aitor.entities.Profesor;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;


public class ProfesorRepositoryImpl implements Repository<Profesor> {
    private final MongoDatabase database;
    private final MongoCollection<Profesor> collection;

    public ProfesorRepositoryImpl(MongoDatabase database) {
        this.database = database;
        collection = database.getCollection("Profesor", Profesor.class);
    }

    @Override
    public void save(Profesor profesor) {
        collection.insertOne(profesor);
    }

    @Override
    public Profesor findOneById(ObjectId id) {
        return collection.find(eq(id)).first();
    }

    @Override
    public List<Profesor> findAll() {
        List<Profesor> list = new ArrayList<>();
        for (Profesor profe : collection.find()) {
            list.add(profe);
        }

        return list;
    }

    @Override
    public void updateById(ObjectId id) {
        Profesor profesor = findOneById(id);
        collection.replaceOne(eq(id), profesor);
    }

    @Override
    public void deleteById(ObjectId id) {
        collection.deleteOne(eq(id));
    }
}
