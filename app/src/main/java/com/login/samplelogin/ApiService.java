package com.login.samplelogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService
{
    @FormUrlEncoded
    @POST("facultyLogin.php")
    Call<Response> performLogin(@Field("faculty_id") String f_id,@Field("password") String pwd);

    @FormUrlEncoded
    @POST("facultyRegistration.php")
    Call<Response> signUp(@Field("faculty_id") String f_id,@Field("f_name") String f_name,@Field("f_mobile") String f_mob,
                          @Field("f_department") String f_dept,@Field("f_designation") String f_des,@Field("password") String pwd);


}
