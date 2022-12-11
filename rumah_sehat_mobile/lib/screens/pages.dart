import 'dart:convert';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_bloc.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_event.dart';
import 'package:rumah_sehat_mobile/api/api.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rumah_sehat_mobile/constants.dart';
import 'package:rumah_sehat_mobile/domain/domain.dart';
import 'package:rumah_sehat_mobile/repository/user_repository.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'dart:async';
import 'dart:convert';

part 'sign_in_page.dart';
part 'sign_up_page.dart';
part 'splash_page.dart';
part 'tagihan_page.dart';
part 'list_appointment.dart';
part 'tagihan_detail.dart';
