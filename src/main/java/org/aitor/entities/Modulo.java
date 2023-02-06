package org.aitor.entities;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class Modulo {
    ObjectId id;
    private String nombre;
    private String curso;
    private int numHorasSemanales;
    private Profesor profesor;
    @ToString.Exclude
    private List<ObjectId> alumnos;

    public Modulo(String nombre, String curso, int numHorasSemanales, Profesor profesor) {
        this.id = new ObjectId();
        this.nombre = nombre;
        this.curso = curso;
        this.numHorasSemanales = numHorasSemanales;
        this.profesor = profesor;
    }
}
