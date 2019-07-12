package activies.rn.senai.br.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends AppCompatActivity {
    private final int TIRAR_FOTO = 1;
    private final int GRAVAR_VIDEO = 2;

    private FloatingActionButton mFbCamera;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        inicializarComponentes();

        definirEventos();

    }

    private void definirEventos() {
        mFbCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TIRAR_FOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TIRAR_FOTO){
            if (resultCode == RESULT_OK){
                if (data != null){
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    mImageView.setImageBitmap(bitmap);
                    Toast.makeText(this, "A captura", Toast.LENGTH_LONG).show();
                } else if (resultCode == RESULT_CANCELED){
                    Toast.makeText(this, "A captura foi cancelada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "A captura foi feita", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void inicializarComponentes() {
        mFbCamera = findViewById(R.id.ac_camera_fb_camera);
        mImageView = findViewById(R.id.imageView);
    }
}
