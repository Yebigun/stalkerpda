package zhp.dupxko.stalkerpda.logic.audioplayer

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession

class PlayerService(
    val context: Context
) {

    val player: Player
    val session: MediaSession
    init {
        player = ExoPlayer.Builder(context)
            .setAudioAttributes(
                AudioAttributes.DEFAULT,
                /* handleAudioFocus */ true
            )
            .setHandleAudioBecomingNoisy(true)
            .setWakeMode(C.WAKE_MODE_LOCAL)
            .build()
        session = MediaSession.Builder(context, player).build()
    }
}