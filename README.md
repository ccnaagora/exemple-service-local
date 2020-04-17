# exemple-service-local

Appli qui implémante et démarre un service local (local à l'appli).
2 classes: 
    MainActivity avec un textview
    Service qui hérite de IntentService
Le service écoute en UDP sur le port 5000 et recopie le message reçu dans un TextView de la classe principale.

On peut solliciter le service depuis un navigateur web grace à un script php
EX de script : envoiUDP.php

Voir les sources du script php dans les commentaires de MainActivity.java

Le message étant récupéré dans l'url, il faut que celle-ci soit de la forma:
http://ip_smartphone/chemin_des_pages_web/envoiUDP?mes=messageA_transmettre

Le service peut aussi être sollicité depuis n'importe quel client udp
