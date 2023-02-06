package org.aitor.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
public class Direccion {
    ObjectId id;
    private String calle;
    private int numero;
    private String poblacion;
    private String provincia;

    public Direccion(String calle, int numero, String poblacion, String provincia) {
        this.id = new ObjectId();
        this.calle = calle;
        this.numero = numero;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }
}
