package com.example.hp1.telaviv;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileNotFoundException;

public class Registration extends AppCompatActivity implements View.OnClickListener{
    Button signup,btGallery;
    ImageView image;
    Bitmap bitmap;
    EditText email, password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    final String TAG  = "Firebase";

    int CAMERA_REQUEST=0,PICK_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        signup=(Button)findViewById(R.id.signup);
        btGallery = (Button) findViewById(R.id.btGallery);
        image=(ImageView)findViewById(R.id.profilephoto);
        btGallery.setOnClickListener(this);
        signup.setOnClickListener(this);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v==signup){
            createAccount(password.getText().toString(),email.getText().toString());

        }
        if(v==btGallery)
        {
            SelectImage();
        }

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void createAccount(String password, String email){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            Intent i=new Intent(Registration.this,Thankyou.class);
                            startActivity(i);
                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bundle extra = data.getExtras();
            bitmap  = (Bitmap) extra.get("data");
            image.setImageBitmap(bitmap);
        }
        else if(requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            Toast.makeText(getApplicationContext(), targetUri.toString(), Toast.LENGTH_SHORT).show();
            //textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {

                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    private void SelectImage()
    {
        final CharSequence[] items={"Camera","Gallery","Cancel"};

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("Add Image");
        alertdialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_REQUEST);
                        break;
                    case 1:
                        Intent gallery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(gallery,PICK_IMAGE);
                        break;
                    case 2:
                        dialog.dismiss();
                        break;
                }
            }
        });
        alertdialog.show();
    }
}
