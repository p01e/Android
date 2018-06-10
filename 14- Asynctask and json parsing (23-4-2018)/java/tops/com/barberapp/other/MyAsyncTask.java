package tops.com.barberapp.other;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyAsyncTask extends AsyncTask<String,Integer,String>
    {
        private final OnResponseListener listener;
        ProgressDialog pd;
        private Context context;

        public interface OnResponseListener
        {
            void onResponse(String result);
        }

        public MyAsyncTask(Context context, OnResponseListener listener){
            this.context=context;
            this.listener=listener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=ProgressDialog.show(context,"Wait","Loading");
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder().url(strings[0]).build();
            try {
                Response response=client.newCall(request).execute();
                String result=response.body().string();
                return result;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            listener.onResponse(s);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }