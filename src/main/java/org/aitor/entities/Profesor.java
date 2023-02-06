package org.aitor.entities;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class Profesor {
    private ObjectId id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private Direccion direccion;
    @ToString.Exclude
    private List<ObjectId> modulos;

    public Profesor(String nombre, String primerApellido, String segundoApellido, String telefono, Direccion direccion) {
        this.id = new ObjectId();
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
