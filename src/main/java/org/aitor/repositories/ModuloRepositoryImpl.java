package org.aitor.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.aitor.entities.Modulo;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;


public class ModuloRepositoryImpl implements Repository<Modulo> {
    private final MongoDatabase database;
    private final MongoCollection<Modulo> collection;

    public ModuloRepositoryImpl(MongoDatabase database) {
        this.database = database;
        collection = database.getCollection("Modulo", Modulo.class);
    }

    @Override
    public void save(Modulo modulo) {
        collection.insertOne(modulo);
    }

    @Override
    public Modulo findOneById(ObjectId id) {
        return collection.find(eq(id)).first();
    }

    @Override
    public List<Modulo> findAll() {
        List<Modulo> list = new ArrayList<>();
        for (Modulo modulo : collection.find()) {
            list.add(modulo);
        }
        return list;
    }

    @Override
    public void updateById(ObjectId id) {
        Modulo modulo = findOneById(id);
        collection.replaceOne(eq(id), modulo);
    }

    @Override
    public void deleteById(ObjectId id) {
        collection.deleteOne(eq(id));
    }

    public List<Modulo> findByCurso(String curso){
        List<Modulo> list = new ArrayList<>();
        for (Modulo modulo : collection.find(eq("curso", curso)).sort(Sorts.descending("numHorasSemanales"))) {
            list.add(modulo);
        }
        return list;
    }
}
