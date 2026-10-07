@echo off
chcp 65001 > nul
echo Compiling...
javac -encoding UTF-8 -cp "lib/*;src" -d bin src/Client.java

if %errorlevel% equ 0 (
    echo Running...
    java -cp "lib/*;bin" Client
) else (
    echo Compilation failed.
)
pause