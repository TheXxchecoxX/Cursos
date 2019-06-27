package com.example.kotlincurso2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.view.View
import android.widget.Button
import android.widget.EditText


import com.example.kotlincurso2.App.Companion.CHANNEL_1_ID
import com.example.kotlincurso2.App.Companion.CHANNEL_2_ID

class NotificacioesActivity : AppCompatActivity(),View.OnClickListener  {
    private var notificationManager: NotificationManagerCompat? = null
    private var editTextTitle: EditText? = null
    private var editTextMessage: EditText? = null
    internal var title: String? = null
    internal var message:String? = null
    private var sendOnChannel2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacioes)


        notificationManager = NotificationManagerCompat.from(this)

        editTextTitle = findViewById(R.id.edit_text_title)
        editTextMessage = findViewById(R.id.edit_text_message)


        sendOnChannel2 = findViewById(R.id.sendOnChannel2) as Button
        sendOnChannel2!!.setOnClickListener(this)


    }

    fun sendOnChannel1(v: View) {
        if (validacionp()) {
            val title = editTextTitle?.getText().toString()
            val message = editTextMessage?.getText().toString()

            val notification = NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build()

            notificationManager?.notify(1, notification)
        }
    }

    override fun onClick(v: View) {

        val title = editTextTitle?.getText().toString()
        val message = editTextMessage?.getText().toString()

        if (v.id == R.id.sendOnChannel2) {
            if (validacionp()) {

                val notification = NotificationCompat.Builder(this, CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_two)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build()

                notificationManager?.notify(2, notification)
            }
        }
    }

    private fun validacionp(): Boolean {

        var isVañidated = true

        val title = editTextTitle?.getText().toString()
        val message = editTextMessage?.getText().toString()

        if (title == "") {
            editTextTitle?.setError("Required")
            isVañidated = false
        } else if (message == "") {
            editTextMessage?.setError("Required")
            isVañidated = false
        }
        return isVañidated
    }

}