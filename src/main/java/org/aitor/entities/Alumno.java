package org.aitor.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class Alumno {
    ObjectId id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String nia;
    private String telefono;
    private List<ObjectId> modulos;

    public Alumno(String nombre, String primerApellido, String segundoApellido, String nia, String telefono) {
        this.id = new ObjectId();
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nia = nia;
        this.telefono = telefono;
    }
}
