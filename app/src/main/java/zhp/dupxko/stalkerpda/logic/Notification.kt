package zhp.dupxko.stalkerpda.logic

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import zhp.dupxko.stalkerpda.MainActivity
import zhp.dupxko.stalkerpda.R

class Notification : Service() {

    private val CHANNEL_ID = "diary_eneterance"
    private val notificationId = 111

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    fun createNotificationChannel(a: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "a"
            val descriptionText = "aaa"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        }
    }

    private fun sendNotification() {


        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Title")
            .setContentText("description")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
//          to chyba do main
//        with(NotificationManagerCompat.from()){
//            if (ActivityCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return
//            }
//            notify(notificationId, builder.build())
//        }

    }

}