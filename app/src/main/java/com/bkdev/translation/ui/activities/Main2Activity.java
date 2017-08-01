package com.bkdev.translation.ui.activities;

import android.content.Intent;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

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

    @Override
    protected void init() {
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
        TestDialog testDialog= new TestDialog();
        testDialog.show(this.getSupportFragmentManager(),"");
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

    @UiThread
    void updateUI(String message) {
        mTvHello.setText(message);
    }

}
