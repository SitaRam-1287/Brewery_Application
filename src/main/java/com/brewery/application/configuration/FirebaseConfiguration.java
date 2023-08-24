package com.brewery.application.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfiguration {

    @Value("brewery_application.json")
    private String firebasePrivateKeyFile;

    @Bean
    public void initFirebase() throws IOException{
        InputStream serviceAccount = new ClassPathResource("brewery_application.json").getInputStream();

        FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(firebaseOptions);
        }
    }


}
