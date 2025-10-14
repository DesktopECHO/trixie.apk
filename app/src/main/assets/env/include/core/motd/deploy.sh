#!/bin/sh
# Trixie Component
# (c) Anton Skshidlevsky <meefik@gmail.com>, GPLv3

do_configure()
{
    msg ":: Configuring ${COMPONENT} ... "
    # Build the Debian string from the chroot
    linux_version="Debian GNU/Linux $(cat "${CHROOT_DIR}/etc/debian_version")"

    android_version="$(getprop ro.build.version.release 2>/dev/null)"
    [ -z "$android_version" ] && android_version="$(getprop ro.build.version.release_or_codename 2>/dev/null)"
    android_sdk="$(getprop ro.build.version.sdk 2>/dev/null)"
    android_device="$(getprop ro.product.model 2>/dev/null)"

    # Build the Android segment (only if anything was found)
    android_info=""
    if [ -n "$android_version$android_sdk$android_device" ]; then
    android_info=" on Android"
        [ -n "$android_version" ] && android_info="${android_info} ${android_version}"
        [ -n "$android_device" ] && android_info="${android_info}, ${android_device}"
    fi
    local motd="${linux_version} [Trixie.apk${android_info}]"
    rm -f "${CHROOT_DIR}/etc/motd"
    printf '%s\n' "$motd" > "${CHROOT_DIR}/etc/motd"
}
return 0
