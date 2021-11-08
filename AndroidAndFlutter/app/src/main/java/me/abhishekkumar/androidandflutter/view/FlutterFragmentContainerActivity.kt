package me.abhishekkumar.androidandflutter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterFragment
import me.abhishekkumar.androidandflutter.R
import me.abhishekkumar.androidandflutter.databinding.ActivityFlutterFragmentContainerBinding
import me.abhishekkumar.androidandflutter.utils.Constants

class FlutterFragmentContainerActivity : AppCompatActivity() {
    companion object {
        // Define a tag String to represent the FlutterFragment within this
        // Activity's FragmentManager.
        private const val TAG_FLUTTER_FRAGMENT = "flutter_fragment"
    }

    private var flutterFragment: FlutterFragment? = null
    private lateinit var binding: ActivityFlutterFragmentContainerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlutterFragmentContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        flutterFragment = supportFragmentManager
            .findFragmentByTag(TAG_FLUTTER_FRAGMENT) as FlutterFragment?

        val route: String? = intent.extras?.get(Constants.ROUTE) as String?
        if (flutterFragment == null) {
            when (route) {
                Constants.ENTRY_POINT -> initFlutterFragmentWithCachedEngine(Constants.ENGINE_ID_TWO)
                else -> initFlutterFragmentWithCachedEngine(Constants.ENGINE_ID_ONE)
            }

        }
    }

    private fun initFlutterFragmentWithCachedEngine(engineId: String) {
        val newFlutterFragment =
            FlutterFragment.withCachedEngine(engineId).build<FlutterFragment>()
        flutterFragment = newFlutterFragment

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragmentContainer,
                newFlutterFragment
            )
            .commit()
    }


}