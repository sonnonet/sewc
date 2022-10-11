import androidx.appcompat.app.AppCompatActivity;

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
    EditText jinputMessage;
    TextView jinputCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jinputMessage = findViewById(R.id.ainputMessage);
        jinputCount = findViewById(R.id.ainputCount);

        Button jsendBtn = findViewById(R.id.asendBtn);
        jsendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = jinputMessage.getText().toString();
                Toast.makeText(getApplicationContext(),"전송할 메시지\n\n" + message, Toast.LENGTH_LONG).show();
            }
        });

        Button jcloseBtn = findViewById(R.id.acloseBtn);
        jcloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before , int count) {
                byte[] bytes = null;
                try{
                    bytes = str.toString().getBytes("KSC5601");
                    int strCount = bytes.length;
                    jinputCount.setText(strCount + " / 80 바이트");

                }catch(UnsupportedEncodingException ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable strEditable) {
                String str = strEditable.toString();
                try{
                    byte[] strBytes = str.getBytes("KSC5601");
                    if(strBytes.length > 80){
                        strEditable.delete(strEditable.length()-2, strEditable.length()-1);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        };
        jinputMessage.addTextChangedListener(watcher);
    }
}
