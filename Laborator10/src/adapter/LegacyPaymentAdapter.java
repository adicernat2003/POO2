package adapter;

import legacy.LegacyPaymentSystem;

public class LegacyPaymentAdapter implements PaymentProcessor {

    // Adapter Pattern: transforma interfața veche (legacy) intr-una compatibila cu aplicația noua.
    private final LegacyPaymentSystem legacyPaymentSystem;

    public LegacyPaymentAdapter(LegacyPaymentSystem legacyPaymentSystem) {
        this.legacyPaymentSystem = legacyPaymentSystem;
    }

    @Override
    public boolean pay(double amountInRon) {

        // Sistemul legacy lucrează in cents, aplicația moderna folosește RON.
        double amountInCents = amountInRon * 100;
        return legacyPaymentSystem.makeOldPayment(amountInCents);
    }
}