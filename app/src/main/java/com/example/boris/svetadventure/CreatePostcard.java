package com.example.boris.svetadventure;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.boris.svetadventure.Utilitys.SingleScanner;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.splunk.mint.Mint;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by boris on 04/01/2016.
 */
public class CreatePostcard extends Activity {
    LinearLayout linearLayout;
    SeekBar opacityBar;
    RelativeLayout saveButton;
    RelativeLayout picture;
    ImageView small_image;
    boolean moveing = false;
    String imgPath;
    float x, y = 0.0f;
    float m, d = 0.0f;
    RelativeLayout finalPost;
    RelativeLayout cameraButton;
    public static final int CAMARA_REQUEST = 1313;
    View v;
    View mView;
    RelativeLayout rotateLeft;
    RelativeLayout rotateRight;
    public static final int SELECTED_PICTURE = 1;
    int numClicks = 1;
    RelativeLayout minusButton;
    RelativeLayout plusButton;
    int height;
    int width;
    RelativeLayout changePost;
    TextView btnClosePopup;
    TextView LEFT;
    ImageView changeImage;
    int pageNumber;
    ImageView bigImage;
    EditText editText;
    RelativeLayout moveText;
    boolean isMoving;
    Button movingButton;
    RelativeLayout showBar;
    TextView doneSeek;
    ImageView textImage;
    RelativeLayout hidePicture;
    boolean hideImage;
    RelativeLayout hideText;
    TextView RIGHT;
    EditText input;
    private final int PERMISSIONS_REQUEST_READ = 0;
    private final int PERMISSIONS_REQUEST_WRITE = 1;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postcardmain);
        Mint.initAndStartSession(CreatePostcard.this, "f241ce49");
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreatePostcard.this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //nothing to be inserted here
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_READ);
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreatePostcard.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //nothing to be inserted here
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_WRITE);
            }
        }
        hideText = (RelativeLayout) findViewById(R.id.hidetext);
        hidePicture = (RelativeLayout) findViewById(R.id.hide_picture);
        textImage = (ImageView) findViewById(R.id.text_image_id);
        textImage.setImageResource(R.drawable.move_text);
        showBar = (RelativeLayout) findViewById(R.id.show_bar);
        movingButton = (Button) findViewById(R.id.moving_button);
        isMoving = false;
        Typeface berry_rotunda = Typeface.createFromAsset(getAssets(), "fonts/berry_rotunda.ttf");
        editText = (EditText) findViewById(R.id.write_text_id);
        editText.setTypeface(berry_rotunda);
        moveText = (RelativeLayout) findViewById(R.id.move_text_id);
        bigImage = (ImageView) findViewById(R.id.big_image);
        small_image = (ImageView) findViewById(R.id.small_image);
        height = small_image.getLayoutParams().height;
        width = small_image.getLayoutParams().width;
        changePost = (RelativeLayout) findViewById(R.id.set_new_image);
        minusButton = (RelativeLayout) findViewById(R.id.minus_size);
        plusButton = (RelativeLayout) findViewById(R.id.plus_size);
        rotateLeft = (RelativeLayout) findViewById(R.id.rotate_left_id);
        rotateRight = (RelativeLayout) findViewById(R.id.rotate_right_id);
        mView = findViewById(R.id.picture_layout);
        cameraButton = (RelativeLayout) findViewById(R.id.camera_id);
        mView.setDrawingCacheEnabled(true);
        mView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        mView.layout(0, 0, mView.getMeasuredWidth(), mView.getMeasuredHeight());
        mView.buildDrawingCache(true);
        picture = (RelativeLayout) findViewById(R.id.picture_id);
        saveButton = (RelativeLayout) findViewById(R.id.savebuttonid);
        input = new EditText(CreatePostcard.this);
        bigImage.setImageResource(R.drawable.svetrazg1);
        v = finalPost;

        hideText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getVisibility() == View.VISIBLE) {
                    editText.setVisibility(View.GONE);
                }
                else {
                    editText.setVisibility(View.VISIBLE);
                }

            }
        });


        hidePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hideImage == false) {
                    small_image.setVisibility(View.GONE);
                    hideImage = true;
                } else if (hideImage == true) {
                    small_image.setVisibility(View.VISIBLE);
                    hideImage = false;

                }
            }
        });

        showBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiatePopupWindowForsSeekBar();
                Log.e("Klik","Kliknuce");
            }
        });

        changePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initiatePopupWindow();
            }
        });

        small_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                small_image.getLayoutParams().height = height;
                small_image.getLayoutParams().width = width;
                height = height - 10;
                width = width - 10;
                small_image.setMinimumWidth(width);
                small_image.setMinimumHeight(height);

            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                small_image.getLayoutParams().height = height;
                small_image.getLayoutParams().width = width;
                height = height + 10;
                width = width + 10;
                small_image.setMinimumWidth(width);
                small_image.setMinimumHeight(height);
            }
        });

        rotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                small_image.setPivotX(small_image.getWidth() / 2);
                small_image.setPivotY(small_image.getHeight() / 2);
                small_image.setRotation(5 * numClicks);
                numClicks--;

            }
        });

        rotateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                small_image.setPivotX(small_image.getWidth() / 2);
                small_image.setPivotY(small_image.getHeight() / 2);
                small_image.setRotation(5 * numClicks);
                numClicks++;
            }
        });

        small_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        moveing = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (moveing = true) {
                            x = event.getRawX() - small_image.getWidth() / 2;
                            y = event.getRawY() - small_image.getHeight() * 3 / 2;
                            small_image.setX(x);
                            small_image.setY(y);
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });

        moveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isMoving == false) {
                    isMoving = true;
                    textImage.setImageResource(R.drawable.text_mod);
                    movingButton.setVisibility(View.VISIBLE);

                } else if (isMoving == true) {
                    isMoving = false;
                    textImage.setImageResource(R.drawable.move_text);
                    movingButton.setVisibility(View.GONE);
                }

            }
        });

        movingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (isMoving == true) {

                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:
                            moveing = true;
                            break;

                        case MotionEvent.ACTION_MOVE:
                            if (moveing = true) {
                                x = event.getRawX() - editText.getWidth() / 2;
                                y = event.getRawY() - editText.getHeight() * 3 / 2;
                                m = event.getRawX() - movingButton.getWidth() / 2;
                                d = event.getRawY() - movingButton.getHeight() * 3 / 2;
                                editText.setX(x);
                                editText.setY(y);
                                movingButton.setX(m);
                                movingButton.setY(d + 120);

                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            break;
                    }
                }

                return true;
            }


        });

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentImage, SELECTED_PICTURE);
            }

        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setCursorVisible(false);
                if (isMoving == true) {
                    movingButton.setVisibility(View.INVISIBLE);
                }
                editText.setBackgroundColor(0);
                Toast.makeText(getApplicationContext(), "Image saved sucessfull", Toast.LENGTH_SHORT).show();
                mView.setDrawingCacheEnabled(true);
                Bitmap b = Bitmap.createBitmap(mView.getDrawingCache());
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 80, bytes);
                mView.setDrawingCacheEnabled(false);
                File f =new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (Exception e) {

                }
              // this is a refresh for gallery
               SingleScanner scanner = new SingleScanner(CreatePostcard.this,f);

                if (isMoving == true) {
                    movingButton.setVisibility(View.VISIBLE);
                }
                editText.setCursorVisible(true);

            }
        });

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

                case SELECTED_PICTURE:
                    try{
                    Log.e("432432432", "datata " + data);
                    if (data != null) {
                        Uri uri = data.getData();
                        String[] projection = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(projection[0]);
                        String filePath = cursor.getString(columnIndex);
                        cursor.close();
                        Bitmap myPostImage = BitmapFactory.decodeFile(filePath);
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        myPostImage.compress(Bitmap.CompressFormat.PNG, 50, out);
                        Drawable d = new BitmapDrawable(myPostImage);
                        small_image.setImageDrawable(d);
                    }

                    } catch (Exception e){

                    }
                    break;

                case CAMARA_REQUEST:
                    ByteArrayOutputStream out1 = new ByteArrayOutputStream();
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    thumbnail.compress(Bitmap.CompressFormat.PNG, 50, out1);
                    small_image.setImageBitmap(thumbnail);
                    break;

            }

    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreatePostcard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.boris.svetadventure/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreatePostcard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.boris.svetadventure/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    private PopupWindow pwindo;
    private PopupWindow pwindowSeek;

    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/", "image" + new Date().getTime() + ".png");
        Uri imgUri = Uri.fromFile(file);
        this.imgPath = file.getAbsolutePath();
        return imgUri;
    }
    public String getImagePath() {
        return imgPath;
    }

    private void initiatePopupWindow() {
        try {

            linearLayout = (LinearLayout) findViewById(R.id.popup_element);
            LEFT =  (TextView) findViewById(R.id.left_id);
            LEFT.setOnClickListener(move_left);
            RIGHT =  (TextView) findViewById(R.id.right_id);
            RIGHT.setOnClickListener(move_right);
            changeImage = (ImageView) findViewById(R.id.changedimage);
            btnClosePopup = (TextView)findViewById(R.id.done_id);
            linearLayout.setVisibility(View.VISIBLE);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            linearLayout.setVisibility(View.GONE);

            switch(pageNumber){
                case 0:
                    bigImage.setImageResource(R.drawable.sav_post_mod1);
                    break;
                case 1:
                    bigImage.setImageResource(R.drawable.svetrazg2);
                    break;
                case 2:
                    bigImage.setImageResource(R.drawable.svetrazg3);
                    break;
                case 3:
                    bigImage.setImageResource(R.drawable.svetrazg4);
                    break;
                case 4:
                    bigImage.setImageResource(R.drawable.svetrazg5);
                    break;
            }

        }
    };
        private View.OnClickListener move_right = new View.OnClickListener() {
            public void onClick(View v) {

                if(pageNumber<6) {
                    pageNumber++;
                }
                Log.e("numClicks", "numClicks" + numClicks);

           switch(pageNumber){
               case 0:
                   changeImage.setImageResource(R.drawable.svetrazg1);
                   break;
               case 1:
                   changeImage.setImageResource(R.drawable.svetrazg2);
                    break;
               case 2:
                   changeImage.setImageResource(R.drawable.svetrazg3);
                   break;
               case 3:
                   changeImage.setImageResource(R.drawable.svetrazg4);
                   break;
               case 4:
                   changeImage.setImageResource(R.drawable.svetrazg5);
                   break;
           }
        }
    };
    private View.OnClickListener move_left = new View.OnClickListener() {
        public void onClick(View v) {

           if(pageNumber>0) {
               pageNumber--;
           }

            switch(pageNumber){
                case 0:
                    changeImage.setImageResource(R.drawable.svetrazg1);
                    break;
                case 1:
                    changeImage.setImageResource(R.drawable.svetrazg2);
                    break;
                case 2:
                    changeImage.setImageResource(R.drawable.svetrazg3);
                    break;
                case 3:
                    changeImage.setImageResource(R.drawable.svetrazg4);
                    break;
                case 4:
                    changeImage.setImageResource(R.drawable.svetrazg5);
                    break;
            }
        }


    };

    private void initiatePopupWindowForsSeekBar() {
        try {
            Log.e("InsideClick001","InsideClick001");

            final LinearLayout window = (LinearLayout) findViewById(R.id.popup_element_alpha);
            if(window.getVisibility() == View.VISIBLE){
                window.setVisibility(View.GONE);
            }
            else {
                opacityBar = (SeekBar) findViewById(R.id.opacity);
                opacityBar.setProgress(255);
                doneSeek = (TextView) findViewById(R.id.done_seek);
                window.setVisibility(View.VISIBLE);
            }
            doneSeek.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   window.setVisibility(View.GONE);
                    }
           });

            opacityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                    small_image.setImageAlpha(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("InsideClick","error"+e.toString());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case PERMISSIONS_REQUEST_READ:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }
                break;
            case PERMISSIONS_REQUEST_WRITE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {

                }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void openEditText(View view) {


    }
}
