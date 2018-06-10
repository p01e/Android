package tops.com.barberapp.other;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyAsyncTask extends AsyncTask<String,Integer,String>
    {
        private final OnResponseListener listener;
        ProgressDialog pd;
        private Context context;
        private HashMap<String,String> hm;

        public interface OnResponseListener
        {
            void onResponse(String result);
        }

        public MyAsyncTask(Context context, OnResponseListener listener){
            this.context=context;
            this.listener=listener;
        }

        public HashMap<String, String> getHm() {
            return hm;
        }

        public void setHm(HashMap<String, String> hm) {
            this.hm = hm;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=ProgressDialog.show(context,"Wait","Loading");
        }

        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client=new OkHttpClient();
            //Request request=new Request.Builder().url(strings[0]).build();
            Request.Builder builder=new Request.Builder().url(strings[0]);
            if(hm!=null){
                FormBody.Builder bodyBuilder=new FormBody.Builder();
                for (Map.Entry<String,String> entry:hm.entrySet()) {
                    bodyBuilder.add(entry.getKey(),entry.getValue());
                }
                builder.post(bodyBuilder.build());
            }
            Request request=builder.build();
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