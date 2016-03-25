package com.example.mizuno.facerecognition;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class FaceRecognitionActivity extends AppCompatActivity {


    //    res/drawableにtest.bmpを入れる
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        TextView faceNum = (TextView) findViewById(R.id.facenum);

        Resources r= getResources();
        Bitmap bitmap2 = BitmapFactory.decodeResource(r, R.drawable.test);
        Bitmap bitmap = bitmap2.copy(Bitmap.Config.RGB_565, true);

        FaceDetector.Face faces[]=new FaceDetector.Face[20];
        FaceDetector detector = new FaceDetector(bitmap.getWidth(),bitmap.getHeight(),faces.length);
        int facenum=detector.findFaces(bitmap,faces);

        faceNum.setText(String.valueOf(facenum));
        img.setImageBitmap(bitmap);

        for(int i=0;i<facenum;i++){

            PointF point = new PointF();
            faces[i].getMidPoint(point);
            Log.d("Face", "Number" + i);
            Log.d("Face", "Confidence:" + faces[i].confidence());
            Log.d("Face", "MidPoint X:" + point.x);
            Log.d("Face", "MidPoint Y:" + point.y);
            Log.d("Face", "EyesDistance:" + faces[i].eyesDistance());

        }

    }
}
