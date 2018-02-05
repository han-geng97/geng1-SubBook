package com.example.geng1.geng1_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by geng1 on 2/1/18.
 */

public class EditSub extends AppCompatActivity {




    private EditText usage;
    private EditText date;
    private EditText charge;
    private EditText comment;
    private Button buttonAdd;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_sub);
        usage = findViewById(R.id.usageText);
        date = findViewById(R.id.dateText);
        charge = findViewById(R.id.chargeText);
        comment = findViewById(R.id.commentText);

        buttonAdd = findViewById(R.id.buttonAdd2);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                try {
                    if (usage.getText().toString().equals(""))
                        throw new EmptyInputException();
                    Float.valueOf(charge.getText().toString());
                    new SimpleDateFormat("yyyy-MM-dd").parse(date.getText().toString());
                    if(comment.getText().toString().length() > 30
                            || usage.getText().toString().length() > 20)
                        throw new TooLongException();
                } catch (EmptyInputException e) {
                    Snackbar.make(buttonAdd, getResources().getString(R.string.usageError), 1000).show();
                    setResult(RESULT_CANCELED);
                    return;
                } catch (ParseException e) {
                    Snackbar.make(buttonAdd, getResources().getString(R.string.dateError), 1000).show();
                    setResult(RESULT_CANCELED);
                    return;
                } catch (NumberFormatException e) {
                    Snackbar.make(buttonAdd, getResources().getString(R.string.chargeError), 1000).show();
                    setResult(RESULT_CANCELED);
                    return;
                } catch (TooLongException e) {
                    Snackbar.make(buttonAdd, "Don't write a novel here!", 1000).show();
                    setResult(RESULT_CANCELED);
                }
                Intent returnIntent = new Intent(EditSub.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",usage.getText().toString());
                bundle.putString("date",date.getText().toString());
                bundle.putFloat("amount",Float.valueOf(charge.getText().toString()));
                bundle.putString("comment",comment.getText().toString());
                returnIntent.putExtras(bundle);
                setResult(RESULT_OK, returnIntent);
                finish();


            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

//    }

    @Override
    public void onStart(){
        super.onStart();
        Intent receiveIntent = getIntent();
        Bundle bundle = receiveIntent.getExtras();
        if (bundle != null && !bundle.isEmpty()){
            usage.setText(bundle.getString("name"));
            charge.setText(String.format( Locale.CANADA, "%.2f", bundle.getFloat("amount")));
            date.setText(bundle.getString("date"));
            comment.setText(bundle.getString("comment"));
        }
        if (receiveIntent.getIntExtra("requestCode", 0) == MainActivity.REQUEST_EDIT){
            buttonDelete.setVisibility(View.VISIBLE);
            buttonAdd.setText("Save");
        }
    }


}
