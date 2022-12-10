part of 'pages.dart';

class AppointmentPage extends StatefulWidget {
  static String routeName = "/AppointmentPage";
  List<Appointment> listAppointment;
  AppointmentPage({super.key, required this.listAppointment});

  @override
  _AppointmentPageState createState() => _AppointmentPageState();
}

class _AppointmentPageState extends State<AppointmentPage> {

  @override
  Widget build(BuildContext context) {

    List<Appointment> data = widget.listAppointment;

    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xff131313),
        title: const Text(
          "List Appointment",
          textScaleFactor: 1.3,
        ),
      ),
      body: ListView.builder(
        padding: const EdgeInsets.all(16),
        shrinkWrap: true,
        itemCount: widget.listAppointment.length,
        itemBuilder: (BuildContext context, int index) {
          return Container(
            margin: const EdgeInsets.only(top: 30),
            child: Column(
              children: [
                Column(
                  children: [
                    Card(
                        margin: const EdgeInsets.only(top: 50),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20),
                        ),
                        child: Padding(
                          padding: const EdgeInsets.only(
                              top: 10, left: 15, right: 15, bottom: 10),
                          child: Column(
                            children: [
                              Row(
                                children: [
                                  const Text(
                                    "Kode Tagihan",
                                    style: TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                  const SizedBox(
                                    width: 40,
                                  ),
                                  Text(
                                    widget.listAppointment[index].kode,
                                    style: const TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 10),
                              Row(
                                children: [
                                  const Text(
                                    "Tanggal terbuat",
                                    style: TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                  const SizedBox(
                                    width: 40,
                                  ),
                                  Text(
                                    widget.listAppointment[index].nama,
                                    style: const TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 10),
                              Row(
                                children: [
                                  const Text(
                                    "Tanggal terbuat",
                                    style: TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                  const SizedBox(
                                    width: 40,
                                  ),
                                  Text(
                                    widget.listAppointment[index].waktuAwal,
                                    style: const TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 10),
                              Row(
                                children: [
                                  const Text(
                                    "Status",
                                    style: TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                  const SizedBox(
                                    width: 100,
                                  ),
                                  Text(
                                    widget.listAppointment[index].status,
                                    style: const TextStyle(
                                        fontFamily: 'Mulish',
                                        fontSize: 16,
                                        color: Colors.black),
                                  ),
                                ],
                              ),
                              const SizedBox(height: 10),
                              // Row(
                              //   mainAxisAlignment: MainAxisAlignment.center,
                              //   children: [
                              //     ElevatedButton(
                              //         onPressed: () async {
                              //           var response = await provider.get(AppUrl.detailTagihan+widget.listTagihan![index].kode);
                              //           print(response.statusCode);
                              //           if (response.statusCode == 200) {
                              //             print(json.decode(response.body));
                              //             final Map<String, dynamic> res = json.decode(response.body);
                              //             print(res);
                              //             final Tagihan tagihanNew = Tagihan.fromJson(res);
                              //             print(tagihanNew);
                              //             // Tagihan tagihanTest = new Tagihan.fromJson(res);
                              //             // print(tagihanTest);
                              //             {Navigator.of(context).push(
                              //               MaterialPageRoute(builder: (context) {
                              //                 return DetailTagihan(tagihanDetail: tagihanNew);
                              //               }),
                              //             );
                              //             }
                              //           } else {
                              //             print("gagal get Data");
                              //           }
                              //         },
                              //         child: const Text("Detail")),
                              //   ],
                              // ),
                            ],
                          ),
                        )),
                  ],
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}