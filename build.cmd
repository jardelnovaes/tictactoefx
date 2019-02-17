@echo off
cls

if "%1"=="--native" goto Native

:Jar
echo.
echo Building app as Jar package
echo.
mvn clean jfx:jar
goto End

:Native
echo.
echo Building app as native .exe file
echo.
mvn clean jfx:native



:End
echo.
echo. 
echo Finished!