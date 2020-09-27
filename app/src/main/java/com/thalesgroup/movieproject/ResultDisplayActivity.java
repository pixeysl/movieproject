package com.thalesgroup.movieproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ResultDisplay> listResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_display);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JSONArray jsonArray = parseIntentArgs(getIntent());
        List<ResultDisplay>  listContent = getContentList(jsonArray);

        adapter = new RecyclerViewAdapter(listContent, this);
        recyclerView.setAdapter(adapter);

    }

    /**'
     * Format intent args into JSON array
     * @param intent for retrieving intent args
     * @return JSONArray results
     */
    private JSONArray parseIntentArgs(Intent intent) {
        JSONObject json = null;
        try {
            json = new JSONObject(intent.getStringExtra("jsonObject"));
            return json.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Format JSONArray into ResultDisplay list to be displayed in UI
     * @param jsonArray
     * @return ResultDisplay list
     */
    private List<ResultDisplay> getContentList(JSONArray jsonArray) {
        listResult = new ArrayList<>();

        //TODO: sorting by native C lib

        for(int i=0; i<10; i++) {
            JSONObject jsonObject = null;
            ResultDisplay result = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
                result = new ResultDisplay(jsonObject.getString("original_title"),
                        jsonObject.getString("release_date"),
                        jsonObject.getString("vote_average"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            listResult.add(result);
        }

        return listResult;
    }
}