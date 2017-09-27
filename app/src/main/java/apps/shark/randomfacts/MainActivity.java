package apps.shark.randomfacts;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue rq;
    String url;
        CardView math, trivia, date, year;// for cards
        TextView math_text , trivia_text , date_text , year_text;//for facts text
        ImageView math_add, trivia_add, date_add, year_add;// for plus on every card to enter text
        String fact_desp_text,trivia_url="trivia", math_url="math" , date_url="date", year_url="year"; // for link in the url
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inistializing card views
        math = (CardView) findViewById(R.id.math);
        trivia = (CardView) findViewById(R.id.trivia);
        date = (CardView) findViewById(R.id.date);
        year = (CardView) findViewById(R.id.year);

        //Initializing addtext buttons
        math_add = (ImageView) findViewById(R.id.math_add);
        trivia_add = (ImageView) findViewById(R.id.trivia_add);
        date_add = (ImageView)findViewById(R.id.date_add);
        year_add = (ImageView)findViewById(R.id.year_add);

        //Initializing textviews
        math_text = (TextView) findViewById(R.id.fact_math_desp);
        trivia_text = (TextView) findViewById(R.id.fact_trivia_desp);
        date_text = (TextView) findViewById(R.id.fact_date_desp);
        year_text = (TextView) findViewById(R.id.fact_year_desp);

        //Initializing add buttons
        math_add = (ImageView) findViewById(R.id.math_add);
        trivia_add = (ImageView) findViewById(R.id.trivia_add);
        date_add = (ImageView) findViewById(R.id.date_add);
        year_add = (ImageView) findViewById(R.id.year_add);

        //request queue for volley
        rq = Volley.newRequestQueue(this);

        //Onclick listeners for the cards
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("@@@","card clicked");
                getjsonrequest(math_url);// calling the n/w method to execute


            }
        });
        trivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("@@@","card clicked");
                getjsonrequest(trivia_url);
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getjsonrequest(date_url);
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getjsonrequest(year_url);
            }
        });

        math_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdailog(math_url);
            }
        });
        date_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdailog(date_url);
            }
        });
        trivia_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdailog(trivia_url);
            }
        });
        year_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdailog(year_url);
            }
        });
    }

    public void showdailog(final String category){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dailog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);

        dialogBuilder.setTitle("Enter Data");
        dialogBuilder.setMessage("Enter number or date or year or trivia");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String data = edt.getText().toString();
                String linkdata = data+"/"+category;
                Log.i("@@@",linkdata);
                getjsonrequest(linkdata);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    //getting JSON from this method shows up in card
    public void getjsonrequest(final String link){
        if(link.contains("/")){
            url="http://numbersapi.com/"+link+"?json";
        }
        else {
            url = "http://numbersapi.com/random/" + link + "?json";// dynamic link of the url
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    fact_desp_text = response.getString("text");
                    Log.i("@@@","fact got sucesssfully");
                    if(link.contains("math")){
                        math_text.setText(fact_desp_text);//shows up the fact in the math card
                    }
                    else if(link.contains("trivia")){
                        trivia_text.setText(fact_desp_text);//shows up the fact in the trivia card
                    }
                    else if(link.contains("date")){
                        date_text.setText(fact_desp_text);//shows up the fact in the date card
                    }
                    else{
                        year_text.setText(fact_desp_text);//shows up the fact in the year card
                    }
                    Log.i("@@@","fact added to card sucesssfully");
                } catch (JSONException e) {// exception if any for JSON
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("@@@","error in JSON");
            }
        });
        rq.add(jsonObjectRequest); //volly request ends here
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
