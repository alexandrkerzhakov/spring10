package ru.gb.diplom;

import okhttp3.MultipartBody;
import okhttp3.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import ru.gb.diplom.model.virusTotal.IpInfo;
import ru.gb.diplom.properties.VirusTotalProperties;
import ru.gb.diplom.service.virusTotal.VirusTotalService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VirusTotalServiceTest {
    @Mock
    private VirusTotalProperties virusTotalProperties;
    @InjectMocks
    private VirusTotalService virusTotalService;

    @BeforeEach
    public void setUp() {
        when(virusTotalProperties.getAPI_KEY()).thenReturn("05c9a3dde9d7257a4968674470599f6639f35dfb14477c924da548ea4f85e2a9");
        when(virusTotalProperties.getBASE_URL()).thenReturn("https://www.virustotal.com/api/v3/");
    }

    @Test
    public void createGetRequestTest() {
        String expectedURL = virusTotalProperties.getBASE_URL() + "ip_addresses/185.220.101.42";
        Request request = virusTotalService.createGetRequest(virusTotalProperties.getBASE_URL(), "ip_addresses/", "185.220.101.42", "");

        assertEquals(expectedURL, request.url().toString());
        assertEquals("GET", request.method());
        assertEquals(virusTotalProperties.getAPI_KEY(), request.headers().get("x-apikey"));
        assertEquals("application/json", request.headers().get("accept"));
    }


    @Test
    public void createPostRequestTest() throws IOException {
        String expectedURL = virusTotalProperties.getBASE_URL() + "files";
        Request request = virusTotalService.createPostRequest(virusTotalProperties.getBASE_URL(), "files",
                new MockMultipartFile("file","test.txt", "text/plain", "test".getBytes(StandardCharsets.UTF_8)));

        MultipartBody multipartBody = (MultipartBody) request.body();
        String filename = multipartBody
                .part(0)
                .headers()
                .get("Content-Disposition")
                .split(";")[2]
                .split("=")[1]
                .replaceAll("\"", "");

        assertEquals(expectedURL, request.url().toString());
        assertEquals("test.txt", filename);
        assertEquals("POST", request.method());
        assertEquals(virusTotalProperties.getAPI_KEY(), request.headers().get("x-apikey"));
        assertEquals("multipart/form-data", request.headers().get("content-Type"));
        assertEquals("application/json", request.headers().get("accept"));
    }

    @Test
    public void getResponseStringAndGetInstanceTest() {
        Request request = virusTotalService.createGetRequest(virusTotalProperties.getBASE_URL(), "ip_addresses/", "185.220.101.42", "");
        String jsonString = virusTotalService.getResponseString(request).orElseThrow();
        IpInfo ipInfo = virusTotalService.getInstance(jsonString, IpInfo.class);

        assertEquals("185.220.101.42", ipInfo.getData().getId());
    }



}