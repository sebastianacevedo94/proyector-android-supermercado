package com.example.supermercado2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etcodigo,etproducto,etprecio,etvendedor;
    Button btncreate,btnfetch;

    RequestQueue requestQueue;

    private static final String url1="http://192.168.1.12/android/save.php";
    private Object StringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue volley ;newRequesrQueue(this);
        

        initui();

        btncreate.setOnClickListener(this);
        btnfetch.setOnClickListener(this);

    }

    private void newRequesrQueue(MainActivity mainActivity) {
    }

    private void initui() {
        etcodigo=findViewById(R.id.etcodigo);
        etproducto=findViewById(R.id.etproducto);
        etprecio=findViewById(R.id.etprecio);
        etvendedor=findViewById(R.id.etvendedor);

        btncreate=findViewById(R.id.btncreate);
        btnfetch=findViewById(R.id.btnfetch);
    }

    @Override
    public void onClick(View v) {
        int codigo1 =v.getId();

        if(codigo1==R.id.btncreate){
            String codigo = etcodigo.getText().toString().trim();
            String producto = etproducto.getText().toString().trim();
            String precio = etprecio.getText().toString().trim();
            String vendedor = etvendedor.getText().toString().trim();

            createUser(codigo,producto,precio,vendedor);

        }else if (codigo1 == R.id.btnfetch){

        }
    }

    private void createUser(final String codigo, final String producto, final String precio, final String vendedor) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "correcto", Toast.LENGTH_SHORT).show();
                        
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("codigo",codigo);
                params.put("producto",producto);
                params.put("precio",precio);
                params.put("vendedor",vendedor);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}