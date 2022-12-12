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
    var size = MediaQuery.of(context)
        .size; //this gonna give us total height and with of our device
    return Scaffold(
      //bottomNavigationBar: BottomNavBar(),
      body: Stack(
        children: <Widget>[
          Container(
            // Here the height of the container is 45% of our total height
            height: size.height * .45,
            decoration: BoxDecoration(
              color: kPrimaryColor2,
              image: DecorationImage(
                alignment: Alignment.centerLeft,
                image: AssetImage("assets/images/undraw_pilates_gpdb.png"),
              ),
            ),
          ),
          SafeArea(
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Align(
                    alignment: Alignment.topRight,
                    child: Container(
                      alignment: Alignment.center,
                      height: 52,
                      width: 52,
                      decoration: BoxDecoration(
                        color: kPrimaryColor,
                        shape: BoxShape.circle,
                      ),
                      child: GestureDetector(
                        child:
                        Icon(Icons.logout_outlined,
                        color: kErrorColor,),
                        onTap: () {
                          BlocProvider.of<AuthenticationBloc>(context)
                            .add(SignedOut());
                          RepositoryProvider.of<UserRepository>(context)
                              .deleteToken();
                          RepositoryProvider.of<UserRepository>(context).clearAll();
                        },
                      )
                    ),
                  ),
                  Text(
                    "Halo, \nShishir",
                    style: Theme.of(context)
                        .textTheme.displayMedium?.copyWith(fontWeight: FontWeight.w900),
                  ),
                  SizedBox(height: 60,),
                  //SearchBar(),
                  Expanded(
                    child: GridView.count(
                      crossAxisCount: 2,
                      childAspectRatio: .85,
                      crossAxisSpacing: 20,
                      mainAxisSpacing: 20,
                      children: <Widget>[
                        ClipRRect(
                          borderRadius: BorderRadius.circular(13),
                          child: Container(
                            // padding: EdgeInsets.all(20),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              borderRadius: BorderRadius.circular(13),
                              boxShadow: [
                                BoxShadow(
                                  offset: Offset(0, 17),
                                  blurRadius: 17,
                                  spreadRadius: -23,
                                  color: kTextLightColor,
                                ),
                              ],
                            ),
                            child: Material(
                              color: Colors.transparent,
                              child: InkWell(
                                onTap: () async {
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
                                child: Padding(
                                  padding: const EdgeInsets.all(20.0),
                                  child: Column(
                                    children: <Widget>[
                                      Spacer(),
                                      Icon(
                                        Icons.list_alt_outlined, 
                                        color: kPrimaryColor,
                                        size: 60,),
                                      Spacer(),
                                      Text(
                                        "List Appointment",
                                        textAlign: TextAlign.center,
                                        style: TextStyle(
                                          color: kPrimaryColor,
                                          fontWeight: FontWeight.w600
                                        )
                                      )
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                        ClipRRect(
                          borderRadius: BorderRadius.circular(13),
                          child: Container(
                            // padding: EdgeInsets.all(20),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              borderRadius: BorderRadius.circular(13),
                              boxShadow: [
                                BoxShadow(
                                  offset: Offset(0, 17),
                                  blurRadius: 17,
                                  spreadRadius: -23,
                                  color: kTextLightColor,
                                ),
                              ],
                            ),
                            child: Material(
                              color: Colors.transparent,
                              child: InkWell(
                                onTap: () async {
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
                                child: Padding(
                                  padding: const EdgeInsets.all(20.0),
                                  child: Column(
                                    children: <Widget>[
                                      Spacer(),
                                      Icon(
                                        Icons.payment_outlined, 
                                        color: kPrimaryColor,
                                        size: 60,),
                                      Spacer(),
                                      Text(
                                        "List Tagihan",
                                        textAlign: TextAlign.center,
                                        style: TextStyle(
                                          color: kPrimaryColor,
                                          fontWeight: FontWeight.w600
                                        )
                                      )
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                        ClipRRect(
                          borderRadius: BorderRadius.circular(13),
                          child: Container(
                            // padding: EdgeInsets.all(20),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              borderRadius: BorderRadius.circular(13),
                              boxShadow: [
                                BoxShadow(
                                  offset: Offset(0, 17),
                                  blurRadius: 17,
                                  spreadRadius: -23,
                                  color: kTextLightColor,
                                ),
                              ],
                            ),
                            child: Material(
                              color: Colors.transparent,
                              child: InkWell(
                                onTap: () async {
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
                                child: Padding(
                                  padding: const EdgeInsets.all(20.0),
                                  child: Column(
                                    children: <Widget>[
                                      Spacer(),
                                      Icon(
                                        Icons.list_alt_outlined, 
                                        color: kPrimaryColor,
                                        size: 60,),
                                      Spacer(),
                                      Text(
                                        "List Appointment",
                                        textAlign: TextAlign.center,
                                        style: TextStyle(
                                          color: kPrimaryColor,
                                          fontWeight: FontWeight.w600
                                        )
                                      )
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                        ClipRRect(
                          borderRadius: BorderRadius.circular(13),
                          child: Container(
                            // padding: EdgeInsets.all(20),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              borderRadius: BorderRadius.circular(13),
                              boxShadow: [
                                BoxShadow(
                                  offset: Offset(0, 17),
                                  blurRadius: 17,
                                  spreadRadius: -23,
                                  color: kTextLightColor,
                                ),
                              ],
                            ),
                            child: Material(
                              color: Colors.transparent,
                              child: InkWell(
                                onTap: () async {
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
                                child: Padding(
                                  padding: const EdgeInsets.all(20.0),
                                  child: Column(
                                    children: <Widget>[
                                      Spacer(),
                                      Icon(
                                        Icons.list_alt_outlined, 
                                        color: kPrimaryColor,
                                        size: 60,),
                                      Spacer(),
                                      Text(
                                        "List Appointment",
                                        textAlign: TextAlign.center,
                                        style: TextStyle(
                                          color: kPrimaryColor,
                                          fontWeight: FontWeight.w600
                                        )
                                      )
                                    ],
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
    // return SafeArea(
    //   child: Scaffold(
    //     body: Column(
    //       children: [
    //         const Text('Home Page'),
    //         TextButton(
    //           style: ButtonStyle(
    //             backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
    //           ),
    //           onPressed: () async {
    //             BlocProvider.of<AuthenticationBloc>(context)
    //                 .add(SignedOut());
    //             RepositoryProvider.of<UserRepository>(context)
    //                 .deleteToken();
    //             RepositoryProvider.of<UserRepository>(context).clearAll();
    //           },
    //           child: const Text(
    //             'Logout',
    //             style: TextStyle(
    //               color: Colors.white
    //             ),
    //           )
    //         ),
    //         TextButton(
    //           style: ButtonStyle(
    //             backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
    //           ),
    //           onPressed: () async {
    //             var response = await Api.fetchAppointment();
    //             if (response.statusCode == 200) {
    //               List jsonRes = json.decode(response.body);
    //               List<Appointment> listAppointment= jsonRes.map((data) => new Appointment.fromJson(data)).toList();
    //               {Navigator.of(context).push(
    //                   MaterialPageRoute(builder: (context) {
    //                     return AppointmentPage(listAppointment: listAppointment);
    //                   }),
    //                 );
    //               }
    //             } else {
    //               print("gagal get Data");
    //             }
    //           },
    //           child: const Text(
    //             'List Appointment',
    //             style: TextStyle(
    //               color: Colors.white
    //             ),
    //           )
    //         ),
    //         TextButton(
    //           style: ButtonStyle(
    //             backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
    //           ),
    //           onPressed: () async {
    //             var response = await Api.fetchTagihan();
    //             if (response.statusCode == 200) {
    //               List jsonRes = json.decode(response.body);
    //               List<Tagihan> listTagihan= jsonRes.map((data) => new Tagihan.fromJson(data)).toList();
    //               {Navigator.of(context).push(
    //                   MaterialPageRoute(builder: (context) {
    //                     return TagihanPage(listTagihan: listTagihan);
    //                   }),
    //                 );
    //               }
    //             } else {
    //               print("gagal get Data");
    //             }
    //           },
    //           child: const Text(
    //             'List Tagihan',
    //             style: TextStyle(
    //               color: Colors.white
    //             ),
    //           )
    //         ),

    //         TextButton(
    //             style: ButtonStyle(
    //                 backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
    //             ),
    //             onPressed: () async {
    //               Navigator.push(
    //                   context,
    //                   MaterialPageRoute(
    //                       builder: (BuildContext context) => const TopUpPage()));
    //             },
    //             child: const Text(
    //               'Top Up(Temporary)',
    //               style: TextStyle(
    //                   color: Colors.white
    //               ),
    //             )
    //         )

    //       ],
    //     ),
    //   )
    // );
  }
}
