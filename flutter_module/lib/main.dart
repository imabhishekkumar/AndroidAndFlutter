import 'package:flutter/material.dart';
import 'package:flutter_module/communicate_with_native.dart';
import 'package:flutter_module/default_flutter_app.dart';

void main() => runApp(const MyApp());

@pragma('vm:helloRoute')
void helloWorld() => runApp(const Text(
      "Hello World",
      textDirection: TextDirection.ltr,
    ));

@pragma('vm:forSpecificRoute')
void defaultFlutterApp() => runApp(const DefaultFlutterApp());

@pragma('vm:forSpecificRoute')
void specificRoute() => runApp(const MyApp(initialRoute: ""));

@pragma('vm:forDataPassRoute')
void dataPassRoute() => runApp(const CommunicateWithNativeScreen());

class MyApp extends StatefulWidget {
  final String? initialRoute;

  const MyApp({Key? key, this.initialRoute}) : super(key: key);

  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
          body: Center(
        child: widget.initialRoute == null
            ? Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: const [
                  FlutterLogo(
                    size: 60,
                  ),
                  SizedBox(
                    height: 50,
                  ),
                  Text("Hello from Flutter")
                ],
              )
            : Column(
                children: const [
                  Text("You have navigated to a specific route"),
                ],
              ),
      )),
    );
  }
}
