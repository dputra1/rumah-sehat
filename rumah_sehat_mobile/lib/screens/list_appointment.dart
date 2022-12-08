part of 'pages.dart';

String formatDateTime(String unformattedDateTime) {
  List<String> datetime = unformattedDateTime.split("T");
  String date = datetime[0];
  String time = datetime[1];
  String formattedDate =
      "${date.split("-")[2]}-${date.split("-")[1]}-${date.split("-")[0]}";
  String formattedTime = "${time.split(":")[0]}:${time.split(":")[1]}";
  return "$formattedDate $formattedTime";
}

class Appointment {
  final String kode;
  final String nama;
  final String waktuAwal;
  final String status;

  const Appointment(
      {required this.kode,
      required this.nama,
      required this.waktuAwal,
      required this.status});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        kode: json['kode'],
        nama: json['dokter']['nama'],
        waktuAwal: formatDateTime(json['waktuAwal']),
        status: json['isDone'] == 1 ? 'Selesai' : 'Belum Selesai');
  }
}

Future<List<Appointment>> fetchAppointment() async {
  final storage = FlutterSecureStorage();
  final token = await storage.read(key: "token");
  final response = await http.get(
    Uri.parse('http://localhost:2020/api/appointment/list-appointment'),
    headers:{
    'Authorization': '$token',
    },);
  print(response.statusCode);

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    Iterable l = json.decode(response.body);
    List<Appointment> listAppointment =
        List<Appointment>.from(l.map((model) => Appointment.fromJson(model)));
    return listAppointment;
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load appointment');
  }
}

class AppointmentPage extends StatefulWidget {
  const AppointmentPage({super.key});
  static String routeName = "/SignInPage";

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => SignInPage());
  }

  @override
  // ignore: library_private_types_in_public_api
  _AppointmentPageState createState() => _AppointmentPageState();
}

class _AppointmentPageState extends State<AppointmentPage> {
  late Future<List<Appointment>> listAppointment;

  @override
  void initState() {
    super.initState();
    listAppointment = fetchAppointment();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Jadwal Appointment"),
      ),
      body: FutureBuilder(
        future: listAppointment,
        builder: (context, snapshot) {
          if (!snapshot.hasData) {
            return const Center(child: CircularProgressIndicator());
          }

          if (snapshot.hasData) {
            return Column(
              mainAxisSize: MainAxisSize.min,
              mainAxisAlignment: MainAxisAlignment.center,
              verticalDirection: VerticalDirection.down,
              children: <Widget>[
                Expanded(
                  child: Container(
                      padding: const EdgeInsets.all(5),
                      child: dataBody(snapshot.data!, context)),
                )
              ],
            );
          }

          return const Center();
        },
      ),
    );
  }
}

ListView dataBody(List<Appointment> listAppointment, BuildContext context) {
  return ListView(scrollDirection: Axis.vertical, children: [
    DataTable(
      sortColumnIndex: 0,
      showCheckboxColumn: false,
      columns: const [
        DataColumn(label: Text("Nama Dokter")),
        DataColumn(
          label: Text("Waktu Awal"),
        ),
        DataColumn(
          label: Text("Status"),
        ),
        // DataColumn(
        //   label: Text("Detail"),
        // ),
      ],
      rows: listAppointment
          .map(
            (appointment) => DataRow(cells: [
              DataCell(Text(appointment.nama)),
              DataCell(
                Text(appointment.waktuAwal),
              ),
              // DataCell(
              //   Text(appointment.status),
              // ),
              // DataCell(ElevatedButton(
              //   child: const Text("Detail"),
              //   onPressed: () {
              //     Navigator.push(
              //       context,
              //       MaterialPageRoute(
              //           builder: (context) =>
              //               DetailAppointment(kode: appointment.kode)),
              //     );
              //   },
              // )),
            ]),
          )
          .toList(),
    ),
  ]);
}
