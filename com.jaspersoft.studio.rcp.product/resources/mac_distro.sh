#!/bin/sh

#cd /Users/gtoffoli/Desktop/jss

# Disk creation -----------------------

## 1. Create a sparse bundle image
#hdiutil create -ov  -size 100m -type SPARSEBUNDLE -volname "ezRepo Designer" -fs "Journaled HFS+" -attach jaspersoftstudio.dmg
## 2. Copy the icon of the volume (jssbox.icns)
#cp jssbox.icns "/Volumes/ezRepo Designer/.VolumeIcon.icns"
## 3. Create the application directory
#mkdir "/Volumes/ezRepo Designer/ezRepo Designer"
## 4. Link the Application directory
#ln -s /Applications "/Volumes/ezRepo Designer/Applications"
## 5. Set the icon for the volume (SetFile is an XCode command)
#SetFile -a C "/Volumes/ezRepo Designer/"
## 6. Set the icon for the ezRepo Designer folder... (SetIcon is a self made command)
#./SetIcon -i jssfolder.icns "/Volumes/ezRepo Designer/ezRepo Designer 1.0/"
## 7. Copy the background to the volume
#cp -Rf jss-package-background.png  "/Volumes/ezRepo Designer/"
## 8. Now before unmount everything, use the Finder to customize the look and feel
##    of the folder by setting the background and setting icons size and position.
## 9. Hide the background image...
#SetFile -a V "/Volumes/ezRepo Designer/jss-package-background.png"
## 10. unmount the volume
#hdiutil unmount "/Volumes/ezRepo Designer"
##targz the template...
#tar -czvf jss-package-template.tgz jaspersoftstudio.dmg.sparsebundle

# Distro creation from template ---------------------------
hdiutil detach "/Volumes/ezRepo Designer"
tar -xzvf jss-package-template.tgz
tar -xzvf JaspersoftStudio-macosx.cocoa.x86_64.zip
hdiutil mount jaspersoftstudio.dmg.sparsebundle

# Copy all the files
cp -Rf jaspersoftstudio.app/*  "/Volumes/ezRepo Designer/ezRepo Designer/"
open "/Volumes/ezRepo Designer"
#echo "Sleeping for 3 seconds"
sleep 3

# Remove junk files
rm -Rf "/Volumes/ezRepo Designer/ezRepo Designer/test.txt"
rm -Rf "/Volumes/ezRepo Designer/ezRepo Designer/"Icon*

# Rename folder ezRepo Designer 1.0 to the new version
mv "/Volumes/ezRepo Designer/ezRepo Designer" "/Volumes/ezRepo Designer/ezRepo Designer $1.app"
# Trying to fix issue during the sign process
xattr -cr "/Volumes/ezRepo Designer/ezRepo Designer $1.app"

#Signing the application

# Trigger error exit with tibsign failure
if [ $? -ne 0 ]
then
  echo "ERROR: tibsign tool failed - INFRA intervention required"
  exit 1
fi

# Try to remove a possible "junk" entitlements.xml and zip files (used for signing)
rm -fr "/Volumes/ezRepo Designer/entitlement.xml"
rm -fr "/Volumes/ezRepo Designer/ezRepo Designer $1.app.zip"

# Fix icons position....
echo '
   tell application "Finder"
     tell disk "'ezRepo Designer'"
           open
           set current view of container window to icon view
           set toolbar visible of container window to false
           set statusbar visible of container window to false
            set the bounds of container window to {100, 100, 700, 400}
           set theViewOptions to the icon view options of container window
           set arrangement of theViewOptions to not arranged
           set icon size of theViewOptions to 128
#           # set background picture of theViewOptions to file "jss-package-background.png"
#           #make new alias file at container window to POSIX file "/Applications" with properties {name:"Applications"}
            set position of item "ezRepo Designer '$1'" of container window to {230, 118}
            set position of item "Applications" of container window to {450,118}
#           set position of item "LICENSE.txt" of container window to {300, 450}
           close
           open
           update without registering applications
           delay 5
           eject
     end tell
   end tell
' | osascript


# Unmount the ezRepo Designer image
hdiutil detach "/Volumes/ezRepo Designer/ezRepo Designer $1.app"

# Remove the dmg in case it already exists...
rm jaspersoftstudio.dmg

# Create the new dmg file
#hdiutil convert -format UDCO -o jaspersoftstudio.dmg jaspersoftstudio.dmg.sparsebundle
/usr/bin/hdiutil convert -imagekey zlib-level=9 -format UDBZ jaspersoftstudio.dmg.sparsebundle -o jaspersoftstudio.dmg


