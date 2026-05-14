#!/bin/bash

set -e

echo "Checking Java version..."
java -version

JAVAFX_LIB="lib"
LIB_DIR="lib"
OUT_DIR="out"

if [ ! -f "$JAVAFX_LIB/javafx.controls.jar" ]; then
    echo "Error: JavaFX libraries not found in $JAVAFX_LIB"
    exit 1
fi

echo "Compiling Java files..."

find src -name "*.java" > sources.txt
javac --module-path "$JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml,javafx.media \
      -cp "$OUT_DIR:$LIB_DIR/postgresql-42.6.0.jar:$LIB_DIR/json-20240303.jar" \
      -d "$OUT_DIR" @sources.txt

echo "Copying resources..."
mkdir -p "$OUT_DIR/css"
mkdir -p "$OUT_DIR/fxml"
mkdir -p "$OUT_DIR/images"
mkdir -p "$OUT_DIR/sounds"
mkdir -p "$OUT_DIR/video"

cp -r resources/css/* "$OUT_DIR/css/" 2>/dev/null || true
cp -r resources/fxml/* "$OUT_DIR/fxml/" 2>/dev/null || true
cp -r resources/images/* "$OUT_DIR/images/" 2>/dev/null || true
cp -r resources/video/* "$OUT_DIR/video/" 2>/dev/null || true
cp -r src/main/resources/sounds/* "$OUT_DIR/sounds/" 2>/dev/null || true

if [ -d "src/main/resources/images" ]; then
    cp -r src/main/resources/images/* "$OUT_DIR/images/" 2>/dev/null || true
fi

echo "Build complete! Launching application..."

java -Dprism.order=sw --module-path "$JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml,javafx.media \
     -cp "$OUT_DIR:$LIB_DIR/postgresql-42.6.0.jar:$LIB_DIR/json-20240303.jar" \
     com.fifa.Launcher