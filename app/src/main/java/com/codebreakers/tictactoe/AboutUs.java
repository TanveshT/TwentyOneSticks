package com.codebreakers.tictactoe;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class AboutUs extends AppCompatActivity {

    ImageView imgViewCompanyFacebook,imgViewCompanyGmail,imgViewCompanyInstagram,imgViewCompanyLocation;
    ImageView imgViewDeveloperFacebook,imgViewDeveloperGmail,imgViewDeveloperInstagram,imgViewDeveloperLinkedin;

    final String mTitle = "Prank-I Systems Pvt Ltd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().hide();

        imgViewCompanyFacebook = findViewById(R.id.imgCompanyFacebook);
        imgViewCompanyGmail = findViewById(R.id.imgCompanyGmail);
        imgViewCompanyInstagram = findViewById(R.id.imgCompanyInstagram);
        imgViewCompanyLocation = findViewById(R.id.imgCompanyLocation);

        imgViewDeveloperFacebook = findViewById(R.id.imgDeveloperFacebook);
        imgViewDeveloperGmail = findViewById(R.id.imgDeveloperGmail);
        imgViewDeveloperInstagram = findViewById(R.id.imgDeveloperInstagram);
        imgViewDeveloperLinkedin = findViewById(R.id.imgDeveloperLinkedin);

        imgViewCompanyFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
             //Try Intent For Company's Facebook
        });

        imgViewCompanyGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"prankisystems@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });

        imgViewCompanyInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Write Instagram Code
            }
        });

        imgViewCompanyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?q=loc:" + 18.5306 + "," + 73.9121 + " (" + mTitle + ")"));
                    startActivity(intent);
                } catch (ActivityNotFoundException ane) {

                    Toast.makeText(AboutUs.this, "Please Install Google Maps ", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        });

        //For Developer
        imgViewDeveloperFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent FBIntent = new Intent(Intent.ACTION_SEND);
                FBIntent.setType("text/plain");
                FBIntent.setPackage("https://www.facebook.com/takawaletanvesh");
                FBIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
                try {
                    startActivity(FBIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AboutUs.this, "Facebook have not been installed.", Toast.LENGTH_SHORT).show( );
                }

            }
        });

        imgViewDeveloperGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"tanveshtakawale26@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            }
        });

        imgViewDeveloperLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkedId = "tanvesh-takawale-76a82b126";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%@" + linkedId));
                final PackageManager packageManager = getBaseContext().getPackageManager();
                final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (list.isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + linkedId));
                }
                startActivity(intent);
            }
        });

        imgViewDeveloperInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/_u/tanvesht");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/tanvesht")));
                }

            }
        });

    }
}
