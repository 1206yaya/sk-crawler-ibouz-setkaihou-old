@echo off
cd %~dp0
cd ../


rd /s /q build 
rd /s /q .gradle


cmd /k gradlew.bat test --tests  "app.core.SetKaihou" -i