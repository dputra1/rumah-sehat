part of 'pages.dart';

class SignInPage extends StatefulWidget {
  static String routeName = "/SignInPage";

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => SignInPage());
  }

  @override
  _SignInPageState createState() => _SignInPageState();
}

class _SignInPageState extends State<SignInPage> {
  final _formKey = GlobalKey<FormState>();

  String errorEmail = "";
  String errorPassword = "";
  String url = "https://localhost:2020/login";
  bool _isHidden = true;
  bool isLoading = false;
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        iconTheme: IconThemeData(color: kPrimaryColor),
        automaticallyImplyLeading: false,
        backgroundColor: kBackgroundColor,
      ),
      body: SingleChildScrollView(
        child: Container(
          alignment: Alignment.center,
          child: Container(
            margin: EdgeInsets.fromLTRB(
                30, MediaQuery.of(context).size.height * 0.2, 30, 10),
            child: Column(
              children: [
                Text("Login",
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
                          "Username", "", emailController),
                      controller: emailController,
                    ),
                    decoration: Style().inputBoxDecorationShaddow(),
                  ),
                  SizedBox(height: 20.0),
                  Container(
                    alignment: Alignment.center,
                    child: Container(
                      width: 2000,
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
                  SizedBox(height: 30.0),
                  Container(
                    decoration: Style().buttonBoxDecoration(context),
                    child: ElevatedButton(
                        style: Style().buttonStyle(),
                        child: Padding(
                          padding: const EdgeInsets.fromLTRB(40, 10, 40, 10),
                          child: isLoading ?
                    SpinKitRing(
                      color: Colors.white,
                      lineWidth: 2.0,
                    ) :
                    Text(
                      "Masuk",
                            style: TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.normal,
                              color: Colors.white,
                            ),
                    )
                        ),
                        onPressed: () async {setState(() {
                      errorEmail = "";
                      errorPassword = "";
                      isLoading = true;
                    });
                    try {
                      Map response = await Api.signIn(emailController.text, passwordController.text);
                    if (response['token'] != "Failed") {
                      RepositoryProvider.of<UserRepository>(context)
                          .persistToken(response['token']);
                      RepositoryProvider.of<UserRepository>(context)
                          .setEmail(emailController.text);
                      RepositoryProvider.of<UserRepository>(context)
                          .setUsername(response['username']);

                      BlocProvider.of<AuthenticationBloc>(context)
                          .add(SignedIn(token: response['token']));
                    }
                    } catch (e) {
                      showDialog(
                          context: context,
                          builder: (BuildContext context) =>
                              _buildPopupDialog(context));
                    }
                    setState(() {
                      isLoading = false;
                    });
                  },
                    ),

                  ),SizedBox(height: 10,),
                  Container(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  const Text('Belum punya akun?'),
                  TextButton(
                    key: const Key("toSignUp"),
                    child: Text(
                      "Daftar di sini",
                      style: TextStyle(
                        color: kPrimaryColor
                      ),
                    ),
                    onPressed: () {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (BuildContext context) => SignUpPage()));
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


  Widget _buildPopupDialog(BuildContext context) {
    return AlertDialog(
        shape: RoundedRectangleBorder(
            borderRadius:
            BorderRadius.all(
                Radius.circular(
                    20.0))),
        title: Center(
            child: Text("Gagal Masuk",
                style: TextStyle(
                    fontWeight:
                    FontWeight
                        .w500,
                    color: kPrimaryColor))),
        content: Text(
          "Username atau password salah. Mohon ulangi lagi",
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
