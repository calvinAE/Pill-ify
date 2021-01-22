package com.example.pill_ify;

import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class API {

    //methode om de api aan te spreken
    public void callApi(String searchMedication, TextView drugname, TextView drugdescription, TextView drugIngredient)
    {

        String url =  "https://api.fda.gov/drug/ndc.json?search="+searchMedication;


        new AsyncHttpClient().get(url, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String str = new String(responseBody);
                try {

                    //omzetten responsebody to jsonObject
                    JSONObject result = new JSONObject(new String(responseBody));

                    JSONArray resultArray =  result.getJSONArray("results");

                    final int numberofitems = resultArray.length();
                    String drugsname = "";
                    String description = "";
                    String ingredient = "";

                    for (int i = 0; i < numberofitems; i++)
                    {

                        JSONObject perresult = resultArray.getJSONObject(i);
                        drugsname = perresult.getString("brand_name");


                        JSONArray package1 = new JSONArray(perresult.getString("packaging"));
                        JSONObject perresult2 = package1.getJSONObject(i);
                        description = perresult2.getString("description");

                        JSONArray ingredientArray = new JSONArray(perresult.getString("active_ingredients"));
                        JSONObject perresult3 = ingredientArray.getJSONObject(i);
                        ingredient = perresult3.getString("name");


                    }
                    drugname.setText(drugsname);
                    drugdescription.setText(description);
                    drugIngredient.setText(ingredient);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                drugname.setText("Error calling api");


            }

        });

    }
}
