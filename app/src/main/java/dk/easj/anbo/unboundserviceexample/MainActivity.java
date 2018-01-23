package dk.easj.anbo.unboundserviceexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Adapted from
 * Android Notes for Professionals, chapter 18, http://books.goalkicker.com/AndroidBook/
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            Thread.sleep(1);
//            startOrStopService();
//            Thread.sleep(1);
//            makeServiceDoSomething();
//        } catch (InterruptedException e) {
//            Log.e("MINE", e.getMessage());
//        }
    }

    public void startOrStopService() {
        if (RecordingService.isRunning) {
            // Stop service
            Intent intent = new Intent(this, RecordingService.class);
            stopService(intent);
        } else {
            // Start service
            Intent intent = new Intent(this, RecordingService.class);
            startService(intent);
        }
    }

    public void makeServiceDoSomething(CharSequence message) {
        if (RecordingService.isRunning)
            RecordingService.instance.doSomething(message);
    }

    public void startStopServiceClicked(View view) {
        startOrStopService();
    }

    public void pingServiceClicked(View view) {
        EditText messageInputView = findViewById(R.id.mainMessageEditText);
        CharSequence message = messageInputView.getText();
        makeServiceDoSomething(message);
    }
}
