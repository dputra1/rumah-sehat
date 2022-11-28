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
  bool isLoading = false;
  TextEditingController emailController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        margin: EdgeInsets.fromLTRB(0, 70, 0, 0),
        child: Form(
          key: _formKey,
          child: ListView(children: [
            Padding(
                padding: const EdgeInsets.fromLTRB(24, 0, 0, 0),
                child: Text(
                  'Masuk',
                  style: TextStyle(
                    fontSize: 20
                  ),
                ),
              ),
            SizedBox(
              height: 20,
            ),
            Container(
              width: double.infinity,
              margin: EdgeInsets.fromLTRB(24, 26, 24, 6),
              child: Text(
                "Username"
              ),
            ),
            Container(
              width: double.infinity,
              margin: EdgeInsets.symmetric(horizontal: 24),
              child: TextFormField(
                key: const Key("inputEmailSignin"),
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
                    hintText: 'Masukan username',
                    errorStyle: const TextStyle(
                        color: Colors.red, fontWeight: FontWeight.bold)),
              ),
            ),

            // !!Password part
            Container(
              width: double.infinity,
              margin: EdgeInsets.fromLTRB(24, 16, 24, 6),
              child: Text(
                "Kata Sandi",
              ),
            ),

            Container(
              width: double.infinity,
              margin: EdgeInsets.symmetric(horizontal: 24),
              child: TextFormField(
                key: const Key("inputPasswordSignin"),
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
                    hintText: 'Masukkan password',
                    errorStyle: const TextStyle(
                        color: Colors.red, fontWeight: FontWeight.bold)),
                obscureText: true,
              ),
            ),
            const SizedBox(
              height: 10,
            ),

            Container(
              width: double.infinity,
              margin: const EdgeInsets.only(top: 24),
              height: 56,
              padding: EdgeInsets.symmetric(horizontal: 24),
              child: TextButton(
                  key: const Key("signIn"),
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all(Colors.lightGreen)
                  ),
                  onPressed: () async {
                    setState(() {
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
                  child: isLoading ?
                    SpinKitRing(
                      color: Colors.white,
                      lineWidth: 4.0,
                    ) :
                    Text(
                      'Masuk',
                      style: TextStyle(
                        color: Colors.white
                      ),
                    )
                    
                ),
            ),
            const SizedBox(
              height: 10,
            ),
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
                        color: Colors.lightGreen
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
            )
          ]),
        ),
      ),
    );
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
}
