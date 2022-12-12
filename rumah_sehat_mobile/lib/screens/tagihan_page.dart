part of 'pages.dart';

class TagihanPage extends StatefulWidget {
  static String routeName = "/TagihanPage";
  List<Tagihan> listTagihan;
  TagihanPage({super.key, required this.listTagihan});

  @override
  _TagihanPageState createState() => _TagihanPageState();
}

class _TagihanPageState extends State<TagihanPage> {

  @override
  Widget build(BuildContext context) {

    List<Tagihan> data = widget.listTagihan;

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
                  tag: 'assets/images/tagihan.jpg',
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(30.0),
                    child: Image(
                      image: AssetImage('assets/images/tagihan.jpg'),
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
                right: 20.0,
                bottom: 20.0,
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    Text(
                      "List Tagihan",
                      style: TextStyle(
                        color: Colors.white ,
                        fontSize: 35.0,
                        fontWeight: FontWeight.w600,
                        letterSpacing: 1.2,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
          Expanded(
            child: ListView.builder(
              padding: EdgeInsets.only(top: 10.0, bottom: 15.0),
              itemCount: widget.listTagihan.length,
              itemBuilder: (BuildContext context, int index) {
                return Stack(children: <Widget>[
                    Container(
                      margin: EdgeInsets.fromLTRB(20.0, 5.0, 20.0, 5.0),
                      height: 198.0,
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
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,  
                              children: <Widget>[
                                Text(
                                    widget.listTagihan[index].kode,
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                        fontSize: 18.0,
                                        fontWeight: FontWeight.w600,
                                        color: kPrimaryColor
                                    ),
                                    overflow: TextOverflow.ellipsis,
                                    maxLines: 2,
                                  ),
                                  widget.listTagihan[index].isPaid == "Lunas" ?
                                  Text(
                                    widget.listTagihan[index].isPaid,
                                    textAlign: TextAlign.end,
                                    style: TextStyle(
                                      fontSize: 15.0,
                                      fontWeight: FontWeight.w600,
                                      color: kPrimaryColor2
                                    ),
                                    overflow: TextOverflow.ellipsis,
                                    maxLines: 2,
                                  ) : 
                                  Text(
                                    widget.listTagihan[index].isPaid,
                                    textAlign: TextAlign.end,
                                    style: TextStyle(
                                      fontSize: 15.0,
                                      fontWeight: FontWeight.w600,
                                      color: kTextLightColor
                                    ),
                                    overflow: TextOverflow.ellipsis,
                                    maxLines: 2,
                                  ),
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
                                  "Tanggal Dibuat",
                                  style: TextStyle(
                                    color: kTextColor,
                                    fontWeight: FontWeight.w600,
                                  ),
                                ),
                                const SizedBox(
                                  width: 47,
                                ),
                                Text(": " + (
                                    widget.listTagihan[index].tanggalTerbuat.toString()),
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
                                  "Tanggal Pembayaran",
                                  style: TextStyle(
                                    color: kTextColor,
                                    fontWeight: FontWeight.w600,
                                  ),
                                ),
                                const SizedBox(
                                  width: 10,
                                ),
                                Text(": " + (
                                    widget.listTagihan[index].tanggalBayar == "Belum dibayar" ? "Belum dibayar" :
                                    widget.listTagihan[index].tanggalBayar.toString()),
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
                                  "Jumlah Tagihan",
                                  style: TextStyle(
                                  color: kTextColor,
                                  fontWeight: FontWeight.w600,
                                  ),
                                ),
                                const SizedBox(
                                    width: 42,
                                  ),
                                Text(": ${NumberFormat.currency(name: "Rp").format(widget.listTagihan[index].jumlahTagihan)}",
                                  style: TextStyle(
                                  color: kTextLightColor,
                                  ),
                                ),
                              ],
                            ),
                            SizedBox(height: 15.0),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.end,
                              crossAxisAlignment: CrossAxisAlignment.end,
                              children: <Widget>[
                                GestureDetector(
                              child: Container(
                              padding: EdgeInsets.all(5.0),
                              width: 100.0,
                              decoration: BoxDecoration(
                                color: kPrimaryColor,
                                borderRadius: BorderRadius.circular(10.0),
                              ),
                              alignment: Alignment.center,
                              child: Text(
                                "Detail",
                                style: TextStyle(
                                  color: Colors.white,
                                ),
                              ),
                              ),
                              onTap: () {
                                showModalBottomSheet(
                          context: context,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.only(
                                topLeft: Radius.circular(30),
                                topRight: Radius.circular(30)),
                          ),
                          backgroundColor: kBackgroundColor.withOpacity(0.95),
                          builder: ((builder) => Container(
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(10.0),
                                  color: Colors.black.withOpacity(0.05),
                                ),
                                child: Wrap(
                                  children: [
                                    Column(
                                      mainAxisAlignment:
                                          MainAxisAlignment.center,
                                      children: [
                                        SizedBox(
                                          height: 30,
                                        ),
                                        RichText(
                                          text: TextSpan(
                                            children: [
                                              WidgetSpan(
                                                child: Row(
                                                  mainAxisAlignment:
                                                      MainAxisAlignment.center,
                                                  children: [
                                                    Text(
                                                      widget.listTagihan[index].kode,
                                                      style: TextStyle(
                                                          color: kPrimaryColor,
                                                          fontSize: 18,
                                                          fontWeight:
                                                              FontWeight.w800),
                                                      
                                                    ),
                                                  ],
                                                ),
                                              ),
                                            ],
                                          ),
                                        ),
                                        SizedBox(height: 13.0),
                                                    Divider(
                                                      color: kPrimaryColor2, //color of divider
                                                      height: 5, //height spacing of divider
                                                      thickness: 1, //thickness of divier line
                                                      indent: 8, //spacing at the start of divider
                                                      endIndent: 8, //spacing at the end of divider
                                                    ),
                                                    
                                        SizedBox(
                                          height: 20,
                                        ),
                                        RichText(
                                          text: TextSpan(
                                            children: [
                                              WidgetSpan(
                                                child: Row(
                                                  mainAxisAlignment:
                                                      MainAxisAlignment.center,
                                                  children: [
                                                    Icon(
                                                      Icons.date_range_outlined,
                                                      color: kPrimaryColor,
                                                    ),
                                                    SizedBox(
                                                      width: 10,
                                                    ),
                                                    Text(
                                                      widget.listTagihan[index].tanggalTerbuat.toString(),
                                                      style: TextStyle(
                                                          color: kTextColor,
                                                          fontWeight:
                                                              FontWeight.w600),
                                                    ),
                                                  ],
                                                ),
                                              ),
                                            ],
                                          ),
                                        ),
                                        SizedBox(
                                          height: 20,
                                        ),
                                        RichText(
                                          text: TextSpan(
                                            children: [
                                              WidgetSpan(
                                                child: Row(
                                                  mainAxisAlignment:
                                                      MainAxisAlignment.center,
                                                  children: [
                                                    Icon(
                                                      Icons.payment_outlined,
                                                      color: kPrimaryColor,
                                                    ),
                                                    SizedBox(
                                                      width: 10,
                                                    ),
                                                    Text(
                                                      (
                                                      widget.listTagihan[index].tanggalBayar == "Belum dibayar" ? "Belum dibayar" :
                                                      widget.listTagihan[index].tanggalBayar.toString()),
                                                      style: TextStyle(
                                                          color: kTextColor,
                                                          fontWeight:
                                                              FontWeight.w600),
                                                    ),
                                                  ],
                                                ),
                                              ),
                                            ],
                                          ),
                                        ),
                                        SizedBox(
                                          height: 20,
                                        ),
                                        RichText(
                                          text: TextSpan(
                                            children: [
                                              WidgetSpan(
                                                child: Row(
                                                  mainAxisAlignment:
                                                      MainAxisAlignment.center,
                                                  children: [
                                                    Icon(
                                                      Icons.payments_outlined,
                                                      color: kPrimaryColor,
                                                    ),
                                                    SizedBox(
                                                      width: 10,
                                                    ),
                                                    Text("${NumberFormat.currency(name: "Rp").format(widget.listTagihan[index].jumlahTagihan)}",
                                                      style: TextStyle(
                                                          color: kTextColor,
                                                          fontWeight:
                                                              FontWeight.w600),
                                                    ),
                                                  ],
                                                ),
                                              ),
                                            ],
                                          ),
                                        ),
                                        //SizedBox(height: 20,),
                                        widget.listTagihan[index].isPaid=='Lunas' ? SizedBox() : Button(
                                          kode: widget.listTagihan[index].kode, jumlahTagihan: widget.listTagihan[index].jumlahTagihan,
                                        ),
                                        
                                        SizedBox(
                                          height: 30,
                                        ),
                                      ],
                                    ),
                                  ],
                                ),
                              )),
                        );

                              }
                            )
                              ],
                            )
                          ],
                        ),
                      ),
                    ),
                  ],
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}