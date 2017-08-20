package com.bkdev.translation.ui.activities;

import android.content.Intent;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bkdev.translation.R;
import com.bkdev.translation.adapters.PersonRecyclerAdapter;
import com.bkdev.translation.database.PersonManager;
import com.bkdev.translation.model.person.Person;
import com.bkdev.translation.recyclers.RecyclerTouchListener;
import com.bkdev.translation.ui.BaseActivity;
import com.bkdev.translation.ui.dialogs.TestDialog;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;


@EActivity(R.layout.activity_main2)

public class Main2Activity extends BaseActivity {

    @ViewById
    EditText mEdtName;
    @ViewById
    EditText mEdtClassName;
    @ViewById
    Button mBtnAdd;
    @ViewById
    Button mBtnMove;
    @ViewById
    Button mBtnShowDialog;
    @ViewById
    RecyclerView mRcvPersons;
    @ViewById(R.id.mTvHello)
    TextView mTvHello;

    private PersonManager mPersonManager;
    private PersonRecyclerAdapter mPersonRecyclerAdapter;
    private List<Person> mPersons;
    private ShareDialog shareDialog;
    private Target loadtarget;
    private  Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
    }

    @Override
    protected void init() {
        loadBitmap("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg");
        shareDialog = new ShareDialog(this);
        Realm.init(this);
        mPersonManager = new PersonManager();
        mPersons = mPersonManager.getPersons();
        mPersonRecyclerAdapter = new PersonRecyclerAdapter(this, mPersons);
        mRcvPersons.setLayoutManager(new LinearLayoutManager(this));
        mRcvPersons.setAdapter(mPersonRecyclerAdapter);
        mRcvPersons.addOnItemTouchListener(new RecyclerTouchListener(this, mRcvPersons, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(), mPersons.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(), mPersons.get(position).getClassName(), Toast.LENGTH_SHORT).show();

            }
        }));


    }

    protected void facebookSDKInitialize() {

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

    }


    @Click(R.id.mBtnAdd)
    void addPerson() {
        String name, className;
        if (!(name = mEdtName.getText().toString()).equals("") && !(className = mEdtClassName.getText().toString()).equals("")) {
            Person person = new Person();
            person.setName(name);
            person.setClassName(className);
            if (mPersonManager.createPerson(person) > 0) {
                mPersons.add(person);
                mPersonRecyclerAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Add ok", Toast.LENGTH_SHORT).show();
                mEdtName.setText("");
                mEdtClassName.setText("");
            } else {
                Toast.makeText(this, "Add fail", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.mBtnMove)
    void move() {
        Intent intent = BottomNavActivity_.intent(this).myMessage("hello").get();
        startActivity(intent);

    }

    @Click(R.id.mBtnShowDialog)
    void showDialog(){
//        TestDialog testDialog= new TestDialog();
//        testDialog.show(this.getSupportFragmentManager(),"");
share();
    }

    private void share(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"aaaaaaaaaaaaaaaaa");
        startActivity(Intent.createChooser(intent, "Share with"));
    }

    private void shareToFb(){
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                    .setContentUrl(Uri.parse("https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20478936_1884259261836234_7612737564025290752_n.jpg"))
                    .build();

            shareDialog.show(linkContent);  // Show facebook ShareDialog
        }
    }

    private  void shareImageToFB(){
            if(bitmap!=null){
                SharePhoto photo = new SharePhoto.Builder()
                        .setCaption("aaaaaaaaaaaaaaaaaaa")
                        .setUserGenerated(true)
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareDialog.show(content);  // Show facebook ShareDialog
            }


    }

    @Background
    void doBackGround() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
            }

            String message = i + "";

            updateUI(message);
        }
    }




        public void loadBitmap(String url) {
            Log.i("TAG7","load ");
            if (loadtarget == null) loadtarget = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    handleLoadedBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };

            Picasso.with(this).load(url).into(loadtarget);

        }

        public void handleLoadedBitmap(Bitmap b) {
            Log.i("TAG7","load OK");
            bitmap=b;
        }


    @UiThread
    void updateUI(String message) {
        mTvHello.setText(message);
    }

}
