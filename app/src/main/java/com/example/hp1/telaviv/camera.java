package com.example.hp1.telaviv;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class camera extends Activity implements OnClickListener {
    private Bitmap bitmap;
    private ImageView imageView;
    Button btCamera,btGalery;
    static final int SELECT_IMAGE=1;
    static final int TAKE_IMAGE=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.imageView);

        btCamera =(Button) findViewById(R.id.takePhotoButton);
        //btGalery = (Button) findViewById(R.id.btGalery);
        btCamera.setOnClickListener(this);
        //btGalery.setOnClickListener(this);

        if(!hasCamera()){
            btCamera.setEnabled(false);
        }
    }
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
    @Override
    public void onClick(View v) {
        if(v==btCamera){
            //start another activity and receive a result back in case the activity exists return 0
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,TAKE_IMAGE );
        }else{
            Intent i = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }
    }
    /**
     *  The resultCode will be RESULT_CANCELED if the activity explicitly returned that, didn't return any result, or crashed during its operation.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_IMAGE && resultCode == RESULT_OK){
            Bundle extra = data.getExtras();
            bitmap  = (Bitmap) extra.get("data");
            imageView.setImageBitmap(bitmap);
        }
        else if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK)
        {
            Uri targetUri = data.getData();
            Toast.makeText(getApplicationContext(), targetUri.toString(), Toast.LENGTH_SHORT).show();
            //textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {

                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
			/*String[] filePathColumn = {MediaStore.Images.Media.DATA};

			Cursor cursor = getContentResolver().query(data.getData(), filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

			String filePath = cursor.getString(columnIndex);
			cursor.close();

			Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
			Toast.makeText(getApplicationContext(), filePath, 1000).show();
			imageView.setImageBitmap(yourSelectedImage);
*/
        }
    }
}
