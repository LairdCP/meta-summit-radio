#!/bin/sh

[ -f /etc/default/bt-service ] && . /etc/default/bt-service

case "${1}" in
	start)
		mfg_mode=/sys/class/ieee80211/phy0/device/lrd/mfg_mode
		[ -f "${mfg_mode}" ] && read -r mfg_val < ${mfg_mode} && \
		[ "${mfg_val}" = 1 ] && baud=115200 || baud=3000000

		/usr/bin/btattach -B ${PORT} -P h4 -S ${baud} &
		;;

	stop)
		killall btattach
		;;

	*)
		echo $"Usage: $0 {start|stop}"
		exit 1
		;;
esac
