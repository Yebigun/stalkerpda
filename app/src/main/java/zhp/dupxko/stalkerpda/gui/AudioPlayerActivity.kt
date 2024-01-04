package zhp.dupxko.stalkerpda.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media3.ui.PlayerView
import zhp.dupxko.stalkerpda.R
import zhp.dupxko.stalkerpda.logic.audioplayer.PlayerService

class AudioPlayerActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var playerService: PlayerService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_player)

        //get the view
        playerView = findViewById(R.id.player_view)
        // Instantiate the player.
        playerService = PlayerService(this)
        // Attach player to the view.
        playerView.player = playerService.player
    }
}