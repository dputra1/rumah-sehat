part of 'pages.dart';


class Button extends StatelessWidget {
  final String kode;
  final int jumlahTagihan;

  const Button({Key? key,required this.kode, required this.jumlahTagihan}) : super(key: key);

Widget build(BuildContext context) {
    return ElevatedButton(
        child: Text("Bayar Tagihan"),
        
        onPressed: () async{
          {
          var response = await Api.fetchUser();
          //var namaDokter = appointment.dokter.nama;
          //print(namaDokter);
          if (response.statusCode == 200) {
            final Map<String, dynamic> res = json.decode(response.body);
            Pasien pasien = Pasien.fromJson(res);
            //print(user.toJson());

            showDialog(
                context: context,
                builder: (BuildContext context) {
                  return AlertDialog(
                      shape: RoundedRectangleBorder(
                          borderRadius:
                          BorderRadius.all(Radius.circular(20.0))),
                      title: Center(
                          child: Text("Bayar Tagihan",
                              style: TextStyle(
                                  fontWeight: FontWeight.w500,
                                  color: kPrimaryColor))),
                      content: Text(
                        "Ingin membayar tagihan ini?",
                        textAlign: TextAlign.center,
                      ),
                      actions: [
                        Center(
                          child: ButtonBar(
                            mainAxisSize: MainAxisSize.min,
                            children: <Widget>[
                              ElevatedButton(
                                  child: Text('Tidak'),
                                  onPressed: () {
                                    Navigator.of(context).pop();
                                  },
                                  style: ButtonStyle(
                                    backgroundColor: MaterialStateProperty
                                        .resolveWith<Color>(
                                          (Set<MaterialState> states) {
                                        if (states
                                            .contains(MaterialState.pressed))
                                          return kTextMediumColor;
                                        return kTextLightColor;
                                      },
                                    ),
                                  )),
                              ElevatedButton(
                                  child: Text('Ya'),
                                  onPressed: () async {
                                    Navigator.of(context).pop();
                                    int saldoAwal = pasien.saldo;
                                    int saldoAkhir = 0;

                                    saldoAkhir = saldoAwal - jumlahTagihan;
                                    if (saldoAkhir < 0) {
                                      showDialog(
                                          context: context,
                                          builder: (BuildContext context) {
                                            return AlertDialog(
                                                shape: RoundedRectangleBorder(
                                                    borderRadius:
                                                    BorderRadius.all(
                                                        Radius.circular(
                                                            20.0))),
                                                title: Center(
                                                    child: Text("Gagal",
                                                        style: TextStyle(
                                                            fontWeight:
                                                            FontWeight.w500,
                                                            color:
                                                            kPrimaryColor))),
                                                content: Text(
                                                  "Saldo anda tidak cukup.",
                                                  textAlign: TextAlign.center,
                                                ),
                                                actions: [
                                                  Center(
                                                      child: 
                                                      ButtonBar(mainAxisSize: MainAxisSize.min,
                                                        children: <Widget>[
                                                          ElevatedButton(
                                                          child: Text("Ok"),
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
                                                                  return kTextMediumColor;
                                                                return kTextLightColor;
                                                              },
                                                            ),
                                                          ),
                                                        ), 
                                                        ElevatedButton(
                                                          child: Text("Top Up"),
                                                          onPressed: () {
                                                            Navigator.push(
                                                            context,
                                                            MaterialPageRoute(
                                                              builder: (BuildContext context) => const TopUpPage()));
                                                          },
                                                          style: ButtonStyle(
                                                            backgroundColor:
                                                            MaterialStateProperty
                                                                .resolveWith<
                                                                Color>(
                                                                  (Set<MaterialState>
                                                              states) {
                                                                if (states.contains(
                                                                    MaterialState.pressed))
                                                                  return kPrimaryColor;
                                                                return kPrimaryColor;
                                                              },
                                                            ),
                                                          ),
                                                        ),
                                                      ],)
                                                      )
                                                ]);
                                          });
                                    } else {
                                      var response = await Api
                                          .updateSaldo(jsonEncode(<String, int>{
                                            "min": saldoAkhir
                                          }),);
                                      var response1 = await Api
                                          .updateStatusTagihan(jsonEncode({
                                            "kode": kode,
                                            "isPaid": 'false',
                                            "jumlahTagihan": jumlahTagihan
                                      }));
                                      if ((response.statusCode == 200) && (response1.statusCode == 200)) {
                                          showDialog(
                                            context: context,
                                          builder: (BuildContext context) {return
                                        AlertDialog(
                                          shape: RoundedRectangleBorder(
                                              borderRadius:
                                              BorderRadius.all(
                                                  Radius.circular(
                                                      20.0))),
                                          title: Center(
                                              child: Text("Sukses",
                                                  style: TextStyle(
                                                      fontWeight:
                                                      FontWeight
                                                          .w500,
                                                      color: kPrimaryColor))),
                                          content: Text(
                                            "Tagihan berhasil dibayar. Anda akan diarahkan ke Homepage",
                                            textAlign:
                                            TextAlign.center,
                                          ),
                                          actions: [
                                            Center(
                                                child: ElevatedButton(
                                                  child: Text("Ok"),
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
                                                          return kPrimaryColor;
                                                        return kPrimaryColor;
                                                      },
                                                    ),
                                                  ),
                                                ))
                                          ]);});
                                        }
                                        else {
                                          
                                        }
                                      }
                                  },
                                  style: ButtonStyle(
                                    backgroundColor: MaterialStateProperty
                                        .resolveWith<Color>(
                                          (Set<MaterialState> states) {
                                        if (states
                                            .contains(MaterialState.pressed))
                                          return kPrimaryColor;
                                        return kPrimaryColor;
                                      },
                                    ),
                                  )),
                            ],
                          ),
                        )
                      ]);
                });
          }
        }
        },
        style: ButtonStyle(
          backgroundColor:
          MaterialStateProperty
              .resolveWith<
              Color>(
                (Set<MaterialState>
            states) {
              if (states.contains(
                  MaterialState.pressed))
                return kPrimaryColor;
              return kPrimaryColor;
            },
          ),
        ),
      );
}
  }