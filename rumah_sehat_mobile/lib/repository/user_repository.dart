

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:meta/meta.dart';

class UserRepository {
  final FlutterSecureStorage _storage;
  final String key = "token";
  final String emailKey = "email";
  final String namaKey = "nama";
  final String usernameKey = "username";
  final String saldoKey = "saldo";
  final String umurKey = "umur";
  final String rolesKey = "pasien";

  UserRepository({@required FlutterSecureStorage storage}) : _storage = storage;

  Future<void> clearAll() async {
    await _storage.delete(key: emailKey);
    await _storage.delete(key: namaKey);
    await _storage.delete(key: usernameKey);
    await _storage.delete(key: saldoKey);
    await _storage.delete(key: umurKey);
  }

  Future<Map> getData() async {
    Map data = {};
    data[emailKey] = await _storage.read(key: emailKey);
    data[namaKey] = await _storage.read(key: namaKey);
    data[usernameKey] = await _storage.read(key: usernameKey);
    data[saldoKey] = await _storage.read(key: saldoKey);
    data[umurKey] = await _storage.read(key: umurKey);
    data[rolesKey] = await _storage.read(key: rolesKey);
    data[key] = await _storage.read(key: key);
    return data;
  }

  Future<void> deleteToken() async {
    await _storage.delete(key: key);
  }

  Future<void> persistToken(String token) async {
    await _storage.write(key: key, value: token);
  }

  Future<bool> hasToken() async {
    final value = await _storage.read(key: key);
    return value != null;
  }

  Future<String> getToken() async {
    return await _storage.read(key: key);
  }

  Future<String> getNama() async {
    return await _storage.read(key: namaKey);
  }

  Future<void> setNama(String nama) async {
    await _storage.write(key: namaKey, value: nama);
  }

  Future<String> getEmail() async {
    return await _storage.read(key: emailKey);
  }

  Future<void> setEmail(String email) async {
    await _storage.write(key: emailKey, value: email);
  }

  Future<String> getUsername() async {
    return await _storage.read(key: usernameKey);
  }

  Future<void> setUsername(String username) async {
    await _storage.write(key: usernameKey, value: username);
  }
  
  Future<String> getSaldo() async {
    return await _storage.read(key: saldoKey);
  }

  Future<void> setSaldo(String saldo) async {
    await _storage.write(key: saldoKey, value: saldo);
  }
  
  Future<String> getUmur() async {
    return await _storage.read(key: umurKey);
  }

  Future<void> setUmur(String umur) async {
    await _storage.write(key: umurKey, value: umur);
  }
  
  Future<String> getRoles() async {
    return await _storage.read(key: rolesKey);
  }

  Future<void> setRoles(String roles) async {
    await _storage.write(key: rolesKey, value: roles);
  }
}