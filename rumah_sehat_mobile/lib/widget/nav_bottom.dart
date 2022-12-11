// import 'package:flutter/material.dart';
// import 'package:flutter_svg/flutter_svg.dart';
// import 'package:rumah_sehat_mobile/constants.dart';
// import 'package:rumah_sehat_mobile/screens/pages.dart';

// class BottomNavBar extends StatelessWidget {
//   const BottomNavBar({
//     Key? key,
//   }) : super(key: key);

//   @override
//   Widget build(BuildContext context) {
//     return Container(
//       padding: EdgeInsets.symmetric(horizontal: 40, vertical: 10),
//       height: 80,
//       color: Colors.white,
//       child: Row(
//         mainAxisAlignment: MainAxisAlignment.spaceBetween,
//         children: <Widget>[
//           BottomNavItem(
//             title: "Home",
//             svgScr: "assets/icons/calendar.svg",
//             isActive: true,
//             press: () {
//               Navigator.push(
//                 context,
//                 MaterialPageRoute(builder: (context) {
//                   return SplashPage();
//                 }),
//               );
//             },
//           ),
//           BottomNavItem(
//             title: "Create Appointment",
//             svgScr: "assets/icons/gym.svg",
//             press: () {
//               Navigator.push(
//                 context,
//                 MaterialPageRoute(builder: (context) {
//                   return SplashPage();
//                 }),
//               );
//             },
//           ),
//           BottomNavItem(
//             title: "Settings",
//             svgScr: "assets/icons/Settings.svg",
//             press: () {
//               Navigator.push(
//                 context,
//                 MaterialPageRoute(builder: (context) {
//                   return SplashPage();
//                 }),
//               );
//             },
//           ),
//         ],
//       ),
//     );
//   }
// }

// class BottomNavItem extends StatelessWidget {
//   final String svgScr;
//   final String title;
//   final Function press;
//   final bool isActive;
//   const BottomNavItem({
//     Key? key,
//     required this.svgScr,
//     required this.title,
//     required this.press,
//     this.isActive = false,
//   }) : super(key: key);

//   @override
//   Widget build(BuildContext context) {
//     return GestureDetector(
//       onTap: press,
//       child: Column(
//         mainAxisAlignment: MainAxisAlignment.spaceAround,
//         children: <Widget>[
//           SvgPicture.asset(
//             svgScr,
//             color: isActive ? kPrimaryColor : kTextColor,
//           ),
//           Text(
//             title,
//             style: TextStyle(color: isActive ? kPrimaryColor : kTextColor),
//           ),
//         ],
//       ),
//     );
//   }
// }