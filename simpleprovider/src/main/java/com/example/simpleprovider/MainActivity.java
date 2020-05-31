package com.example.simpleprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickAddName(View view) {
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(StudentsProvider.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentsProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();

    }

    public void onClickRetrieveStudents(View view) {
        String URL = "content://com.example.simpleprovider.StudentsProvider";

        Uri students = Uri.parse(URL);
        Cursor cursor = getContentResolver().query(students, null, null, null, "name");
        if (cursor.moveToFirst()) {
            do {
                Toast.makeText(this, cursor.getString(cursor.getColumnIndex(StudentsProvider._ID)) +
                                ", " + cursor.getString(cursor.getColumnIndex(StudentsProvider.NAME)) +
                                ", " + cursor.getString(cursor.getColumnIndex(StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
    }
}
