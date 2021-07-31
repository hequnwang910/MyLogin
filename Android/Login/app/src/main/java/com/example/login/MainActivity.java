package com.example.login;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;



import com.example.login.entity.LoginUser;
import com.example.login.entity.ResultGson;
import com.example.login.entity.User;
import com.example.login.utils.AESHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import retrofit2.Retrofit;



public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private User user;
    private LoginUser loginUser;
    private Retrofit retrofit;
    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                Log.i("ahuwhq拦截器",message);
//            }
//        }).setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        client = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build();
//
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://114.213.208.85:8001/server/user/")
//                .client(client)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);

     /*   //同步请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    retrofit2.Response<String> response = apiService.testget("1").execute();

                    Log.i("ahuwhq","返回数据"+response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

//        //异步请求
//        apiService.testpost("\\").enqueue(new retrofit2.Callback<String>() {
//            @Override
//            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
//                Log.i("ahuwhq","返回数据"+response.body());
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<String> call, Throwable t) {
//
//            }
//        });


    }

    public void register(View view) {
        user = new User();
        user.setUserName(username.getText().toString());
        user.setUserPassword(password.getText().toString());
        Log.d("whqusername",username.getText().toString());
        Log.d("whqpassword",password.getText().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                JSONObject jsonObject = new JSONObject();
                OkHttpClient httpClient = new OkHttpClient();
                try {
                    jsonObject.put("userId",10);
                    jsonObject.put("userName",user.getUserName());
                    jsonObject.put("userPassword",user.getUserPassword());
                    jsonObject.put("currentVersion",null);
                    jsonObject.put("latestVersion",null);
                    jsonObject.put("updateDescription",null);
                    jsonObject.put("headPortrait",null);
                    jsonObject.put("nickName",user.getUserName());
                    jsonObject.put("vipTime",null);
                    jsonObject.put("userCategory",null);
                    jsonObject.put("registerDate",null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(JSON, String.valueOf(jsonObject));
                String url = "http://114.213.208.85:8001/server/user/addUser/";
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("whq注册","失败了");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("whq注册",response.toString()+"------------------");
                        Log.d("whq注册",response.body().toString()+"------------------");

                    }
                });

            }
        }).start();
    }

    public void getUser(View view) {
        OkHttpClient httpClient = new OkHttpClient();

        String url = "http://114.213.208.85:8001/server/user/getAllUsers";
        Request getRequest = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = httpClient.newCall(getRequest);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步请求，要放到子线程执行
                    Response response = call.execute();
                    Log.i("whq+getAllUserName", "okHttpGet run: response:"+ response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void login(View view) {

        loginUser = new LoginUser();
        loginUser.setUserName(username.getText().toString());
        loginUser.setUserPassword(password.getText().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                JSONObject jsonObject = new JSONObject();
                OkHttpClient httpClient = new OkHttpClient();
                try {
                    jsonObject.put("userName",loginUser.getUserName());
                    jsonObject.put("userPassword",loginUser.getUserPassword());
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                //7.29 对数组进行加密解密，后端解密，前端加密，秘钥为1；
                String encrypt = AESHelper.encrypt(jsonObject.toString(), "111");


//                RequestBody requestBody = RequestBody.create(JSON, String.valueOf(jsonObject));
                RequestBody requestBody = RequestBody.create(JSON, encrypt);


                Log.d("前端测试发送的jsonObject：",jsonObject.toString());
                Log.d("encrypt：",encrypt);
                Log.d("前端测试发送的requestBody：",requestBody.toString());
//                String url = "http://114.213.208.85:8001/server/user/login";
                String url = "http://114.213.208.85:8001/server/user/loginaes";
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Log.d("前端测试发送的request：",request.toString());

                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("whq登录","失败了");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String MyResult = response.body().string();
                        Gson gson = new GsonBuilder().create();
                        ResultGson resultGson = gson.fromJson(MyResult, ResultGson.class);

                        Log.d("whq登录",resultGson.getData().get("result")+"---------resultGson---------");
                        Log.d("whq登录",resultGson.toString()+"---------resultGson---------");

                        Log.d("whq登录","----------------------------");
                        Map<String, Object> data = new HashMap<String, Object>();
                        data.put("result","success login");
                        ResultGson json = new ResultGson(true, 20000, "成功", data);
                        Log.d("原始json",json.toString());
                        Gson gson1 = new GsonBuilder().create();
                        String toJson = gson1.toJson(json);
                        Log.d("转化之后的json",toJson);

                        Log.d("whq登录",response+"---------response---------");
                        Log.d("whq登录",response.message()+"---------message---------");
                        Log.d("whq登录",response.body().toString()+"------------------");
                        Log.d("whq登录",MyResult+"-----------MyResult-------");
                        Log.d("whq登录",MyResult.contains("success login")+"-----------MyResult.length()-------");

                    }
                });
            }
        }).start();



    }

    public void getNickname(View view) {
        OkHttpClient httpClient = new OkHttpClient();

        String url = "http://114.213.208.85:8001/server/user/getUserNicknameByname";
        String userName = username.getText().toString();

        String ahuwhq = String.format("%s/%s", url,userName);
        Request getRequest = new Request.Builder()
                .url(ahuwhq)
                .get()
                .build();

        Call call = httpClient.newCall(getRequest);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步请求，要放到子线程执行
                    Response response = call.execute();
                    Log.i("whq登录", "okHttpGet run: response:"+ response.body().string());
                    Log.i("whq登录", "okHttpGet run: response:"+ response);
                    Log.i("whq登录", "okHttpGet run: response:"+ response.message());
                    Log.i("whq登录", "okHttpGet run: response:"+ response.code());


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void getcode(View view){


    }


}