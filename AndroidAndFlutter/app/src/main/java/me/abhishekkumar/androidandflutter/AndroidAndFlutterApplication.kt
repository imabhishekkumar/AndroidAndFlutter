package me.abhishekkumar.androidandflutter

import android.app.Application
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.view.FlutterMain
import me.abhishekkumar.androidandflutter.utils.Constants
import me.abhishekkumar.androidandflutter.utils.Utils


class AndroidAndFlutterApplication : Application() {
    private lateinit var flutterEngine: FlutterEngine
    private lateinit var flutterEngineWithEntryPoint: FlutterEngine
    private lateinit var flutterEngineWithHelloWorldEntryPoint : FlutterEngine
    private lateinit var flutterEngineWithEntryPointDataPass : FlutterEngine
    private lateinit var flutterEngineWithEntryPointDefaultApp : FlutterEngine

    override fun onCreate() {
        super.onCreate()
        initDefaultFlutterEngine()
        initFlutterEngineWithHelloWorldEntryPoint()
        initFlutterEngineWithEntryPoint()
        initFlutterDataPassEngineWithEntryPoint()
        initFlutterEngineWithDefaultAppEntryPoint()
    }

    private fun initDefaultFlutterEngine() {
        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(Constants.ENGINE_ID_ONE, flutterEngine)

    }

    private fun initFlutterEngineWithHelloWorldEntryPoint() {
        // Instantiate a FlutterEngine.
        flutterEngineWithHelloWorldEntryPoint = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngineWithHelloWorldEntryPoint.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterMain.findAppBundlePath(), Constants.ENTRY_HELLO
            )
        )


        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(Constants.ENGINE_ID_HELLO, flutterEngineWithHelloWorldEntryPoint)
    }

    private fun initFlutterEngineWithDefaultAppEntryPoint() {
        // Instantiate a FlutterEngine.
        flutterEngineWithEntryPointDefaultApp = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngineWithEntryPointDefaultApp.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterMain.findAppBundlePath(), Constants.ENTRY_DEFAULT_APP
            )
        )


        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(Constants.ENGINE_ID_DEFAULT, flutterEngineWithEntryPointDefaultApp)
    }

    private fun initFlutterEngineWithEntryPoint() {
        // Instantiate a FlutterEngine.
        flutterEngineWithEntryPoint = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngineWithEntryPoint.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterMain.findAppBundlePath(), Constants.ENTRY_POINT
            )
        )


        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(Constants.ENGINE_ID_TWO, flutterEngineWithEntryPoint)
    }

    private fun initFlutterDataPassEngineWithEntryPoint() {

        val channel = "flutter/defaultChannel"
        // Instantiate a FlutterEngine.
        flutterEngineWithEntryPointDataPass = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngineWithEntryPointDataPass.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterMain.findAppBundlePath(), Constants.DATA_PASS_ENTRY_POINT
            )
        )
        GeneratedPluginRegistrant.registerWith(flutterEngineWithEntryPointDataPass)

        val mc = MethodChannel(flutterEngineWithEntryPointDataPass.dartExecutor.binaryMessenger, channel)
        mc.setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
            if (methodCall.method == "getTextFromNative") {
                result.success(Utils.getSampleText())
            }
        }
        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(Constants.ENGINE_ID_THREE, flutterEngineWithEntryPointDataPass)
    }


}