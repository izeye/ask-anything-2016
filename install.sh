#!/bin/bash

git pull
./gradlew clean bootRepackage -Penv=production
