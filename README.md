INSTRUCCIONES PARA CORRER

1 - Abrir docker desktop o similar



2 - Entrar a la carpeta raiz del proyecto(MensajeriaRest) desde terminal(en visual, cmd o lo que sea)



3 - Correr el  siguiente comando 
```bash
docker-compose up --build
```


3.1 En el caso de que no funcione, compilar cada aplicacion y construir jar


3.2 vayan a la carpeta de cada servidor por (hearthbeat, proxy, receiver, sanity, sender)


3.3 Repetir para los demas servidores


3.3 ejecutar por terminal el siguiente comando
```bash
mvn clean package
```

4 - Esperar que todo cargue (El tiempo depende de tu computador, puede demorarse 1 minuto)



