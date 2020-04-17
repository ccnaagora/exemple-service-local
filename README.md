# exemple-service-local

Appli qui implémante et démarre un service local (local à l'appli).
2 classes: 
    MainActivity avec un textview
    Service qui hérite de IntentService
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

Le service peut aussi être sollicité depuis n'importe quel client udp
