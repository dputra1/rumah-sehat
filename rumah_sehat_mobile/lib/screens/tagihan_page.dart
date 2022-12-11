part of 'pages.dart';

class TagihanPage extends StatefulWidget {
  static String routeName = "/TagihanPage";
  List<Tagihan> listTagihan;
  TagihanPage({super.key, required this.listTagihan});

  @override
  _TagihanPageState createState() => _TagihanPageState();
}

class _TagihanPageState extends State<TagihanPage> {

    void movePage() {
    Navigator.pop(context);
    // Navigator.pushNamed(context, '/booking');
  }

  @override
  Widget build(BuildContext context) {

    List<Tagihan> data = widget.listTagihan;

    return Scaffold(
      appBar: AppBar(
        title: const Text("Jadwal Tagihan"),
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
                          widget.listTagihan[index].kode,
                          style: TextStyle(
                              fontSize: 22, fontWeight: FontWeight.bold),
                        ),
                        Text(
                          NumberFormat.currency(name: "Rp").format(widget.listTagihan[index].jumlahTagihan),
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
                            child: Text("Bayar sekarang"))
                      ],
                    ),
                  ));
                },
                itemCount: widget.listTagihan.length)
          ],
        )));
  }
}
