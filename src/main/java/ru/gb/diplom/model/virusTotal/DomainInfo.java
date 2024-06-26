package ru.gb.diplom.model.virusTotal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainInfo {
    private Data data;

    @lombok.Data
    public static class Data {
        private String id;
        private String type;
        private Links links;
        private Attributes attributes;

        @lombok.Data
        public static class Links {
            private String self;
        }

        @lombok.Data
        public static class Attributes {
            private int reputation;
            private LastAnalysisStats last_analysis_stats;
            private String[] tags;
            private TotalVotes total_votes;
            private Map<String, PopularityRanks> popularity_ranks;
            private Long last_dns_records_date;
            private String tld;
            private Categories categories;
            private String whois;
            private long last_https_certificate_date;
            private String jarm;
            private LastHttpsCertificate last_https_certificate;
            private Map<String, LastAnalysisResult> last_analysis_results;
            private long last_modification_date;
            public List<LastDnsRecords> last_dns_records;
            private String registrar;
            private long whois_date;
            private String last_analysis_date;
            private List<CrowdsourcedContext> crowdsourced_context;

        }

        @lombok.Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Categories {
        }

        @lombok.Data
        public static class PopularityRanks {
            private long timestamp;
            private int rank;
        }

        @lombok.Data
        public static class LastDnsRecords {
            private String type;
            private int ttl;
            private String value;
            private String rname;
            private long serial;
            private int refresh;
            private int retry;
            private int expire;
            private int minimum;
            private String flag;
            private String tag;
            private Long priority;

        }

        @lombok.Data
        public static class CrowdsourcedContext {
            private String severity;
            private String link;
            private String title;
            private String details;
            private long timestamp;
            private String source;
        }

        @lombok.Data
        public static class LastAnalysisStats {
            private int malicious;
            private int suspicious;
            private int undetected;
            private int harmless;
            private int timeout;
        }

        @lombok.Data
        public static class LastAnalysisResult {
            private String method;
            private String engine_name;
            private String category;
            private String result;
        }

        @lombok.Data
        public static class TotalVotes {
            private int harmless;
            private int malicious;
        }

        @lombok.Data
        public static class LastHttpsCertificate {
            private CertSignature cert_signature;
            private Extensions extensions;
            private Validity validity;
            private int size;
            private String version;
            private PublicKey public_key;
            private String thumbprint_sha256;
            private String thumbprint;
            private String serial_number;
            private Issuer issuer;
            private Subject subject;
        }

        @lombok.Data
        public static class CertSignature {
            private String signature_algorithm;
            private String signature;
        }

        @lombok.Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Extensions {
            private List<String> key_usage;
            private CaInformationAccess ca_information_access;
            private List<String> certificate_policies;
            @JsonProperty("CA")
            private boolean ca;
            private List<String> crl_distribution_points;
            private List<String> subject_alternative_name;
            private List<String> extended_key_usage;
            private AuthorityKeyIdentifier authority_key_identifier;
            private String subject_key_identifier;
            private String key_identifier;
        }

        @lombok.Data
        public static class CaInformationAccess {
            @JsonProperty("CA Issuers")
            private String ca_issuers;
            @JsonProperty("OCSP")
            private String ocsp;
        }

        @lombok.Data
        public static class AuthorityKeyIdentifier {
            private String keyid;

        }

        @lombok.Data
        public static class PublicKey {
            private String algorithm;
            private Ec ec;
            private Rsa rsa;
        }

        @lombok.Data
        public static class Ec {
            private String oid;
            private String pub;
        }

        @lombok.Data
        public static class Rsa {
            private String modulus;
            private String exponent;
            private int key_size;

        }

        @lombok.Data
        public static class Validity {
            private String not_after;
            private String not_before;

        }

        @lombok.Data
        public static class Issuer {
            @JsonProperty("C")
            private String c;
            @JsonProperty("O")
            private String o;
            @JsonProperty("CN")
            private String cn;
        }

        @lombok.Data
        public static class Subject {
            @JsonProperty("CN")
            private String cn;

            @JsonProperty("C")
            private String c;

            @JsonProperty("ST")
            private String st;

            @JsonProperty("L")
            private String l;

            @JsonProperty("O")
            private String o;
        }
    }
}

