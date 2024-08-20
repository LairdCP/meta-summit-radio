#!/bin/sh
# SPDX-License-Identifier: LicenseRef-Ezurio-Clause
# Copyright (C) 2016 Ezurio

# bttest.sh - manage bluetooth testing for the SOM60 in production
#

ERROR_LOG="/var/log/bttest.log"
BT_PATH="/dev/ttyS1"
BT_PARAMS="0:4:1cb2:a30:3:1c:7f:15:4:0:1:0:11:13:1a:0:12:f:17:16:0:0:0:0:0:0:0:0:0:0:0:0:0:0:0:0"
# These parameters equate to:
# speed 115200 baud;
# line = 0;
# intr = <undef>; quit = <undef>; erase = <undef>; kill = <undef>; eof = <undef>;
# eol = <undef>; eol2 = <undef>; swtch = <undef>; start = <undef>;
# stop = <undef>; susp = <undef>; rprnt = <undef>; werase = <undef>;
# lnext = <undef>; flush = <undef>; min = 1; time = 1;
# parenb -parodd cs8 -hupcl -cstopb cread clocal -crtscts
# -ignbrk -brkint -ignpar -parmrk -inpck -istrip -inlcr -igncr -icrnl -ixon
# -ixoff -iuclc -ixany -imaxbel -iutf8
# -opost -olcuc -ocrnl -onlcr -onocr -onlret -ofill -ofdel nl0 cr0 tab0 bs0 vt0 ff0
# -isig -icanon -iexten -echo -echoe -echok -echonl -noflsh -xcase -tostop
# -echoprt -echoctl -echoke
#
# For a detailed description of the settings used please refer to the stty manpage.


do_() {
  echo -e "+ $@"; $@
}

display_usage() {
    echo "Use to set the SOM60 into passthrough mode for production testing."
    echo
    echo "Action:"
    echo "  start - set test mode for Bluetooth"
    echo "  stop - set normal Bluetooth operation"
    echo
    echo "Usage:"
    echo "  ${0} [action] [/dev/ttyS*]"
    echo
}

exec 2> ${ERROR_LOG}

case ${1} in
    start)
        # Start testing Bluetooth on the specified port
        if [ $# -lt 2 ] || [ ! -c "${2}" ] || [ "${BT_PATH}" = "${2}" ]
        then
            display_usage
            exit 1
        fi

        if [ -e /tmp/bttest.rx.pid ] || [ -e /tmp/bttest.tx.pid ]
        then
            echo "Test mode running, exiting"
            exit 1
        fi

        echo "Using ${2} for passthrough."

        printf "Shutting down Bluetooth..."
        systemctl stop btattach
        echo "done."

        printf "Setting parameters for ports..."
        # Setup serial port baudrate + params
        stty -F ${BT_PATH} ${BT_PARAMS} > /dev/null
        stty -F "${2}" ${BT_PARAMS} > /dev/null

        mfg_mode=/sys/class/ieee80211/phy0/device/lrd/mfg_mode
        [ -f "${mfg_mode}" ] && read -r mfg_val < ${mfg_mode} && \
        [ "${mfg_val}" = 1 ] && baud=115200 || baud=3000000
        stty -F ${BT_PATH} speed "${baud}" > /dev/null

        echo "done."

        printf "Setting up passthrough..."
        # Setup redirects/socat  (and background them)
        cat < "${2}" > ${BT_PATH} &
        echo $! > /tmp/bttest.rx.pid
        cat < ${BT_PATH} > "${2}" &
        echo $! > /tmp/bttest.tx.pid
        echo "done."

        echo
        echo "SOM60 Bluetooth now in passthrough test mode."
    ;;

    stop)
        # Stop testing
        if [ ! -e /tmp/bttest.rx.pid ] || [ ! -e /tmp/bttest.tx.pid ]
        then
            echo "Test mode not running, exiting"
            exit 1
        fi

        printf "Tearing down passthrough..."
        # Kill the PID's of the passthrough
        read -r BT_RXPID < /tmp/bttest.rx.pid && kill "${BT_RXPID}"
        read -r BT_TXPID < /tmp/bttest.tx.pid && kill "${BT_TXPID}"
        rm -f /tmp/bttest*
        echo "done."

        printf "Starting Bluetooth..."
        systemctl start btattach
        echo "done."
    ;;

    *)
        display_usage
        exit 1
    ;;
esac
