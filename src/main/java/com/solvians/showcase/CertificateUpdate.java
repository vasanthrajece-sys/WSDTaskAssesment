package com.solvians.showcase;

import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class CertificateUpdate implements Callable<String> {
    @Override
    public String call() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int bidPriceCents = random.nextInt(10_000, 20_001);
        int bidSize = random.nextInt(1_000, 5_001);
        int askPriceCents = random.nextInt(10_000, 20_001);
        int askSize = random.nextInt(1_000, 10_001);

        return String.format(
                Locale.ROOT,
                "%d,%s,%.2f,%d,%.2f,%d",
                System.currentTimeMillis(),
                new IsinGenerator().generateIsin(),
                bidPriceCents / 100.0,
                bidSize,
                askPriceCents / 100.0,
                askSize);
    }
}
