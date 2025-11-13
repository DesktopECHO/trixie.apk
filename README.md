# Trixie.apk

### Features

 - Debian 13 (Trixie) can be used as a server or desktop OS
 - Desktop environment can be accessed on-device or remotely
 - Reliable autostart when the deivce is powered-up or rebooted
 - Audio and Microphone (in/out) both work
 - Preconfigured Pi-hole DNS ad-blocker with Unbound DoT forwarder
 - AirPlay 2 receiver makes any wired or bluetooth speaker visible in HomeKit
    
### Requirements:

- Android 5 (Lollipop) or newer device that has been rooted

### Install
- Install [trixie.apk](https://github.com/DesktopECHO/trixie.apk/releases/latest/download/trixie.apk) and open the app.
  
- Tap **Allow** if asked for permission to access files or run as root.
  
- Tap **More Options** (**Three dots** at the top right of screen)
    ![image](https://github.com/DesktopECHO/Pi-hole-for-Android/assets/33142753/b8cbe9e3-f47a-4e3b-a155-b6b0aa3a5161")

- Tap **New Deployment**:
    ![image](https://github.com/DesktopECHO/Pi-hole-for-Android/assets/33142753/a438ee12-b849-4c04-8be1-e5f61f4ba659)

- In a few minutes, the [Raspbian Pi-hole Image](https://github.com/DesktopECHO/Pi-hole-for-Android/releases/latest/download/raspbian.tgz) will be downloaded and installed on your device.
- When deployment is complete, tap **[  ▷ START ]**  to launch the instance.
- The instance will provide you with a password to login to Pi-hole webadmin or via SSH/RDP (Username: _android_, see screenshot below)
- **Note**: The password appears only once when the image is deployed, make sure you record this information.  You can also copy the password text to your clipboard for easier management. 

**Additional Info:**

RDP Sessions launch the Openbox window manager with QTerminal in fullscreen mode.  To open a new tab hit **[Ctrl-Shift-T]** and to un-hide the menubar hit **[Ctrl-Shift-M]**

You can stop the Pi-hole instance by pressing **[ ■ STOP ]** and waiting a few seconds for all services to stop.  Sart the instance by pressing **[ ▸ START ]**

When a Pi-hole instance starts up, the default configuration is set to automagically configure networking.  If you change networks on the Android device simply restart the instance for Pi-hole to pick up the new settings.


**If your Android device has a battery and was unused for months or years, replace its battery.**  Old, worn, or abused Li-ion batteries can fail when pushed back into service.  Failure appears as a bulge in the battery, or worse a [**_thermal event_**](https://www.urbandictionary.com/define.php?term=unexpected+thermal+event).  A good battery provides [UPS](https://en.wikipedia.org/wiki/Uninterruptible_power_supply) protection for your device.
