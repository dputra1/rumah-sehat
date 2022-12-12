part of 'pages.dart';

class TagihanDetail extends StatelessWidget {
  final Tagihan tagihan;

  TagihanDetail({Key? key, required this.tagihan}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          textAlign: TextAlign.right,
          'DETAIL TAGIHAN',
          style: TextStyle(
              fontSize: 20, fontWeight: FontWeight.bold, color: Colors.white),
        ),
        backgroundColor: Colors.teal,
      ),
      body: Row(
        children: <Widget>[
          Expanded(
            flex: 1,
            child: Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                children: <Widget>[
                  Column(
                    children: [
                      Text(
                        tagihan.kode,
                        style: const TextStyle(
                            fontSize: 24, fontWeight: FontWeight.bold),
                        textAlign: TextAlign.center,
                      ),
                      const SizedBox(height: 20),
                      ListTile(
                          title: const Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              'Kode: ',
                              style: TextStyle(
                                  fontSize: 16, fontWeight: FontWeight.bold),
                            ),
                          ),
                          dense: true,
                          subtitle: Text(
                            tagihan.kode,
                            style: const TextStyle(
                                fontSize: 14, color: Colors.black),
                          )),
                      ListTile(
                          title: const Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              'Jumlah Tagihan: ',
                              style: TextStyle(
                                  fontSize: 16, fontWeight: FontWeight.bold),
                            ),
                          ),
                          dense: true,
                          subtitle: Text(
                            tagihan.jumlahTagihan.toString(),
                            style: const TextStyle(
                                fontSize: 14, color: Colors.black),
                          )),
                      ListTile(
                        title: const Padding(
                          padding: EdgeInsets.only(bottom: 10.0),
                          child: Text(
                            'Tanggal Bayar: ',
                            style: TextStyle(
                                fontSize: 16, fontWeight: FontWeight.bold),
                          ),
                        ),
                        subtitle: Text(
                          (tagihan.tanggalBayar == "Belum Bayar"
                              ? "-"
                              : tagihan.tanggalBayar
                              .toString()
                              .substring(0, 10)),
                          style: const TextStyle(
                              fontSize: 14, color: Colors.black),
                        ),
                        dense: true,
                      ),
                      ListTile(
                          title: const Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              'Tanggal Terbuat: ',
                              style: TextStyle(
                                  fontSize: 16, fontWeight: FontWeight.bold),
                            ),
                          ),
                          dense: true,
                          subtitle: Text(
                            (tagihan.tanggalTerbuat.toString()),
                            style: const TextStyle(
                                fontSize: 14, color: Colors.black),
                          )),
                    ],
                  ),
                  tagihan.isPaid=='' ? const Spacer() : button(context),
                  const Spacer(),
                  TextButton(
                    style: TextButton.styleFrom(
                        backgroundColor: Colors.teal,
                        padding: const EdgeInsets.symmetric(
                            horizontal: 90, vertical: 20),
                        tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                        alignment: Alignment.center),
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: const Text(
                      "Back",
                      style: TextStyle(color: Colors.white),
                    ),
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget button(BuildContext context) {
    return SizedBox(
      width: double.infinity,
      child: TextButton(
        style: const ButtonStyle(
          alignment: Alignment.centerRight,
        ),
        child: Text(
          textAlign: TextAlign.right,
          'Bayar Tagihan',
          style: TextStyle(
              fontSize: 18, fontWeight: FontWeight.bold, color: Colors.teal),
        ),
        onPressed: () async {
          var response = await Api.fetchUser();
          //var namaDokter = appointment.dokter.nama;
          //print(namaDokter);
          if (response.statusCode == 200) {
            final Map<String, dynamic> res = json.decode(response.body);
            //Profile user = Profile.fromJson(res);
            //print(user.toJson());

            showDialog(
                context: context,
                builder: (BuildContext context) {
                  return AlertDialog(
                      shape: RoundedRectangleBorder(
                          borderRadius:
                          BorderRadius.all(Radius.circular(30.0))),
                      title: Center(
                          child: Text("Bayar Tagihan",
                              style: TextStyle(
                                  fontWeight: FontWeight.w500,
                                  color: Colors.teal))),
                      content: Text(
                        "Apakah anda ingin membayar tagihan ini?",
                        textAlign: TextAlign.center,
                      ),
                      actions: [
                        Center(
                          child: ButtonBar(
                            mainAxisSize: MainAxisSize
                                .min, // this will take space as minimum as posible(to center)
                            children: <Widget>[
                              ElevatedButton(
                                  child: Text('Batal'),
                                  onPressed: () {
                                    Navigator.of(context).pop();
                                  },
                                  style: ButtonStyle(
                                    backgroundColor: MaterialStateProperty
                                        .resolveWith<Color>(
                                          (Set<MaterialState> states) {
                                        if (states
                                            .contains(MaterialState.pressed))
                                          return Colors.grey;
                                        return Colors.grey;
                                      },
                                    ),
                                  )),
                              ElevatedButton(
                                  child: Text('Yakin'),
                                  onPressed: () async {
                                    Navigator.of(context).pop();
                                    int saldoAwal = 0;
                                    int saldoAkhir = 0;

                                    saldoAkhir = saldoAwal - tagihan.jumlahTagihan;
                                    if (saldoAkhir < 0) {
                                      showDialog(
                                          context: context,
                                          builder: (BuildContext context) {
                                            return AlertDialog(
                                                shape: RoundedRectangleBorder(
                                                    borderRadius:
                                                    BorderRadius.all(
                                                        Radius.circular(
                                                            30.0))),
                                                title: Center(
                                                    child: Text("Gagal",
                                                        style: TextStyle(
                                                            fontWeight:
                                                            FontWeight.w500,
                                                            color:
                                                            Colors.teal))),
                                                content: Text(
                                                  "Saldo tidak cukup",
                                                  textAlign: TextAlign.center,
                                                ),
                                                actions: [
                                                  Center(
                                                      child: ElevatedButton(
                                                        child: Text("OK"),
                                                        onPressed: () {
                                                          Navigator.of(context)
                                                              .pop();
                                                        },
                                                        style: ButtonStyle(
                                                          backgroundColor:
                                                          MaterialStateProperty
                                                              .resolveWith<
                                                              Color>(
                                                                (Set<MaterialState>
                                                            states) {
                                                              if (states.contains(
                                                                  MaterialState
                                                                      .pressed))
                                                                return Colors.teal;
                                                              return Colors.teal;
                                                            },
                                                          ),
                                                        ),
                                                      ))
                                                ]);
                                          });
                                    } else {
                                      var response = await Api
                                          .updateSaldo(jsonEncode({
                                        "nama": 'user.nama',
                                        "email": 'user.email',
                                        "umur": 'user.umur',
                                        "username": 'user.username',
                                        "password": "1",
                                        "saldo": saldoAkhir
                                      }));
                                      var response1 = await Api
                                          .updateStatusTagihan(jsonEncode({
                                        "kode": tagihan.kode,
                                        "isPaid": 'tagihan.is_paid',
                                        "jumlahTagihan": tagihan.jumlahTagihan
                                      }));
                                      if (response.statusCode == 200) {
                                        if (response1.statusCode == 200) {
                                          showDialog(
                                              context: context,
                                              builder: (BuildContext context) {
                                                return AlertDialog(
                                                    shape: RoundedRectangleBorder(
                                                        borderRadius:
                                                        BorderRadius.all(
                                                            Radius.circular(
                                                                30.0))),
                                                    title: Center(
                                                        child: Text("Berhasil",
                                                            style: TextStyle(
                                                                fontWeight:
                                                                FontWeight
                                                                    .w500,
                                                                color: Colors
                                                                    .teal))),
                                                    content: Text(
                                                      "Berhasil membayar tagihan",
                                                      textAlign:
                                                      TextAlign.center,
                                                    ),
                                                    actions: [
                                                      Center(
                                                          child: ElevatedButton(
                                                            child: Text("OK"),
                                                            onPressed: () {
                                                              Navigator.of(context).pushReplacement(MaterialPageRoute(
                                                                builder: (context) => SplashPage(),
                                                              ));
                                                            },
                                                            style: ButtonStyle(
                                                              backgroundColor:
                                                              MaterialStateProperty
                                                                  .resolveWith<
                                                                  Color>(
                                                                    (Set<MaterialState>
                                                                states) {
                                                                  if (states.contains(
                                                                      MaterialState
                                                                          .pressed))
                                                                    return Colors
                                                                        .teal;
                                                                  return Colors
                                                                      .teal;
                                                                },
                                                              ),
                                                            ),
                                                          ))
                                                    ]);
                                              });
                                        }
                                      }
                                    }
                                  },
                                  style: ButtonStyle(
                                    backgroundColor: MaterialStateProperty
                                        .resolveWith<Color>(
                                          (Set<MaterialState> states) {
                                        if (states
                                            .contains(MaterialState.pressed))
                                          return Colors.teal;
                                        return Colors.teal;
                                      },
                                    ),
                                  )),
                            ],
                          ),
                        )
                      ]);
                });
          }
        },
      ),
    );
  }
}