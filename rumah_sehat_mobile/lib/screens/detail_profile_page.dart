part of 'pages.dart';

class DetailProfilePage extends StatefulWidget {
  static String routeName = "/DetailProfilePage";

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => DetailProfilePage());
  }

  @override
  _DetailProfilePageState createState() => _DetailProfilePageState();
}

class _DetailProfilePageState extends State<DetailProfilePage> {
  TextEditingController usernameController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController namaController = TextEditingController();
  TextEditingController saldoController = TextEditingController();

  initState() {
    getDetailProfile();
  }

  getDetailProfile() async {
    try {
      String? token = await RepositoryProvider.of<UserRepository>(context).getToken();
      Map response = await Api.getDetailProfile(token!);
      usernameController.text = response['username'];
      emailController.text = response['email'];
      namaController.text = response['nama'];
      saldoController.text = response['saldo'].toString();
    } catch (e) {}
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Container(
        margin: EdgeInsets.fromLTRB(0, 70, 0, 0),
        child: Form(
          child: ListView(
            children: [
              Padding(
                padding: const EdgeInsets.fromLTRB(24, 0, 0, 0),
                child: Text(
                  'Detail Profile',
                  style: TextStyle(
                    fontSize: 20
                  ),
                ),
              ),
              SizedBox(
                height: 20,
              ),
              // Nama Lengkap
              Container(
                width: double.infinity,
                margin: EdgeInsets.fromLTRB(24, 0, 24, 6),
                child: Text(
                  "Username",
                  // style: blackFontStyleSemiBold,
                ),
              ),
              Container(
                width: double.infinity,
                margin: EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  controller: usernameController,
                  decoration: InputDecoration(
                    border: const OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(10.0),
                      ),
                      borderSide: BorderSide.none,
                    ),
                    filled: true,
                    fillColor: Color.fromARGB(255, 235, 235, 235),
                    hintText: '-',
                    enabled: false,
                  ),
                ),
              ),

              // Email part
              Container(
                width: double.infinity,
                margin:
                    const EdgeInsets.fromLTRB(24, 16, 24, 6),
                child: Text(
                  "Email",
                  // style: blackFontStyleSemiBold,
                ),
              ),

              Container(
                width: double.infinity,
                margin: const EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  controller: emailController,
                  decoration: InputDecoration(
                    border: const OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(10.0),
                      ),
                      borderSide: BorderSide.none,
                    ),
                    filled: true,
                    fillColor: Color.fromARGB(255, 235, 235, 235),
                    hintText: '-',
                    enabled: false,
                  ),
                ),
              ),

              // Nomor Telepon part
              Container(
                width: double.infinity,
                margin: const EdgeInsets.fromLTRB(24, 16, 24, 6),
                child: Text(
                  "Nama",
                  // style: blackFontStyleSemiBold,
                ),
              ),

              Container(
                width: double.infinity,
                margin: const EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  controller: namaController,
                  decoration: InputDecoration(
                    border: const OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(10.0),
                      ),
                      borderSide: BorderSide.none,
                    ),
                    filled: true,
                    fillColor: Color.fromARGB(255, 235, 235, 235),
                    hintText: '-',
                    enabled: false,
                  ),
                ),
              ),

              // Kata Sandi part
              Container(
                width: double.infinity,
                margin:
                    EdgeInsets.fromLTRB(24, 16, 24, 6),
                child: Text(
                  "Saldo",
                  // style: blackFontStyleSemiBold,
                ),
              ),

              Container(
                width: double.infinity,
                margin: EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  controller: saldoController,
                  decoration: InputDecoration(
                    border: const OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(10.0),
                      ),
                      borderSide: BorderSide.none,
                    ),
                    filled: true,
                    fillColor: Color.fromARGB(255, 235, 235, 235),
                    hintText: '-',
                    enabled: false,
                  ),
                ),
              ),

              const SizedBox(
                height: 60,
              ),
              SizedBox(
                height: 10,
              ),

              Container(
                width: double.infinity,
                margin: EdgeInsets.symmetric(horizontal: 24),
                child: TextButton(
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
                  ),
                  onPressed: () async {
                    // Navigate to route
                    Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (BuildContext context) =>
                                      TopUpPage()));
                  },
                  child: Text(
                    'Top up saldo',
                    style: TextStyle(
                      color: Colors.white
                    ),
                  )
                ),
              )
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildPopupDialog(BuildContext context, String msg) {
    return AlertDialog(
      title: const Text('Gagal Daftar'),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(msg),
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
