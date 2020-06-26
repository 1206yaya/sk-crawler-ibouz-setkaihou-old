@echo off
cd %~dp0
cd ../

rm -rf .gradle
rm -rf build

cmd /k gradlew.bat test --tests  "app.core.SetKaihou" -i