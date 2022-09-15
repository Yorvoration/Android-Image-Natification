package app.test.mynotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private val Channel_id = "Astrum Students"
    private val notificationID = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_button = findViewById<Button>(R.id.btn_button)

        createNotificationChannel()

        btn_button.setOnClickListener{
            sendNotification()
        }

    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification description"
            val importance: Int =NotificationManager.IMPORTANCE_DEFAULT
            val channel: NotificationChannel = NotificationChannel(Channel_id, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {



        val intent: Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val bitmap: Bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.boyogli)
        val bitmapLargeIcon: Bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.mushuk)





        val builder = NotificationCompat.Builder(this, Channel_id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Asadbek Doimjonov")
            .setContentText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")


            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap)) //BigTextStyle.bigText("Asadbek Doimjonov 2004-yil 2-oktabrda tug'ilgan")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(NotificationManagerCompat.from(this)){
            notify(notificationID, builder.build())
        }
    }

}