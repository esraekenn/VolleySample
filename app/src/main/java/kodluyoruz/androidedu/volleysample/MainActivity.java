package kodluyoruz.androidedu.volleysample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import kodluyoruz.androidedu.volleysample.viewmodels.InfoViewModel;
import kodluyoruz.androidedu.volleysample.volley.AppController;

public class MainActivity extends AppCompatActivity {

    /*http://myjson.com//
    *https://www.w3schools.com/
    * http://json.parser.online.fr/
    *JSON (JavaScript Object Notation).
    * https://github.com/ihsanbal/LoggingInterceptor
    *
    * GET method'u kullanilirsa parametreleri url icerisinde gonderiyoruz. Basit yapıda parametre gonderilirken kullanilir. Link uzerinde gorulur.
    *
    * POST method'u kullanilirsa parametreleri getParams() icerisinde veriyoruz. Karmasik parametre gonderilirken kullanilir. Link uzerinde gorulmez.
    * Bilgi form'lar uzerinden gider. "Kayıt Formu" ekranlari gibi.
    *
    * PUT method'u kullanilirsa parametreleri yine getParams() icerisinde veriyoruz. Genelde server'a dosya gondermek icin kullanilir.
    * Gonderilen dosya server'da varsa yenisi ile degistirilir yoksa yenisi yaratilir.
    *
    * */

    private String TAG = "AndroidBootCamp";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonObjectRequest();

        jsonArrayRequest();

        jsonStringRequest();
    }

    private void jsonObjectRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        String tag_json_obj = "AndroidBootCampJsonObjectRequest";

        //adsoyad, yas ve email bilgisi icerir.
        String url = "https://api.myjson.com/bins/x8yrj";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Json Object : ", response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.e(TAG, "Error: " + error.getMessage());

                pDialog.hide();
            }
        });

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void jsonArrayRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        String tag_json_arry = "AndroidBootCampJsonArrayRequest";

        String url = "https://api.myjson.com/bins/16pyzz";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.e("Json Array : ", response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.e(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }

    private void jsonStringRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        String tag_string_req = "AndroidBootCampJsonStringRequest";

        //object mi array mi oldugu farketmez.
        String url = "https://api.myjson.com/bins/x8yrj";
        String url2 = "https://api.myjson.com/bins/16pyzz";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                /*
                * Gson Samples
                * */
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                //object request cast islemi icin. // url
                InfoViewModel post = gson.fromJson(response, InfoViewModel.class);
                Toast.makeText(MainActivity.this, post.getName(), Toast.LENGTH_SHORT).show();

                //array request cast islemi icin. // url2
                //List<InfoViewModel> posts = Arrays.asList(gson.fromJson(response, InfoViewModel[].class));
                //Toast.makeText(MainActivity.this, posts.get(0).getName(), Toast.LENGTH_SHORT).show();


                /*
                * Jackson Samples
                * */

                ObjectMapper mapper = new ObjectMapper();

//                try {
//                    //object request cast islemi icin. // url
////                    InfoViewModelJackson jackson = mapper.readValue(response.toString(), InfoViewModelJackson.class);
////                    Toast.makeText(MainActivity.this, jackson.getName(), Toast.LENGTH_SHORT).show();
//
//                    //array request cast islemi icin. // url2
////                    List<InfoViewModelJackson> jackson = Arrays.asList(mapper.readValue(response.toString(), InfoViewModelJackson[].class));
////                    Toast.makeText(MainActivity.this, jackson.get(0).getName(), Toast.LENGTH_SHORT).show();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                Log.e("Json String : ", response.toString());
                pDialog.hide();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.e(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {

            //encoding ayarlamak icin yazilmali. Silip deneyelim bir de.
            @Override
            protected Response<String> parseNetworkResponse(
                    NetworkResponse response) {

                String charset = "UTF-8";
                String parsed;
                try {
                    if (charset != null) {
                        parsed = new String(response.data, charset);
                    } else {
                        parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    }
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void paramRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        String tag_json_obj = "AndroidBootCampJsonObjectParamRequest";

        String url = "http://api.androidhive.info/volley/person_object.json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {
            /**
             * name, email, password bilgileri parametre olarak veriliyor.
             * */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Gökhan ÖZTÜRK");
                params.put("email", "GokhanOZTURK@AndroidEdu.IO");
                params.put("password", "AndroidBootCamp");

                return params;
            }

        };

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void headerRequest() {

        // daha sonra iptal etmek istenirse // activity ölürse otomatik istek iptal olur.
        String tag_json_obj = "AndroidBootCampJsonObjectHeaderRequest";

        String url = "http://api.androidhive.info/volley/person_object.json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {

            /**
             * api key header'a paslaniyor.
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("apiKey", "xxxxxxxxxxxxxxx");
                return headers;
            }

        };

        // requesti kuyruga ekler.
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void requestPrioritization() {

        //onceliklendirme "Normal, Low, Immediate ve High" olarak kullanilabilir.
        final Request.Priority priority = Request.Priority.HIGH;

        String url = "http://api.androidhive.info/volley/string_response.html";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e(TAG, "Error: " + error.getMessage());
            }
        }) {
            //onemli olan kisim.
            @Override
            public Priority getPriority() {
                return priority;
            }

        };
    }
}
