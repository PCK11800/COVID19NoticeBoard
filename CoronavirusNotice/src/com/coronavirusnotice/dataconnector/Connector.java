package com.coronavirusnotice.dataconnector;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;;

public class Connector {

    public Connector(String url)
    {
        try{
            trustAllCertificates();
            connect(url);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void connect(String link) throws IOException {

        URL url = new URL(link);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        int statusCode = huc.getResponseCode(); //get response code
        System.out.println("Status Code: " + statusCode);
        if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP
                || statusCode == HttpURLConnection.HTTP_MOVED_PERM){ // if file is moved, then pick new URL
            link = huc.getHeaderField("Location");
            url = new URL(link);
            huc = (HttpURLConnection)url.openConnection();
        }
        System.out.println("Downloading");
        System.out.println(link);

        InputStream is = huc.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        FileOutputStream fos = new FileOutputStream("data.csv");
        int i = 0;
        while ((i = bis.read()) != -1) {
            fos.write(i);
        }

        System.out.println("Download complete\n");
    }

    private static void trustAllCertificates() throws NoSuchAlgorithmException, KeyManagementException
    {
        TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager()
        {
            public X509Certificate[] getAcceptedIssuers()
            {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(
                    X509Certificate[] certs, String authType)
            {
            }

            public void checkServerTrusted(
                    X509Certificate[] certs, String authType)
            {
            }
        }};

        SSLContext sslContext = SSLContext.getInstance("SSL");
        Security.setProperty("crypto.policy", "unlimited");
        sslContext.init(null, trustManagers, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }
}

