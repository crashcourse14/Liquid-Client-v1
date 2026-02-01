#!/bin/bash

# Run the compile command
java -jar "$(dirname "$0")/../epkcompiler/CompilePackage.jar" \
    "$(dirname "$0")/../lwjgl-rundir/resources" \
    "$(dirname "$0")/../javascript/assets.epk"

# Check if the last command succeeded
if [ $? -eq 0 ]; then
    echo "CompilePackage ran successfully!"
else
    echo "CompilePackage failed. Check your paths or files."
fi
