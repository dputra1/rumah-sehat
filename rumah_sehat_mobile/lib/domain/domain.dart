import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:dio/dio.dart';

String formatDateTime(String unformattedDateTime) {
  List<String> datetime = unformattedDateTime.split("T");
  String date = datetime[0];
  String time = datetime[1];
  String formattedDate =
      "${date.split("-")[2]}-${date.split("-")[1]}-${date.split("-")[0]}";
  String formattedTime = "${time.split(":")[0]}:${time.split(":")[1]}";
  return "$formattedDate $formattedTime";
}

class Appointment {
  final String kode;
  final String nama;
  final String waktuAwal;
  final String status;

  const Appointment(
      {required this.kode,
      required this.nama,
      required this.waktuAwal,
      required this.status});

  factory Appointment.fromJson(Map<String, dynamic> json) {
    return Appointment(
        kode: json['kode'],
        nama: json['dokter']['nama'],
        waktuAwal: formatDateTime(json['waktuAwal']),
        status: json['isDone'] == 1 ? 'Selesai' : 'Belum Selesai');
  }
}