ECHO OFF
SET RUN_ENV=%1
ECHO %RUN_ENV%

IF "%RUN_ENV"=="" (
	goto development
) else IF "%RUN_ENV%"=="development" (
	goto development
) else IF "%RUN_ENV%"=="production" (
	goto production
)

:development 
echo development
IF exist install/WEB-INF/success.lock (
	echo development
	ant startJetty
) ELSE (
	ant installJetty -Denv=development
) 

:production 
echo production
IF exist install/WEB-INF/success.lock (
	ant -buildfile production.build.xml  startJetty -Dport=8080 -Dlistenerport=8081 -Dpath=/ -Drootdir=./webroot 
) ELSE (
	ant installJetty -Denv=production
)