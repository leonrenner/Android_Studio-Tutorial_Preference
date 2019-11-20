package com.leonrenner.preferencetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_PRIVATE = "private";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.OnSharedPreferenceChangeListener sharedPrefListener;

    private boolean privateMode;

    private TextView preferenceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceTextView = findViewById(R.id.preferenceTextView);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPrefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                privateMode = sharedPreferences.getBoolean(KEY_PRIVATE, false);
                setPrivacy();
            }
        };
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPrefListener);

        privateMode = sharedPreferences.getBoolean(KEY_PRIVATE, false);
        setPrivacy();

    }

    public void setPrivacy() {
        if (privateMode) {
            // GONE will influence the layout
            preferenceTextView.setVisibility(View.GONE);
        } else {
            preferenceTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
