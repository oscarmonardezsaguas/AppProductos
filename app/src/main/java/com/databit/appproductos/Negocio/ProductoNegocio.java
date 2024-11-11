package com.databit.appproductos.Negocio;

import com.databit.appproductos.Controlador.ProductoFirebase;

public class ProductoNegocio {
    ProductoFirebase productofirebase=new ProductoFirebase();

    //validador insertar negocio producto
    public String insertarNegocio(int codigo, String descripcion, int precio) {
        String mensaje="";
        boolean respuesta = productofirebase.insertarDB(codigo, descripcion, precio);
        if (respuesta=true){
            mensaje = "ATENCION, registro almacenado con éxito...";
        } else {
            mensaje = "ATENCION, error al guardar registro...";
        }
        return mensaje;
    }

    //validador modificar negocio producto
    public String modificarNegocio(int codigo, String descripcion, int precio) {
        String mensaje="";
        boolean respuesta = productofirebase.modificarDB(codigo, descripcion, precio);
        if (respuesta=true){
            mensaje = "ATENCION, registro almacenado con éxito...";
        } else {
            mensaje = "ATENCION, error al guardar registro...";
        }
        return mensaje;
    }

    //validador eliminar negocio producto
    public String eliminarNegocio(int codigo) {
        String mensaje="";
        boolean respuesta = productofirebase.eliminarDB(codigo);
        if (respuesta=true){
            mensaje = "ATENCION, registro eliminado con éxito...";
        } else {
            mensaje = "ATENCION, error al eliminar el registro...";
        }
        return mensaje;
    }
}
