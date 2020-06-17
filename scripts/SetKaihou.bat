@echo off
cd %~dp0
cd ../


cmd /k gradlew.bat test --tests  "app.core.SetKaihou" -i