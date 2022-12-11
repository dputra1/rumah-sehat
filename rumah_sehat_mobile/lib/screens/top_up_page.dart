part of 'pages.dart';


class TopUpPage extends StatefulWidget {
  static String routeName = "/topupPage";

  const TopUpPage({super.key});

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => const TopUpPage());
  }

  @override
  _TopUpPageState createState() => _TopUpPageState();
}


class _TopUpPageState extends State<TopUpPage> {
  Map<String, String> headers = {};
  final _formKey = GlobalKey<FormState>();
  String url = "http://localhost:2020/api/pasien/top-up";
  bool isLoading = false;
  TextEditingController nominalController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        margin: const EdgeInsets.fromLTRB(0, 70, 0, 0),
        child:
        Form(
          key: _formKey,
          child: ListView(children: [
            const Padding(
              padding: EdgeInsets.fromLTRB(24, 0, 0, 0),
              child: Text(
                'Top Up',
                style: TextStyle(
                    fontSize: 20
                ),
              ),
            ),
            const SizedBox(
              height: 20,
            ),

            Container(
              width: double.infinity,
              margin: const EdgeInsets.symmetric(horizontal: 24),
              child: TextFormField(
                key: const Key("inputNominal"),
                controller: nominalController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(10.0),
                      ),
                      borderSide: BorderSide.none,
                    ),
                    filled: true,
                    fillColor: Color.fromARGB(255, 235, 235, 235),
                    hintText: 'Masukan Nominal Top Up',
                    errorStyle: TextStyle(
                        color: Colors.red, fontWeight: FontWeight.bold)),
              ),
            ),

            const SizedBox(
              height: 10,
            ),

            Container(
              width: double.infinity,
              margin: const EdgeInsets.only(top: 24),
              height: 56,
              padding: const EdgeInsets.symmetric(horizontal: 24),
              child: TextButton(
                  key: const Key("topUp"),
                  style: ButtonStyle(
                      backgroundColor: MaterialStateProperty.all(
                          Colors.lightGreen)
                  ),
                  onPressed: () async {
                    setState(() {
                      isLoading = true;
                    });
                    try {
                      int temp = int.tryParse(nominalController.text)!;
                      Uri uri = Uri.parse(url);
                      const storage = FlutterSecureStorage();
                      final token = await storage.read(key: "token");
                      final response = await http.post(
                        uri,
                        headers:{
                          'Content-Type': 'application/json',
                          'Accept': 'application/json',
                          'Authorization': 'Bearer $token',
                        },
                        body: jsonEncode(<String, int>{
                          "add": temp
                        }),
                      );

                      if (response.statusCode == 200) {
                        showDialog<String>(
                          context: context,
                          builder: (BuildContext context) => AlertDialog(
                            title: const Text('Login Success'),
                            content: const Text('Top-up saldo berhasil'),
                            shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(30),
                            ),
                            backgroundColor: Colors.white,
                            actions: <Widget>[
                              TextButton(
                                onPressed: () =>
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (BuildContext context) =>
                                                const TopUpPage())),
                                child: const Text('OK'),
                              ),
                            ],
                          ),
                        );

                      } else {
                        showDialog(
                            context: context,
                            builder: (BuildContext context) =>
                                _buildPopupDialog(context, response.statusCode.toString()));
                      }

                    } catch (e) {
                      showDialog(
                          context: context,
                          builder: (BuildContext context) =>
                              _buildPopupDialog(context, e.toString()));
                    }
                    setState(() {
                      isLoading = false;
                    });
                  },
                  child: isLoading ?
                  const SpinKitRing(
                    color: Colors.white,
                    lineWidth: 4.0,
                  ) :
                  const Text(
                    'Konfirmasi',
                    style: TextStyle(
                        color: Colors.white
                    ),
                  )
              ),
            ),
            const SizedBox(
              height: 10,
            ),

          ]),
        ),
      ),
    );
  }

  Widget _buildPopupDialog(BuildContext context, String msg) {
    return AlertDialog(
      title: const Text('Gagal Top Up'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: const <Widget>[
          Text("Anda gagal Top Up"),
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
