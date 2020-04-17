package agora.ccna.testdeservice;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/*
classe qui implémante et démarre un service local (local à l'appli).
Le service écoute en UDP sur le port 5000 et recopie le message reçu dans un TextView de la classe principale.

On peut solliciter le service depuis un navigateur web grace à un script php
EX de script : envoiUDP.php
//************************************************************
<?php
if (extension_loaded('sockets')) {
	$socket = socket_create(AF_INET, SOCK_DGRAM, 0) ;
	if($socket === false) {
		$errorcode = socket_last_error() ;
		$errormsg = socket_strerror($errorcode);
		echo "<p>Erreur socket IPv4: ".$errormsg."</p>\n" ;
	}
	else {
		echo "<p>Socket IPv4 ok</p>\n" ;
		$mes = $_GET['mes'];//"Coucou message web";
		//socket_sendto ( resource $socket , string $buf , int $len , int $flags , string $addr [, int $port = 0 ] ) : int
		//envoi du message passer dans l'url:		http://ip_smartphone/chemin_des_pages_web/envoiUDP?mes=xxxxxxxxxxxxx
		$nb = socket_sendto ( $socket , $mes ,  strlen($mes) , 0 , "192.168.1.13" , 5000);
		echo ("<br>envoi de ".$nb." octets");
		socket_close($socket);
	}
}
?>
//******************************************************
Le message étant récupéré dans l'url, il faut que celle-ci soit de la forma:
http://ip_smartphone/chemin_des_pages_web/envoiUDP?mes=messageA_transmettre

 */
public class MainActivity extends AppCompatActivity {

    TextView tvr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tvr = findViewById(R.id.tvresultat);
        //pour démarrer le service
        Button bdem = findViewById(R.id.bdemarrer);
        bdem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceTCP serv = new ServiceTCP();
                //on passe le widget en paramètre au service
                ServiceTCP.setTvr(tvr);
                Intent it = new Intent(getApplicationContext() , ServiceTCP.class);
                //paramètre : ici il ne sert à rien
                it.putExtra(ServiceTCP.EXTRA_PARAM1 , "coucou");
                it.setAction(ServiceTCP.ACTION_MAFONCTION);
                startService(new Intent(it));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
