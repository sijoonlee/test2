package test;

public class Demo {
    public static void main(String[] args) {
        SalesforceConnector A = SalesforceConnector.getInstance(); //        constructor
        A.connect();
        System.out.println(A.getConnection()); // A login
        A.disconnect();                        // A -> disconnect
        System.out.println(A.getConnection()); // A disconnected

        SalesforceConnector B = SalesforceConnector.getInstance();
        System.out.println(B.getConnection()); // B disconnected
        B.connect();                         // B -> reconnect
        System.out.println(A.getConnection()); // A login
        System.out.println(B.getConnection()); // B login

    }
}
