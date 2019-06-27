package com.example.cursojava2;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.cursojava2.App.CHANNEL_1_ID;
import static com.example.cursojava2.App.CHANNEL_2_ID;

public class NotificacioesActivity extends AppCompatActivity implements View.OnClickListener{

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    String title, message;
    private Button sendOnChannel2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacioes);


        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);


        sendOnChannel2=(Button) findViewById(R.id.sendOnChannel2);
        sendOnChannel2.setOnClickListener(this);


    }

    public void sendOnChannel1(View v) {
        if (validacionp()) {
            String title = editTextTitle.getText().toString();
            String message = editTextMessage.getText().toString();

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_one)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .build();

            notificationManager.notify(1, notification);
        }
    }

    public void onClick(View v) {

        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        if (v.getId() == R.id.sendOnChannel2) {
            if (validacionp()) {

                Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                        .setSmallIcon(R.drawable.ic_two)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .build();

                notificationManager.notify(2, notification);
            }
        }
    }

    private boolean validacionp() {

        boolean isVañidated = true;

        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        if (title.equals("")){
            editTextTitle.setError("Required");
            isVañidated = false;
        }
        else if (message.equals("")){
            editTextMessage.setError("Required");
            isVañidated=false;
        }
        return isVañidated;
    }

}