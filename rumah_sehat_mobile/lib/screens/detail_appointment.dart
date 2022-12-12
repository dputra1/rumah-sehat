part of 'pages.dart';

class DetailAppointmentPage extends StatefulWidget {
  static String routeName = "/DetailAppointment";

  final String kodeAppointment;

  const DetailAppointmentPage({super.key,
    required this.kodeAppointment,
  });

  @override
  _DetailAppointmentPageState createState() => _DetailAppointmentPageState();
}

class _DetailAppointmentPageState extends State<DetailAppointmentPage> {
  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.red,
          title: const Center(
            child: Text('Detail Appointment'),
          ),
        ),
        body: FutureBuilder<Map<String, dynamic>>(
            future: Api.fetchDetailAppointment(widget.kodeAppointment),
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.waiting) {
                return const CircularProgressIndicator.adaptive();
              }
              if (snapshot.hasError) {
                return Text(snapshot.error.toString());
              } else {
                return ListView(
                  children: [
                    Column(
                      children: <Widget>[
                        ListTile(
                          title: const Text(
                            'Kode Appointment',
                            style: TextStyle(
                              color: Colors.deepOrange,
                              fontSize: 20,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          subtitle: Text(
                            snapshot.data!['kode'],
                            style: const TextStyle(
                              fontSize: 18,
                            ),
                          ),
                        ),
                        const Divider(),
                        ListTile(
                          title: const Text(
                            'Waktu Awal Appointment',
                            style: TextStyle(
                              color: Colors.deepOrange,
                              fontSize: 20,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          subtitle: Text(
                            snapshot.data!['waktuAwal'],
                            style: const TextStyle(
                              fontSize: 18,
                            ),
                          ),
                        ),
                        const Divider(),
                        ListTile(
                          title: const Text(
                            'Status Appointment',
                            style: TextStyle(
                              color: Colors.deepOrange,
                              fontSize: 20,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          subtitle: Text(
                            snapshot.data!['isDone'].toString(),
                            style: const TextStyle(
                              fontSize: 18,
                            ),
                          ),
                        ),
                        const Divider(),
                        ListTile(
                          title: const Text(
                            'Nama Dokter',
                            style: TextStyle(
                              color: Colors.deepOrange,
                              fontSize: 20,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          subtitle: Text(
                            snapshot.data!['dokter']['nama'],
                            style: const TextStyle(
                              fontSize: 18,
                            ),
                          ),
                        ),
                        const Divider(),
                        ListTile(
                          title: const Text(
                            'Nama Pasien',
                            style: TextStyle(
                              color: Colors.deepOrange,
                              fontSize: 20,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          subtitle: Text(
                            snapshot.data!['pasien']['nama'],
                            style: const TextStyle(
                              fontSize: 18,
                            ),
                          ),
                        ),
                        const Divider(),
                      ],
                    ),
                  ],
                );
              }
            }));
  }

  Widget _buildPopupDialog(BuildContext context) {
    return AlertDialog(
      title: const Text('Gagal Masuk'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: const <Widget>[
          Text("Username atau kata sandi yang anda gunakan salah"),
        ],
      ),
      actions: <Widget>[
        TextButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          style: ButtonStyle(
              foregroundColor: MaterialStateProperty.all(Colors.black)),
          child: const Text('Tutup'),
        ),
      ],
    );
  }
}
