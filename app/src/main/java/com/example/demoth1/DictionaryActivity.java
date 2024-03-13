package com.example.demoth1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoth1.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DictionaryActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<String> dataList;
    private Context context;

    RequestQueue requestQueue;
//    TextView textMean;
    ListView listView;
    EditText editValue;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot);

//        textMean = findViewById(R.id.textMean);
        listView = findViewById(R.id.listView);
        btnSearch = findViewById(R.id.btnSearch);
        editValue = findViewById(R.id.editValue);

        requestQueue = Volley.newRequestQueue(this);

        btnSearch.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String value = editValue.getText().toString();
                dictionary(value);
            }
        });
    }

    public void dictionary(String value) {
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + value;
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//          return JSON file

            @Override
            public void onResponse(JSONArray response) {
//              textMean.setText("");
//              khoi tao arraylist
                dataList = new ArrayList<>();

                for (int i = 0; i < response.length(); i++) {
                    try {
//                      get key = meanings
                        JSONObject jsonObject = response.getJSONObject(i);
                        JSONArray meanArray = jsonObject.getJSONArray("meanings");

                        for (int j = 0; j < meanArray.length(); j++) {
                            JSONObject meanObject = meanArray.getJSONObject(j);

//                          get partOfSpeech
                            String typeWord = meanObject.getString("partOfSpeech");
                            dataList.add("(" + typeWord + ")");

//                          get key = definition
                            JSONArray definitionArray = meanObject.getJSONArray("definitions");
                            for (int k = 0; k < definitionArray.length(); k++) {
                                JSONObject definitionObject = definitionArray.getJSONObject(k);
                                String noDay = definitionObject.getString("definition");
                                dataList.add("=> " + noDay);
//                                textMean.append("==> " + noDay + "\n\n");
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(DictionaryActivity.this, android.R.layout.simple_list_item_1, dataList);
                            listView.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

//                if (response.length() > 0) {
//                    Toast.makeText(DictionaryActivity.this, "success", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(DictionaryActivity.this, "failed", Toast.LENGTH_SHORT).show();
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(arrayRequest);
    }

}