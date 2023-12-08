package zhp.dupxko.stalkerpda.logic

import android.content.Context
import androidx.core.app.NotificationCompat
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
