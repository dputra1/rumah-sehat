import 'dart:convert';

class TagihanModel {
  final String kode;
  final DateTime tanggalTerbuat;
  final DateTime tanggalBayar;
  final int jumlahTagihan;
  final bool isPaid;

  const TagihanModel(
      {required this.kode,
      required this.tanggalTerbuat,
      required this.isPaid,
      required this.jumlahTagihan,
      required this.tanggalBayar,
      });

  factory TagihanModel.fromJson(Map<String, dynamic> json) {
    return TagihanModel(
      kode: json['kode'],
      tanggalTerbuat: DateTime.parse(json['tanggalTerbuat']),
      tanggalBayar: DateTime.parse(json['tanggalBayar']),
      isPaid: json['isPaid'],
      jumlahTagihan: json['jumlahTagihan'],
    );
  }
}
