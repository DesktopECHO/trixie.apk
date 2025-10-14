#!/bin/sh
# Trixie Component
# (c) Anton Skshidlevsky <meefik@gmail.com>, GPLv3

GRAPHICS="${GRAPHICS##*/}"

do_help()
{
cat <<EOF
   --graphics="${GRAPHICS}"
     Graphics subsystem.

EOF
}
