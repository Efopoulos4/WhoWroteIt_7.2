package com.example.whowroteit_72;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.koushikdutta.ion.Ion;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText mUrlInput;
    private TextView mTextView;
    private Spinner mSpinner;
    private String spinnerString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrlInput = findViewById(R.id.URL_input);
        mTextView = findViewById(R.id.textView_page_source);

        mSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

    }

    public void searchBooks(View view) {
        String queryUrl = mUrlInput.getText().toString();
        queryUrl = spinnerString.toLowerCase()+"://"+queryUrl;
        Ion.with(getApplicationContext())
                .load(queryUrl)
                .asString()
                .setCallback((e, result) -> mTextView.setText(result));
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerString = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}