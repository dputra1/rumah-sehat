part of 'pages.dart';

class SignUpPage extends StatefulWidget {
  static String routeName = "/SignUpPage";

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => SignUpPage());
  }

  @override
  _SignUpPageState createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  final _formKey = GlobalKey<FormState>();
  bool isLoading = false;
  TextEditingController namaController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController umurController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController usernameController = TextEditingController();

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Container(
        margin: EdgeInsets.fromLTRB(0, 70, 0, 0),
        child: Form(
          key: _formKey,
          child: ListView(
            children: [
              Padding(
                padding: const EdgeInsets.fromLTRB(24, 0, 0, 0),
                child: Text(
                  'Daftar',
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
                  "Nama Lengkap",
                  // style: blackFontStyleSemiBold,
                ),
              ),
              Container(
                width: double.infinity,
                margin: EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  key: Key("inputNama"),
                  autovalidateMode: AutovalidateMode.onUserInteraction,
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
                    hintText: 'Masukkan nama lengkap',
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
                  key: Key("inputEmail"),
                  autovalidateMode: AutovalidateMode.onUserInteraction,
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
                    hintText: 'Contoh: john@rumahsehatmobile.com',
                  ),
                ),
              ),

              // Nomor Telepon part
              Container(
                width: double.infinity,
                margin: const EdgeInsets.fromLTRB(24, 16, 24, 6),
                child: Text(
                  "Umur",
                  // style: blackFontStyleSemiBold,
                ),
              ),

              Container(
                width: double.infinity,
                margin: const EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  key: const Key("inputUmur"),
                  controller: umurController,
                  decoration: InputDecoration(
                    border: const OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(10.0),
                      ),
                      borderSide: BorderSide.none,
                    ),
                    filled: true,
                    fillColor: Color.fromARGB(255, 235, 235, 235),
                    hintText: 'Masukkkan umur',
                  ),
                ),
              ),

              // Kata Sandi part
              Container(
                width: double.infinity,
                margin:
                    EdgeInsets.fromLTRB(24, 16, 24, 6),
                child: Text(
                  "Username",
                  // style: blackFontStyleSemiBold,
                ),
              ),

              Container(
                width: double.infinity,
                margin: EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  key: Key("inputUsername"),
                  autovalidateMode: AutovalidateMode.onUserInteraction,
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
                    hintText: 'Masukkan username',
                  ),
                  obscureText: true,
                ),
              ),

              // Konfirmasi Kata Sandi part
              Container(
                width: double.infinity,
                margin: const EdgeInsets.fromLTRB(24, 16, 24, 6),
                child: Text(
                  "Kata Sandi",
                  // style: blackFontStyleSemiBold,
                ),
              ),

              Container(
                width: double.infinity,
                margin: const EdgeInsets.symmetric(horizontal: 24),
                child: TextFormField(
                  key: Key("inputPassword"),
                  autovalidateMode: AutovalidateMode.onUserInteraction,
                  controller: passwordController,
                  decoration: InputDecoration(
                    border: const OutlineInputBorder(
                      borderRadius: BorderRadius.all(
                        Radius.circular(10.0),
                      ),
                      borderSide: BorderSide.none,
                    ),
                    filled: true,
                    fillColor: Color.fromARGB(255, 235, 235, 235),
                    hintText: 'Masukkan kata sandi',
                  ),
                  obscureText: true,
                ),
              ),

              const SizedBox(
                height: 60,
              ),
              // !! Button untuk daftar
              Container(
                width: double.infinity,
                margin: EdgeInsets.only(top: 0),
                height: 56,
                padding: const EdgeInsets.symmetric(horizontal: 24),
                child: TextButton(
                  key: const Key("daftarButton"),
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
                  ),
                  child: isLoading ?
                    const SpinKitRing(
                      color: Colors.white,
                      lineWidth: 4.0,
                    ) :
                    Text(
                      'Daftar',
                      style: TextStyle(
                        color: Colors.white
                      ),
                    )
                  ,
                  onPressed: () async {
                    if (_formKey.currentState!.validate()) {
                      setState(() {
                        isLoading = true;
                      });
                      String email = emailController.text;
                      String password = passwordController.text;
                      String username = usernameController.text;
                      String nama = namaController.text;
                      String umur = umurController.text;

                      try {  
                        int statusCode = await Api.signUp(email, password, username, umur, nama);
                        if (statusCode == 200) {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (BuildContext context) =>
                                    SignInPage()));
                        } else {
                          showDialog(
                            context: context,
                            builder: (BuildContext context) =>
                                _buildPopupDialog(context, statusCode.toString()));
                        }
                      } catch (e) {
                        showDialog(
                          context: context,
                          builder: (BuildContext context) =>
                              _buildPopupDialog(context, e.toString()));
                        }
                      }
                      setState(() {
                        isLoading = false;
                      });
                  },
                ),
              ),
              SizedBox(
                height: 10,
              ),
              Container(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text('Sudah punya akun?'),
                    TextButton(
                      key: Key("toSignIn"),
                      child: Text(
                        "Masuk sekarang",
                        style: TextStyle(
                          color: kPrimaryColor
                        ),
                      ),
                      onPressed: () {
                        Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (BuildContext context) =>
                            SignInPage()
                          )
                        );
                      },
                    )
                  ],
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
