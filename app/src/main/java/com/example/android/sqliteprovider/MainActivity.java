package com.example.android.sqliteprovider;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<ToDo> mWordList = new LinkedList<>();
    private int mCount = 0;

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check DB
        databaseHandler = new DatabaseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void gotoAdd(View view) {
        Intent intent = new Intent(this, AddTodo.class);
//        startActivity(intent);
        startActivityForResult(intent, 1);
    }

    public void setRecyclerView(){
        mWordList = databaseHandler.findAll();
        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new RecyclerViewAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.Callback callback = new SwipeHelper(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1){
            Log.d("new name : ",data.getStringExtra("name"));
            Log.d("new desc : ",data.getStringExtra("desc"));
            Log.d("new priority : ",data.getStringExtra("priority"));
            databaseHandler.save(new ToDo(data.getStringExtra("name"), data.getStringExtra("desc"), data.getStringExtra("priority")));
        }
        setRecyclerView();
        mAdapter.notifyDataSetChanged();
    }
}
