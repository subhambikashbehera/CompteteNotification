package com.subhambnikash.notification

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MainActivity3 : AppCompatActivity() {

    private val channelId = "com.anushka.notificationdemo.channel1"
    private val notificationID=45
   var textView:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        textView=findViewById(R.id.textView)

        setRemoteView()


    }

    private fun setRemoteView() {
        val intent=this.intent
        val remoteInputInstance=RemoteInput.getResultsFromIntent(intent)
        if (remoteInputInstance!=null){
            val data=remoteInputInstance.getCharSequence("KEYS_DATA")
            textView?.text=data.toString()

            val notificationCompa=NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(data)
                .setContentTitle("sent")

            val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationID,notificationCompa.build())
        }
    }


}