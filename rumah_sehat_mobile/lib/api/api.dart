import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import 'package:http/http.dart';
import 'dart:convert';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

import '../models/dokter_model.dart';

class Api {
  static final String url = 'http://localhost:2020/api/';
  static final dio = Dio(BaseOptions(baseUrl: url));

  static Future<Map> signIn(String userId, String password) async {
    Uri uri = Uri.parse('${url}auth/signin/');
    final response = await http.post(
      uri,
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(
          <String, String>{'username': userId, 'password': password}),
    );
    if (response.statusCode == 200) {
      final Map parsedResponse = json.decode(response.body);
      final String token = parsedResponse['token'];
      bool isTokenExpired = JwtDecoder.isExpired(token);
      if (!isTokenExpired) {
        return parsedResponse;
      }
    }
    return {"token": "Failed"};
  }

  static Future<int> signUp(String email, String password, String username,
      String umur, String nama) async {
    Uri uri = Uri.parse('${url}auth/signup/');
    final response = await http.post(
      uri,
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        "email": email,
        "password": password,
        "nama": nama,
        "username": username,
        "umur": umur
      }),
    );

    return response.statusCode;
  }

  static Future<int> addAppointment(
      String uuid, String waktuAwal, String token) async {
    Uri uri = Uri.parse('${url}dokter/addAppointment/');
    final response = await http.post(
      uri,
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        "Authorization": "Bearer " + token,
      },
      body: jsonEncode(<String, String>{
        "waktuAwal": waktuAwal,
        "uuid": uuid,
      }),
    );

    return response.statusCode;
  }

  static Future<List> getAllDokterRaw() async {
    String endpoint = "http://localhost:2020/api/dokter/list-dokter";
    String token = (await FlutterSecureStorage().read(key: 'token'))!;
    http.Response response =
        await get(Uri.parse(endpoint), headers: <String, String>{
      "Authorization": "Bearer " + token,
    });
    if (response.statusCode == 200) {
      final List result = jsonDecode(response.body);

      // return result.map(((e) => DokterModel.fromJson(e))).toList();
      return result;
    } else {
      throw Exception(response.reasonPhrase);
    }
  }

  static Future<List<DropdownMenuItem<String>>> getAllDokterRawDropdownList() async {
      List<DropdownMenuItem<String>> menuItems = [];
      String endpoint = "http://localhost:2020/api/dokter/list-dokter";
      String token = (await FlutterSecureStorage().read(key: 'token'))!;
      print(token);
      http.Response response =
          await http.get(Uri.parse(endpoint), headers: <String, String>{
        "Authorization": "Bearer " + token,
      });
      if (response.statusCode == 200) {
        final List result = jsonDecode(response.body);
        print(result);
        menuItems.add(DropdownMenuItem(
          child: Text(""),
          value: ""!,
        ));
        for (Map<String, dynamic> data in result) {
          print(data['nama']);
          menuItems.add(DropdownMenuItem(
              child: Text(data['nama']), value: data['username']));
        }
        print(menuItems.length);
        // return result.map(((e) => DokterModel.fromJson(e))).toList();
        return menuItems;
      } else {
        throw Exception(response.reasonPhrase);
      }
  }

  static Future<http.Response> tambahAppointment(
      String waktuAwal, String username) async {
    String token = (await FlutterSecureStorage().read(key: 'token'))!;
    String endpoint = "http://localhost:2020/api/appointment/add-appointment";
    http.Response response = await post(
      Uri.parse(endpoint),
      headers: <String, String>{
        "Authorization": "Bearer " + token,
        "Content-Type": "application/json"
      },
      body: jsonEncode(<String, String>{
        'waktuAwal': waktuAwal,
        'username': username,
      }),
    );
    print(response.statusCode);
    //final List result = jsonDecode(response.body);
    // return result.map(((e) => DokterModel.fromJson(e))).toList();
    return response;
  }
  
  static Future<dynamic> fetchUser() async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");

    final response = await http.get(
      Uri.parse('${url}pasien/getPasien'),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer $token',
      },);
  
    return response;
  }

  static Future<dynamic> fetchAppointment() async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");

    final response = await http.get(
      Uri.parse('${url}appointment/list-appointment'),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer $token',
      },);
   
    return response;
  }

  static Future<Map<String, dynamic>> fetchDetailResep(String kodeResep) async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");
    final response = await http.get(
      Uri.parse('http://localhost:2020/api/resep/detail-resep/'+kodeResep),
      headers: {
        'Authorization': 'Bearer $token',
      },
    );
    Map<String, dynamic> data = jsonDecode(response.body);
    return data;
  }

  static Future<Map<String, dynamic>> fetchDetailAppointment(String kodeAppointment) async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");
    final response = await http.get(
      Uri.parse('${url}appointment/detail-appointment/' + kodeAppointment),
      headers: {
        'Authorization': 'Bearer $token',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
    );
    print(response.body);
    Map<String, dynamic> data = jsonDecode(response.body);
    return data;
  }


  static Future<dynamic> fetchTagihan() async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");

    final response = await http.get(
      Uri.parse('${url}tagihan/getAllTagihanUser'),
      headers:{
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer $token',
      },);
    
    return response;
  }

  static Future<dynamic> updateStatusTagihan(dynamic data) async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");

    final response = await http.put(
      Uri.parse('${url}tagihan/updateStatusTagihan'),
      headers:{
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer $token',
      },
      body: data);
   
    return response;
  }

  static Future<dynamic> updateSaldo(dynamic data) async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");
    
    final response = await http.put(
      Uri.parse('${url}pasien/updateSaldoPasien'),
      headers:{
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer $token',
      },
      body: data);

    return response;
  }
}
