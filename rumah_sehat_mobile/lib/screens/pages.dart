import 'dart:convert';
import 'dart:convert';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:intl/intl.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_bloc.dart';
import 'package:rumah_sehat_mobile/bloc/authentication_event.dart';
import 'package:rumah_sehat_mobile/api/api.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:rumah_sehat_mobile/constants.dart';
import 'package:rumah_sehat_mobile/domain/domain.dart';
import 'package:rumah_sehat_mobile/repository/user_repository.dart';
import 'package:rumah_sehat_mobile/repository/dokter_repository.dart';
import 'package:rumah_sehat_mobile/style.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import 'package:intl/intl.dart';
import 'package:http/http.dart';
import 'package:top_snackbar_flutter/custom_snack_bar.dart';
import 'package:top_snackbar_flutter/safe_area_values.dart';
import 'package:top_snackbar_flutter/tap_bounce_container.dart';
import 'package:top_snackbar_flutter/top_snack_bar.dart';



import 'dart:async';
import 'dart:convert';

part 'sign_in_page.dart';
part 'sign_up_page.dart';
part 'splash_page.dart';
part 'add_appointment.dart';
part 'tagihan_page.dart';
part 'list_appointment.dart';
part 'detail_resep.dart';
part 'tagihan_detail.dart';
part 'top_up_page.dart';
part 'button.dart';
part 'detail_profile_page.dart';
part 'detail_appointment.dart';

