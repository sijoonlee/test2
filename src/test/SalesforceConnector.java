package test;

public class SalesforceConnector {

        // SalesforceConnector will have unique instance, so 'connection' doesn't need to be static
        private volatile String connection = null;
        public int count = 0;

        // Double checked locking Singleton pattern
        // Use getInstance method to get unique instance (Singleton s = Singleton.getInstance();)
        private volatile static SalesforceConnector uniqueInstance = null;

        public static SalesforceConnector getInstance()  {
            if(uniqueInstance == null){
                synchronized (SalesforceConnector.class) {
                    if(uniqueInstance == null){
                        uniqueInstance = new SalesforceConnector();
                    }
                }
            }
            return uniqueInstance;
        }

        public SalesforceConnector()  {
            System.out.println("constructor");
        }

        // we want to allow only one thread to use login method
        public void connect()  {
                if (connection == null || connection == "disconnected") {
                    synchronized(this){
                        if (connection == null || connection == "disconnected") {
                            connection = "login";
                            count += 1;
                        }
                    }
                } else {
                    //System.out.println("connection already exist");
                }
        }
        public void disconnect() {
            this.connection = "disconnected";
        }
        public void setConnection(String co) {
            this.connection = co;
        }
        public String getConnection() {
            return this.connection;
        }


}
