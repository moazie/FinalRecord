@echo off
chcp 65001 > nul
echo Compiling...
javac -cp "lib/*;src" src/Client.java

if %errorlevel% equ 0 (
    echo Running...
    java -cp "lib/*;src" Client
) else (
    echo Compilation failed.
)
pause
