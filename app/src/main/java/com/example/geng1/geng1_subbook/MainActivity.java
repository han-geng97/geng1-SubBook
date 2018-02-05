package com.example.geng1.geng1_subbook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by geng1 on 2/1/18.
 */

/**The main activity**/
public class MainActivity extends AppCompatActivity {

    private ListView oldSubList;
    private EditText bodyText;
    private EditText date;
    private EditText charge;
    private EditText comments;
    private static final String FILENAME = "file.sav";

    private TextView totalNum;
    private String tempUsage;
    private String tempDate;
    private String tempCharge;
    private String tempComment;


    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> adapter;

    private Button buttonAdd;
    //private Button buttonCancle;


    private int input_field;

    protected static int REQUEST_ADD = 10;
    protected static int REQUEST_EDIT = 11;
    protected static int RESULT_DELETE = 12;


    /////////////////

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        /////////////////
        oldSubList = findViewById( R.id.subscriptionList );
        buttonAdd = findViewById( R.id.button );
        //buttonCancle = findViewById( R.id.buttonCancle );
        subList = new ArrayList<>();
    }}
        /**buttonAdd.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             setResult( RESULT_OK );
                                             String text = bodyText.getText().toString();
                                             String text1 = date.getText().toString();
                                             String text2 = charge.getText().toString();
                                             String text3 = comments.getText().toString();

                                             subList.add( new Subscription( text, text1, text2, text3 ) );
                                             adapter.notifyDataSetChanged();
                                             saveInFile();
                                         }

                                         @Override
                                         protected void onStart() {
                                             // TODO Auto-generated method stub
                                             super.onStart();
                                             loadFromFile();
                                             adapter = new ArrayAdapter<>( this, R.layout.list_item, subList );
                                             oldSubList.setAdapter( adapter );


                                         }


                                         private void loadFromFile() {
                                             try {
                                                 FileInputStream fis = openFileInput( FILENAME );
                                                 BufferedReader in = new BufferedReader( new InputStreamReader( fis ) );

                                                 Gson gson = new Gson();

                                                 // Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
                                                 // 2018-01-26
                                                 Type listType = new TypeToken<ArrayList<Subscription>>() {
                                                 }.getType();
                                                 subList = gson.fromJson( in, listType );

                                             } catch (FileNotFoundException e) {
                                                 // TODO Auto-generated catch block
                                                 subList = new ArrayList<>();
                                             }
                                         }

                                         private void saveInFile() {
                                             try {
                                                 FileOutputStream fos = openFileOutput( FILENAME,
                                                         Context.MODE_PRIVATE );
                                                 BufferedWriter out = new BufferedWriter( new OutputStreamWriter( fos ) );
                                                 Gson gson = new Gson();
                                                 gson.toJson( subList, out );
                                                 out.flush();
                                             } catch (FileNotFoundException e) {
                                                 // TODO Auto-generated catch block
                                                 throw new RuntimeException();
                                             } catch (IOException e) {
                                                 // TODO Auto-generated catch block
                                                 throw new RuntimeException();
                                             }
                                         }
                                     }**/



/** there is some problem with my code, it could not function properly so I commented them out **/

/**
        buttonAdd.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult( new Intent( MainActivity.this, EditSub.class ),
                        REQUEST_ADD );
            }
        });

        oldSubList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {
                String usageStr = subList.get( x ).getUsage();
                String theDate = subList.get( x ).getDate();
                String theCharge = subList.get( x ).getCharge();
                String commentStr = subList.get( x ).getComment();
**/

                /** storing the strings into bundle **/
/**                Intent intent = new Intent( MainActivity.this, EditSub.class );
                Bundle bundle = new Bundle();
                bundle.putInt( "pos", x );
                bundle.putString( "usage", usageStr );
                bundle.putString( "date", theDate );
                bundle.putString( "charge", theCharge );
                bundle.putString( "comment", commentStr );

                intent.putExtras( bundle );
                startActivityForResult( intent, REQUEST_EDIT );

            }
        });

        ////
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<>(this, R.layout.list_item, subList);
        oldSubList.setAdapter(adapter);
        float totalMonthlyCharge = 0;
        for (Subscription S: subList){
            totalMonthlyCharge = totalMonthlyCharge + Float.parseFloat( S.getCharge() );
        }
        totalNum.setText("$ ".concat(String.format(Locale.CANADA,"%.2f",totalNum)));


    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        intent.putExtra("requestCode", requestCode);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_ADD) {
            Bundle bundle = intent.getExtras();
            if (bundle != null && !bundle.isEmpty()){
                try {
                    tempUsage = bundle.getString("usage");
                } catch(NullPointerException e) {
                    tempUsage = "";
                }
                try {
                    tempComment = bundle.getString("comment");
                } catch(NullPointerException e) {
                    tempComment = "";
                }
                subList.add(new Subscription(tempUsage,
                        bundle.getString("date"),
                        bundle.getString("charge"),tempComment));
                adapter.notifyDataSetChanged();
                saveInFile();

            }

        } else if(resultCode == Activity.RESULT_OK && resultCode == REQUEST_EDIT) {
            Bundle bundle = intent.getExtras();
            int pos = intent.getExtras().getInt("posBack");
            subList.get(pos).setUsage(tempUsage);
            subList.get(pos).setDate(bundle.getString("date"));
            subList.get(pos).setCharge(bundle.getString("charge"));
            subList.get(pos).setComment(tempComment);

            adapter.notifyDataSetChanged();
            saveInFile();

        } else if(resultCode == RESULT_DELETE) {
            int pos = intent.getExtras().getInt("posBack");
            subList.remove(pos);
            saveInFile();
        }
    }



    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-26
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subList = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            subList = new ArrayList<>();}
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}
**/




