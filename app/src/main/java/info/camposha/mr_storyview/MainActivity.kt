package info.camposha.mr_storyview

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import info.camposha.mr_storyview.databinding.ActivityMainBinding
import omari.hamza.storyview.StoryView
import omari.hamza.storyview.callback.StoryClickListeners
import omari.hamza.storyview.model.MyStory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.btn.setOnClickListener { showStories() }
    }

    fun showStories() {
        val myStories = ArrayList<MyStory>()
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
        try {
            val story1 = MyStory(
                "https://media.pri.org/s3fs-public/styles/story_main/public/images/2019/09/092419-germany-climate.jpg?itok=P3FbPOp-",
                simpleDateFormat.parse("20-10-2019 10:00:00")
            )
            myStories.add(story1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        try {
            val story2 = MyStory(
                "http://i.imgur.com/0BfsmUd.jpg",
                simpleDateFormat.parse("26-10-2019 15:00:00"),
                "#TEAM_STANNIS"
            )
            myStories.add(story2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val story3 = MyStory(
            "https://mfiles.alphacoders.com/681/681242.jpg"
        )
        myStories.add(story3)
        StoryView.Builder(supportFragmentManager)
            .setStoriesList(myStories)
            .setStoryDuration(5000)
            .setTitleText("Hamza Al-Omari")
            .setSubtitleText("Damascus")
            .setStoryClickListeners(object : StoryClickListeners {
                override fun onDescriptionClickListener(position: Int) {
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked: " + myStories[position].description,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onTitleIconClickListener(position: Int) {}
            })
            .setOnStoryChangedCallback { position ->
                Toast.makeText(
                    this@MainActivity,
                    position.toString() + "",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setStartingIndex(0)
            .build()
            .show()
    }
}