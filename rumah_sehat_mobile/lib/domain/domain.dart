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
        status: json['isDone'] == true ? 'Selesai' : 'Belum Selesai');
  }
}

class Tagihan {
  final String kode;
  final String tanggalTerbuat;
  String? tanggalBayar;
  final int jumlahTagihan;
  final String isPaid;

  Tagihan(
      {required this.kode,
      required this.tanggalTerbuat,
      required this.isPaid,
      required this.jumlahTagihan,
      required this.tanggalBayar,
      });

  factory Tagihan.fromJson(Map<String, dynamic> json) {
    return Tagihan(
      kode: json['kode'],
      tanggalTerbuat: formatDateTime(json['tanggalTerbuat']),
      tanggalBayar: json['tanggalBayar'] == null ? 'Belum Dibayar' : formatDateTime(json['tanggalBayar']),    
      isPaid: json['isPaid'] == true ? 'Lunas' : 'Belum Lunas',
      jumlahTagihan: json['jumlahTagihan'],
    );
  }

  Map<dynamic, dynamic> toJson() => {
        kode: kode,
        tanggalTerbuat: tanggalTerbuat,
        tanggalBayar: tanggalBayar,
        isPaid: isPaid,
        jumlahTagihan: jumlahTagihan,
      };
}

class TagihanModel {
  final String kode;
  final DateTime tanggalTerbuat;
  DateTime? tanggalBayar;
  final bool isPaid;
  final int jumlahTagihan;

  TagihanModel(
      {required this.kode,
      required this.tanggalTerbuat,
      required this.tanggalBayar,
      required this.isPaid,
      required this.jumlahTagihan,});

  factory TagihanModel.fromJson(Map<String, dynamic> json) => TagihanModel(
        kode: json["kode"],
        tanggalTerbuat: DateTime.parse(json['tanggalTerbuat']),
        tanggalBayar: json['tanggalBayar'] == null
            ? null
            : DateTime.parse(json['tanggalBayar']),
        isPaid: (json['isPaid']) == false ? false : true,
        jumlahTagihan: json['jumlahTagihan'],
      );

  Map<dynamic, dynamic> toJson() => {
    kode: kode,
    tanggalTerbuat: tanggalTerbuat,
    tanggalBayar: tanggalBayar,
    isPaid: isPaid,
    jumlahTagihan: jumlahTagihan,
  };
}
