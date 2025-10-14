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
    rm -f /data/local/tmp/rdplocal.lock
    /data/data/com.desktopecho.trixie/files/rdplocal > /data/local/tmp/rdplocal.tmp &
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
