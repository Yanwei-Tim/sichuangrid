#!/bin/bash
RUN_ENV=$1
echo $RUN_ENV

if [ "$RUN_ENV" == "" ]; then
        if [ -f install/WEB-INF/success.lock ];
                then ant startJetty
        else 
                ant installJetty -Denv=development
        fi
elif [ "$RUN_ENV" == "development" ]; then
        echo "development"
        if [ -f install/WEB-INF/success.lock ];
                then ant startJetty
        else
                ant installJetty -Denv=development
        fi
elif [ "$RUN_ENV" == "production" ]; then
        echo "production"
        if [ -f install/WEB-INF/success.lock ];
                then ant -buildfile production.build.xml  startJetty -Dport=8080 -Dlistenerport=8081 -Dpath=/ -Drootdir=./webroot 
        else
                ant installJetty -Denv=production
        fi
fi