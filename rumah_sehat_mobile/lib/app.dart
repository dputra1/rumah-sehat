import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_bloc.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_event.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_state.dart';
import 'package:rumah_sehat_mobile/repository/user_repository.dart';
import 'package:rumah_sehat_mobile/routes.dart';
import 'package:rumah_sehat_mobile/screens/pages.dart';

class MyApp extends StatelessWidget {
  final _navigatorKey = GlobalKey<NavigatorState>();

  NavigatorState? get _navigator => _navigatorKey.currentState;
  final secureStorage = FlutterSecureStorage();

  @override
  Widget build(BuildContext context) {
    final userRepository = UserRepository(storage: secureStorage);
    return BlocProvider<AuthenticationBloc>(
      create: (context) {
        return AuthenticationBloc(userRepository: userRepository)..add(AppStarted());
      },
      // child: BlocListener(
      //   listener: (context, state) {
      //     print('halo');
      //     print(state.status == AuthenticationStatus.unauthenticated);
      //     if (state.status == AuthenticationStatus.unauthenticated) {
      //       _navigator.pushAndRemoveUntil<void>(
      //           MaterialPageRoute<void>(builder: (_) => SignInPage()),
      //           (route) => false);
      //     }
      //     if (state.status == AuthenticationStatus.authenticated) {
      //       _navigator.pushAndRemoveUntil<void>(MaterialPageRoute<void>(builder: (_) => SplashPage()), (route) => false);
      //     }
      //   },
      // ),
      child: RepositoryProvider<UserRepository>(
        create: (context) => userRepository,
        child: MaterialApp(
          title: 'Rumah Sehat Mobile',
          debugShowCheckedModeBanner: false,
          navigatorKey: _navigatorKey,
          routes: routes,
          builder: (context, child) {
            return BlocListener<AuthenticationBloc, AuthenticationState>(
              key: Key("appBlocListener"),
              listener: (context, state) {
                print('halo');
                print(state.status == AuthenticationStatus.unauthenticated);
                if (state.status == AuthenticationStatus.unauthenticated) {
                  _navigator?.pushAndRemoveUntil<void>(
                      MaterialPageRoute<void>(builder: (_) => SignInPage()),
                      (route) => false);
                }
                if (state.status == AuthenticationStatus.authenticated) {
                  _navigator?.pushAndRemoveUntil<void>(MaterialPageRoute<void>(builder: (_) => SplashPage()), (route) => false);
                }
              },
              child: child,
            );
          },
        ),
      ),
    );
    // return RepositoryProvider.value(
    //   value: userRepository,
    //   child: BlocProvider(
    //     create: (_) => AuthenticationBloc(
    //       userRepository: userRepository,
    //     )..add(AppStarted()),
    //     child: MaterialApp(
    //       navigatorKey: _navigatorKey,
    //       builder: (context, child) {
    //         print('masuk builder?');
    //         return BlocListener<AuthenticationBloc, AuthenticationState>(
    //           listener: (context, state) {
    //             print('authenticated!');
    //             switch (state.status) {
    //               case AuthenticationStatus.authenticated:
    //                 _navigator.pushAndRemoveUntil<void>(
    //                   SplashPage.route(),
    //                   (route) => false,
    //                 );
    //                 break;
    //               case AuthenticationStatus.unauthenticated:
    //                 _navigator.pushAndRemoveUntil<void>(
    //                   SignInPage.route(),
    //                   (route) => false,
    //                 );
    //                 break;
    //             }
    //           },
    //           child: child,
    //         );
    //       },
    //       onGenerateRoute: (_) => SplashPage.route(),
    //     )
    //   ),
    // );
  }
}
