package com.databit.appproductos.Controlador;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.databit.appproductos.Modelo.Producto;

import java.util.HashMap;
import java.util.Map;

public class ProductoFirebase {
    boolean estado=true;

    // metodo que agregar un registro en base datos
    public boolean insertarDB(int codigo,String descripcion, int precio){
        boolean b = true;
        try {
            // Inicializar la conexión a Firebase
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference dbref = db.getReference(ProductoFirebase.class.getSimpleName());

            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Producto producto = new Producto(codigo,descripcion,precio);
                    dbref.push().setValue(producto);
                    estado =true;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    String mensaje = "ATENCION, el registro e NO fue guardado...";
                    estado =false;
                }
            });


        } catch (Exception e) {
            // Manejar otras excepciones generales
            String mensaje = "ATENCION, error general...";
        }
        return estado;
    }

    // metodo que modifica un registro en base de datos
    public boolean modificarDB(int codigo, String descripcion, int precio) {
        boolean b = true;
        try {
            // Inicializar la conexión a Firebase
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference dbref = db.getReference(ProductoFirebase.class.getSimpleName());

            // Buscar el registro con el código especificado
            dbref.orderByChild("codigo").equalTo(codigo).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Actualizar todos los registros que coinciden con el código
                        for (DataSnapshot data : snapshot.getChildren()) {
                            // Crear un mapa con los valores actualizados
                            Map<String, Object> productoActualizado = new HashMap<>();
                            productoActualizado.put("descripcion", descripcion);
                            productoActualizado.put("precio", precio);

                            // Actualizar el registro en Firebase
                            data.getRef().updateChildren(productoActualizado);
                        }
                        estado = true;
                        System.out.println("Registro modificado correctamente");
                    } else {
                        System.out.println("No se encontró un registro con ese código");
                        estado = false;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    String mensaje = "ATENCIÓN, el registro no fue modificado...";
                    System.out.println(mensaje);
                    estado = false;
                }
            });
        } catch (Exception e) {
            // Manejar otras excepciones generales
            String mensaje = "ATENCIÓN, error general...";
            System.out.println(mensaje);
            estado = false;
        }
        return estado;
    }

        // metodo que elimina un registro de base de datos
    public boolean eliminarDB(int codigo) {
        boolean b = true;
        try {
            // Inicializar la conexión a Firebase
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference dbref = db.getReference(ProductoFirebase.class.getSimpleName());

            // Buscar el registro con el código especificado
            dbref.orderByChild("codigo").equalTo(codigo).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Eliminar todos los registros que coinciden con el código
                        for (DataSnapshot data : snapshot.getChildren()) {
                            data.getRef().removeValue();
                        }
                        estado = true;
                        System.out.println("Registro eliminado correctamente");
                    } else {
                        System.out.println("No se encontró un registro con ese código");
                        estado = false;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    String mensaje = "ATENCIÓN, no se pudo eliminar el registro...";
                    System.out.println(mensaje);
                    estado = false;
                }
            });
        } catch (Exception e) {
            // Manejar otras excepciones generales
            String mensaje = "ATENCIÓN, error general...";
            System.out.println(mensaje);
            estado = false;
        }
        return estado;
    }

}
