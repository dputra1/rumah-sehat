// part of 'pages.dart';

// class DetailResepPage extends StatefulWidget {
//   static String routeName = "/DetailResep";

//   final String kodeResep;

//   const DetailResepPage({
//     required this.kodeResep,
//   });

//   @override
//   _DetailResepPageState createState() => _DetailResepPageState();
// }

// class _DetailResepPageState extends State<DetailResepPage> {
//   final _formKey = GlobalKey<FormState>();

//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//         appBar: AppBar(
//           backgroundColor: Colors.red,
//           title: Center(
//             child: const Text('Detail Resep'),
//           ),
//         ),
//         body: FutureBuilder<Map<String, dynamic>>(
//             future: Api.fetchDetailResep(widget.kodeResep),
//             builder: (context, snapshot) {
//               if (snapshot.connectionState == ConnectionState.waiting) {
//                 return const CircularProgressIndicator.adaptive();
//               }
//               if (snapshot.hasError) {
//                 return Text(snapshot.error.toString());
//               } else {
//                 return ListView(
//                   children: [
//                     Container(
//                       child: Column(
//                         children: <Widget>[
//                           ListTile(
//                             title: Text(
//                               'Email',
//                               style: TextStyle(
//                                 color: Colors.deepOrange,
//                                 fontSize: 20,
//                                 fontWeight: FontWeight.bold,
//                               ),
//                             ),
//                             subtitle: Text(
//                               'palmeiro.leonardo@gmail.com',
//                               style: TextStyle(
//                                 fontSize: 18,
//                               ),
//                             ),
//                           ),
//                           Divider(),
//                           ListTile(
//                             title: Text(
//                               'GitHub',
//                               style: TextStyle(
//                                 color: Colors.deepOrange,
//                                 fontSize: 20,
//                                 fontWeight: FontWeight.bold,
//                               ),
//                             ),
//                             subtitle: Text(
//                               'https://github.com/leopalmeiro',
//                               style: TextStyle(
//                                 fontSize: 18,
//                               ),
//                             ),
//                           ),
//                           Divider(),
//                           ListTile(
//                             title: Text(
//                               'Linkedin',
//                               style: TextStyle(
//                                 color: Colors.deepOrange,
//                                 fontSize: 20,
//                                 fontWeight: FontWeight.bold,
//                               ),
//                             ),
//                             subtitle: Text(
//                               'www.linkedin.com/in/leonardo-palmeiro-834a1755',
//                               style: TextStyle(
//                                 fontSize: 18,
//                               ),
//                             ),
//                           ),
//                         ],
//                       ),
//                     ),
//                     Container(
//                         child: Column(
//                       children: buildTableobat(snapshot.data['obat']),
//                     ))
//                   ],
//                 );
//               }
//             }));
//   }

//   Widget _buildPopupDialog(BuildContext context) {
//     return AlertDialog(
//       title: const Text('Gagal Masuk'),
//       content: Column(
//         mainAxisSize: MainAxisSize.min,
//         crossAxisAlignment: CrossAxisAlignment.start,
//         children: <Widget>[
//           const Text("Username atau kata sandi yang anda gunakan salah"),
//         ],
//       ),
//       actions: <Widget>[
//         TextButton(
//           onPressed: () {
//             Navigator.of(context).pop();
//           },
//           style: ButtonStyle(
//               foregroundColor: MaterialStateProperty.all(Colors.black)),
//           child: const Text('Tutup'),
//         ),
//       ],
//     );
//   }

//   List<Widget> buildTableobat(List dataObat) {

//   }
// }
