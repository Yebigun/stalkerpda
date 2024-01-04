package zhp.dupxko.stalkerpda.gui

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.ui.PlayerView
import zhp.dupxko.stalkerpda.R
import zhp.dupxko.stalkerpda.logic.audioplayer.PlayerService

class AudioPlayerActivity : AppCompatActivity() {
    private lateinit var playerView: PlayerView
    private lateinit var playerService: PlayerService

    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_player)
        //get the view
        playerView = findViewById(R.id.player_view)
        // Instantiate the player.
        playerService = PlayerService(this)
        // Attach player to the view.
        val player = playerService.player
        playerView.player = player

        val audioId: Int = intent.getIntExtra("testuwa", 0)
// Build the media item.
        val uri = RawResourceDataSource.buildRawResourceUri(audioId)
        val mediaItem = MediaItem.fromUri(uri)
// Set the media item to be played.
        player.setMediaItem(mediaItem)
// Prepare the player.
        player.prepare()
// Start the playback.
        player.play()
    }
}