package com.subhambnikash.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput

class MainActivity : AppCompatActivity() {

    private val channelId = "com.anushka.notificationdemo.channel1"
    private lateinit var notificationManager:NotificationManager
    private lateinit var button: Button

    private val remoteKey="KEYS_DATA"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button)
        notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelId,"dsfdsf",":etreste")


        button.setOnClickListener {
            showNotification()
        }



    }


    @SuppressLint("UnspecifiedImmutableFlag")
    private fun showNotification(){

        val intent=Intent(this,MainActivity2::class.java)
        val pendingIntent=PendingIntent.getActivities(this,0, arrayOf(intent),PendingIntent.FLAG_CANCEL_CURRENT)

        val intent1=Intent(this,MainActivity3::class.java)
        val pendingIntentAction=PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_CANCEL_CURRENT)

        val actionCompat=NotificationCompat.Action.Builder(0,"setting",pendingIntentAction).build()

        val remoteInput=RemoteInput.Builder(remoteKey).run {
            setLabel("enter your name?")
            build()
        }

        val replyAction=NotificationCompat.Action.Builder(0,"replay",pendingIntentAction).addRemoteInput(remoteInput).build()










        val createNotification=NotificationCompat.Builder(this,channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentText("this hi")
            title = "subhuman"
            setContentTitle("skjajksa")
            priority=NotificationCompat.PRIORITY_HIGH
            setAutoCancel(true)
            setContentIntent(pendingIntent)
            addAction(actionCompat)
            addAction(replyAction)

        }.build()
        notificationManager.notify(45,createNotification)
    }


    private fun createNotificationChannel(id:String,name:String,channelDescription:String){
        val importance=NotificationManager.IMPORTANCE_HIGH
        val channel=NotificationChannel(id,name,importance).apply {
            description=channelDescription
        }
        notificationManager.createNotificationChannel(channel)

    }





}