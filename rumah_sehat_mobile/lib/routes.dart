import 'package:flutter/widgets.dart';
import 'package:rumah_sehat_mobile/screens/pages.dart';

// We use name route
// All our routes will be available here
final Map<String, WidgetBuilder> routes = {
  SignInPage.routeName: (context) => SignInPage(),
  SignUpPage.routeName: (context) => SignUpPage(),
  SplashPage.routeName: (context) => SplashPage(),
  ListTagihanPage.routeName: (context) => ListTagihanPage(),
  AppointmentPage.routeName: (context) => AppointmentPage()
};
