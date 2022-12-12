part of 'pages.dart';

class AddAppointmentPage extends StatefulWidget {
  static String routeName = "/addAppointment";

  static Route<void> route() {
    return MaterialPageRoute<void>(builder: (_) => AddAppointmentPage());
  }

  @override
  _AddAppointmentPageState createState() => _AddAppointmentPageState();
}

class _AddAppointmentPageState extends State<AddAppointmentPage> {
  final _formKey = GlobalKey<FormState>();
  bool isLoading = false;
  TextEditingController _waktuAwalController = TextEditingController();
  String _dokterPilihan = "";
  String selectedValue = "";

  @override
  void initState() {
    super.initState();
    _dokterPilihan = '';
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Container(
        margin: EdgeInsets.fromLTRB(0, 70, 0, 0),
          child: FutureBuilder<List<DropdownMenuItem<String>>>(
            future: Api.getAllDokterRawDropdownList(),
            builder: (context, snapshot) {
              if(snapshot.connectionState == ConnectionState.waiting){
                return const CircularProgressIndicator.adaptive();
              }
              if(snapshot.hasError){
                return Text(snapshot.error.toString());
              } else {
                return Form(
                  key: _formKey,
                  child: ListView(
                    children: [
                      Padding (
                        padding: const EdgeInsets.fromLTRB(24, 0, 0, 0),
                        child: Text(
                          'Add Appointment',
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
                          "Waktu Pertemuan",
                          // style: blackFontStyleSemiBold,
                        ),
                      ),
                      Container(
                        width: double.infinity,
                        margin: EdgeInsets.symmetric(horizontal: 24),
                        child: TextField(
                          controller: _waktuAwalController,
                          decoration: InputDecoration(
                            icon: Icon(Icons.calendar_today_rounded),
                            border: const OutlineInputBorder(
                              borderRadius: BorderRadius.all(
                                Radius.circular(10.0),
                              ),
                              borderSide: BorderSide.none,
                            ),
                            filled: true,
                            fillColor: Color.fromARGB(255, 235, 235, 235),
                            hintText: 'Select Date Time',
                          ),
                          onTap: () async {
                            DateTime pickeddate = (await DatePicker.showDateTimePicker(context,
                              minTime: DateTime(2001),
                              maxTime: DateTime(2200),
                            ))!;

                            if(pickeddate != null){
                              setState(() {
                                _waktuAwalController.text = DateFormat("yyyy-MM-dd'T'HH:mm").format(pickeddate);
                              });
                            }
                          },
                        ),
                      ),

                      // Email part
                      Container(
                        width: double.infinity,
                        margin:
                        const EdgeInsets.fromLTRB(24, 16, 24, 6),
                        child: Text(
                          "Pilih Dokter",
                          // style: blackFontStyleSemiBold,
                        ),
                      ),

                      Container(
                        width: double.infinity,
                        margin: const EdgeInsets.symmetric(horizontal: 24),
                        child: DropdownButton(
                            value: selectedValue,
                            items: snapshot.data,
                            onChanged: (String? newValue) {
                              setState(() {
                                selectedValue = newValue!;
                              });
                            },
                          ),
                      ),
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
                            'Tambah Appointment',
                            style: TextStyle(
                                color: Colors.white
                            ),
                          )
                          ,
                          onPressed: () async {
                            setState(() {
                              isLoading = true;
                            });
                            Response response = await Api.tambahAppointment(_waktuAwalController.text, selectedValue);
                            if(response.statusCode != 200){
                              showDialog(
                                  context: context,
                                  builder: (BuildContext context) =>
                                      _buildPopupDialog(context, "Jadwal appointment tidak tersedia"));
                            }
                            setState(() {
                              isLoading = false;
                            });
                          },
                        ),
                      ),
                    ],
                  ),
                );
              }
            }
          )
      ),
    );
  }

  Widget _buildPopupDialog(BuildContext context, String msg) {
    return AlertDialog(
      title: const Text('Gagal Tambah Appointment'),
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