package in.bmsit.bmsit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Login extends ActionBarActivity {
    String TAG="BMSIT APP",ACCESS_TOKEN;
    String id,password;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ProgressBar progressBar= (ProgressBar)findViewById(R.id.progressBar);
        final EditText eId = (EditText)findViewById(R.id.editId);
        final EditText ePass = (EditText)findViewById(R.id.editPass);
        final Button bLogin = (Button)findViewById(R.id.bLogin);
        final Button bReg = (Button)findViewById(R.id.bRegister);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager= (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null && networkInfo.isConnected()){
                    id=eId.getText().toString();
                    password=ePass.getText().toString();
                    url="http://192.168.0.105:4567/token?user_id="+id+"&password="+password;
                    progressBar.setVisibility(View.VISIBLE);
                    bLogin.setEnabled(false);
                    new Authenticate().execute(url);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Not connected to the network",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(".Register");
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private class Authenticate extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            try {
                return Access(urls[0]);
            } catch (IOException e) {
                return "IOException while accessing the URL";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            ACCESS_TOKEN=s;
            Toast.makeText(getApplicationContext(),ACCESS_TOKEN,Toast.LENGTH_SHORT).show();
            Intent i=new Intent(".Broadcast");
            startActivity(i);
            Button bLog= (Button) findViewById(R.id.bLogin);
            ProgressBar progressBar= (ProgressBar) findViewById(R.id.progressBar);
            bLog.setEnabled(true);
            progressBar.setVisibility(View.GONE);
            SharedPreferences sharedPreferences=getSharedPreferences("in.bmsit.bmsit.MYPREFS",
                    MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ACCESS_TOKEN",ACCESS_TOKEN);
            editor.apply();
        }

        private String Access(String myUrl) throws IOException{
            InputStream is = null;
            try {
                Log.d(TAG,"Entered Access method");
                URL url = new URL(myUrl);
                Log.d(TAG,url.toString());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(20000);
                httpURLConnection.setReadTimeout(20000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                int response = httpURLConnection.getResponseCode();
                is = httpURLConnection.getInputStream();
                Reader reader = null;
                reader = new InputStreamReader(is, "UTF-8");
                char[] buffer = new char[500];
                reader.read(buffer);
                String content = new String(buffer);
                return content;
            }
            finally {
                if(is!=null)
                    is.close();
            }

        }
    }
}