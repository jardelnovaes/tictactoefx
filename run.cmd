@echo off
cls

if "%1"=="--native" goto Native

:Jar
echo.
echo Running app as Jar package
echo.

java -Dlogback.configurationFile=E:\Projetos\JavaProjs\JavaFX\TicTacToeFX\target\jfx\app\logback.xml -jar target\jfx\app\tic-tac-toe-fx-0.0.1-SNAPSHOT-jfx.jar 


goto End

:Native
echo.
echo Running app as native .exe file
echo.
target\jfx\native\tic-tac-toe-fx-0.0.1-SNAPSHOT\tic-tac-toe-fx-0.0.1-SNAPSHOT.exe

:End
echo.
echo. 
echo Finished!
