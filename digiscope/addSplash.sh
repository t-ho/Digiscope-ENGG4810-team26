#!/bin/bash
cd dist/
mkdir digis
cd digis
unzip ../digiscope.jar
/bin/rm ../digiscope.jar
# /bin/cp ../META-INF_withSplashScreen/MANIFEST.MF META-INF/
/bin/cp ../modifiedManifest/MANIFEST.MF META-INF/
# make a zip without those invisible Mac resource files such as “_MACOSX” or “._Filename” and .ds store files, use the “-X” option
zip -r -X ../digiscope.jar *
cd ..
rm -r digis 
mv digiscope.jar ~/Desktop/
