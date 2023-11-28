package zhp.dupxko.stalkerpda.logic

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import zhp.dupxko.stalkerpda.R

class LocationForegroundService : Service() {

    private val CHANNEL_ID = "diary_eneterance"
    private val notificationId = 111

    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("gaysex", "onstart")
        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Run is active")
            .setContentText("peepee")
            .build()
        startForeground(1,notification)
    }

    enum class Actions {
        START, STOP
    }

    override fun onCreate() {
        //todo podejrzewam że tu trzeba będzie utworzyć obiekty związane z powiadomieniem
        super.onCreate()
    }

    override fun onDestroy() {
        //todo podejrzewam że tu trzeba będzie zniszczyć/uwolnić obiekty związane z powiadomieniem
        super.onDestroy()
    }

}