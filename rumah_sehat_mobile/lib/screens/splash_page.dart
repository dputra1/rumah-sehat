part of 'pages.dart';

class SplashPage extends StatefulWidget {
  static String routeName = "/";
  @override
  _SplashPageState createState() => _SplashPageState();

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => SplashPage());
  }
}

class _SplashPageState extends State<SplashPage> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Column(
          children: [
            Text('Home Page'),
            TextButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
              ),
              onPressed: () async {
                BlocProvider.of<AuthenticationBloc>(context)
                    .add(SignedOut());
                RepositoryProvider.of<UserRepository>(context)
                    .deleteToken();
                RepositoryProvider.of<UserRepository>(context).clearAll();
              },
              child: Text(
                'Logout',
                style: TextStyle(
                  color: Colors.white
                ),
              )
            ),
            TextButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
              ),
              onPressed: () async {
                var response = await Api.fetchAppointment();
                if (response.statusCode == 200) {
                  List jsonRes = json.decode(response.body);
                  List<Appointment> listAppointment= jsonRes.map((data) => new Appointment.fromJson(data)).toList();
                  {Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) {
                        return AppointmentPage(listAppointment: listAppointment);
                      }),
                    );
                  }
                } else {
                  print("gagal get Data");
                }
              },
              child: Text(
                'List Appointment',
                style: TextStyle(
                  color: Colors.white
                ),
              )
            )
          ],
        ),
      )
    );
  }
}
