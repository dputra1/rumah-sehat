part of 'pages.dart';

class DetailResepPage extends StatefulWidget {
  static String routeName = "/DetailResep";

  final String kodeResep;

  const DetailResepPage({
    required this.kodeResep,
  });

  @override
  _DetailResepPageState createState() => _DetailResepPageState();
}

class _DetailResepPageState extends State<DetailResepPage> {
  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Colors.red,
          title: Center(
            child: const Text('Detail Resep'),
          ),
        ),
        body: FutureBuilder<Map<String, dynamic>>(
            future: Api.fetchDetailResep(widget.kodeResep),
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.waiting) {
                return const CircularProgressIndicator.adaptive();
              }
              if (snapshot.hasError) {
                return Text(snapshot.error.toString());
              } else {
                return ListView(
                  children: [
                    Container(
                      child: Column(
                        children: <Widget>[
                          ListTile(
                            title: Text(
                              'ID Resep',
                              style: TextStyle(
                                color: Colors.deepOrange,
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            subtitle: Text(
                              snapshot.data!['id'],
                              style: TextStyle(
                                fontSize: 18,
                              ),
                            ),
                          ),
                          Divider(),
                          ListTile(
                            title: Text(
                              'Nama Dokter',
                              style: TextStyle(
                                color: Colors.deepOrange,
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            subtitle: Text(
                              snapshot.data!['appointment']['dokter']['nama'],
                              style: TextStyle(
                                fontSize: 18,
                              ),
                            ),
                          ),
                          Divider(),
                          ListTile(
                            title: Text(
                              'Nama Pasien',
                              style: TextStyle(
                                color: Colors.deepOrange,
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            subtitle: Text(
                              snapshot.data!['appointment']['pasien']['nama'],
                              style: TextStyle(
                                fontSize: 18,
                              ),
                            ),
                          ),
                          Divider(),
                          ListTile(
                            title: Text(
                              'Selesai',
                              style: TextStyle(
                                color: Colors.deepOrange,
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            subtitle: Text(
                              snapshot.data!['isDone'],
                              style: TextStyle(
                                fontSize: 18,
                              ),
                            ),
                          ),
                          Divider(),
                          ListTile(
                            title: Text(
                              snapshot.data!['appoteker']!['nama'],
                              style: TextStyle(
                                color: Colors.deepOrange,
                                fontSize: 20,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            subtitle: Text(
                              'www.linkedin.com/in/leonardo-palmeiro-834a1755',
                              style: TextStyle(
                                fontSize: 18,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                    Container(
                      child: buildTableobat(snapshot.data!['listJumlah']),
                    )
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
        children: <Widget>[
          const Text("Username atau kata sandi yang anda gunakan salah"),
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

  Widget buildTableobat(List listJumlah) {
    List<TableRow> isiTabel = [];
    int nomor = 1;
    isiTabel.add(TableRow(children: [
      Column(children: [Text('No', style: TextStyle(fontSize: 20.0))]),
      Column(children: [Text('Nama Obat', style: TextStyle(fontSize: 20.0))]),
      Column(children: [Text('Stok', style: TextStyle(fontSize: 20.0))]),
      Column(children: [
        Text('Jumlah Dibutuhkan', style: TextStyle(fontSize: 20.0))
      ]),
    ]));

    for (Map<String, dynamic> jumlah in listJumlah) {
      Map<String, dynamic> dataObat = jumlah["obat"];
      isiTabel.add(
        TableRow(children: [
          Column(children: [Text(nomor.toString())]),
          Column(children: [Text(dataObat['namaObat'])]),
          Column(children: [Text(dataObat['stok'])]),
          Column(children: [Text(jumlah['kuantitas'])]),
        ]),
      );
    }

    return Table(
      defaultColumnWidth: FixedColumnWidth(120.0),
      border: TableBorder.all(
          color: Colors.black, style: BorderStyle.solid, width: 2),
      children: isiTabel,
    );
  }
}
