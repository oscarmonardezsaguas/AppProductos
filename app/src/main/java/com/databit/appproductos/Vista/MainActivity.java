package com.databit.appproductos.Vista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.databit.appproductos.Modelo.Producto;
import com.databit.appproductos.Negocio.ProductoNegocio;
import com.databit.appproductos.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText txtcodigo;
    EditText txtdescripcion;
    EditText txtprecio;

    Button btnguardar, btnmodificar, btneliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtcodigo=(EditText) findViewById(R.id.txtCodigo);
        txtdescripcion=(EditText) findViewById(R.id.txtDescripcion);
        txtprecio=(EditText) findViewById(R.id.txtPrecio);

        btnguardar=(Button) findViewById(R.id.btnGuardar);
        btnmodificar=(Button) findViewById(R.id.btnModificar);
        btneliminar=(Button) findViewById(R.id.btnEliminar);

        // accion boton guardar registro
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });

        // accion boton modificar registro
        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar();
            }
        });

        // accion boton modificar registro
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Eliminar();
            }
        });

    }

    //metodo insertarr registro
    private void Registrar() {
        String resultadomsj = null;
        if (txtcodigo.getText().toString().trim().isEmpty()
                || txtdescripcion.getText().toString().trim().isEmpty()
                || txtprecio.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "ATENCION, complete los campos faltantes...", Toast.LENGTH_SHORT).show();
        }else {
            int codigo = Integer.parseInt(txtcodigo.getText().toString());
            String descripcion =txtdescripcion.getText().toString();
            int precio = Integer.parseInt(txtprecio.getText().toString());
            ProductoNegocio productonegocio = new ProductoNegocio();
            resultadomsj = productonegocio.insertarNegocio(codigo, descripcion, precio);
            Toast.makeText(this, resultadomsj, Toast.LENGTH_SHORT).show();
        }
    }

    //metodo modificar registro
    private void Modificar(){
        String resultadomsj = null;
        if (txtcodigo.getText().toString().trim().isEmpty()
                || txtdescripcion.getText().toString().trim().isEmpty()
                || txtprecio.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "ATENCION, complete los campos faltantes...", Toast.LENGTH_SHORT).show();
        }else {

            int codigo = Integer.parseInt(txtcodigo.getText().toString());
            String descripcion = txtdescripcion.getText().toString();
            int precio = Integer.parseInt(txtprecio.getText().toString());
            ProductoNegocio productonegocio = new ProductoNegocio();
            resultadomsj = productonegocio.modificarNegocio(codigo, descripcion, precio);
            Toast.makeText(this, resultadomsj, Toast.LENGTH_SHORT).show();
        }
    }

    //metodo eliminar registro
    private void Eliminar(){
        String resultadomsj = null;
        if (txtcodigo.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this, "ATENCION, complete los campos faltantes...", Toast.LENGTH_SHORT).show();
        }else {
            int codigo = Integer.parseInt(txtcodigo.getText().toString());
            ProductoNegocio productonegocio = new ProductoNegocio();
            resultadomsj = productonegocio.eliminarNegocio(codigo);
            Toast.makeText(this, resultadomsj, Toast.LENGTH_SHORT).show();
        }

    }

}