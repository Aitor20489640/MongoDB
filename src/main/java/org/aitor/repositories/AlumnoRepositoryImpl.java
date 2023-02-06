package org.aitor.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.aitor.MongoManager;
import org.aitor.entities.Alumno;
import org.aitor.entities.Modulo;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.List;


public class AlumnoRepositoryImpl implements Repository<Alumno> {
    private final MongoDatabase database;
    private final MongoCollection<Alumno> collection;
    public AlumnoRepositoryImpl(MongoDatabase database) {
        this.database = database;
        collection = database.getCollection("Alumno", Alumno.class);

    }

    @Override
    public void save(Alumno alumno) {
        collection.insertOne(alumno);
    }

    @Override
    public Alumno findOneById(ObjectId id) {
        return collection.find(eq("id", id)).first();
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> list = new ArrayList<>();
        for (Alumno alumnos : collection.find()) {
            list.add(alumnos);
        }
        return list;
    }

    @Override
    public void updateById(ObjectId id) {
        Alumno alumno = findOneById(id);
        collection.replaceOne(eq(id), alumno);
    }

    @Override
    public void deleteById(ObjectId id) {
        collection.deleteOne(eq(id));
    }

    public List<Alumno> findAllPendientes() {
        MongoManager manager = new MongoManager();
        List<Alumno> alumnoList = new ArrayList<>();
        for (Alumno alumno : collection.find()) {
            List<String> cursosModulos = new ArrayList<>();
            for (ObjectId moduloId : alumno.getModulos()) {
                Modulo modulo = manager.getModuloRepository().findOneById(moduloId);
                cursosModulos.add(modulo.getCurso());
            }
            boolean pendientes = (cursosModulos.stream().distinct().toArray().length > 1);
            if (pendientes)
                alumnoList.add(alumno);
        }
        return alumnoList;
    }
}
