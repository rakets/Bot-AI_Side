package com.university.routing.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class apiKey {
    public String readApiKey() throws IOException {            //method for reading API-key
        try (BufferedReader reader = new BufferedReader(new FileReader("D:/WSPA/erasmus/Subject Molise University/Automated software delivery/API_token_cloud_google.txt"))) {
            return reader.readLine();
        }
    }
}
