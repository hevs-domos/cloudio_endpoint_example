import ch.hevs.cloudio.endpoint.*;

public class MyEndpoint implements Runnable, CloudioEndpointListener {
    CloudioEndpoint myEndpoint;

    MyEndpoint(){
        try{
            //creating the cloud.io endpoint, you can also give another name and place the uuid
            // in the properties file
            myEndpoint = new CloudioEndpoint("telemes-bellevue");

            myEndpoint.addEndpointListener(this);

            myEndpoint.addNode("myNode", MyNode.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            MyNode myNode = myEndpoint.getNode("myNode");

            double val = 0;
            while (true) {
                val++;
                val %= 10;
                myNode.myObject.measure.setValue(val);
                System.out.println("New value pushed: " + val);
                Thread.sleep(2000);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void endpointIsOnline(CloudioEndpoint endpoint) {
        System.out.println("Endpoint is online");
    }

    @Override
    public void endpointIsOffline(CloudioEndpoint endpoint) {
        System.out.println("Endpoint is online");
    }
}
