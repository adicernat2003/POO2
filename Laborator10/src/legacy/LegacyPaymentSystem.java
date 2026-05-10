package legacy;

public class LegacyPaymentSystem {
    public boolean makeOldPayment(double amountInCents) {
        System.out.println("Legacy payment processed: " + amountInCents + " cents");
        return true;
    }
}