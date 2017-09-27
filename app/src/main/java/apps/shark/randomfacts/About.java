package apps.shark.randomfacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Harsha on 9/27/2017.
 */

public class About extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Button moreapps= (Button) findViewById(R.id.moreapps);
        Button shareapp= (Button) findViewById(R.id.shareapps);
        Button rate= (Button) findViewById(R.id.rateapp);
        //Button support= (Button) findViewById(R.id.support);
        Button website= (Button) findViewById(R.id.website);

        moreapps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=SHARK+App+Development")));
                }
                catch (android.content.ActivityNotFoundException anfe) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=SHARK+App+Development")));
                }
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://sharkapps.tk")));
            }
        });

        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Random facts");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Get Random Facts app ||Not in store yet! ");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdailog();
            }
        });

    }

    public void showdailog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Opps!");
        alertDialogBuilder.setMessage("Not in store yet..");

        alertDialogBuilder.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {


                    }
                });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
