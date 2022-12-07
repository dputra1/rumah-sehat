import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_bloc.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_event.dart';
import 'package:rumah_sehat_mobile/api/api.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rumah_sehat_mobile/repository/user_repository.dart';

part 'sign_in_page.dart';
part 'sign_up_page.dart';
part 'splash_page.dart';
part 'list_tagihan_page.dart';