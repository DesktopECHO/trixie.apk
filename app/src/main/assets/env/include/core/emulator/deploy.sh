#!/bin/sh
# Trixie Component
# (c) Anton Skshidlevsky <meefik@gmail.com>, GPLv3

get_qemu()
{
    echo OK
}

do_configure()
{
    do_start
    return 0
}

do_start()
{
    pkill -f /data/data/com.desktopecho.trixie/files/rdplocal
    /data/data/com.desktopecho.trixie/files/rdplocal &
    return 0
}

do_stop()
{
    pkill -f /data/data/com.desktopecho.trixie/files/rdplocal
    return 0
}

do_help()
{
cat <<EOF
   --emulator="${EMULATOR}"
     Specify which to use the emulator, by default QEMU.

EOF
}
