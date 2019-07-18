package com.example.codelabsfacedetection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class MainActivity extends AppCompatActivity {

    private static final int OPEN_IMAGE_REQUEST = 1;
    private Button button;
    private ImageView image;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (resultCode == Activity.RESULT_OK && requestCode == OPEN_IMAGE_REQUEST ) {
          image.setImageBitmap(null);

            Uri uri = null;

            if (data != null) {
                uri = data.getData();
            }

            if (uri != null) {

                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    image.setImageBitmap(bitmap);
                } catch (FileNotFoundException e){
                    ///
                }
            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        image = findViewById(R.id.image_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, OPEN_IMAGE_REQUEST);
            }
        });
    }
}
