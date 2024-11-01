// import 'dart:js';

import 'package:flutter/widgets.dart';
import 'package:rumah_sehat_mobile/screens/pages.dart';

// We use name route
// All our routes will be available here
final Map<String, WidgetBuilder> routes = {
  SignInPage.routeName: (context) => SignInPage(),
  SignUpPage.routeName: (context) => SignUpPage(),
  SplashPage.routeName: (context) => SplashPage(),
  AddAppointmentPage.routeName: (context) => AddAppointmentPage(),
  TagihanPage.routeName: (context) => TagihanPage(listTagihan: [],),
  AppointmentPage.routeName: (context) => AppointmentPage(listAppointment: [],),
  TopUpPage.routeName: (context) => TopUpPage(),
  DetailAppointmentPage.routeName: (context) => DetailAppointmentPage(kodeAppointment: '123123',)
};
