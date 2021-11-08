import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class CommunicateWithNativeScreen extends StatefulWidget {
  const CommunicateWithNativeScreen({Key? key}) : super(key: key);

  @override
  _CommunicateWithNativeScreenState createState() =>
      _CommunicateWithNativeScreenState();
}

class _CommunicateWithNativeScreenState
    extends State<CommunicateWithNativeScreen> {
  static const platform = MethodChannel('flutter/defaultChannel');

  String _textFromNative = "";
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ElevatedButton(
                child: Text('Get Text From Native'),
                onPressed: _getTextFromNative,
              ),
              Text(_textFromNative),
            ],
          ),
        ),
      ),
    );
  }

  Future<void> _getTextFromNative() async {
    String textFromNative;
    try {
      final String result = await platform.invokeMethod('getTextFromNative');
      textFromNative = result;
    } on PlatformException catch (e) {
      textFromNative = "Failed to get text: '${e.message}'.";
    }

    setState(() {
      _textFromNative = textFromNative;
    });
  }
}
