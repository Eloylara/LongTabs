package longtabs.app.longtabs;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificarActivity extends AppCompatActivity {
    EditText et_nombre,et_apellido;
    Button bt_modificar,bt_eliminar;
    int id;
    String nombre;
    String apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b= getIntent().getExtras();
        if (b!=null){
            id = b.getInt("Id");
            nombre=b.getString("Nombre");
            apellido=b.getString("Apellido");
        }
        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido = (EditText) findViewById(R.id.et_apellido);

        et_nombre.setText(nombre);
        et_apellido.setText(apellido);

        bt_modificar = (Button) findViewById(R.id.bt_modificar);
        bt_eliminar= (Button) findViewById(R.id.bt_eliminar);

        bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });
        bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modificar(id,et_nombre.getText().toString(),et_apellido.getText().toString());
                onBackPressed();
            }
        });
    }

    private void Modificar(int Id, String Nombre, String Apellido){
    }
        BaseHelper helper = new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="update Personas set Nombre='"+ Nombre + "',Apellido='" + Apellido +"'where Id="+Id;
        db.execSQL(sql);
        db.close();

    }
    private void Eliminar(int Id){
        BaseHelper helper = new BaseHelper(this, "Demo",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql ="delete from Personas where Id="+Id;
        db.execSQL(sql);
        db.close();
    }