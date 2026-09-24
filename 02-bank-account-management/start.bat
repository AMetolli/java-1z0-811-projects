@echo off
cd /d "%~dp0"

if exist "bin" rmdir /s /q "bin"
mkdir bin

echo Kompiliere Java-Dateien...
javac -d bin src\bank\*.java

if %errorlevel% neq 0 (
    echo.
    echo [FEHLER] Kompilierung fehlgeschlagen!
    pause
    exit /b %errorlevel%
)

echo Starte BankApp...
cls
java -cp bin bank.BankApp

pause