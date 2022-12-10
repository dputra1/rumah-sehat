import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:dio/dio.dart';

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
      body: jsonEncode(<String, String>{'username': userId, 'password': password}),
    );
    if (response.statusCode == 200) {
      final Map parsedResponse = json.decode(response.body);
      final String token = parsedResponse['token'];
      bool isTokenExpired = JwtDecoder.isExpired(token);
      if (!isTokenExpired) {
        return parsedResponse;
      }
    }
    return {

      "token": "Failed"
    };
  }

  static Future<int> signUp(String email, String password, String username, String umur, String nama) async {
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

  static Future<dynamic> fetchAppointment() async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");
    final response = await http.get(
      Uri.parse('http://localhost:2020/api/appointment/list-appointment'),
      headers:{
      'Authorization': '$token',
      },);
    return response;
  }

  static Future<dynamic> fetchTagihan() async {
    final storage = FlutterSecureStorage();
    final token = await storage.read(key: "token");
    final response = await http.get(
      Uri.parse('http://localhost:2020/api/tagihan/getall'),
      headers:{
      'Authorization': '$token',
      },);
    return response;
  }
}
