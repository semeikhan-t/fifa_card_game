param(
    [switch]$reinitDb = $false
)
$ErrorActionPreference = "Stop"

$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.10"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"

Write-Host "Using Java from: $env:JAVA_HOME" -ForegroundColor Yellow
Write-Host "Checking PostgreSQL..." -ForegroundColor Cyan
$pgPath = "C:\Program Files\PostgreSQL\18\bin\pg_ctl.exe"
$pgData = "C:\Program Files\PostgreSQL\18\data"
if (Test-Path $pgPath) {
    
    $status = & $pgPath -D $pgData status
    if ($status -match "no server running") {
        & $pgPath -D $pgData start
    }
}

if ($reinitDb) {
    Write-Host "Checking PostgreSQL..."
    $psql = "C:\Program Files\PostgreSQL\18\bin\psql.exe"
    $dbName = "fifa_card_game"
    $env:PGPASSWORD = "helloworld"
    
    & $psql -U postgres -d $dbName -f "src/main/resources/db/init.sql"
    & $psql -U postgres -d $dbName -f "src/main/resources/db/seed.sql"
    
    Write-Host "Running photo path matcher..."
    javac -cp "out;lib\postgresql-42.6.0.jar" UpdatePhotoPaths.java
    java -cp ".;out;lib\postgresql-42.6.0.jar" UpdatePhotoPaths
}

Write-Host "Compiling Java files..." -ForegroundColor Cyan
Get-ChildItem -Path "src" -Filter "*.java" -Recurse | Select-Object -ExpandProperty FullName | Out-File sources.txt -Encoding default
cmd /c "javac --module-path `"javafx-sdk-21.0.2\lib`" --add-modules javafx.controls,javafx.fxml,javafx.media -cp `"lib\postgresql-42.6.0.jar;lib\json-20240303.jar`" -d out @sources.txt"

Write-Host "Copying resources..." -ForegroundColor Cyan
if (!(Test-Path "out\css")) { New-Item -ItemType Directory -Force -Path "out\css" | Out-Null }
if (!(Test-Path "out\fxml")) { New-Item -ItemType Directory -Force -Path "out\fxml" | Out-Null }
if (!(Test-Path "out\images")) { New-Item -ItemType Directory -Force -Path "out\images" | Out-Null }
if (!(Test-Path "out\sounds")) { New-Item -ItemType Directory -Force -Path "out\sounds" | Out-Null }

if (!(Test-Path "out\video")) { New-Item -ItemType Directory -Force -Path "out\video" | Out-Null }

Copy-Item -Path "resources\css\*" -Destination "out\css" -Recurse -Force
Copy-Item -Path "resources\fxml\*" -Destination "out\fxml" -Recurse -Force
Copy-Item -Path "resources\images\*" -Destination "out\images" -Recurse -Force
Copy-Item -Path "resources\video\*" -Destination "out\video" -Recurse -Force
Copy-Item -Path "src\main\resources\sounds\*" -Destination "out\sounds" -Recurse -Force

if (Test-Path "src\main\resources\images") {
    Copy-Item -Path "src\main\resources\images\*" -Destination "out\images\" -Recurse -Force
}

Write-Host "Build complete! Launching application..." -ForegroundColor Green
cmd /c "java ""-Djava.library.path=javafx-sdk-21.0.2\bin"" --module-path ""javafx-sdk-21.0.2\lib"" --add-modules javafx.controls,javafx.fxml,javafx.media -cp ""out;lib\postgresql-42.6.0.jar;lib\json-20240303.jar"" com.fifa.Launcher"