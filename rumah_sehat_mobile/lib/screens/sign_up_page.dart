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
  bool _isHidden = true;
  TextEditingController namaController = TextEditingController();
  TextEditingController emailController = TextEditingController();
  TextEditingController umurController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController usernameController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        elevation: 0,
        iconTheme: IconThemeData(color: kPrimaryColor),
        backgroundColor: kBackgroundColor,
      ),
      body: SingleChildScrollView(
        child: Container(
          alignment: Alignment.center,
          child: Container(
            margin: EdgeInsets.fromLTRB(
                30, MediaQuery.of(context).size.height * 0.1, 30, 10),
            child: Column(
              children: [
                Text("Daftar Akun Baru!",
                    style: TextStyle(
                        fontWeight: FontWeight.w900,
                        color: kPrimaryColor,
                        fontSize: 24)),
                SizedBox(height: 40.0),
                Form(
                  key: _formKey,
                    child: Column(children: [
                  Container(
                    child: TextFormField(
                      decoration: Style().textInputDecoration(
                          "Nama", "", namaController),
                      controller: namaController,
                    ),
                    decoration: Style().inputBoxDecorationShaddow(),
                  ),
                  SizedBox(height: 30.0),
                  Container(
                    child: TextFormField(
                      decoration: Style().textInputDecoration(
                          "Username", "", usernameController),
                      controller: usernameController,
                    ),
                    decoration: Style().inputBoxDecorationShaddow(),
                  ),
                  SizedBox(height: 30.0),
                  Container(
                    child: TextFormField(
                      decoration: Style().textInputDecoration(
                          "Email", "", emailController),
                      controller: emailController,
                    ),
                    decoration: Style().inputBoxDecorationShaddow(),
                  ),
                  SizedBox(height: 30.0),
                  Container(
                    child: TextFormField(
                      decoration: Style().textInputDecoration(
                          "Umur", "", umurController),
                      controller: umurController,
                    ),
                    decoration: Style().inputBoxDecorationShaddow(),
                  ),
                  SizedBox(height: 30.0),
                  Container(
                    alignment: Alignment.centerLeft,
                    child: Container(
                      width: 1500,
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(22),
                          border: Border.all(
                              width: 1,
                              color: kPrimaryColor,
                              style: BorderStyle.solid)),
                      child: TextFormField(
                        controller: passwordController,
                        obscureText: _isHidden,
                        keyboardType: TextInputType.text,
                        decoration: InputDecoration(
                          hintText: "Kata Sandi",
                          contentPadding: EdgeInsets.all(15),
                          border: InputBorder.none,
                          suffix: GestureDetector(
                            onTap: () {
                              _togglePasswordView();
                            },
                            child: Icon(
                              _isHidden
                                  ? Icons.visibility_off
                                  : Icons.visibility,
                              color: _isHidden ? Colors.grey : Colors.grey,
                              size: 20,
                            ),
                          ),
                        ),
                      ),
                    ),
                    decoration: Style().inputBoxDecorationShaddow(),
                  ),
                  SizedBox(height: 25.0),
                  Container(
                    decoration: Style().buttonBoxDecoration(context),
                    child: ElevatedButton(
                        style: Style().buttonStyle(),
                        child: Padding(
                          padding: const EdgeInsets.fromLTRB(20, 10, 20, 10),
                          child: Text(
                            "Daftarkan Akun",
                            style: TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.normal,
                              color: Colors.white,
                            ),
                          ),
                        ),
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
                  },),
                  ),
                  SizedBox(
                    height: 20,
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
              ),
                  SizedBox(height: 30.0),
                ])),
              ],
            ),
          ),
        ),
      ),
    );
  }
  void _togglePasswordView() {
    setState(() {
      _isHidden = !_isHidden;
    });
  }


  Widget _buildPopupDialog(BuildContext context, String msg) {
    return AlertDialog(
        shape: RoundedRectangleBorder(
            borderRadius:
            BorderRadius.all(
                Radius.circular(
                    20.0))),
        title: Center(
            child: Text("Gagal Daftar",
                style: TextStyle(
                    fontWeight:
                    FontWeight
                        .w500,
                    color: kPrimaryColor))),
        content: Text(
          msg,
          textAlign:
          TextAlign.center,
        ),
        actions: [
          Center(
              child: ElevatedButton(
                child: Text("Ok"),
                onPressed: () => Navigator.pop(context),
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
        ]);
  }
}
