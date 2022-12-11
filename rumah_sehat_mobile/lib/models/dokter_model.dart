import 'package:flutter/material.dart';

class DokterModel {
  final String uuid;
  final String nama;

  DokterModel({
    required this.uuid,
    required this.nama
  });

  factory DokterModel.fromJson(Map<String, dynamic> json) {
    return DokterModel(uuid: json['uuid'], nama: json['nama']);
  }
}