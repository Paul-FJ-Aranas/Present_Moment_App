package com.paularanas.presentmomentapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

import static com.paularanas.presentmomentapp.R.array.quotes;
import static com.paularanas.presentmomentapp.R.styleable.CompoundButton;

public class MainActivity extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener {
    ToggleButton toggleButton;
    String[] quoteArray;
    TextView quote_view;
    private boolean firstQuote = true;
    int previousIndex = 0;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        quote_view = (TextView) findViewById(R.id.quote_text);
        quoteArray = getResources().getStringArray(quotes);
        Random random = new Random();
        index = random.nextInt(4);
        String quote = quoteArray[index];
        quote_view.setText(quote);
        toggleButton = (ToggleButton) findViewById(R.id.time_button);
        toggleButton.setOnCheckedChangeListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void newQuote(View view) {
        quoteArray = getResources().getStringArray(quotes);
        Random random = new Random();

        //prevent duplicate quotes on two consecutive clicks
        if (!firstQuote) {
            index = random.nextInt(4);
            while (index == previousIndex) {
                index = random.nextInt(4);
            }
            String quote = quoteArray[index];
            quote_view.setText(quote);
            previousIndex = index;
        } else {
            index = random.nextInt(4);
            String quote = quoteArray[index];
            quote_view.setText(quote);
            previousIndex = index;
            firstQuote = false;
        }

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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        TextView time = (TextView) findViewById(R.id.time_text);
        time.setText(getResources().getString(R.string.the_time_is_now));

        if (b) {
            time.setVisibility(View.VISIBLE);
        } else {
            time.setVisibility(View.INVISIBLE);
        }

    }
}
