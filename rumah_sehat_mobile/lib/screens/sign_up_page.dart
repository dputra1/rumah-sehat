part of "pages.dart";

class SignUpPage extends StatefulWidget{
  static String routeName = "/SignUpPage";
  
  static Route<void> route(){
    return MaterialPageRoute(builder: (_) => SignUpPage());
  }
  
  @override
  _SignUpPageState createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage>{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    throw UnimplementedError();
  }
  
}
