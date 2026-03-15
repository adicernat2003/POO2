package lab.interfaces;

public interface Connectable {

    void connectToWiFi(String networkName);

    default void showConnectionType() {
        System.out.println("Acest dispozitiv se poate conecta la o retea.");
    }

    static void printNetworkRules() {
        System.out.println("Regula: folositi o parola sigura pentru reteaua WiFi.");
    }
}
