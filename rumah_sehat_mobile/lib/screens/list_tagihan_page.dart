part of 'pages.dart';

class Tagihan {
  String model = "";
  int pk = 0;
  Map<String, dynamic> fields = {};

  Tagihan(this.model, this.pk, this.fields);

  Tagihan.fromJson(Map<String, dynamic> json) {
    model = json['model'];
    pk = json['pk'];
    fields = json['fields'];
  }
}

class ListTagihanPage extends StatefulWidget {
  static String routeName = "/ListTagihanPage";

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => ListTagihanPage());
  }

  @override
  _ListTagihanPage createState() => _ListTagihanPage();
}

class _ListTagihanPage extends State<ListTagihanPage> {
  List<Tagihan> _Tagihan = <Tagihan>[];

  Future<List<Tagihan>> fetchTagihan() async {
    var url = 'http://slowlab-core.herokuapp.com/product-list/json';
    var response = await http.get(Uri.parse(url));

    var bill = <Tagihan>[];

    if (response.statusCode == 200) {
      var billsJson = json.decode(response.body);
      for (var billJson in billsJson) {
        bill.add(Tagihan.fromJson(billJson));
      }
    }
    return bill;
  }

// TODO: ganti ke detail tagihan
  void movePage() {
    Navigator.pop(context);
    Navigator.pushNamed(context, '/booking');
  }

  @override
  void initState() {
    fetchTagihan().then((value) {
      setState(() {
        _Tagihan.addAll(value);
      });
    });
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("List Tagihan"),
        ),
        body: Container(
            padding: EdgeInsets.symmetric(horizontal: 20.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(
                  "Daftar Tagihan Anda",
                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 50),
                ),
                Padding(padding: EdgeInsets.symmetric(vertical: 20.0)),
                ListView.builder(
                    physics: NeverScrollableScrollPhysics(),
                    shrinkWrap: true,
                    itemBuilder: (context, index) {
                      return Card(
                          child: Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Text(
                              _Tagihan[index].fields['name'],
                              style: TextStyle(
                                  fontSize: 22, fontWeight: FontWeight.bold),
                            ),
                            Text(
                              "Rp" + _Tagihan[index].fields['price'].toString(),
                              style: TextStyle(color: Colors.grey.shade600),
                            ),
                            Padding(
                                padding: EdgeInsets.symmetric(vertical: 10.0)),
                            ElevatedButton(
                                style: ElevatedButton.styleFrom(
                                  primary: Color(0xFFFFC300),
                                  onPrimary: Colors.white,
                                ),
                                onPressed: movePage,
                                child: Text("Pesan sekarang"))
                          ],
                        ),
                      ));
                    },
                    itemCount: _Tagihan.length)
              ],
            )));
  }
}
