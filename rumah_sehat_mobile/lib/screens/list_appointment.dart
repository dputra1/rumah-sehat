part of 'pages.dart';

class AppointmentPage extends StatefulWidget {
  static String routeName = "/AppointmentPage";
  List<Appointment> listAppointment;
  AppointmentPage({super.key, required this.listAppointment});

  @override
  _AppointmentPageState createState() => _AppointmentPageState();
}

class _AppointmentPageState extends State<AppointmentPage> {

  @override
  Widget build(BuildContext context) {

    List<Appointment> data = widget.listAppointment;

    return Scaffold(
      body: Column(
        children: <Widget>[
          Stack(
            children: <Widget>[
              Container(
                height: MediaQuery.of(context).size.width,
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(30.0),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.black26,
                      offset: Offset(0.0, 2.0),
                      blurRadius: 6.0,
                    ),
                  ],
                ),
                child: Hero(
                  tag: 'assets/images/appointment.jpg',
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(30.0),
                    child: Image(
                      image: AssetImage('assets/images/appointment.jpg'),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
              ),
              Padding(
                padding: EdgeInsets.symmetric(horizontal: 10.0, vertical: 40.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    IconButton(
                      icon: Icon(Icons.arrow_back),
                      iconSize: 30.0,
                      color: Colors.white,
                      onPressed: () => Navigator.pop(context),
                    ),
                  ],
                ),
              ),
              Positioned(
                left: 20.0,
                bottom: 20.0,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      "List Appointment",
                      style: TextStyle(
                        color: Colors.white ,
                        fontSize: 35.0,
                        fontWeight: FontWeight.w600,
                        letterSpacing: 1.2,
                      ),
                    ),
                    // Row(
                    //   children: <Widget>[
                    //     Icon(
                    //       FontAwesomeIcons.locationArrow,
                    //       size: 15.0,
                    //       color: Colors.white70,
                    //     ),
                    //     SizedBox(width: 5.0),
                    //     Text(
                    //       widget.destination.country,
                    //       style: TextStyle(
                    //         color: Colors.white70,
                    //         fontSize: 20.0,
                    //       ),
                    //     ),
                    //   ],
                    // ),
                  ],
                ),
              ),
              // Positioned(
              //   right: 20.0,
              //   bottom: 20.0,
              //   child: Icon(
              //     Icons.location_on,
              //     color: Colors.white70,
              //     size: 25.0,
              //   ),
              // ),
            ],
          ),
          Expanded(
            child: ListView.builder(
              padding: EdgeInsets.only(top: 10.0, bottom: 15.0),
              itemCount: widget.listAppointment.length,
              itemBuilder: (BuildContext context, int index) {
                return InkWell(
                  child: Stack(children: <Widget>[
                    Container(
                      margin: EdgeInsets.fromLTRB(20.0, 5.0, 20.0, 5.0),
                      height: 170.0,
                      width: double.infinity,
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(20.0),
                      ),
                      child: Padding(
                        padding: EdgeInsets.fromLTRB(20.0, 20.0, 20.0, 20.0),
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.center,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: <Widget>[
                            Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: <Widget>[
                                Center(
                                  child: Text(
                                    widget.listAppointment[index].kode,
                                    style: TextStyle(
                                      fontSize: 18.0,
                                      fontWeight: FontWeight.w600,
                                      color: kPrimaryColor
                                    ),
                                    overflow: TextOverflow.ellipsis,
                                    maxLines: 2,
                                  ),
                                ),
                                
                                // Column(
                                //   children: <Widget>[
                                //     Text(
                                //       '\$${activity.price}',
                                //       style: TextStyle(
                                //         fontSize: 22.0,
                                //         fontWeight: FontWeight.w600,
                                //       ),
                                //     ),
                                //     Text(
                                //       'per pax',
                                //       style: TextStyle(
                                //         color: Colors.grey,
                                //       ),
                                //     ),
                                //   ],
                                // ),
                              ],
                            ),
                            SizedBox(height: 13.0), 
                            Divider(
                                  color: kPrimaryColor2, //color of divider
                                  height: 5, //height spacing of divider
                                  thickness: 1, //thickness of divier line
                                  indent: 8, //spacing at the start of divider
                                  endIndent: 8, //spacing at the end of divider
                                ),
                            SizedBox(height: 10.0), 
                            Row(
                              children: [
                                Text(
                                  "Nama Dokter",
                                  style: TextStyle(
                                  color: kTextColor,
                                  fontWeight: FontWeight.w600,
                                  ),
                                ),
                                const SizedBox(
                                    width: 29,
                                  ),
                                Text(": " + (
                                  widget.listAppointment[index].nama),
                                  style: TextStyle(
                                  color: kTextLightColor,
                                  ),
                                ),
                              ],
                            ),
                            SizedBox(height: 10.0),
                            Row(
                              children: [
                                Text(
                                  "Tanggal Terbuat",
                                  style: TextStyle(
                                  color: kTextColor,
                                  fontWeight: FontWeight.w600,
                                  ),
                                ),
                                const SizedBox(
                                    width: 10,
                                  ),
                                Text(": " + (
                                  widget.listAppointment[index].waktuAwal),
                                  style: TextStyle(
                                    color: kTextLightColor,
                                  ),
                                ),
                              ],
                            ),
                            // SizedBox(height: 10.0),
                            // Row(
                            //   children: [
                            //     Text(
                            //       "Status",
                            //       style: TextStyle(
                            //       color: Colors.black87,
                            //       fontWeight: FontWeight.w500,
                            //       ),
                            //     ),
                            //     const SizedBox(
                            //         width: 71,
                            //       ),
                            //     Text(": " + (
                            //       widget.listAppointment[index].status),
                            //       style: TextStyle(
                            //         color: Colors.grey,
                            //       ),
                            //     ),
                            //   ],
                            // ),
                            SizedBox(height: 10.0), 
                            widget.listAppointment[index].status == "Belum Selesai"?
                            Container(
                              padding: EdgeInsets.all(5.0),
                              width: 100.0,
                              decoration: BoxDecoration(
                                color: kTextLightColor,
                                borderRadius: BorderRadius.circular(10.0),
                              ),
                              alignment: Alignment.center,
                              child: Text(
                                widget.listAppointment[index].status,
                                style: TextStyle(
                                    color: Colors.white,
                                  ),
                              ),
                            ):
                            Container(
                              padding: EdgeInsets.all(5.0),
                              width: 60.0,
                              decoration: BoxDecoration(
                                color: kPrimaryColor,
                                borderRadius: BorderRadius.circular(10.0),
                              ),
                              alignment: Alignment.center,
                              child: Text(
                                widget.listAppointment[index].status,
                                style: TextStyle(color: Colors.white),
                              ),
                            ),   
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
                onTap: () {
                  // Navigator.push(
                  //       context,
                  //       MaterialPageRoute(
                  //         builder: (context) => TagihanDetail(
                  //           tagihan: widget.listAppointment[index],
                  //         ),
                  //       ));
                  }
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}