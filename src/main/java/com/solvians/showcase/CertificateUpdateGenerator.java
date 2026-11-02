package com.solvians.showcase;

import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<CertificateUpdate> generateQuotes() {
        return Stream.generate(CertificateUpdate::new)
                .parallel()
                .limit((long) threads * quotes);
    }
}
