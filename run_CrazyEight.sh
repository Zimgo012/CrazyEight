#!/bin/bash

# ---------------------------------------------------------------------
# JAP COURSE - SCRIPT
# SCRIPT CST8221 - Fall 2024
# ---------------------------------------------------------------------
# Begin of Script (Labs - F24)
# ---------------------------------------------------------------------

clear  # Equivalent to Windows 'CLS'

# LOCAL VARIABLES ....................................................

LIBDIR="lib"
SRCDIR="$(pwd)/src"
BINDIR="out/production/CrazyEight2"
BINERR="labs-javac.err"
JARNAME="CrazyEight2.jar"
JAROUT="labs-jar.out"
JARERR="labs-jar.err"
DOCDIR="doc"
DOCERR="labs-javadoc.err"
ARTIFACTDIR="out/artifacts/CrazyEight2_jar"
MANIFEST="resources/META-INF/MANIFEST.MF"
MAINCLASSSRC="$SRCDIR/Main.java"  # No package
MAINCLASSBIN="Main"  # No package

echo "==============================================="
echo "  ALGONQUIN COLLEGE - JAVA APPLICATION PROGRAMMING - FALL 2024"
echo "==============================================="
echo "ATTENTION : PLEASE USE JAVA 8 ONLY"
echo ""
echo "========================================================"
echo "ATTENTION: PLEASE USE JAVA 8 ONLY"
echo "JDK folder should be like C:\Program Files\Java\jdk-1.8"
echo "========================================================"
echo ""
echo "Do you want to use the system's default JDK? (y/n)"
read -r use_default

if [[ "$use_default" == "y" || "$use_default" == "Y" ]]; then
    JAVA_CMD="java"
    JAVAC_CMD="javac"
    JAR_CMD="jar"
    JAVADOC_CMD="javadoc"
else
    echo "Enter the path to your JDK (e.g., /Library/Java/JavaVirtualMachines/jdk-17):"
    read -r JDK_PATH

    if [ ! -d "$JDK_PATH" ]; then
        echo "Invalid JDK path! Exiting..."
        exit 1
    fi

    export JAVA_HOME="$JDK_PATH"
    export PATH="$JAVA_HOME/bin:$PATH"

    JAVA_CMD="$JAVA_HOME/bin/java"
    JAVAC_CMD="$JAVA_HOME/bin/javac"
    JAR_CMD="$JAVA_HOME/bin/jar"
    JAVADOC_CMD="$JAVA_HOME/bin/javadoc"
fi

echo "[LABS SCRIPT ---------------------]"

# Ensure directories exist
mkdir -p "$BINDIR"
mkdir -p "$ARTIFACTDIR"
mkdir -p "$DOCDIR"
mkdir -p "$(dirname "$MANIFEST")"

# Step 1: Compile Java files
echo "1. Compiling ......................"
find "$SRCDIR" -name "*.java" > sources.txt
$JAVAC_CMD -Xlint -cp ".:$SRCDIR:$LIBDIR/*" @sources.txt -d "$BINDIR" 2> "$BINERR"
rm sources.txt

if [ $? -ne 0 ]; then
    echo "Compilation failed! Check $BINERR for details."
    exit 1
fi

echo "Compilation successful!"

# Step 2: Create JAR file
echo "2. Creating Jar ..................."
cd "$BINDIR" || exit
mkdir -p "../../$ARTIFACTDIR"
$JAR_CMD cvfe "../../$ARTIFACTDIR/$JARNAME" "$MAINCLASSBIN" . > "../../$JAROUT" 2> "../../$JARERR"
cd ../../ || exit

if [ ! -f "$ARTIFACTDIR/$JARNAME" ]; then
    echo "Error: JAR file not found! Something went wrong in creation."
    cat "$JARERR"
    exit 1
fi

# Step 3: Generate Javadoc
echo "3. Creating Javadoc ..............."
echo "Checking for source directory at: $SRCDIR"

if [ ! -d "$SRCDIR" ]; then
    echo "Error: Source directory '$SRCDIR' not found!"
    exit 1
fi

find "$SRCDIR" -type f -name "*.java" > java_files_list.txt

if [ -s java_files_list.txt ]; then
    $JAVADOC_CMD -cp ".:$BINDIR:$LIBDIR/*" -d "$DOCDIR" @java_files_list.txt 2> "$DOCERR"
else
    echo "No Java files found to document."
    exit 1
fi

rm java_files_list.txt

# Step 4: Run the JAR file
echo "4. Running Jar ...................."
cd "$ARTIFACTDIR" || exit
$JAVA_CMD -jar "$JARNAME"
cd ../../ || exit

echo "[END OF SCRIPT -------------------]"
