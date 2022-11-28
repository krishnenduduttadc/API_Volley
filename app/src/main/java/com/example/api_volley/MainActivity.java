package com.example.api_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "test";
    private EditText e1, e2,e3,e4;
    Button b;
    //String url = "https://api.opentopodata.org/v1/test-dataset?locations=56,123";
    String url ="https://api.github.com/users/hadley/orgs";
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        e1 = (EditText) findViewById(R.id.editTextTextPersonName1);
        e2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        e3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        e4 = (EditText) findViewById(R.id.editTextTextPersonName4);
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                api_fetch2();
            }
        });

    }



    void api_fetch() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonarray = response.getJSONArray("results");
                            JSONObject object = (JSONObject) jsonarray.get(0);
                            e1.setText( "elevation is: "+ object.getInt("elevation"));
                            e2.setText("lat is: "+ object.getJSONObject("location").getInt("lat"));
                            e3.setText("lng is: "+object.getJSONObject("location").getInt("lng"));
                            e4.setText( "dataset is: "+ object.getString("dataset"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error", error);
            }
        });



        queue.add(jsonObjectRequest);
    }


    void api_fetch2() {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray  response) {
                        try {
                            //JSONArray jsonarray = response.getJSONArray("results");
                            JSONObject object = (JSONObject) response.get(0);
                            e1.setText( ""+ object.getString("login"));
                            Toast.makeText(getApplicationContext(), ""+response.get(0), Toast.LENGTH_SHORT).show();
                            //e2.setText("lat is: "+ object.getJSONObject("location").getInt("lat"));
                            //3.setText("lng is: "+object.getJSONObject("location").getInt("lng"));
                            //e4.setText( "dataset is: "+ object.getString("dataset"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error", error);
            }
        });



        queue.add(jsonObjectRequest);
    }
}