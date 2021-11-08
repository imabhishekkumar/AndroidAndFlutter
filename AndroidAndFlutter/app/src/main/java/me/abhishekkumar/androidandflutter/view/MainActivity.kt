package me.abhishekkumar.androidandflutter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import me.abhishekkumar.androidandflutter.databinding.ActivityMainBinding
import me.abhishekkumar.androidandflutter.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.activity = this
    }

    fun navigateToHelloWorld(){
        startActivity(
            FlutterActivity
                .withCachedEngine(Constants.ENGINE_ID_HELLO)
                .build(this)
        )
    }

    fun navigateToDefaultFlutterApp(){
        startActivity(
            FlutterActivity
                .withCachedEngine(Constants.ENGINE_ID_DEFAULT)
                .build(this)
        )
    }

    fun navigateToFlutterActivity() {
        startActivity(
            FlutterActivity
                .createDefaultIntent(this)
        )
    }

    fun navigateToFlutterActivityWithCacheEngine() {
        startActivity(
            FlutterActivity
                .withCachedEngine(Constants.ENGINE_ID_ONE)
                .build(this)
        )
    }

    fun navigateToActivityWhereFlutterIsUsedAsFragment() {
        startActivity(
            Intent(this, FlutterFragmentContainerActivity::class.java)
        )
    }

    fun navigateToASpecificRouteInFlutterModule() {
        startActivity(
            Intent(this, FlutterFragmentContainerActivity::class.java).apply {
                putExtra(Constants.ROUTE, Constants.ENTRY_POINT)
            }
        )
    }

    fun navigateToFlutterContainerActivity() {
        startActivity(
            FlutterActivity.withCachedEngine(Constants.ENGINE_ID_THREE)
                .build(this)
        )
    }


}