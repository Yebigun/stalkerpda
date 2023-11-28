package zhp.dupxko.stalkerpda.logic

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import zhp.dupxko.stalkerpda.R

fun prepareNotification(
    ctx: Context,
    channelId: String,): NotificationCompat.Builder {
    return NotificationCompat.Builder(ctx, channelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("Title")
        .setContentText("description")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
}
