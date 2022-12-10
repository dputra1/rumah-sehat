part of 'pages.dart';
// import 'detail_appointment.dart';

class Tagihan {
  final String kode;
  final DateTime tanggalTerbuat;
  final DateTime tanggalBayar;
  final int jumlahTagihan;
  final bool isPaid;

  const Tagihan(
      {required this.kode,
      required this.tanggalTerbuat,
      required this.isPaid,
      required this.jumlahTagihan,
      required this.tanggalBayar,
      });

  factory Tagihan.fromJson(Map<String, dynamic> json) {
    return Tagihan(
      kode: json['kode'],
      tanggalTerbuat: DateTime.parse(json['tanggalTerbuat']),
      tanggalBayar: DateTime.parse(json['tanggalBayar']),
      isPaid: json['isPaid'],
      jumlahTagihan: json['jumlahTagihan'],
    );
  }
}

Future<List<Tagihan>> fetchTagihan(String id) async {
  final response = await http
      .get(Uri.parse('http://localhost:2020/api/tagihan/getall'));

  if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    Iterable l = json.decode(response.body);
    List<Tagihan> listTagihan =
        List<Tagihan>.from(l.map((model) => Tagihan.fromJson(model)));
    return listTagihan;
  } else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    throw Exception('Failed to load Tagihan');
  }
}

class TagihanPage extends StatefulWidget {
  const TagihanPage({super.key});
  static String routeName = "/TagihanPage";
  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => TagihanPage());
  }

  @override
  // ignore: library_private_types_in_public_api
  _TagihanPageState createState() => _TagihanPageState();
}

class _TagihanPageState extends State<TagihanPage> {
  late Future<List<Tagihan>> listTagihan;

  @override
  void initState() {
    super.initState();
    listTagihan = fetchTagihan("4");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Jadwal Tagihan"),
      ),
      body: FutureBuilder(
        future: listTagihan,
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
                      child: dataBodyTagihan(snapshot.data!, context)),
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

ListView dataBodyTagihan(List<Tagihan> listTagihan, BuildContext context) {
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
        DataColumn(
          label: Text("Detail"),
        ),
      ],
      rows: listTagihan
          .map(
            (tagihan) => DataRow(cells: [
              DataCell(Text(tagihan.nama)),
              DataCell(
                Text(tagihan.waktuAwal),
              ),
              DataCell(
                Text(tagihan.status),
              ),
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
