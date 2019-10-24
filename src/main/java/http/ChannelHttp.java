package http;

import dao.DataLayer;
import dao.model.ChannelJournal;
import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class ChannelHttp {

    private String db;
    private String url;
    private String user;
    private String password;

    public ChannelHttp(String db, String url, String user, String password) {
        this.db = db;
        this.url = url;
        this.user = user;
        this.password = password;
    }




    public String doGetHttpsRequest(String url, String transactionId, Map<String, String> mapHeader)
            throws IOException {

        Request.Builder builder = new Request.Builder().url(url);
        if (mapHeader != null) {
            mapHeader.forEach(builder::addHeader);
        }

        Request request = builder.get().build();

        OkHttpClient client = ChannelHttp.getUnsafeOkHttpClient();

        String response = client.newCall(request).execute().body().string();

        ChannelJournal channel = new ChannelJournal();
        channel.setTransactionID(transactionId);
        channel.setRaw_request(url);
        channel.setRaw_response(response);

        new DataLayer().PersistJournal(this.db, this.url, this.user, this.password, channel);

        return response;
    }

    public String doPostHttpsRequest(String url, String json, String mediaType, String transactionId, Map<String, String> mapHeader)
            throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        if (mapHeader != null) {
            mapHeader.forEach(builder::addHeader);
        }
        Request request = builder.post(RequestBody.create(MediaType.parse(mediaType), json)).build();

        OkHttpClient client = ChannelHttp.getUnsafeOkHttpClient();

        String response = client.newCall(request).execute().body().string();

        ChannelJournal channel = new ChannelJournal();
        channel.setTransactionID(transactionId);
        channel.setRaw_request(url);
        channel.setRaw_response(response);

        new DataLayer().PersistJournal(this.db, this.url, this.user, this.password, channel);

        return response;
    }


    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            ;
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
