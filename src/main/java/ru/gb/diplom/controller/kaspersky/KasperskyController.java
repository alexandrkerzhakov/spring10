package ru.gb.diplom.controller.kaspersky;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import ru.gb.diplom.model.kaspersky.AnalysesFile;
import ru.gb.diplom.model.kaspersky.DomainInfo;
import ru.gb.diplom.model.kaspersky.IpInfo;
import ru.gb.diplom.model.kaspersky.UrlInfo;
import ru.gb.diplom.service.kaspersky.KasperskyService;

import java.io.IOException;

@Controller
@RequestMapping("/kaspersky")
public class KasperskyController {

    @Autowired
    private KasperskyService kasperskyService;

    @GetMapping("/ip")
    public String getIp(@RequestParam("ip") String ip, Model model) {

        Request request = kasperskyService.createGetRequest("search/", "ip?request=", ip);
        System.out.println(request);
        String json = kasperskyService.getResponseString(request).orElseThrow();
        System.out.println(json);
        IpInfo ipInfoInstance = kasperskyService.getInstance(json, IpInfo.class);
        System.out.println(ipInfoInstance);
        model.addAttribute("ipInfoInstance", ipInfoInstance);

        return "kaspersky/ip_info";

    }

    @GetMapping("/domain")
    public String getDomainInfo(@RequestParam("domain") String domain, Model model) {

        Request request = kasperskyService.createGetRequest("search/", "domain?request=", domain);
        String json = kasperskyService.getResponseString(request).orElseThrow();
        DomainInfo domainInfoInstance = kasperskyService.getInstance(json, DomainInfo.class);
        model.addAttribute("domainInfoInstance", domainInfoInstance);

        return "kaspersky/domain_info";
    }

    @GetMapping("/url")
    public String getUrlInfo(@RequestParam("url") String url, Model model) {

        Request request = kasperskyService.createGetRequest("search/", "url?request=", url);
        String json = kasperskyService.getResponseString(request).orElseThrow();
        UrlInfo urlInfoInstance = kasperskyService.getInstance(json, UrlInfo.class);
        model.addAttribute("urlInfoInstance", urlInfoInstance);

        return "kaspersky/url_info";
    }

    @GetMapping("/getupload")
    public String fileUpload() {
        return "kaspersky/upload_file";
    }


    @PostMapping("/upload")
    public String autoFileUpload(@RequestBody MultipartFile file, Model model) throws IOException {

        Request request = kasperskyService.createPostRequest("scan/", "file?filename=", file);
        String json = kasperskyService.getResponseString(request).orElseThrow();
        AnalysesFile analysesFileInstance = kasperskyService.getInstance(json, AnalysesFile.class);
        model.addAttribute("analysesFileInstance", analysesFileInstance);

        return "kaspersky/analyses_file";
    }
}


