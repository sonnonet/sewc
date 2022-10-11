

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    myDBHelper myDBHelper;
    EditText jinputMessage,msgResult;
    TextView jinputCount;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jinputMessage = findViewById(R.id.ainputMessage);
        jinputCount = findViewById(R.id.ainputCount);
        msgResult = findViewById(R.id.msgResult);
        Button jsendBtn = findViewById(R.id.asendBtn);
        myDBHelper = new myDBHelper(this);
        jsendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Button jcloseBtn = findViewById(R.id.acloseBtn);
        jcloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button initBtn = findViewById(R.id.initBtn);
        initBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });
        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO sms VALUES ( '" + jinputMessage.getText().toString() + "' )");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",Toast.LENGTH_LONG).show();

            }
        });
        Button selectBtn = findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM sms;",null);

                String strSms = "메세지 : "+"\r\n";

                while (cursor.moveToNext()){
                   strSms += cursor.getString(0) + "\r\n";
                }
                msgResult.setText(strSms);
                cursor.close();
                sqlDB.close();
            }
        });

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {
                byte[] bytes = null;
                try{
                    bytes = str.toString().getBytes("KSC5601");
                    int strCount = bytes.length;
                    jinputCount.setText(strCount + " / 80 바이트");
                }catch (UnsupportedEncodingException ex){
                    ex.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable streditable) {
                String str = streditable.toString();
                try{
                    byte[] strBytes = str.getBytes("KSC5601");
                    if(strBytes.length>80){
                        streditable.delete(streditable.length()-2,streditable.length()-1);
                    }

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        jinputMessage.addTextChangedListener(watcher);
    }
    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE sms ( message CHAR(80));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS sms");
            onCreate(db);

        }
    }
}
