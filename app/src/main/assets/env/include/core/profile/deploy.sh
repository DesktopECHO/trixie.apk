#!/bin/sh
# Trixie.apk (Linux Deploy) Component
# (c) Anton Skshidlevsky <meefik@gmail.com>, GPLv3

[ -n "${USER_NAME}" ] || USER_NAME="root"
[ -n "${USER_PASSWORD}" ] || USER_PASSWORD="changeme"

do_configure()
{
    msg ":: Configuring ${COMPONENT} ... "
    if [ -z "${USER_NAME%aid_*}" ]; then
        echo "Username \"${USER_NAME}\" is reserved."; return 1
    fi

    ANDROID_VERSION=$(getprop ro.build.version.release | tr -d '[:space:]')

    if [ "${USER_NAME}" = "android" ]; then
        GECOS_STRING="Android ${ANDROID_VERSION}"
    else
        GECOS_NAME=$(echo "${USER_NAME}" | awk '{print toupper(substr($0, 1, 1)) substr($0, 2)}')
        GECOS_STRING="${GECOS_NAME} · Android ${ANDROID_VERSION}"
    fi

    if [ "${USER_NAME}" != "root" ]; then
        chroot_exec -u root groupadd "${USER_NAME}" -g 1100
        chroot_exec -u root useradd -c "'${GECOS_STRING}'" -g "${USER_NAME}" -m -s /bin/bash -u 1100 "${USER_NAME}"
        chroot_exec -u root usermod -a -G "${USER_NAME}" "${USER_NAME}"
    fi
    echo "${USER_NAME}:${USER_PASSWORD}" | chroot_exec -u root chpasswd
    chroot_exec -u root chown -R "${USER_NAME}:${USER_NAME}" "$(user_home "${USER_NAME}")"
    return 0
}

do_help()
{
cat <<EOF
   --user-name="${USER_NAME}"
     Username that will be created in the container.

   --user-password="${USER_PASSWORD}"
     Password will be assigned to the specified user.

EOF
}
