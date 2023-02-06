package org.aitor;
//String pwd = "jhlwOCQtrCcTAi1D";

import org.aitor.entities.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {

        List<ObjectId> modulosList = new ArrayList<>();
        MongoManager manager = new MongoManager();

        // creacion de objetos
        Direccion direccion = new Direccion("Calle", 22, "Castellon", "Castellon");
        Profesor profesor = new Profesor("Alejandro", "Roig", "Aguilar", "654638594", direccion);
        Modulo modulo1 = new Modulo("Mates", "1", 7, profesor);
        Modulo modulo2 = new Modulo("Castellano", "2", 6, profesor);
        Modulo modulo3 = new Modulo("Biologia", "3", 6, profesor);
        Alumno alumno1 = new Alumno("Aitor", "Rodriguez", "Gallardo", "12345678", "654245678");
        Alumno alumno2 = new Alumno("alumno2", "alumno2", "alumno2", "87654321", "756496875");
        modulosList.add(modulo1.getId());
        modulosList.add(modulo2.getId());
        modulosList.add(modulo3.getId());


        // Asignacion de relaciones
        profesor.setModulos(modulosList);

        alumno1.setModulos(Arrays.asList(modulo1.getId()));
        alumno2.setModulos(Arrays.asList(modulo2.getId(), modulo3.getId()));
        modulo1.setAlumnos(Arrays.asList(alumno1.getId()));
        modulo2.setAlumnos(Arrays.asList(alumno2.getId()));
        modulo3.setAlumnos(Arrays.asList(alumno2.getId()));

        // CRUD

        // Save de todos los objetos
        /*manager.getAlumnoRepository().save(alumno1);
        manager.getAlumnoRepository().save(alumno2);
        manager.getProfesorRepository().save(profesor);
        manager.getModuloRepository().save(modulo1);
        manager.getModuloRepository().save(modulo2);
        manager.getModuloRepository().save(modulo3);*/

        // Mostrar todos los alumnos
        System.out.println("Alumnos----------");
        manager.getAlumnoRepository().findAll().forEach(System.out::println);

        System.out.println("Modulos----------");
        manager.getModuloRepository().findAll().forEach(System.out::println);

        System.out.println("Profesores--------");
        manager.getProfesorRepository().findAll().forEach(System.out::println);


        System.out.println("Find pendientes---");
        manager.getAlumnoRepository().findAllPendientes().forEach(System.out::println);

        System.out.println("Modulos curso 2----");
        manager.getModuloRepository().findByCurso("2").forEach(System.out::println);
    }
}