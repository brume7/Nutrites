package com.example.nutrites;

import android.os.Bundle;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class CaloriesTabFragment extends Fragment {
    String food;
    EditText et;
    Button calcBc;
    TextView tv1, tv2, tv3, tv4, tv5;
    View v1, v2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.calories_tab_fragment, container, false);
        et = (EditText) root.findViewById(R.id.editTextCalories);
        tv1 = (TextView) root.findViewById(R.id.textView13);
        tv2 = (TextView) root.findViewById(R.id.textView16);
        tv3 = (TextView) root.findViewById(R.id.textView17);
        tv4 = (TextView) root.findViewById(R.id.textView18);
        tv5 = (TextView) root.findViewById(R.id.textView19);
        calcBc = (Button) root.findViewById(R.id.calculateBtn);
        food = et.getText().toString();


        calcBc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData(food);

            }
        });


        return root;
    }

    private void fetchData(String food) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://calorieninjas.p.rapidapi.com/v1/nutrition?query="+food+"")
                .get()
                .addHeader("x-rapidapi-host", "calorieninjas.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "577c954e1cmsh29073eee6626404p1275dbjsn8466a95f2476")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getContext().getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {

                    String resp = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(resp);
                                String val1 = jsonObject.getString("items");
                                tv2.setText("energy kilo calories is "+ val1 );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                }
            }
        });
    }



}
