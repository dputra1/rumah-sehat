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
    // var size = MediaQuery.of(context)
    //     .size; //this gonna give us total height and with of our device
    // return Scaffold(
    //   //bottomNavigationBar: BottomNavBar(),
    //   body: Stack(
    //     children: <Widget>[
    //       Container(
    //         // Here the height of the container is 45% of our total height
    //         height: size.height * .45,
    //         decoration: BoxDecoration(
    //           color: Color(0xFFF5CEB8),
    //           image: DecorationImage(
    //             alignment: Alignment.centerLeft,
    //             image: AssetImage("assets/images/undraw_pilates_gpdb.png"),
    //           ),
    //         ),
    //       ),
    //       SafeArea(
    //         child: Padding(
    //           padding: const EdgeInsets.symmetric(horizontal: 20),
    //           child: Column(
    //             crossAxisAlignment: CrossAxisAlignment.start,
    //             children: <Widget>[
    //               Align(
    //                 alignment: Alignment.topRight,
    //                 child: Container(
    //                   alignment: Alignment.center,
    //                   height: 52,
    //                   width: 52,
    //                   decoration: BoxDecoration(
    //                     color: Color(0xFFF2BEA1),
    //                     shape: BoxShape.circle,
    //                   ),
    //                   //child: SvgPicture.asset("assets/icons/menu.svg"),
    //                 ),
    //               ),
    //               Text(
    //                 "Good Mornign \nShishir",
    //                 style: Theme.of(context)
    //                     .textTheme.displayLarge?.copyWith(fontWeight: FontWeight.w900),
    //               ),
    //               //SearchBar(),
    //               Expanded(
    //                 child: GridView.count(
    //                   crossAxisCount: 2,
    //                   childAspectRatio: .85,
    //                   crossAxisSpacing: 20,
    //                   mainAxisSpacing: 20,
    //                   children: <Widget>[
    //                     CategoryCard(
    //                       title: "Diet Recommendation",
    //                       svgSrc: "assets/icons/Hamburger.svg",
    //                       press: () {},
    //                     ),
    //                     CategoryCard(
    //                       title: "Kegel Exercises",
    //                       svgSrc: "assets/icons/Excrecises.svg",
    //                       press: () {},
    //                     ),
    //                     CategoryCard(
    //                       title: "Meditation",
    //                       svgSrc: "assets/icons/Meditation.svg",
    //                       press: () {
    //                         Navigator.push(
    //                           context,
    //                           MaterialPageRoute(builder: (context) {
    //                             return DetailsScreen();
    //                           }),
    //                         );
    //                       },
    //                     ),
    //                     CategoryCard(
    //                       title: "Yoga",
    //                       svgSrc: "assets/icons/yoga.svg",
    //                       press: () {},
    //                     ),
    //                   ],
    //                 ),
    //               ),
    //             ],
    //           ),
    //         ),
    //       )
    //     ],
    //   ),
    // );
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
            ),
            TextButton(
              style: ButtonStyle(
                backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
              ),
              onPressed: () async {
                var response = await Api.fetchTagihan();
                if (response.statusCode == 200) {
                  List jsonRes = json.decode(response.body);
                  List<Tagihan> listTagihan= jsonRes.map((data) => new Tagihan.fromJson(data)).toList();
                  {Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) {
                        return TagihanPage(listTagihan: listTagihan);
                      }),
                    );
                  }
                } else {
                  print("gagal get Data");
                }
              },
              child: Text(
                'List Tagihan',
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
