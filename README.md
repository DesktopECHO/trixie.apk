# Trixie.apk
<img width="192" height="192" alt="image" src="https://github.com/user-attachments/assets/c9933895-5972-4fa2-b535-8fa96969759c" />


### Project Goals

 - Trixie environment for Android behaves like a 'real' computer
 - Support 32 and 64 bit devices from Lollipop (Android 5.0) onward
 - Demonstrate performant XRDP with H.264 codec
 - Make 'dumb' speakers smart
 - Keep useful devices out of landfill

### Features

 - Debian 13 (Trixie) for use as a server or Xfce desktop
 - Desktop environment can be accessed on-device or remotely
 - Available autostart feature to start Trixie at boot
 - Microphone and speakers (audio in/out) both work
 - Preconfigured Pi-hole DNS ad-blocker with Unbound DoT forwarder
 - AirPlay 2 (Shairplay-sync) makes any attached speaker visible in HomeKit
 - Run Android studio on-device (requires at least 6GB RAM)

### Requirements:

- Android 5 (Lollipop) or newer device that has been rooted

### Install:

- Install [trixie.apk](https://github.com/DesktopECHO/trixie.apk/releases/latest/download/trixie.apk) and open the app.  
- Tap **Allow** if asked for permission to access files or run as root.  
- Tap **More Options** (**Three dots** at the top right of screen).
- Tap **Install**, and **OK** to confirm.
- The container will generate a password for remote access, use it for Pi-hole admin, SSH, or RDP login.  
- Default username is _android_ but you can change this in Properties (In the navigation panel at the top left) before you deploy.
- **Note**: The password appears only once when Trixie is deployed, make sure you record this information.  You can also copy the password text to your clipboard for easier management. 

<img width="1890" height="1364" alt="image" src="https://github.com/user-attachments/assets/6f05e7a4-34a7-4b5f-bdc8-7c63f8767ed7" />

- In a few minutes the Trixie deployment will complete and the Xfce Desktop session will appeear.  If you receive a popup for SSL certificate or Device Access, no need to click 'Connect', the installer will continue past these warnings for you.

<img width="1890" height="1063" alt="image" src="https://github.com/user-attachments/assets/9bdbf9fc-55d4-45d6-884f-e18dc5bbb278" />
   
## Post-Deployment:
 
- You can stop the Trixie instance by pressing **[ ■ STOP ]** and waiting a few seconds for services to terminate.  Sart the instance by pressing **[ ▸ START ]**

- If you are using Trixie as a server you can disable desktop autostart by running `sudo rm /etc/autogui`.  Re-enable with `sudo touch /etc/autogui`  

**If your Android device has a battery and was unused for months or years, replace its battery.**  Old, worn, or abused Li-ion batteries can fail when pushed back into service.  Failure appears as a bulge in the battery, or worse a [**_thermal event_**](https://www.urbandictionary.com/define.php?term=unexpected+thermal+event).  A good battery provides [UPS](https://en.wikipedia.org/wiki/Uninterruptible_power_supply) protection for your device.

## #StupidAndroidTricks
- Android Studio building trixie.apk on a 2018 Razer Phone 2:

<img width="1500" height="750" alt="IMG" src="https://github.com/user-attachments/assets/0e801772-16a8-4d78-aeb5-e343d610b411" />

- From a PC (or iPad), RDP into your Android device and run scrcpy over ADB to virtually control the screen. Watch out for inception events!

![IMG_6891 (1)](https://github.com/user-attachments/assets/2f0a0ea4-3ba7-4fd6-9f94-e46396e9dd7a)

