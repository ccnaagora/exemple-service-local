package agora.ccna.testdeservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceTCP extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    //surtout, penser à changer le nom des actions par défault (du wizard) sinon
    //problème de droit et de permissions (action de google)
    public static final String ACTION_MAFONCTION = "agora.ccna.testdeservice.action.MAFONCTION";

    // TODO: Rename parameters
    public static final String EXTRA_PARAM1 = "agora.ccna.testdeservice.extra.PARAM1";

    //le widget de l'activité principale
    public static TextView tvr=null;

    //constructeur : ne pas modifier le prototype, ça pose problème
    public ServiceTCP() {
        super("ServiceTCP");
        Log.i("SERV" , "constructeur ServiceTCP");
    }

    public static void setTvr(TextView t){
        tvr = t;
    }
    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    /*public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ServiceTCP.class);
        intent.setAction(ACTION_MAFONCTION);
        intent.putExtra(EXTRA_PARAM1, param1);
        context.startService(intent);
        Log.i("SERV" , "Start start BAZ : " + param1);
    }*/



    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("SERV" , "onHandle: " + intent.getAction());
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_MAFONCTION.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionMaFonction(param1);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionMaFonction(String param1) {
        while(true){
            Log.i("SERV" , "handleActionMaFonction");
            try {
                    DatagramSocket socket = new DatagramSocket(5000);
                    while(true) {
                        //socket udp (c'est plus simple et moins problématique)
                        Log.i("SERV" , "Serveur udp sur le port 5000 démarré: " );
                        byte[] buffer = new byte[32];
                        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                        socket.receive(request);
                        String r = new String(request.getData() , 0 , request.getLength());
                        //on affecte le texte rçu au widget de la classe principale.
                        if(tvr != null)tvr.setText(r);
                        else Log.d("SERV" , "tvr null");
                        Log.i("SERV" , "Reçu: :" + r);
                        Thread.sleep(50);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }


}
